/**
 * Enemies engage the player in combat during a Battle. They may attack the player while also defending strikes from opponents. They also have basic combat stats determining how frequent they attack, how powerful their attacks are, and a general defense. Enemies also can be immune or weak to certain FlaskEffects.
 */

/**
 * @author Peter Humphreys
 */
package com.mineslayers.entity;
import javafx.scene.image.Image;

public class Enemy {
    private double attackSpeed;
    private double criticalStrikeChance;
    private double protection;
    private int coinReward;
    private int damage;
    private int health;
    private FlaskEffect flaskWeakness;
    private Image image;
    private String name;

    /**
     * Parameterized constructor, sets private fields to parameter inputs
     * @param
     * @return
     */
    public Enemy(double attackSpeed, double criticalStrikeChance, double protection, int coinReward, int damage, int health, FlaskEffect flaskWeakness, Image image, String name)
    {
        this.attackSpeed = attackSpeed;
        this.criticalStrikeChance = criticalStrikeChance;
        this.protection = protection;
        this.coinReward = coinReward;
        this.damage = damage;
        this.health = health;
        this.flaskWeakness = flaskWeakness;
        this.image = image;
        this.name = name;
    }

    /**
     * Sets the Enemy's attackSpeed
     * @param attackSpeed of type double
     * @return
     */
    public void setAttackSpeed(double attackSpeed)
    {
        this.attackSpeed = attackSpeed;
    }

    /**
     * Gets the Enemy's attackSpeed
     * @param
     * @return attackSpeed of type double
     */
    public double getAttackSpeed()
    {
        return this.attackSpeed;
    }

    /**
     * Sets the Enemy's criticalStrikeChance
     * @param criticalStrikeChance of type double
     * @return
     */
    public void setCriticalStrikeChance(double criticalStrikeChance)
    {
        this.criticalStrikeChance = criticalStrikeChance ;
    }

    /**
     * Gets the Enemy's criticalStrikeChance
     * @param
     * @return criticalStrikeChance of type double
     */
    public double getCriticalStrikeChance()
    {
        return this.criticalStrikeChance;
    }

    /**
     * Sets the Enemy's protection
     * @param protection of type double
     * @return
     */
    public void setProtection(double protection)
    {
        this.protection = protection;
    }

    /**
     * Gets the Enemy's protection
     * @param
     * @return protection of type double
     */
    public double getProtection()
    {
        return this.protection;
    }

    /**
     * Sets the Enemy's coinReward
     * @param coinReward of type int
     * @return
     */
    public void setCoinReward(int coinReward)
    {
        this.coinReward = coinReward;
    }

    /**
     * Gets the Enemy's coinReward
     * @param
     * @return coinReward of type int
     */
    public int getCoinReward()
    {
        return this.coinReward;
    }

    /**
     * Sets the Enemy's damage
     * @param damage of type int
     * @return
     */
    public void setDamage(int damage)
    {
        this.damage = damage;
    }

    /**
     * Gets the Enemy's damage
     * @param
     * @return damage of type int
     */
    public int getDamage()
    {
        return this.damage;
    }

    /**
     * Sets the Enemy's health
     * @param health of type int
     * @return
     */
    public void setHealth(int health)
    {
        this.health = health;
    }

    /**
     * Gets the Enemy's health
     * @param
     * @return health of type int
     */
    public int getHealth()
    {
        return this.health;
    }

    /**
     * Sets the Enemy's flaskWeakness
     * @param flaskWeakness of type FlaskEffect
     * @return
     */
    public void setFlaskWeakness(FlaskEffect flaskWeakness)
    {
        this.flaskWeakness = flaskWeakness ;
    }

    /**
     * Gets the Enemy's flaskWeakness
     * @param
     * @return flaskWeakness of type FlaskEffect
     */
    public FlaskEffect getFlaskWeakness()
    {
        return this.flaskWeakness;
    }

    /**
     * Sets the player's image
     * @param image of Type Image
     * @return
     */
    public void setImage(Image image)
    {
        this.image = image;
    }

    /**
     * Gets the player's image
     * @param
     * @return image of type Image
     */
    public Image getImage()
    {
        return this.image;
    }

    /**
     * Sets the player's name
     * @param name of type String
     * @return none
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Gets the player's name
     * @param
     * @return name of type String
     */
    public String getName()
    {
        return this.name;
    }
}
