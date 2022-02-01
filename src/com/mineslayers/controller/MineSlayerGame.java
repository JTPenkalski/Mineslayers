package com.mineslayers.controller;

import com.mineslayers.entity.Player;
import com.mineslayers.ui.*;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * The primary game manager. Responsible for storing all windows the game may show, as well as managing multiple games.
 */
public class MineSlayerGame extends Application
{
    public static final boolean ALLOW_MAXIMIZE = false;
    public static final int WINDOW_RES_X = 1280;
    public static final int WINDOW_RES_Y = 720;
    public static final String WINDOW_TITLE = "Mineslayer Game";

    public static BattleController BattleController;
    public static GameBoardController GameBoardController;
    public static GameOverWindow GameOverWindow;
    public static MainMenuWindow MainMenuWindow;
    public static MerchantWindow MerchantWindow;

    public static void main(String[] args) { launch(args); }

    private static Player player;

    /**
     * Begins the application by displaying the first window.
     * @param stage Default stage to show. Unused.
     */
    @Override
    public void start(Stage stage)
    {
        GameOverWindow = new GameOverWindow();
        MainMenuWindow = new MainMenuWindow();

        MainMenuWindow.show();
    }

    /**
     * Initialize all windows that rely on persistent information, such as the player, so that they are reset to their
     * initial state.
     */
    public static void restart()
    {
        player = new Player();

        BattleController = new BattleController(new BattleWindow());
        GameBoardController = new GameBoardController(player, new GameBoardWindow());
        MerchantWindow = new MerchantWindow(); // NOTE: This should be replaced with a MerchantController eventually.
    }
}