package com.mineslayers.entity;

public class Battle
{
    private final int MAX_ENEMIES = 4;
    private final Player player;

    private int activeEnemy;
    private int[] playerDamage;
    private int turn;
    private int[] enemiesDamage;
    private String playerUsed;
    private Enemy[] enemies;

    public Battle(Enemy[] enemies, Player player)
    {
        this.turn = 0;
        this.activeEnemy = 0;
        this.player = player;
        this.enemies = enemies;
        this.playerUsed = "Nothing";
        this.playerDamage = new int[MAX_ENEMIES];
        this.enemiesDamage = new int[MAX_ENEMIES];
    }

    public int getRemainingEnemies()
    {
        int left = 0;
        for(Enemy e : enemies)
            left += e.getHealth() > 0 ? 1 : 0;
        return left;
    }

    public Enemy getEnemy(int index)
    {
        return enemies[index];
    }

    public Enemy[] getEnemies()
    {
        return enemies;
    }

    public Player getPlayer()
    {
        return player;
    }

    public int getActiveEnemy()
    {
        return activeEnemy;
    }

    public void setActiveEnemy(int activeEnemy)
    {
        this.activeEnemy = activeEnemy;
    }

    public String getPlayerUsed()
    {
        return playerUsed;
    }

    public void setPlayerUsed(String playerUsed)
    {
        this.playerUsed = playerUsed;
    }

    public int getPlayerDamage(int index)
    {
        return playerDamage[index];
    }


    public void setPlayerDamage(int index, int playerDamage)
    {
        //If a -1 is passed in it sets all value to 0 else it
        //sets the index to a damage value.
        if(index != -1)
            this.playerDamage[index] = playerDamage;
        else
            for(int i = 0; i < MAX_ENEMIES; i++)
                this.playerDamage[i] = playerDamage;
    }

    public int getEnemyDamage(int index)
    {
        return enemiesDamage[index];
    }

    public void setEnemiesDamage(int index, int enemiesDamage)
    {
        this.enemiesDamage[index] = enemiesDamage;
    }

    public int getTurn()
    {
        return turn;
    }

    public void setTurn(int turn)
    {
        this.turn = turn;
    }

    public int getReward()
    {
        int reward = 0;

        for (Enemy e : enemies)
            reward += e.getCoinReward();

        return reward;
    }
}
