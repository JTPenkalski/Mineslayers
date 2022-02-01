/**
 *FlaskItems are also used in combat. Players can apply them to weapons to make them do special damage bonuses.
 */

/**
 * @author Peter Humphreys
 */
package com.mineslayers.entity;
import javafx.scene.image.Image;

public class FlaskItem extends Item
{
    private FlaskEffect type;

    /**
     * Parameterized constructor, sets private fields to parameter inputs
     * @param name of type String, description of type String, image of type Image, marketValue of type int, type of type FlaskEffect
     * @return
     */
    public FlaskItem(String name, String description, int marketValue, Image image, FlaskEffect type)
    {
        super(name, description, image, marketValue);
        this.type = type;
    }

    /**
     * Changes various player fields depending on the type of flask used
     * @param
     * @return
     */
    public void use(Player player)
    {
        if (player.getWeaponItem() != null)
            player.getWeaponItem().setFlaskEffect(type);
    }

    /**
     * Returns the FlaskItem's type
     * @param
     * @return type of type FlaskEffect
     */
    public FlaskEffect getType()
    {
        return this.type;
    }
}
