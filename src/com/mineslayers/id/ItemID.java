package com.mineslayers.id;

import java.util.ArrayList;
import java.util.Random;
import com.mineslayers.entity.*;

/**
 * A database for all items used throughout the game. Common item settings are also stored here.
 */
public class ItemID
{
    private static ArrayList<Item> allItems = new ArrayList<>()
    {{
        add(getArmorItem("ENCHANTED"));
        add(getArmorItem("FIRECLOAK"));
        add(getArmorItem("IRON"));
        add(getArmorItem("LEATHER"));
        add(getArmorItem("NINJAGEAR"));
        add(getFlagItem());
        add(getFlaskItem("ELECTRIC"));
        add(getFlaskItem("FIRE"));
        add(getFlaskItem("ICE"));
        add(getFlaskItem("POISON"));
        add(getFlaskItem("WATER"));
        add(getLifeCharm());
        add(getPotionItem("CRITCHANCE"));
        add(getPotionItem("DEFENSE"));
        add(getPotionItem("HEALTH"));
        add(getPotionItem("SPEED"));
        add(getWeaponItem("AXE"));
        add(getWeaponItem("BOW"));
        add(getWeaponItem("DAGGER"));
        add(getWeaponItem("EXCALIBUR"));
        add(getWeaponItem("STAFF"));
        add(getWeaponItem("SPEAR"));
    }};

    public static Item getRandom(Random rng) { return allItems.get(rng.nextInt(allItems.size())); }

    /**
     * @param armor Name of armor to get.
     * @return Corresponding Armor.
     */
    public static ArmorItem getArmorItem(String armor)
    {
        switch (armor.toUpperCase())
        {
            case "ENCHANTED": return new ArmorItem("Enchanted Armor", "The most powerful protection.", 20, ImageID.ARMOR_ENCHANCTED, 0.75, 1, FlaskEffect.POISON);
            case "FIRECLOAK": return new ArmorItem("Fire Cloak", "Offers the best protection against Fire attacks.", 12, ImageID.ARMOR_FIRECLOAK, 0.2, 1, FlaskEffect.FIRE);
            case "IRON": return new ArmorItem("Iron Armor", "A knight's armor.", 10, ImageID.ARMOR_IRON, 0.45, 0.2, FlaskEffect.POISON);
            case "LEATHER": return new ArmorItem("Leather Armor", "Basic protection.", 5, ImageID.ARMOR_LEATHER, 0.15, 0, FlaskEffect.NONE);
            case "NINJAGEAR": return new ArmorItem("Ninja Gear", "Lightweight protection.", 12, ImageID.ARMOR_NINJAGEAR, 0.25, 1, FlaskEffect.ICE);
            default: return null;
        }
    }

    /**
     * @return A Flag item.
     */
    public static FlagItem getFlagItem() { return new FlagItem("Flag", "Used to mark Board Tiles that potentially have enemies.", ImageID.FLAG_BOARD, 8); }

    /**
     * @param flask Name of flask to get.
     * @return Corresponding Flask.
     */
    public static FlaskItem getFlaskItem(String flask)
    {
        switch (flask.toUpperCase())
        {
            case "ELECTRIC": return new FlaskItem("Electric Flask", "Adds electric damage to weapon.", 5, ImageID.FLASK_ELECTRIC, FlaskEffect.ELECTRIC);
            case "FIRE": return new FlaskItem("Fire Flask", "Adds fire damage to weapon.", 5, ImageID.FLASK_FIRE, FlaskEffect.FIRE);
            case "ICE": return new FlaskItem("Ice Flask", "Adds ice damage to weapon.", 5, ImageID.FLASK_ICE, FlaskEffect.ICE);
            case "POISON": return new FlaskItem("Poison Flask", "Adds poison damage to weapon.", 5, ImageID.FLASK_POISON, FlaskEffect.POISON);
            case "WATER": return new FlaskItem("Water Flask", "Adds water damage to weapon.", 5, ImageID.FLASK_WATER, FlaskEffect.WATER);
            default: return null;
        }
    }

    /**
     * @return A Life Charm item.
     */
    public static PotionItem getLifeCharm() { return new PotionItem("Life Charm", "Grants 1 additional life. Corresponds to 10 HP in battle.", ImageID.LIFE_CHARM, 10, PotionType.LIFE, 1); }

    /**
     * @param potion Name of potion to get.
     * @return Corresponding Potion.
     */
    public static PotionItem getPotionItem(String potion)
    {
        switch (potion.toUpperCase())
        {
            case "CRITCHANCE": return new PotionItem("Critical Strike Chance Potion", "Increases critical strike chance.", ImageID.POTION_CRIT, 5, PotionType.CRIT_CHANCE, 0.5);
            case "DEFENSE": return new PotionItem("Defense Potion", "Increases defense.", ImageID.POTION_DEFENSE, 5, PotionType.DEFENSE, 0.7);
            case "HEALTH": return new PotionItem("Health Potion", "Increases health.", ImageID.POTION_HEALTH, 5, PotionType.HEALTH, 30);
            case "SPEED": return new PotionItem("Speed Potion", "Increases attack speed.", ImageID.POTION_SPEED, 5, PotionType.SPEED, 0.4);
            default: return null;
        }
    }

    /**
     * @param weapon Name of weapon to get.
     * @return Corresponding Weapon.
     */
    public static WeaponItem getWeaponItem(String weapon)
    {
        switch (weapon.toUpperCase())
        {
            case "AXE": return new WeaponItem("Battle Axe", "Semi-powerful attack", ImageID.AXE, 8, 7);
            case "BOW": return new WeaponItem("Wooden Bow", "Regular attack", ImageID.BOW, 10, 10);
            case "DAGGER": return new WeaponItem("Dagger", "Weak attack", ImageID.DAGGER, 5, 3);
            case "EXCALIBUR": return new WeaponItem("Excalibur", "Super-powerful attack", ImageID.EXCALIBUR, 25, 20);
            case "STAFF": return new WeaponItem("Mystic Staff", "Weak attack", ImageID.MYSTIC_STAFF, 6, 4);
            case "SPEAR": return new WeaponItem("Spear", "Regular attack", ImageID.SPEAR, 10, 12);
            default: return null;
        }
    }
}