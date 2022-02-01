package com.mineslayers.controller;

import com.mineslayers.entity.*;
import com.mineslayers.event.EventArgs;
import com.mineslayers.event.MessageObserver;
import com.mineslayers.event.MouseEventArgs;
import com.mineslayers.id.ImageID;
import com.mineslayers.id.ItemID;
import com.mineslayers.ui.BoardTile;
import com.mineslayers.ui.GameBoardWindow;
import com.mineslayers.ui.ItemIcon;

/**
 * The GameBoardController manages interaction between the GameBoardWindow's UI events and the data stored in the
 * Player class, as well as general game state information.
 */
public class GameBoardController
{
    /**
     * A BoardTileListener is responsible for observing BoardTile events and responding accordingly.
     */
    private class BoardTileListener implements MessageObserver<BoardTile, MouseEventArgs>
    {
        /**
         * Activates when a BoardTile invokes an event.
         *
         * @param sender    The BoardTile that invoked the event.
         * @param eventArgs Any additional information that is necessary to be passed with the event.
         */
        @Override
        public void onEvent(BoardTile sender, MouseEventArgs eventArgs)
        {
            // Left click on BoardTile (interact)
            if (!eventArgs.isRightClick())
            {
                // Left click most BoardTiles
                if (!sender.isVisited())
                {
                    player.setCoins(player.getCoins() + EMPTY_BOARDTILE_REWARD);
                    processEmptyNeighbors(sender.getX(), sender.getY());
                    getGameBoardWindow().updateCounters(player);
                }
                // Left click discovered Item BoardTiles
                else if (sender.isVisited() && sender.getEvent() != null && sender.getEvent().getType() == BoardTileEventType.ITEM)
                {
                    ItemBoardTileEvent itemEvent = (ItemBoardTileEvent) sender.getEvent();

                    if (itemEvent.getItem() != null)
                    {
                        if (givePlayerItem(itemEvent.getItem()))
                            itemEvent.setItem(null);
                    }
                }
            }
            // Right click on BoardTile (flag)
            else
            {
                if (!(sender.getEvent() != null && sender.getEvent().getType() == BoardTileEventType.ITEM))
                    flagBoardTile(sender);
            }
        }
    }

    /**
     * An ItemIconListener is responsible for observing ItemIcon events and responding accordingly.
     */
    private class ItemIconListener implements MessageObserver<ItemIcon, EventArgs>
    {
        /**
         * Activates when an ItemIcon invokes an event.
         *
         * @param sender    The ItemIcon that invoked the event.
         * @param eventArgs Any additional information that is necessary to be passed with the event.
         */
        @Override
        public void onEvent(ItemIcon sender, EventArgs eventArgs)
        {
            // Update current inventory selection
            if (sender.getInventoryIndex() >= 0)
            {
                player.getInventory().setCurrentSelectionIndex(sender.getInventoryIndex());
                gameBoardWindow.getInventoryPane().update(player.getInventory());
            }
        }
    }

    public static int EMPTY_BOARDTILE_REWARD = 1;

    private int enemiesDefeated;
    private BoardTileListener boardTileListener;
    private GameBoardWindow gameBoardWindow;
    private ItemIconListener itemIconListener;
    private Player player;

    /**
     * Creates a GameBoardController.
     *
     * @param player A reference the the active player entity.
     * @param gameBoardWindow The GameBoardWindow to observe for GUI events.
     */
    public GameBoardController(Player player, GameBoardWindow gameBoardWindow)
    {
        // Initialize
        this.player = player;
        this.gameBoardWindow = gameBoardWindow;
        boardTileListener = new BoardTileListener();
        itemIconListener = new ItemIconListener();

        // Register for events
        this.gameBoardWindow.getBoardTileListener().addObserver(boardTileListener);
        this.gameBoardWindow.getItemIconListener().addObserver(itemIconListener);

        // Setup initial Game Board
        gameBoardWindow.getGameBoard().generate(player);

        // Setup GUI
        this.gameBoardWindow.updateCounters(player);
        this.gameBoardWindow.getInventoryPane().update(this.player.getInventory());
    }

    /**
     * Sets the active ArmorItem for the player to use when defending in a battle. If null is passed, any ArmorItem is
     * unequipped.
     *
     * @param armor The ArmorItem to equip.
     */
    public void equipArmor(ArmorItem armor)
    {
        player.setArmorItem(armor);
        gameBoardWindow.updateEquipped(player);
    }

    /**
     * Sets the active WeaponItem for the player to use when attacking in a battle. If null is passed, any WeaponItem is
     * unequipped.
     *
     * @param weapon The WeaponItem to equip.
     */
    public void equipWeapon(WeaponItem weapon)
    {
        player.setWeaponItem(weapon);
        gameBoardWindow.updateEquipped(player);
    }

    /**
     * Places a FlagItem on a BoardTile, if the player has any. If the player correctly flags an enemy, it is marked as
     * defeated. Otherwise, nothing happens.
     *
     * @param boardTile
     */
    public void flagBoardTile(BoardTile boardTile)
    {
        // Try to use a flag
        if (player.getInventory().useFlag())
        {
            // Remove the flag and update GUI
            processEmptyNeighbors(boardTile.getX(), boardTile.getY());
            gameBoardWindow.updateCounters(player);

            // If correct, update enemy progress and reset GUI to look nice
            if (boardTile.getEvent() != null && boardTile.getEvent().getType() == BoardTileEventType.ENEMY)
            {
                boardTile.setBaseImage(ImageID.GRASS);
                boardTile.setDetailImage(ImageID.FLAG_BOARD);
                handleBattleOutcome(true, ((EnemyBoardTileEvent) boardTile.getEvent()).getBattle().getReward());
            }
        }
    }

