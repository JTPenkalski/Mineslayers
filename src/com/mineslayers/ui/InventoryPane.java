package com.mineslayers.ui;

import com.mineslayers.entity.Inventory;
import com.mineslayers.entity.Item;
import com.mineslayers.event.EventArgs;
import com.mineslayers.event.MessageInvoker;
import com.mineslayers.event.MessageObserver;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class InventoryPane extends GridPane implements MessageInvoker<ItemIcon, EventArgs>, MessageObserver<ItemIcon, EventArgs>
{
    private ArrayList<MessageObserver<ItemIcon, EventArgs>> observers;

    public InventoryPane()
    {
        // Initialize fields
        observers = new ArrayList<>();

        // Create class instance
        try
        {
            // Try to create an instance from an FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/InventoryPane.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);

            fxmlLoader.load();
        }
        catch (IOException ex)
        {
            System.out.println("Error creating InventoryPane from FXML file!");

            // If the FXML file fails, create the class normally
            int inventoryIndex = 0;
            for (int col = 0; col < 2; col++)
                for (int row = 0; row < 3; row++)
                    add(new ItemIcon(inventoryIndex++), col, row);

            // Add final inventory slot (takes up more horizontal space to be centered)
            add(new ItemIcon(inventoryIndex++), 0, 3, 2, 1);

            setHgap(5.0);
            setVgap(5.0);

            initialize();
        }
    }

    @FXML
    private void initialize()
    {
        // Configure JavaFX properties
        getStyleClass().add("inventory-pane");

        // Setup JavaFX event system
        for (Node child : getChildren())
            ((ItemIcon)child).addObserver(this);
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

    public void setItem(int index, Item item, boolean selected)
    {
        for (Node child : getChildren())
        {
            if (child instanceof ItemIcon)
            {
                ItemIcon itemIcon = (ItemIcon)child;

                if (itemIcon.getInventoryIndex() == index)
                {
                    itemIcon.setSelected(selected);

                    if (item != null)
                        itemIcon.setItemImage(item.getImage());
                    else
                        itemIcon.setItemImage(null);
                }
            }
        }
    }

    public void update(Inventory inventory)
    {
        for (int i = 0; i < Inventory.CAPACITY; i++)
            setItem(i, inventory.getItem(i), i == inventory.getCurrentSelectionIndex());
    }
}