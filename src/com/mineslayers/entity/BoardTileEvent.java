/**
 *BoardTilesEvents are an abstract base class to be used for any event that can occur when a player clicks on a BoardTile.
 */

/**
 * @author Peter Humphreys
 */
package com.mineslayers.entity;

import com.mineslayers.ui.BoardTile;

public abstract class BoardTileEvent {
    private BoardTileEventType type;
    private BoardTile tile;

    /**
     * Parameterized constructor, sets private fields to parameter inputs
     * @param tile of type BoardTile, type of type BoardTileEventType
     * @return
     */
    public BoardTileEvent(BoardTile tile, BoardTileEventType type)
    {
        this.tile = tile;
        this.type = type;
    }

    /**
     * Parameterized constructor, sets private fields to parameter inputs
     * @param
     * @return type of type BoardTileEventType
     */
    public BoardTileEventType getType()
    {
        return this.type;
    }

    /**
     * Abstract method that invoked the proper window in MineSlayerGame
     * @param
     * @return
     */
    public abstract void invoke();

    /**
     * Returns the BoardTileEvent's tile
     * @param
     * @return tile of type BoardTile
     */
    protected BoardTile getTile()
    {
        return this.tile;
    }
}