    /**
     * Determines which course of action to take after a battle. A win rewards the player, while a defeat costs the
     * player a life. All lives gone triggers a game over.
     *
     * @param win Whether or not the player won a battle.
     */
    public void handleBattleOutcome(boolean win, int reward)
    {
        enemiesDefeated++;
        updateLevelProgress();

        if (win)
        {
            // Give player reward
            player.setCoins(player.getCoins() + reward);
        } else
        {
            player.setLives(player.getLives() - 1);

            // Game over if the player is out of lives
            if (player.getLives() == 0)
            {
                MineSlayerGame.GameOverWindow.setTitleMessage("Game Over");
                MineSlayerGame.GameOverWindow.show();
                gameBoardWindow.close();
                return;
            }
        }

        // Game over if the player finished all battles
        if (enemiesDefeated == gameBoardWindow.getGameBoard().getTotalEnemies())
        {
            MineSlayerGame.GameOverWindow.setTitleMessage("You Win");
            MineSlayerGame.GameOverWindow.show();
            gameBoardWindow.close();
            return;
        }

        player.resetHealth();
        gameBoardWindow.updateCounters(player);
        gameBoardWindow.updateEquipped(player);
        gameBoardWindow.getInventoryPane().update(player.getInventory());
    }

    /**
     * Removes the currently selected item from the player's inventory.
     */
    public void trashItem()
    {
        Item currentItem = player.getInventory().getItem(player.getInventory().getCurrentSelectionIndex());

        if (currentItem != null)
        {
            // Unequip armor
            if (currentItem instanceof ArmorItem && currentItem.equals(player.getArmorItem()))
                equipArmor(null);

            // Unequip weapon
            else if (currentItem instanceof WeaponItem && currentItem.equals(player.getWeaponItem()))
                equipWeapon(null);

            // Remove the item
            player.getInventory().removeItem(player.getInventory().getCurrentSelectionIndex());
            gameBoardWindow.getInventoryPane().update(player.getInventory());
        }
    }

    /**
     * Moves the progress bar indicating how many enemies out of the total are defeated.
     */
    public void updateLevelProgress()
    {
        gameBoardWindow.getLevelProgressBar().setProgress((double) enemiesDefeated / gameBoardWindow.getGameBoard().getTotalEnemies());
    }

    /**
     * Uses or equips the currently selected item.
     */
    public void useItem()
    {
        Item currentItem = player.getInventory().getItem(player.getInventory().getCurrentSelectionIndex());

        if (currentItem != null)
        {
            // Equip armor
            if (currentItem instanceof ArmorItem)
                equipArmor((ArmorItem) currentItem);

            // Equip weapon
            else if (currentItem instanceof WeaponItem)
                equipWeapon((WeaponItem) currentItem);

            // Use the item
            currentItem.use(player);

            // Remove it if consumable
            if (!(currentItem instanceof ArmorItem) && !(currentItem instanceof WeaponItem))
                player.getInventory().removeItem(player.getInventory().getCurrentSelectionIndex());

            gameBoardWindow.updateCounters(player);
            gameBoardWindow.getInventoryPane().update(player.getInventory());
        }
    }

    /**
     * Adds an item to the player's inventory. If their inventory is full, nothing is added.
     *
     * @param item The item to add.
     */
    public boolean givePlayerItem(Item item)
    {
        if (item instanceof FlagItem)
        {
            if (player.getInventory().addFlag((FlagItem) item))
            {
                gameBoardWindow.updateCounters(player);
                return true;
            }
        } else if (player.getInventory().addItem(item))
        {
            gameBoardWindow.getInventoryPane().update(player.getInventory());
            return true;
        }

        return false;
    }

    /**
     * @return The instance of GameBoardWindow managed by this controller.
     */
    public GameBoardWindow getGameBoardWindow() { return gameBoardWindow; }

    /**
     * Recursively iterates through all BoardTiles until out-of-bounds or it finds a BoardTile that is adjacent to one
     * with a BoardTileEvent.
     *
     * @param x X-coordinate of BoardTile
     * @param y Y-coordinate of BoardTile
     */
    private void processEmptyNeighbors(int x, int y)
    {
        // Base cases: stop if out of bounds or already visited
        if (!gameBoardWindow.getGameBoard().withinBounds(x, y) || gameBoardWindow.getGameBoard().at(x, y).isVisited())
            return;

        // Base case: there's at least 1 event in the surrounding tiles
        if (gameBoardWindow.getGameBoard().checkAdjacentTiles(x, y))
        {
            // Update tile's visited state and counters
            gameBoardWindow.getGameBoard().at(x, y).setVisited(true);
            gameBoardWindow.getGameBoard().updateBoardTileCounter(x, y);
            return;
        }

        // Update tile's visited state
        gameBoardWindow.getGameBoard().at(x, y).setVisited(true);

        // General case: modify surrounding tiles
        processEmptyNeighbors(x + 1, y);
        processEmptyNeighbors(x - 1, y);
        processEmptyNeighbors(x, y + 1);
        processEmptyNeighbors(x, y - 1);
    }
}