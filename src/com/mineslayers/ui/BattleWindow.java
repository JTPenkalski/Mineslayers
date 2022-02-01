package com.mineslayers.ui;

import java.io.IOException;
import java.util.ArrayList;

import com.mineslayers.controller.BattleController;
import com.mineslayers.controller.MineSlayerGame;
import com.mineslayers.entity.Battle;
import com.mineslayers.entity.Player;
import com.mineslayers.event.EventArgs;
import com.mineslayers.event.MessageInvoker;
import com.mineslayers.event.MessageObserver;
import com.mineslayers.id.ImageID;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class BattleWindow extends Stage implements MessageInvoker<ItemIcon, EventArgs>, MessageObserver<ItemIcon, EventArgs>
{
    @FXML private Button attackButton;
    @FXML private Button defendButton;
    @FXML private Button useButton;
    @FXML private Label enemy1StatsLabel;
    @FXML private Label enemy2StatsLabel;
    @FXML private Label enemy3StatsLabel;
    @FXML private Label enemy4StatsLabel;
    @FXML private Label playerStatsLabel;
    @FXML private Label turnDetailsLabel;
    @FXML private ImageView enemy1ImageView;
    @FXML private ImageView enemy2ImageView;
    @FXML private ImageView enemy3ImageView;
    @FXML private ImageView enemy4ImageView;
    @FXML private ImageView playerImageView;
    @FXML private InventoryPane playerInventoryPane;
    @FXML private ItemIcon primaryArmorItemIcon;
    @FXML private ItemIcon primaryWeaponItemIcon;
    private ArrayList<MessageObserver<ItemIcon, EventArgs>> observers;

    public BattleWindow()
    {
        // Initialize fields
        observers = new ArrayList<>();
        Scene scene = null;

        // Create class instance
        try
        {
            // Try to create an instance from an FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/BattleWindow.fxml"));
            fxmlLoader.setController(this);

            scene = new Scene(fxmlLoader.load(), MineSlayerGame.WINDOW_RES_X, MineSlayerGame.WINDOW_RES_Y);
            scene.getStylesheets().add(getClass().getClassLoader().getResource("css/BattleWindow.css").toExternalForm());
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
        initModality(Modality.APPLICATION_MODAL);
        setResizable(MineSlayerGame.ALLOW_MAXIMIZE);
        setTitle(MineSlayerGame.WINDOW_TITLE);
        playerImageView.setImage(ImageID.PLAYER);

        // Setup JavaFX event system
        playerInventoryPane.addObserver(this);

        attackButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->
        {
            // Attack enemies
            MineSlayerGame.BattleController.attack();
        });

        defendButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->
        {
            // Defend against enemies
            MineSlayerGame.BattleController.defend();
        });

        useButton.addEventHandler(MouseEvent.MOUSE_CLICKED, e ->
        {
            // Defend against enemies
            MineSlayerGame.BattleController.useItem();
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

    public void update(Battle battle)
    {
        int enemyCount = battle.getEnemies().length;

        // Clear previous battle
        enemy1ImageView.setImage(null);
        enemy1StatsLabel.setText("");
        enemy2ImageView.setImage(null);
        enemy2StatsLabel.setText("");
        enemy3ImageView.setImage(null);
        enemy3StatsLabel.setText("");
        enemy4ImageView.setImage(null);
        enemy4StatsLabel.setText("");

        // Update Enemy 1
        enemy1ImageView.setImage(battle.getEnemy(0).getImage());
        enemy1StatsLabel.setText(battle.getEnemy(0).getName() + "\nHealth: " + Math.max(0, battle.getEnemy(0).getHealth()));

        // Update Enemy 2
        if (enemyCount > 1)
        {
            enemy2ImageView.setImage(battle.getEnemy(1).getImage());
            enemy2StatsLabel.setText(battle.getEnemy(1).getName() + "\nHealth: " + Math.max(0, battle.getEnemy(1).getHealth()));
        }

        // Update Enemy 3
        if (enemyCount > 2)
        {
            enemy3ImageView.setImage(battle.getEnemy(2).getImage());
            enemy3StatsLabel.setText(battle.getEnemy(2).getName() + "\nHealth: " + Math.max(0, battle.getEnemy(2).getHealth()));
        }

        // Update Enemy 4
        if (enemyCount > 3)
        {
            enemy4ImageView.setImage(battle.getEnemy(3).getImage());
            enemy4StatsLabel.setText(battle.getEnemy(3).getName() + "\nHealth: " + Math.max(0, battle.getEnemy(3).getHealth()));
        }

        // Update Player
        playerStatsLabel.setText("Player\n Health: " + battle.getPlayer().getHealth());

        // Update turn summary
        if (battle.getTurn() > 0)
        {
            String turnDetails = "Turn " + battle.getTurn() + " Summary\n\nPlayer used " + battle.getPlayerUsed() + " and lost " +
                    battle.getEnemyDamage(battle.getActiveEnemy()) + " HP.\n" +
                    "Enemy 1 used ATTACK and lost " + battle.getPlayerDamage(0) + " HP.\n";

            if (enemyCount > 1)
                turnDetails += "Enemy 2 used ATTACK and lost " + battle.getPlayerDamage(1) + " HP.\n";

            if (enemyCount > 2)
                turnDetails += "Enemy 3 used ATTACK and lost " + battle.getPlayerDamage(2) + " HP.\n";

            if (enemyCount > 3)
                turnDetails += "Enemy 4 used ATTACK and lost " + battle.getPlayerDamage(3) + " HP.\n";

            turnDetails += "Next Enemy Attack From " + battle.getEnemy((battle.getActiveEnemy() + 1) % battle.getEnemies().length).getName();

            turnDetailsLabel.setText(turnDetails);
        }
        else
        {
            turnDetailsLabel.setText("Attack or Defend to begin. You may use or equip items at any point during battle.");
        }
    }

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

    public InventoryPane getInventoryPane() { return playerInventoryPane; }
}