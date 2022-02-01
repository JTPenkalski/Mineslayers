/**
 *An ItemBoardTileEvent will reveal an item on the gameboard which the player can choose to pick up and add to their inventory.
 */

/**
 * @author Peter Humphreys
 */
package com.mineslayers.entity;

import com.mineslayers.id.ImageID;
import com.mineslayers.ui.BoardTile;

public class ItemBoardTileEvent extends BoardTileEvent
{
    private Item item;

    /**
     * Parameterized constructor, sets private fields to parameter inputs
     * @param tile of type BoardTile, type of type BoardTileEventType
     * @return
     */
    public ItemBoardTileEvent(BoardTile tile, BoardTileEventType type, Item item)
    {
        super(tile, type);
        this.item = item;
    }

    /**
     *
     * @param
     * @return
     */
    public void invoke()
    {
        getTile().setDetailImage(ImageID.BOARD_ITEM);
    }

    /**
     * Sets the ItemBoardTileEvent's item
     * @param item of type Item
     * @return
     */
    public void setItem(Item item)
    {
        this.item = item;
        getTile().setDetailImage(item == null ? null : item.getImage());
    }

    /**
     * Returns the ItemBoardTileEvent's item
     * @param
     * @return item of type Item
     */
    public Item getItem()
    {
        return this.item;
    }
}
