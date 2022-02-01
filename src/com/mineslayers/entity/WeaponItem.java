/**
 *WeaponItems are used in combat. Enemies and players use them to damage each other and FlaskItems can be applied
 *to them to provide additional effects in combat.
 */

/**
 * @author Peter Humphreys
 */
package com.mineslayers.entity;
import javafx.scene.image.Image;

public class WeaponItem extends Item{

    private int damage;
    private int flaskDamage;
    private FlaskEffect flaskEffect;

    /**
     * Parameterized constructor, sets private fields to parameter inputs
     * @param name of type String, description of type String, image of type Image, marketValue of type int, damage of type int,
     *             flaskEffect of type FlaskEffect
     * @return
     */
    public WeaponItem(String name, String description, Image image, int marketValue, int damage)
    {
        super(name, description, image, marketValue);
        this.damage = damage;
        this.flaskDamage = (int)(damage * 0.3);
    }

    /**
     *This method will do nothing, but needs to exist since it is declared abstract in Item
     * @param player of type Player
     * @return
     */
    public void use(Player player) { }

    /**
     * Sets the WeaponItem's damage
     * @param damage of type int
     * @return
     */
    public void setDamage(int damage)
    {
        this.damage = damage;
    }

    /**
     * Returns the WeaponItem's damage
     * @param
     * @return damage of type int
     */
    public int getDamage()
    {
        return this.damage;
    }

    /**
     * Sets the WeaponItem's flaskDamage
     * @param flaskDamage of type int
     * @return
     */
    public void setFlaskDamage(int flaskDamage) { this.flaskDamage = flaskDamage; }

    /**
     * Returns the WeaponItem's flaskDamage
     * @param
     * @return flaskDamage of type int
     */
    public int getFlaskDamage()
    {
        return this.flaskDamage;
    }

    /**
     * Sets the WeaponItem's flaskEffect
     * @param flaskEffect of type FlaskEffect
     * @return
     */
    public void setFlaskEffect(FlaskEffect flaskEffect) { this.flaskEffect = flaskEffect; }

    /**
     * Returns the WeaponItem's flaskEffect
     * @param
     * @return flaskEffect of type FlaskEffect
     */
    public FlaskEffect getFlaskEffect()
    {
        return this.flaskEffect;
    }
}
