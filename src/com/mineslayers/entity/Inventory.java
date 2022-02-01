/**
 *The inventory stores all items the player currently has. The player can then choose to manage them as necessary,
 * by using or trashing them.
 */

/**
 * @author Peter Humphreys
 */
package com.mineslayers.entity;

import java.util.ArrayList;

public class Inventory
{
    public final static int MAX_FLAGS = 10;
    public final static int CAPACITY = 6;
    private int count;
    private int currentSelectionIndex;
    private Item[] items;
    private ArrayList<FlagItem> flags;

    public Inventory()
    {
        count = 0;
        currentSelectionIndex = 0;
        items = new Item[CAPACITY];
        flags = new ArrayList<>(MAX_FLAGS);
    }

    /**
     * Returns the Item at the specified index
     * @param
     * @return items[index] of type Item
     */
    public Item getItem(int index)
    {
        //Make sure to check for !isEmpty() before calling this method!!!
        return items[index];
    }

    /**
     * Returns the Inventory's flagCount
     * @param
     * @return flagCount of type int
     */
    public int getFlagCount() { return this.flags.size(); }

    /**
     * Adds a flag to the flags array if not full and returns true, otherwise returns false
     * @param flag of type FlagItem
     * @return whether a flag was added, of type boolean
     */
    public boolean addFlag(FlagItem flag)
    {
        if (!flagsFull())
        {
            flags.add(flag);
            return true;
        }
        return false;
    }

    /**
     * If there are flags in the flags array, removes one, and returns true, else returns false
     * @param
     * @return Whether flag was succesfully removed, of type boolean
     */
    public boolean useFlag()
    {
        if (!flagsEmpty())
        {
            flags.remove(flags.size() - 1);
            return true;
        }
        return false;
    }

    /**
     * Returns true if no flags exist in flags array, false otherwise
     * @param
     * @return flagCount == 0 of type boolean
     */
    public boolean flagsEmpty() {return flags.size() == 0;}

    /**
     * Returns true if flags array is full, false otherwise
     * @param
     * @return flagCount == MAX_FLAGS of type boolean
     */
    public boolean flagsFull() {return flags.size() == MAX_FLAGS;}

    /**
     * Sets the Inventory's currentSelectionIndex to currentSelectionIndex
     * @param currentSelectionIndex of type int
     * @return
     */
    public void setCurrentSelectionIndex(int currentSelectionIndex)
    {
        this.currentSelectionIndex = currentSelectionIndex;
    }

    /**
     * Returns the Inventory's currentSelectionIndex to currentSelectionIndex
     * @param
     * @return currentSelectionIndex of type int
     */
    public int getCurrentSelectionIndex()
    {
        return this.currentSelectionIndex;
    }

    /**
     * If the array is not empty, calls the item's use() method and removes from the inventory
     * @param player of type Player
     * @return
     */
    public void useCurrentItem(Player player)
    {
        if (!isEmpty())
        {
            items[currentSelectionIndex].use(player);
            removeItem(currentSelectionIndex);
        }
    }

    /**
     * Tries to add an item to the inventory, increments count and returns true.  Returns false otherwise
     * @param item of type Item
     * @return whether item was succesfully added, of type boolean
     */
    public boolean addItem(Item item)
    {
        if (!isFull())
        {
            items [getAddIndex()] = item;
            count ++;
            return true;
        }
        return false;
    }

    /**
     * Returns whether or not the specified item exists in the inventory
     * @param item of type Item
     * @return whether or not the specified item exists in items[], of type boolean
     */
    public boolean contains(Item item)
    {
        for (int i = 0; i < items.length; i ++)
        {
            if (items[i].equals(item))
                return true;
        }
        return false;
    }

    /**
     * Removes the specified item if it exists, decrements count and returns true, else returns false
     * @param item of type Item
     * @return whether or not the item was removed of type boolean
     */
    public boolean removeItem(Item item) { return removeItem(getRemoveIndex(item)); }

    /**
     * Removes item at specified index from inventory, decrements count and returns true, else returns false
     * @param index of type int
     * @return whether or not item was removed, of type boolean
     */
    public boolean removeItem(int index)
    {
        //Out of bounds
        if (index >= CAPACITY || index < 0)
            return false;
        //valid item
        if (items[index] != null)
        {
            items[index] = null;
            count --;
            return true;
        }
        return false;
    }

    /**
     * Returns true if the Inventory is empty, false otherwise
     * @param
     * @return count == 0 of type boolean
     */
    public boolean isEmpty()
    {
        return count == 0;
    }

    /**
     * Returns true if the Inventory is full, false otherwise
     * @param
     * @return count == CAPACITY of type boolean
     */
    public boolean isFull()
    {
        return count == CAPACITY;
    }

    /**
     * Returns Inventory's count
     * @param
     * @return count of type int
     */
    public int getCount()
    {
        return this.count;
    }

    /**
     * Returns index to add item to, -1 if full
     * @param
     * @return index to add item to of type int
     */
    private int getAddIndex()
    {
        //Return index of first open item slot
        for (int i = 0; i < CAPACITY; i++)
        {
            if (items[i] == null)
                return i;
        }
        //Return -1 if no empty index found
        return -1;
    }

    /**
     * Returns Returns index of specified item, -1 if not found
     * @param item of type Item
     * @return index of item of type int
     */
    private int getRemoveIndex(Item item)
    {
        //Return index of item if found
        for (int i = 0; i < CAPACITY; i++)
        {
            if (items [i] != null && items[i].equals(item))
                return i;
        }
        //Return -1 if item not found
        return -1;
    }
}
