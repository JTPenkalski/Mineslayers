package com.mineslayers.ui;

import java.io.IOException;
import com.mineslayers.controller.MineSlayerGame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * The GameOverWindow manages all GUI for the window responsible for displaying the final window when the game is over.
 */
public class GameOverWindow extends Stage
{
    @FXML private Button restartButton;
    @FXML private Button menuButton;
    @FXML private Label titleLabel;

    /**
     * Creates a window to display the final window.
     */
    public GameOverWindow()
    {
        // Initialize fields
        Scene scene = null;

        // Create class instance
        try
        {
            // Try to create an instance from an FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GameOverWindow.fxml"));
            fxmlLoader.setController(this);

            scene = new Scene(fxmlLoader.load(), MineSlayerGame.WINDOW_RES_X, MineSlayerGame.WINDOW_RES_Y);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("css/GameOverWindow.css").toExternalForm());
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
        menuButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->
        {
            // Return to the Main Menu Window
            MineSlayerGame.MainMenuWindow.show();
            close();
        });

        restartButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->
        {
            // Return to the Game Board Window
            MineSlayerGame.restart();
            MineSlayerGame.GameBoardController.getGameBoardWindow().show();
            close();
        });
    }

    /**
     * Change the text in the top-center of the window.
     * @param title The new title to display.
     */
    public void setTitleMessage(String title) { titleLabel.setText(title); }
}