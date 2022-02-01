/**
 *ValueItems do not exist in the player’s inventory, rather they are simply a container for coins, so that they can
 *be awarded to the player and hidden in BoardTiles the same as any other Item.
 */

/**
 * @author Peter Humphreys
 */
package com.mineslayers.entity;
import javafx.scene.image.Image;

public class TreasureItem extends Item {

    private int value;

    /**
     * Parameterized constructor, sets private fields to parameter inputs
     * @param name of type String, description of type String, image of type Image, marketValue of type int, value of type int
     * @return
     */
    public TreasureItem(String name, String description, Image image, int marketValue, int value)
    {
        super(name, description, image, marketValue);
        this.value = value;
    }

    /**
     *This method will do nothing, but needs to exist since it is declared abstract in Item
     * @param player of type Player
     * @return
     */
    public void use(Player player)
    {

    }

    /**
     * Returns the TreasureItem's value
     * @param
     * @return value of type int
     */
    public int getValue()
    {
        return this.value;
    }

}
