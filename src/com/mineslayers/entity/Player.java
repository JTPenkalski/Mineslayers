/**
 *Player manages everything the player would have control over in their game.
 */

/**
 * @author Peter Humphreys
 */
package com.mineslayers.entity;

import com.mineslayers.id.ItemID;

public class Player {
    private double attackSpeed;
    private double criticalStrikeChance;
    private int coins;
    private int health;
    private int lives;
    private ArmorItem armorItem;
    private WeaponItem weaponItem;
    private Inventory inventory;

    /**
     *Default constructor
     * @param
     * @return none
     */
    public Player()
    {
        this.attackSpeed = 1;
        this.criticalStrikeChance = 0;
        this.coins = 0;
        this.lives = 3;
        this.armorItem = null;
        this.weaponItem = null;
        this.inventory = new Inventory();

        resetHealth();

        for (int i = 0; i < 3; i++)
            getInventory().addFlag(ItemID.getFlagItem());
    }

    /**
     * Sets the Player's attackSpeed
     * @param attackSpeed of type double
     * @return none
     */
    public void setAttackSpeed(double attackSpeed)
    {
        this.attackSpeed = attackSpeed;
    }

    /**
     * Returns the Player's attackSpeed
     * @param
     * @return attackSpeed of type double
     */
    public double getAttackSpeed()
    {
        return this.attackSpeed;
    }

    /**
     * Sets the Player's criticalStrikeChance
     * @param criticalStrikeChance of type double
     * @return
     */
    public void setCriticalStrikeChance(double criticalStrikeChance)
    {
        this.criticalStrikeChance = criticalStrikeChance;
    }

    /**
     * Returns the Player's criticalStrikeChance
     * @param
     * @return criticalStrikeChance of type double
     */
    public double getCriticalStrikeChance()
    {
        return this.criticalStrikeChance;
    }

    /**
     * Sets the Player's coins
     * @param coins of type int
     * @return
     */
    public void setCoins(int coins)
    {
        this.coins = coins;
    }

    /**
     * Returns the Player's coins
     * @param
     * @return coins of type int
     */
    public int getCoins()
    {
        return this.coins;
    }

    /**
     * Sets the Player's health
     * @param health of type int
     * @return
     */
    public void setHealth(int health)
    {
        this.health = health;
    }

    /**
     * Returns the Player's health
     * @param
     * @return health of type int
     */
    public int getHealth()
    {
        return this.health;
    }

    /**
     * Resets the Player's health to 10 * LIVES
     * @param
     * @return
     */
    public void resetHealth()
    {
        setHealth(7 * lives);
    }

    /**
     * Sets the Player's lives
     * @param lives of type int
     * @return
     */
    public void setLives(int lives)
    {
        this.lives = lives;
    }

    /**
     * Returns the Player's lives
     * @param
     * @return lives of type int
     */
    public int getLives()
    {
        return this.lives;
    }

    /**
     * Sets the Player's armorITem
     * @param armorItem of type ArmorItem
     * @return
     */
    public void setArmorItem(ArmorItem armorItem)
    {
        this.armorItem = armorItem;
    }

    /**
     * Returns the Player's armorItem
     * @param
     * @return armorItem of type ArmorItem
     */
    public ArmorItem getArmorItem()
    {
        return this.armorItem;
    }

    /**
     * Sets the Player's weaponItem
     * @param weaponItem of type WeaponItem
     * @return
     */
    public void setWeaponItem(WeaponItem weaponItem)
    {
        this.weaponItem = weaponItem;
    }

    /**
     * Returns the Player's weaponItem
     * @param
     * @return weaponItem of type WeaponItem
     */
    public WeaponItem getWeaponItem()
    {
        return this.weaponItem;
    }

    /**
     * Returns the Player's inventory
     * @param
     * @return inventory of type Inventory
     */
    public Inventory getInventory()
    {
        return this.inventory;
    }







}
