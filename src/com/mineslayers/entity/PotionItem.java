/**
 *PotionItems are also used in combat. Players can use them to provide various stat increases when battling enemies.
 */

/**
 * @author Peter Humphreys
 */
package com.mineslayers.entity;
import javafx.scene.image.Image;

public class PotionItem extends Item {
    private PotionType type;
    private double propertyChange;

    /**
     * Parameterized constructor, sets private fields to parameter inputs
     * @param name of type String, description of type String, image of type Image, marketValue of type String, type of type PotionType,
     *             propertyChange of type double
     * @return
     */
    public PotionItem(String name, String description, Image image, int marketValue, PotionType type, double propertyChange)
    {
        super(name, description, image, marketValue);
        this.type = type;
        this.propertyChange = propertyChange;
    }

    /**
     * Changes various player fields depending on the type of potion used
     * @param player of type Player
     * @return
     */
    public void use(Player player)
    {
        if (type == PotionType.CRIT_CHANCE)
            player.setCriticalStrikeChance(propertyChange);
        else if (type == PotionType.DEFENSE)
            player.getArmorItem().setProtection(propertyChange);
        else if (type == PotionType.HEALTH)
            player.setHealth(player.getHealth() + (int)propertyChange);
        else if (type == PotionType.LIFE)
            player.setLives(player.getLives() + (int)propertyChange);
        else if (type == PotionType.SPEED)
            player.setAttackSpeed(propertyChange);
    }
}
