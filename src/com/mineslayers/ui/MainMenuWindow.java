package com.mineslayers.ui;

import java.io.IOException;
import com.mineslayers.controller.MineSlayerGame;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * The MainMenuWindow manages all GUI for the window responsible for displaying the first window when the game is
 * started.
 */
public class MainMenuWindow extends Stage
{
    @FXML private Button startButton;
    @FXML private Button quitButton;

    /**
     * Creates a window to display the first window.
     */
    public MainMenuWindow()
    {
        // Initialize fields
        Scene scene = null;

        // Create class instance
        try
        {
            // Try to create an instance from an FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/MainMenuWindow.fxml"));
            fxmlLoader.setController(this);
            scene = new Scene(fxmlLoader.load(), MineSlayerGame.WINDOW_RES_X, MineSlayerGame.WINDOW_RES_Y);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("css/MainMenuWindow.css").toExternalForm());
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
        quitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->
        {
            // Close the game
            close();
        });

        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->
        {
            // Go to the Game Board Window
            MineSlayerGame.restart();
            MineSlayerGame.GameBoardController.getGameBoardWindow().show();
            close();
        });
    }
}