package com.mineslayers.controller;

import com.mineslayers.entity.*;
import com.mineslayers.event.EventArgs;
import com.mineslayers.event.MessageObserver;
import com.mineslayers.ui.BattleWindow;
import com.mineslayers.ui.BoardTile;
import com.mineslayers.ui.ItemIcon;

public class BattleController implements MessageObserver<ItemIcon, EventArgs>
{
    public final BattleWindow battleWindow;
    private Battle battle;

    public BattleController(BattleWindow battleWindow)
    {
        this.battle = null;
        this.battleWindow = battleWindow;

        this.battleWindow.addObserver(this);
    }

    @Override
    public void onEvent(ItemIcon sender, EventArgs eventArgs)
    {
        // Update current inventory selection
        if (sender.getInventoryIndex() >= 0)
        {
            battle.getPlayer().getInventory().setCurrentSelectionIndex(sender.getInventoryIndex());
            battleWindow.getInventoryPane().update(battle.getPlayer().getInventory());
        }
    }

    public void attack()
    {
        // Basically, loop through all enemies in battle.getEnemies() and subtract their
        // health by battle.getPlayer().getWeaponItem().getDamage().// Do the same to
        // the player, just with whatever the "activeEnemy" is. (This is just the index
        // in battle.getEnemies() that attacks the player in a turn. You can do it this way, and update
        // it each attack/defend for the next turn or just ignore it and use your own solution, whichever makes
        // more sense to you.) If you wanted to, you can also add logic for flasks. The way I see it,
        // loop through all enemies. If battle.getEnemy(i).getFlaskWeakness() == battle.getPlayer().getWeaponItem().getFlaskEffect(),
        // then do an additional battle.getPlayer().getWeaponItem().getFlaskDamage() to the enemy.
        //----------------------------------------------------------------------------------------------
        //Sets the last action used.
        battle.setPlayerUsed("ATTACK");

        //Player Attacks all Enemies
        int i = 0;
        for (Enemy e : battle.getEnemies())
        {
            if (e.getHealth() > 0)
            {
                int damage = 2;
                WeaponItem playerWeapon = battle.getPlayer().getWeaponItem();

                if (playerWeapon != null)
                {
                    damage = battle.getPlayer().getWeaponItem().getDamage();

                    if (e.getFlaskWeakness() == battle.getPlayer().getWeaponItem().getFlaskEffect())
                        damage += battle.getPlayer().getWeaponItem().getFlaskDamage();
                }

                battle.setPlayerDamage(i, (int) damage);
                e.setHealth(e.getHealth() - (int) damage);
            }
            else
            {
                battle.setPlayerDamage(i, 0);
            }
            i++;
        }

        //Active Enemy Attacks
        int damage = battle.getEnemy(battle.getActiveEnemy()).getHealth() > 0 ? battle.getEnemy(battle.getActiveEnemy()).getDamage() : 0;
        battle.setEnemiesDamage(battle.getActiveEnemy(), damage);
        battle.getPlayer().setHealth(battle.getPlayer().getHealth() - damage);

        //Decide fate
        endTurn();
    }

    public void defend()
    {
        // To take defense into account, multiply battle.getEnemy(0).getDamage() by battle.getPlayer().getArmorItem().getProtection().
        // getProtection() is a decimal [0, 1] that reduces the damage of the enemy. Feel free to be creative
        // with this. Armor also has a resistFlaskEffect if we wanted to give enemies flasks in the future. Players
        // and enemies also have an attackSpeed field if you wanted to incorporate that somehow.
        //----------------------------------------------------------------------------------------------
        //Sets the last action used.
        battle.setPlayerUsed("DEFEND");

        //Sets the player damage to 0.
        battle.setPlayerDamage(-1, 0);

        //Active Enemy Attacks and is reduced by armor
        if (battle.getEnemy(battle.getActiveEnemy()).getHealth() > 0)
        {
            double damage = battle.getEnemy(battle.getActiveEnemy()).getDamage();
            ArmorItem playerArmor = battle.getPlayer().getArmorItem();

            if (playerArmor != null)
            {
                damage *= 1 - battle.getPlayer().getArmorItem().getProtection();
            }

            battle.setEnemiesDamage(battle.getActiveEnemy(), (int) damage);
            battle.getPlayer().setHealth(battle.getPlayer().getHealth() - (int) damage);
        }

        //Decide fate
        endTurn();
    }

    public void endBattle(boolean outcome)
    {
        //When the battle ends close the battle window
        battleWindow.close();
        MineSlayerGame.GameBoardController.getGameBoardWindow().show();
        MineSlayerGame.GameBoardController.handleBattleOutcome(outcome, battle.getReward());
    }

    public void equipArmor(ArmorItem armor)
    {
        // To unequip, pass null
        battle.getPlayer().setArmorItem(armor);
        battleWindow.updateEquipped(battle.getPlayer());
    }

    public void equipWeapon(WeaponItem weapon)
    {
        // To unequip, pass null
        battle.getPlayer().setWeaponItem(weapon);
        battleWindow.updateEquipped(battle.getPlayer());
    }

    public void startBattle(Battle battle)
    {
        //Sets up the window to start a battle
        this.battle = battle;

        battleWindow.update(battle);
        battleWindow.getInventoryPane().update(battle.getPlayer().getInventory());
        battleWindow.updateEquipped(battle.getPlayer());

        battleWindow.show();
        MineSlayerGame.GameBoardController.getGameBoardWindow().close();
    }

    /**
     * Uses or equips the currently selected item.
     */
    public void useItem()
    {
        Item currentItem = battle.getPlayer().getInventory().getItem(battle.getPlayer().getInventory().getCurrentSelectionIndex());

        if (currentItem != null)
        {
            // Equip armor
            if (currentItem instanceof ArmorItem)
                equipArmor((ArmorItem) currentItem);

                // Equip weapon
            else if (currentItem instanceof WeaponItem)
                equipWeapon((WeaponItem) currentItem);

            // Use the item
            currentItem.use(battle.getPlayer());

            // Remove it if consumable
            if (!(currentItem instanceof ArmorItem) && !(currentItem instanceof WeaponItem))
                battle.getPlayer().getInventory().removeItem(battle.getPlayer().getInventory().getCurrentSelectionIndex());

            battleWindow.update(battle);
            battleWindow.getInventoryPane().update(battle.getPlayer().getInventory());
        }
    }

    public void useSelectedItem()
    {
        battle.getPlayer().getInventory().useCurrentItem(battle.getPlayer());
        battleWindow.getInventoryPane().update(battle.getPlayer().getInventory());
    }

    public BattleWindow getBattleWindow() { return battleWindow; }

    private void endTurn()
    {
        //Decide fate
        if (battle.getPlayer().getHealth() <= 0)
            endBattle(false);
        else if (battle.getRemainingEnemies() == 0)
            endBattle(true);
        else
        {
            //Update the Window and get ready for the next round of attacks!
            battle.setTurn(battle.getTurn() + 1);
            battleWindow.update(battle);
            battle.setActiveEnemy((battle.getActiveEnemy() + 1) % battle.getEnemies().length);
        }
    }
}
