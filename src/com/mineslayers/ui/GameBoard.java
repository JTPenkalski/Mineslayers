package com.mineslayers.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import com.mineslayers.entity.*;
import com.mineslayers.event.MessageInvoker;
import com.mineslayers.event.MessageObserver;
import com.mineslayers.event.MouseEventArgs;
import com.mineslayers.id.EnemyID;
import com.mineslayers.id.ImageID;
import com.mineslayers.id.ItemID;
import javafx.beans.NamedArg;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class GameBoard extends GridPane implements MessageInvoker<BoardTile, MouseEventArgs>, MessageObserver<BoardTile, MouseEventArgs>
{
    private static double ENEMY_TILE_PERCENTAGE = 0.15;
    private static double ITEM_TILE_PERCENTAGE = 0.05;

    private int boardHeight;
    private int boardWidth;
    private int cellSize;
    private int totalEnemies;
    private int totalItems;
    private int totalMerchants;
    private ArrayList<MessageObserver<BoardTile, MouseEventArgs>> observers;
    private BoardTile[][] boardTiles;
    private Random rng;

    public GameBoard(@NamedArg("boardWidth") int boardWidth, @NamedArg("boardHeight") int boardHeight, @NamedArg("cellSize") int cellSize)
    {
        // Initialize fields
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.cellSize = cellSize;
        observers = new ArrayList<>();
        boardTiles = new BoardTile[boardWidth][boardHeight];
        rng = new Random(System.currentTimeMillis());

        // Create class instance
        try
        {
            // Try to create an instance from an FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/GameBoard.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);

            fxmlLoader.load();
        }
        catch (IOException ex)
        {
            System.out.println("Error creating GameBoard from FXML file!");

            // If the FXML file fails, create the class normally
            initialize();
        }
    }

    @FXML
    private void initialize()
    {
        // Configure JavaFX properties
        getStyleClass().add("game-board");
    }

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
    public void onEvent(BoardTile sender, MouseEventArgs eventArgs)
    {
        invoke(sender, eventArgs);
    }

    public void generate(Player player)
    {
        // Randomly generate a level
        for (int x = 0; x < boardWidth; x++)
        {
            for (int y = 0; y < boardHeight; y++)
            {
                BoardTile tile = new BoardTile(x, y, ImageID.GRASS, generateDetailImage());
                tile.addObserver(this);

                add(tile, x, y);
                boardTiles[x][y] = tile;
            }
        }

        // Add battle events
        generateBattleEvents(player);

        // Add item events
        generateItemEvents();
    }

    public void updateBoardTileCounter(int x, int y)
    {
        int enemyCount = 0;
        int itemCount = 0;

        for (int xCheck = x - 1; xCheck <= x + 1; xCheck++)
        {
            for (int yCheck = y - 1; yCheck <= y + 1; yCheck++)
            {
                if (!(xCheck == x && yCheck == y) && withinBounds(xCheck, yCheck))
                {
                    BoardTile tile = at(xCheck, yCheck);
                    if (tile.getEvent() != null)
                    {
                        if (tile.getEvent().getType() == BoardTileEventType.ENEMY)
                            enemyCount++;
                        else if (tile.getEvent().getType() == BoardTileEventType.ITEM)
                            itemCount++;
                    }
                }
            }
        }

        at(x, y).setNeighboringEnemies(enemyCount);
        at(x, y).setNeighboringItems(itemCount);
    }

    public boolean checkAdjacentTiles(int x, int y)
    {
        for (int xCheck = x - 1; xCheck <= x + 1; xCheck++)
            for (int yCheck = y - 1; yCheck <= y + 1; yCheck++)
                if (!(xCheck == x && yCheck == y) && withinBounds(xCheck, yCheck) && at(xCheck, yCheck).getEvent() != null)
                    return true;

        return false;
    }

    public boolean withinBounds(int x, int y) { return x >= 0 && x < getBoardWidth() && y >= 0 && y < getBoardHeight(); }

    public int getBoardHeight() { return boardHeight; }

    public int getBoardWidth() { return boardWidth; }

    public int getTotalEnemies() { return totalEnemies; }

    public int getTotalItems() { return totalItems; }

    public int getTotalMerchants() { return totalMerchants; }

    public BoardTile at(int x, int y) { return boardTiles[x][y]; }

    private void generateBattleEvents(Player player)
    {
        totalEnemies = (int)(boardWidth * boardHeight * ENEMY_TILE_PERCENTAGE);
        HashSet<BoardTile> enemyTiles = new HashSet<>(totalEnemies);

        while (enemyTiles.size() < totalEnemies)
        {
            int x = rng.nextInt(boardWidth);
            int y = rng.nextInt(boardHeight);

            enemyTiles.add(boardTiles[x][y]);
        }

        for (BoardTile b : enemyTiles)
            b.setEvent(new EnemyBoardTileEvent(b, BoardTileEventType.ENEMY, new Battle(generateEnemies(), player)));
    }

    private void generateItemEvents()
    {
        totalItems = (int)(boardWidth * boardHeight * ITEM_TILE_PERCENTAGE);
        HashSet<BoardTile> itemTiles = new HashSet<>(totalItems);

        while (itemTiles.size() < totalItems)
        {
            int x = rng.nextInt(boardWidth);
            int y = rng.nextInt(boardHeight);

            if (at(x, y).getEvent() == null)
                itemTiles.add(boardTiles[x][y]);
        }

        for (BoardTile b : itemTiles)
            b.setEvent(new ItemBoardTileEvent(b, BoardTileEventType.ITEM, ItemID.getRandom(rng)));
    }

    private Enemy[] generateEnemies()
    {
        int totalEnemies = rng.nextInt(4) + 1;
        Enemy[] enemies = new Enemy[totalEnemies];

        for (int i = 0; i < totalEnemies; i++)
        {
            int enemyChance = rng.nextInt(100);

            if (enemyChance < 25)
                enemies[i] = EnemyID.getBandit();
            else if (enemyChance < 50)
                enemies[i] = EnemyID.getWarrior();
            else if (enemyChance < 75)
                enemies[i] = EnemyID.getWizard();
            else
                enemies[i] = EnemyID.getZombie();
        }

        return enemies;
    }

    private Image generateDetailImage()
    {
        int detailChance = rng.nextInt(100);
        Image detailImage = null;

        if (detailChance < 20)
            detailImage = ImageID.TREE;
        else if (detailChance < 30)
            detailImage = ImageID.ROCK;
        else if (detailChance < 35)
            detailImage = ImageID.BUSH;

        return detailImage;
    }
}