/**
 *A MerchantBoardTileEvent will cause the player to enter the Merchant Window, initialized with the stored Merchant instance.
 */

/**
 * @author Peter Humphreys
 */
package com.mineslayers.entity;

import com.mineslayers.ui.BoardTile;

public class MerchantBoardTileEvent extends BoardTileEvent
{
    //private Merchant merchant;
    //Empty for now...

    /**
     * Parameterized constructor, sets private fields to parameter inputs
     * @param tile of type BoardTile, type of type BoardTileEventType
     * @return
     */
    MerchantBoardTileEvent(BoardTile tile, BoardTileEventType type/*, Merchant merchant*/)
    {
        super(tile, type);
        //this.merchant = merchant;
    }

    /**
     *
     * @param
     * @return
     */
    public void invoke()
    {

    }

    /**
     * Sets the MerchantBoardTileEvent's merchant
     * @param merchant of type Merchant
     * @return
     */
    /*public void setMerchant(Merchant merchant)
    {
        this.merchant = merchant;
    }*/

    /**
     * Returns the MerchantBoardTileEvent's merchant
     * @param
     * @return merchant of type Merchant
     */
    /*public void setMerchant(Merchant merchant)
    {
        return this.merchant;
    }*/

}
