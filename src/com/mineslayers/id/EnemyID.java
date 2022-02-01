package com.mineslayers.id;

import java.util.Random;
import com.mineslayers.entity.Enemy;
import com.mineslayers.entity.FlaskEffect;

/**
 * A database for all enemies used throughout the game.
 */
public class EnemyID
{
    private static Random rng = new Random();

    /**
     * @return A random Bandit enemy.
     */
    public static Enemy getBandit()
    {
        // Generate random Bandit enemy
        int chance = rng.nextInt(100);

        if (chance < 50)
            return new Enemy(1, 0.10, 0.1, 2, 4, 8, FlaskEffect.POISON, ImageID.BANDIT, "Common Bandit");
        else if (chance < 85)
            return new Enemy(1, 0.20, 0.1, 3, 6, 10, FlaskEffect.POISON, ImageID.BANDIT, "Skilled Bandit");
        else
            return new Enemy(1, 0.10, 0.1, 4, 10, 15, FlaskEffect.POISON, ImageID.BANDIT, "Strong Bandit");
    }

    /**
     * @return A random Warrior enemy.
     */
    public static Enemy getWarrior()
    {
        // Generate random Warrior enemy
        int chance = rng.nextInt(100);

        if (chance < 50)
            return new Enemy(1, 0.10, 0.1, 2, 4, 8, FlaskEffect.FIRE, ImageID.WARRIOR, "Common Warrior");
        else if (chance < 85)
            return new Enemy(1, 0.20, 0.1, 3, 6, 10, FlaskEffect.FIRE, ImageID.WARRIOR, "Skilled Warrior");
        else
            return new Enemy(1, 0.10, 0.1, 4, 10, 15, FlaskEffect.FIRE, ImageID.WARRIOR, "Strong Warrior");
    }

    /**
     * @return A random Wizard enemy.
     */
    public static Enemy getWizard()
    {
        // Generate random Wizard enemy
        int chance = rng.nextInt(100);

        if (chance < 50)
            return new Enemy(1, 0.10, 0.1, 2, 4, 8, FlaskEffect.ICE, ImageID.WIZARD, "Common Wizard");
        else if (chance < 85)
            return new Enemy(1, 0.20, 0.1, 3, 6, 10, FlaskEffect.ICE, ImageID.WIZARD, "Skilled Wizard");
        else
            return new Enemy(1, 0.10, 0.1, 4, 10, 15, FlaskEffect.ICE, ImageID.WIZARD, "Strong Wizard");
    }

    /**
     * @return A random Zombie enemy.
     */
    public static Enemy getZombie()
    {
        // Generate random Zombie enemy
        int chance = rng.nextInt(100);

        if (chance < 50)
            return new Enemy(1, 0.10, 0.1, 2, 4, 8, FlaskEffect.NONE, ImageID.ZOMBIE, "Common Zombie");
        else if (chance < 85)
            return new Enemy(1, 0.20, 0.1, 3, 6, 10, FlaskEffect.NONE, ImageID.ZOMBIE, "Skilled Zombie");
        else
            return new Enemy(1, 0.10, 0.1, 4, 10, 15, FlaskEffect.NONE, ImageID.ZOMBIE, "Strong Zombie");
    }
}