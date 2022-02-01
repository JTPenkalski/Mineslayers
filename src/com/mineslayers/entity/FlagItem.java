/**
 *FlagItems are unique, in that they are used on the GameBoard rather than on the player. The player may use them if
 *they are confident an enemy resides in a particular BoardTile. They are also the only stackable item.
 */

/**
 * @author Peter Humphreys
 */
package com.mineslayers.entity;
import javafx.scene.image.Image;

public class FlagItem extends Item {
    /**
     * Parameterized constructor, sets private fields to parameter inputs, increments count
     * @param name of type String, description of type String, image of type Image, marketValue of type String
     * @return
     */
    public FlagItem(String name, String description, Image image, int marketValue)
    {
        super(name, description, image, marketValue);
    }

    /**
     *
     * @param player of type Player
     * @return
     */
    public void use(Player player)
    {

    }

}
