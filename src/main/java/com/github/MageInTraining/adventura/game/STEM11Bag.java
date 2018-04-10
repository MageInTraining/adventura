/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura.game;

import eu.pedu.adv16w_fw.game_txt.IBag;



/*******************************************************************************
 * Instance of the {@code STEM11Bag} class represents the handbag,
 * to which the players store the items picked up in individual spaces,
 * so that they could be moved to other spaces and/or used.
 * The disposal site has a final capacity defining the maximal permitted
 * sum of weights of items occuring in the repository.
 * <p>
 * In this game the bag is a handbag
 * with capacity 3
 * The item weight represents the weight and bulk of the item
 *
 * @author  Milan STEHLÍK
 * @version 2017-Winter
 */
class STEM11Bag extends AItemContainer implements IBag
{
//== CONSTANT CLASS FIELDS =====================================================

    /** Capacity of the bag. */
    static final int CAPACITY = 3;

    /** The only instance of the bag in the game. */
    private static final STEM11Bag SINGLETON = new STEM11Bag();



//== VARIABLE CLASS FIELDS =====================================================



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE FIELDS ==================================================
//== VARIABLE INSTANCE FIELDS ==================================================

    /** Free capacity of the bag. */
    private int remains;



//##############################################################################
//== CONSTRUCTORS AND FACTORY METHODS ==========================================

    /***************************************************************************
     * Factory method returning the only existing instance of the game.
     *
     * @return The instance of the given game
     */
    static STEM11Bag getInstance()
    {
        return SINGLETON;
    }


    /***************************************************************************
     */
    STEM11Bag()
    {
        super("Taška");
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Returns the bag capacity, i.e. the maximal permitted sum
     * of weights of items, that can be put into the bag at the same time.
     *
     * @return Capacity of the bag
     */
    @Override
    public int getCapacity()
    {
        return CAPACITY;
    }


    /***************************************************************************
     * Returns information if the bag is full,
     * or if some item can be still put in.
     *
     * @return {@code true} if it is full, {@code false} otherwise
     */
    boolean isFull()
    {
        return remains == 0;
    }



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * The method initializing the bag.
     * As the player of this game has an empty bag at the game beginning,
     * this method can only clean the {@link #items} collection.
     */
    void initialize()
    {
        initializeItems();
        remains = CAPACITY;
    }


    /***************************************************************************
     * Removes the given item from the bag
     * and increases the free capacity of the bag.
     *
     * @param item Item taken away
     */
    @Override
    void removeItem(STEM11Item item)
    {
        super.removeItem(item);
        remains += item.getWeight();
    }


    /***************************************************************************
     * If the given item fits to the bag, it adds it;
     * after that it returns the message on the result.
     *
     * @param item The item that has to be added into the bag
     * @return The message on the result: {@code true} = was added,
     *         {@code false} = was not added
     */
    boolean tryAddItem(STEM11Item item)
    {
        if (item.getWeight() > remains) {
            return false;
        }
        addItem(item);
        remains -= item.getWeight();
        return true;
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
