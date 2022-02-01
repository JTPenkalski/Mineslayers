package com.mineslayers.ui;

import com.mineslayers.id.ImageID;
import java.io.IOException;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ItemCounter extends VBox
{
    @FXML private ImageView itemImageView;
    @FXML private Label counterLabel;

    public ItemCounter(@NamedArg("imageName") String imageName)
    {
        // Create class instance
        try
        {
            // Try to create an instance from an FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/ItemCounter.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);

            fxmlLoader.load();
        }
        catch (IOException ex)
        {
            System.out.println("Error creating ItemCounter from FXML file!");

            // If the FXML file fails, create the class normally
            itemImageView = new ImageView();
            counterLabel = new Label();

            getChildren().addAll(itemImageView, counterLabel);
            initialize();
        }

        // Initialize image
        setItemImage(getImage(imageName));
    }

    @FXML
    private void initialize()
    {
        // Configure JavaFX properties
        getStyleClass().add("item-counter");
        counterLabel.setStyle("-fx-text-fill: rgba(255, 255, 255, 1);");
        counterLabel.setText("0");
    }

    public void setCount(int count) { counterLabel.setText(count + ""); }

    public void setItemImage(Image image) { itemImageView.setImage(image); }

    private Image getImage(String imageName)
    {
        // Converts string name to ImageID entry
        switch (imageName.toUpperCase())
        {
            case "COIN": return ImageID.COIN;
            case "FLAG": return ImageID.FLAG;
            case "HEART": return ImageID.HEART;
            default: return null;
        }
    }
}