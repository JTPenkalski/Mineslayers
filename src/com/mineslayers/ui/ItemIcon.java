package com.mineslayers.ui;

import com.mineslayers.event.EventArgs;
import com.mineslayers.event.MessageInvoker;
import com.mineslayers.event.MessageObserver;
import com.mineslayers.id.ImageID;
import java.io.IOException;
import java.util.ArrayList;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

public class ItemIcon extends StackPane implements MessageInvoker<ItemIcon, EventArgs>
{
    private int inventoryIndex;
    @FXML private ImageView backgroundImageView;
    @FXML private ImageView itemImageView;
    @FXML private ImageView selectedImageView;
    private ArrayList<MessageObserver<ItemIcon, EventArgs>> observers;

    public ItemIcon() { this(-1); }

    public ItemIcon(@NamedArg("inventoryIndex") int inventoryIndex)
    {
        // Initialize fields
        this.inventoryIndex = inventoryIndex;
        observers = new ArrayList<>();

        // Create class instance
        try
        {
            // Try to create an instance from an FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/ItemIcon.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);

            fxmlLoader.load();
        }
        catch (IOException ex)
        {
            System.out.println("Error creating ItemIcon from FXML file!");

            // If the FXML file fails, create the class normally
            backgroundImageView = new ImageView();
            itemImageView = new ImageView();
            selectedImageView = new ImageView();

            getChildren().addAll(backgroundImageView, itemImageView, selectedImageView);
            initialize();
        }
    }

    @FXML
    private void initialize()
    {
        // NOTE: By default, ItemIcon's size is determined by the size of the image. To fix this, we can set its size
        // manually and then bind the ImageViews' fitWidth/fitHeight properties to the StackPane's.

        // Configure JavaFX properties
        getStyleClass().add("item-icon");
        setBackgroundImage(ImageID.ICON);
        selectedImageView.setOpacity(0.25);

        // Setup JavaFX event system
        addEventHandler(MouseEvent.MOUSE_CLICKED, e ->
        {
            invoke(this, EventArgs.Empty);
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

    public void setItemImage(Image itemImage) { itemImageView.setImage(itemImage);}

    public void setSelected(boolean selected) { selectedImageView.setImage(selected ? ImageID.WHITE : null); }

    public int getInventoryIndex() { return inventoryIndex; }

    private void setBackgroundImage(Image backgroundImage) { backgroundImageView.setImage(backgroundImage); }
}