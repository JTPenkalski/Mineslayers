/**
 *An EnemyBoardTileEvent will cause the player to enter the Combat Window, initialized with the stored Battle instance.
 */

/**
 * @author Peter Humphreys
 */
package com.mineslayers.entity;

import com.mineslayers.controller.MineSlayerGame;
import com.mineslayers.ui.BoardTile;

public class EnemyBoardTileEvent extends BoardTileEvent
{
    Battle battle;

    /**
     * Parameterized constructor, sets private fields to parameter inputs
     * @param tile of type BoardTile, type of type BoardTileEventType
     * @return
     */
    public EnemyBoardTileEvent(BoardTile tile, BoardTileEventType type, Battle battle)
    {
        super(tile, type);
        this.battle = battle;
    }

    /**
     *
     * @param
     * @return
     */
    public void invoke()
    {
        MineSlayerGame.BattleController.startBattle(battle);
        MineSlayerGame.BattleController.getBattleWindow().show();
    }

    /**
     *Sets the EnemyBoardTileEvent's battle
     * @param battle of type Battle
     * @return
     */
    public void setBattle(Battle battle)
    {
        this.battle = battle;
    }

    /**
     * Returns the EnemyBoardTileEvent's battle
     * @param
     * @return battle of type Battle
     */
    public Battle getBattle()
    {
        return this.battle;
    }

}
