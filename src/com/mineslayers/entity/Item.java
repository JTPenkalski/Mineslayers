/**
 *Item is an abstract class designed for common functionality among all items.
 */

/**
 * @author Peter Humphreys
 */
package com.mineslayers.entity;
import javafx.scene.image.Image;

public abstract class Item {
    private String name;
    private String description;
    private Image image;
    private int marketValue;

    /**
     * Parameterized constructor, sets private fields to parameter inputs
     * @param name of type String, description of type String, image of type Image, marketValue of type int
     * @return
     */
    public Item(String name, String description, Image image, int marketValue)
    {
        this.name = name;
        this.description = description;
        this.marketValue = marketValue;
        this.image = image;
    }

    /**
     *Abstract method that defines how specific item will be used by the player
     * @param player of type Player
     * @return
     */
    public abstract void use(Player player);

    /**
     * Sets the Item's marketValue
     * @param marketValue of type int
     * @return
     */
    public void setMarketValue(int marketValue)
    {
        this.marketValue = marketValue;
    }

    /**
     * Returns the Item's marketValue
     * @param
     * @return marketValue of type int
     */
    public int getMarketValue()
    {
        return this.marketValue;
    }

    /**
     * Returns the Item's name
     * @param
     * @return name of type String
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * Returns the Item's description
     * @param
     * @return description of type String
     */
    public String getDescription()
    {
        return this.description;
    }

    /**
     * Determines if the item's are the same by comparing their names
     * @param item of type Item
     * @return Boolean condition whether items are equal
     */
    public boolean equals (Item item)
    {
        if (this.name.equals(item.name))
            return true;
        return false;
    }

    /**
     * Returns the Item's image
     * @param
     * @return image of type Image
     */
    public Image getImage() { return this.image; }
}
