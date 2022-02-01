package com.mineslayers.id;

import javafx.scene.image.Image;

/**
 * A database for all images used throughout the game. Common image settings are also stored here.
 */
public class ImageID
{
    public static final int BOARD_TILE_RES = 64;
    public static final int ENEMY_RES = 150;
    public static final int ITEM_COUNTER_RES = 100;
    public static final int ITEM_RES = 75;
    public static boolean PRESERVE_RATIO = true;
    public static boolean SMOOTH = true;

    public static final Image BLACK = new Image(ImageID.class.getClassLoader().getResource("images/black.png").toExternalForm(), BOARD_TILE_RES, BOARD_TILE_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image WHITE = new Image(ImageID.class.getClassLoader().getResource("images/white.png").toExternalForm(), BOARD_TILE_RES, BOARD_TILE_RES, PRESERVE_RATIO, SMOOTH);

    public static final Image ARMOR_ENCHANCTED = new Image(ImageID.class.getClassLoader().getResource("images/enchantedArmor.png").toExternalForm(), ITEM_RES, ITEM_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image ARMOR_FIRECLOAK = new Image(ImageID.class.getClassLoader().getResource("images/fireCloak.png").toExternalForm(), ITEM_RES, ITEM_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image ARMOR_IRON = new Image(ImageID.class.getClassLoader().getResource("images/ironArmor.png").toExternalForm(), ITEM_RES, ITEM_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image ARMOR_LEATHER = new Image(ImageID.class.getClassLoader().getResource("images/leatherArmor.png").toExternalForm(), ITEM_RES, ITEM_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image ARMOR_NINJAGEAR = new Image(ImageID.class.getClassLoader().getResource("images/ninjaGear.png").toExternalForm(), ITEM_RES, ITEM_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image AXE = new Image(ImageID.class.getClassLoader().getResource("images/axe.png").toExternalForm(), ITEM_RES, ITEM_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image BANDIT = new Image(ImageID.class.getClassLoader().getResource("images/bandit.png").toExternalForm(), ENEMY_RES, ENEMY_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image BOARD_ITEM = new Image(ImageID.class.getClassLoader().getResource("images/boardItem.png").toExternalForm(), BOARD_TILE_RES, BOARD_TILE_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image BOW = new Image(ImageID.class.getClassLoader().getResource("images/bow.png").toExternalForm(), ITEM_RES, ITEM_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image BUSH = new Image(ImageID.class.getClassLoader().getResource("images/bush.png").toExternalForm(), BOARD_TILE_RES, BOARD_TILE_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image COIN = new Image(ImageID.class.getClassLoader().getResource("images/coin.png").toExternalForm(), ITEM_COUNTER_RES, ITEM_COUNTER_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image EXCALIBUR = new Image(ImageID.class.getClassLoader().getResource("images/excalibur.png").toExternalForm(), ITEM_RES, ITEM_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image DAGGER = new Image(ImageID.class.getClassLoader().getResource("images/dagger.png").toExternalForm(), ITEM_RES, ITEM_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image FLAG = new Image(ImageID.class.getClassLoader().getResource("images/flag.png").toExternalForm(), ITEM_COUNTER_RES, ITEM_COUNTER_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image FLASK_ELECTRIC = new Image(ImageID.class.getClassLoader().getResource("images/electricFlask.png").toExternalForm(), ITEM_RES, ITEM_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image FLASK_FIRE = new Image(ImageID.class.getClassLoader().getResource("images/fireFlask.png").toExternalForm(), ITEM_RES, ITEM_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image FLASK_ICE = new Image(ImageID.class.getClassLoader().getResource("images/iceFlask.png").toExternalForm(), ITEM_RES, ITEM_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image FLASK_POISON = new Image(ImageID.class.getClassLoader().getResource("images/poisonFlask.png").toExternalForm(), ITEM_RES, ITEM_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image FLASK_WATER = new Image(ImageID.class.getClassLoader().getResource("images/waterFlask.png").toExternalForm(), ITEM_RES, ITEM_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image FLAG_BOARD = new Image(ImageID.class.getClassLoader().getResource("images/flag.png").toExternalForm(), BOARD_TILE_RES, BOARD_TILE_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image GRASS = new Image(ImageID.class.getClassLoader().getResource("images/grass.png").toExternalForm(), BOARD_TILE_RES, BOARD_TILE_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image HEART = new Image(ImageID.class.getClassLoader().getResource("images/heart.png").toExternalForm(), ITEM_COUNTER_RES, ITEM_COUNTER_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image ICON = new Image(ImageID.class.getClassLoader().getResource("images/icon.png").toExternalForm(), ITEM_COUNTER_RES, ITEM_COUNTER_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image LIFE_CHARM = new Image(ImageID.class.getClassLoader().getResource("images/lifeCharm.png").toExternalForm(), ITEM_RES, ITEM_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image MYSTIC_STAFF = new Image(ImageID.class.getClassLoader().getResource("images/mysticStaff.png").toExternalForm(), ITEM_RES, ITEM_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image PLAYER = new Image(ImageID.class.getClassLoader().getResource("images/player.png").toExternalForm(), ENEMY_RES, ENEMY_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image POTION_CRIT = new Image(ImageID.class.getClassLoader().getResource("images/critChancePotion.png").toExternalForm(), ITEM_RES, ITEM_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image POTION_DEFENSE = new Image(ImageID.class.getClassLoader().getResource("images/defensePotion.png").toExternalForm(), ITEM_RES, ITEM_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image POTION_HEALTH = new Image(ImageID.class.getClassLoader().getResource("images/healthPotion.png").toExternalForm(), ITEM_RES, ITEM_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image POTION_SPEED = new Image(ImageID.class.getClassLoader().getResource("images/attackSpeedPotion.png").toExternalForm(), ITEM_RES, ITEM_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image ROCK = new Image(ImageID.class.getClassLoader().getResource("images/rock.png").toExternalForm(), BOARD_TILE_RES, BOARD_TILE_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image SPEAR = new Image(ImageID.class.getClassLoader().getResource("images/spear.png").toExternalForm(), ITEM_RES, ITEM_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image STONE = new Image(ImageID.class.getClassLoader().getResource("images/stone.png").toExternalForm(), BOARD_TILE_RES, BOARD_TILE_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image TREE = new Image(ImageID.class.getClassLoader().getResource("images/tree.png").toExternalForm(), BOARD_TILE_RES, BOARD_TILE_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image WARRIOR = new Image(ImageID.class.getClassLoader().getResource("images/warrior.png").toExternalForm(), ENEMY_RES, ENEMY_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image WIZARD = new Image(ImageID.class.getClassLoader().getResource("images/wizard.png").toExternalForm(), BOARD_TILE_RES, BOARD_TILE_RES, PRESERVE_RATIO, SMOOTH);
    public static final Image ZOMBIE = new Image(ImageID.class.getClassLoader().getResource("images/zombie.png").toExternalForm(), ENEMY_RES, ENEMY_RES, PRESERVE_RATIO, SMOOTH);
}