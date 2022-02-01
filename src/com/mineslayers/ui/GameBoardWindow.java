package com.mineslayers.ui;

import java.io.IOException;
import java.util.ArrayList;
import com.mineslayers.controller.MineSlayerGame;
import com.mineslayers.entity.Player;
import com.mineslayers.event.EventArgs;
import com.mineslayers.event.MessageInvoker;
import com.mineslayers.event.MessageObserver;
import com.mineslayers.event.MouseEventArgs;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * The GameBoardWindow manages all GUI for the window responsible for displaying the Game Board.
 */
public class GameBoardWindow extends Stage
{
    /**
     * A BoardTileListener is responsible for observing BoardTile events and responding accordingly.
     */
    public class BoardTileListener implements MessageInvoker<BoardTile, MouseEventArgs>, MessageObserver<BoardTile, MouseEventArgs>
    {
        private ArrayList<MessageObserver<BoardTile, MouseEventArgs>> observers = new ArrayList<>();

        @Override
        public void addObserver(MessageObserver<BoardTile, MouseEventArgs> observer) { observers.add(observer); }

        @Override
        public void invoke(BoardTile sender, MouseEventArgs eventArgs)
        {
            for (var o : observers)
                o.onEvent(sender, eventArgs);
        }

        @Override
        public void removeObserver(MessageObserver<BoardTile, MouseEventArgs> observer) { observers.remove(observer); }

        @Override
        public void onEvent(BoardTile sender, MouseEventArgs eventArgs) { invoke(sender, eventArgs); }
    }

    /**
     * An ItemIconListener is responsible for observing ItemIcon events and responding accordingly.
     */
    public class ItemIconListener implements MessageInvoker<ItemIcon, EventArgs>, MessageObserver<ItemIcon, EventArgs>
    {
        private ArrayList<MessageObserver<ItemIcon, EventArgs>> observers = new ArrayList<>();

        @Override
        public void addObserver(MessageObserver<ItemIcon, EventArgs> observer) { observers.add(observer); }

        @Override
        public void invoke(ItemIcon sender, EventArgs eventArgs)
        {
            for (var o : observers)
                o.onEvent(sender, eventArgs);
        }

        @Override
        public void removeObserver(MessageObserver<ItemIcon, EventArgs> observer) { observers.remove(observer); }

        @Override
        public void onEvent(ItemIcon sender, EventArgs eventArgs) { invoke(sender, eventArgs); }
    }

    @FXML private Button useButton;
    @FXML private Button trashButton;
    @FXML private Button quitButton;
    private BoardTileListener boardTileListener;
    @FXML private GameBoard gameBoard;
    @FXML private InventoryPane playerInventoryPane;
    @FXML private ItemCounter coinItemCounter;
    @FXML private ItemCounter flagItemCounter;
    @FXML private ItemCounter lifeItemCounter;
    @FXML private ItemIcon primaryArmorItemIcon;
    @FXML private ItemIcon primaryWeaponItemIcon;
    private ItemIconListener itemIconListener;
    @FXML private Label levelNameLabel;
    @FXML private Label stageNameLabel;
    @FXML private ProgressBar levelProgressBar;

    /**
     * Creates a window to display the Game Board.
     */
    public GameBoardWindow()
    {
        // Initialize fields
        boardTileListener = new BoardTileListener();
        itemIconListener = new ItemIconListener();
        Scene scene = null;

        // Create class instance
        try
        {
            // Try to create an instance from an FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GameBoardWindow.fxml"));
            fxmlLoader.setController(this);

            scene = new Scene(fxmlLoader.load(), MineSlayerGame.WINDOW_RES_X, MineSlayerGame.WINDOW_RES_Y);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("css/GameBoardWindow.css").toExternalForm());
        }
        catch (IOException ex)
        {
            // If the FXML file fails, something's wrong
            ex.printStackTrace();
        }

        // Finalize scene
        setScene(scene);
    }

    /**
     * Called during FXML loading. Performs initialization of FXML elements.
     */
    @FXML
    public void initialize()
    {
        // Configure JavaFX properties
        setResizable(MineSlayerGame.ALLOW_MAXIMIZE);
        setTitle(MineSlayerGame.WINDOW_TITLE);

        // Setup JavaFX event system
        gameBoard.addObserver(boardTileListener);
        playerInventoryPane.addObserver(itemIconListener);

        quitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->
        {
            // Quit the game
            close();
        });

        trashButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->
        {
            // Use/Equip the item
            MineSlayerGame.GameBoardController.trashItem();
        });

        useButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->
        {
            // Use/Equip the item
            MineSlayerGame.GameBoardController.useItem();
        });
    }

    /**
     * Updates all ItemCounter GUI elements to reflect changes in the player's data.
     * @param player The player whose information will be displayed.
     */
    public void updateCounters(Player player)
    {
        coinItemCounter.setCount(player.getCoins());
        flagItemCounter.setCount(player.getInventory().getFlagCount());
        lifeItemCounter.setCount(player.getLives());
    }

    /**
     * Updates all ItemIcon GUI elements that display armor and weapons to reflect changes in the player's data.
     * @param player The player whose information will be displayed.
     */
    public void updateEquipped(Player player)
    {
        // Update Armor UI
        if (player.getArmorItem() != null)
            primaryArmorItemIcon.setItemImage(player.getArmorItem().getImage());
        else
            primaryArmorItemIcon.setItemImage(null);

        // Update Weapon UI
        if (player.getWeaponItem() != null)
            primaryWeaponItemIcon.setItemImage(player.getWeaponItem().getImage());
        else
            primaryWeaponItemIcon.setItemImage(null);
    }

    /**
     * @return The BoardTileListener responsible for observing BoardTile events.
     */
    public BoardTileListener getBoardTileListener() { return boardTileListener; }

    /**
     * @return The GameBoard instance this window manages.
     */
    public GameBoard getGameBoard() { return gameBoard; }

    /**
     * @return The InventoryPane instance this window manages. May be used to update the InventoryPane with changes
     * to the player's inventory.
     */
    public InventoryPane getInventoryPane() { return playerInventoryPane; }

    /**
     * @return The ItemIconListener responsible for observing ItemIcon events.
     */
    public ItemIconListener getItemIconListener() { return itemIconListener; }

    /**
     * @return The ProgressBar instance this window manages. May be used to update the ProgressBar with changes in
     * the player's progression through the level.
     */
    public ProgressBar getLevelProgressBar() { return levelProgressBar; }
}