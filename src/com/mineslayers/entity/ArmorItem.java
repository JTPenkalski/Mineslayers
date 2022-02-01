/**
 *ArmorItems are also used in combat. Enemies and players use them to protect themselves from damage
 *and certain FlaskEffects.
 */

/**
 * @author Peter Humphreys
 */
package com.mineslayers.entity;
import javafx.scene.image.Image;

public class ArmorItem extends Item {
    private double protection;
    private double flaskProtection;
    private FlaskEffect resistFlaskEffect;

    /**
     * Parameterized constructor, sets private fields to parameter inputs
     * @param name of type String, description of type String, image of type Image, marketValue of type int, protection of type double,
     *             flaskProtection of type double, resistFlaskEffect of type FlaskEffect
     * @return
     */
    public ArmorItem(String name, String description, int marketValue, Image image, double protection, double flaskProtection, FlaskEffect resistFlaskEffect)
    {
        super(name, description, image, marketValue);
        this.protection = protection;
        this.flaskProtection = flaskProtection;
        this.resistFlaskEffect = resistFlaskEffect;
    }

    /**
     *This method will do nothing, but needs to exist since it is declared abstract in Item
     * @param player of type Player
     * @return
     */
    public void use(Player player) { }

    /**
     * Sets the ArmorItem's protection
     * @param protection of type double
     * @return
     */
    public void setProtection(double protection)
    {
        this.protection = protection;
    }

    /**
     * Returns the ArmorItem's protection
     * @param
     * @return protection of type double
     */
    public double getProtection() {
        return this.protection;
    }

    /**
     * Sets the ArmorItem's flaskProtection
     * @param flaskProtection of type double
     * @return
     */
    public void setFlaskProtection(double flaskProtection)
    {
        this.flaskProtection = flaskProtection;
    }

    /**
     * Returns the ArmorItem's flaskProtection
     * @param
     * @return flaskProtection of type double
     */
    public double getFlaskProtection()
    {
        return flaskProtection;
    }

    /**
     * Sets the ArmorItem's resistFlaskEffect
     * @param resistFlaskEffect of type FlaskEffect
     * @return
     */
    public void setResistFlaskEffect(FlaskEffect resistFlaskEffect)
    {
        this.resistFlaskEffect = resistFlaskEffect;
    }

    /**
     * Returns the ArmorItem's resistFlaskEffect
     * @param
     * @return resistFlaskEffect of type FlaskEffect
     */
    public FlaskEffect getResistFlaskEffect()
    {
        return this.resistFlaskEffect;
    }

}
