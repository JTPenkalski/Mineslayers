package com.mineslayers.ui;

import java.io.IOException;
import java.util.ArrayList;
import com.mineslayers.controller.MineSlayerGame;
import com.mineslayers.event.EventArgs;
import com.mineslayers.event.MessageInvoker;
import com.mineslayers.event.MessageObserver;
import com.mineslayers.id.ImageID;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MerchantWindow extends Stage implements MessageInvoker<ItemIcon, EventArgs>, MessageObserver<ItemIcon, EventArgs>
{
    @FXML private Button buyButton;
    @FXML private Button returnToGameButton;
    @FXML private Label itemDescriptionLabel;
    @FXML private Label merchantGreetingLabel;
    @FXML private ImageView merchantImageView;
    @FXML private InventoryPane playerInventoryPane;
    @FXML private ItemCounter playerCoinItemCounter;
    @FXML private ItemIcon merchantItemIcon1;
    @FXML private ItemIcon merchantItemIcon2;
    @FXML private ItemIcon merchantItemIcon3;
    @FXML private ItemIcon merchantItemIcon4;
    @FXML private ItemIcon merchantItemIcon5;
    @FXML private ItemIcon selectedItemIcon;
    private ArrayList<MessageObserver<ItemIcon, EventArgs>> observers;

    public MerchantWindow()
    {
        // Initialize fields
        observers = new ArrayList<>();
        Scene scene = null;

        // Create class instance
        try
        {
            // Try to create an instance from an FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/MerchantWindow.fxml"));
            fxmlLoader.setController(this);

            scene = new Scene(fxmlLoader.load(), MineSlayerGame.WINDOW_RES_X, MineSlayerGame.WINDOW_RES_Y);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("css/MerchantWindow.css").toExternalForm());
        }
        catch (IOException ex)
        {
            // If the FXML file fails, something's wrong
            ex.printStackTrace();
        }

        // Finalize scene
        setScene(scene);
    }

    @FXML
    public void initialize()
    {
        // Configure JavaFX properties
        merchantImageView.setImage(ImageID.WIZARD);
        setResizable(MineSlayerGame.ALLOW_MAXIMIZE);
        setTitle(MineSlayerGame.WINDOW_TITLE);

        // Setup JavaFX event system
        playerInventoryPane.addObserver(this);

        buyButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->
        {
            // Try to buy the selected item
            System.out.println("Buy an item");
            invoke(selectedItemIcon, EventArgs.Empty);
        });

        returnToGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->
        {
            // Return to the Game Board Window
            /*MineSlayerGame.GameBoardWindow.show();*/
            close();
        });
    }

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

    /*public void setup(Merchant merchant, Player player)*/
}