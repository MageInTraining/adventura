/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura.game;

import eu.pedu.adv16w_fw.game_txt.IItemContainer;
import eu.pedu.adv16w_fw.game_txt.INamed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;



/*******************************************************************************
 * Instances of the abstract class {@code AItemContainer} are
 * parent sub-objects of the objects performing as item containers.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2016-Winter
 */
abstract class AItemContainer extends ANamed implements IItemContainer
{
//== CONSTANT CLASS FIELDS =====================================================
//== VARIABLE CLASS FIELDS =====================================================



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE FIELDS ==================================================

    /** Names of items in the given space at the game beginning. */
    private final String[] itemNames;

    /** Names of items currently present in the given space. */
    private final Collection<STEM11Item> items;

    /** Immutable collection of items currently present in the given space,
     *  that continuously maps the {@link #items} collection content. */
    private final Collection<STEM11Item> exportedItems;



//== VARIABLE INSTANCE FIELDS ==================================================



//##############################################################################
//== CONSTRUCTORS AND FACTORY METHODS ==========================================

    /***************************************************************************
     * Creates the parent sub-object of the container of items
     * with the given names of its initial items.
     *
     * @param name      Name of the child object
     * @param itemNames Names of items in the container at the game beginning
     */
    AItemContainer(String name, String... itemNames)
    {
        super(name);
        this.itemNames     = itemNames;
        this.items         = new ArrayList<>();
        this.exportedItems = Collections.unmodifiableCollection(items);
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Returns the item with the given name wrapped into
     * an {@link Optional}{@code <}{@link Item}{@code >}.
     *
     * @return The item with the given name wrapped into
     *         an {@link Optional}{@code <}{@link Item}{@code >}
     */
    public Optional<STEM11Item> getOItem(String name)
    {
        return INamed.getO(name, items);
    }


    /***************************************************************************
     * Returns a collection of items located in the given container.
     *
     * @return Collection of items located in the given container
     */
    @Override
    public Collection<STEM11Item> getItems()
    {
        return exportedItems;
    }



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Adds the given item into the container.
     *
     * @param item The item that has to be added into the container
     */
    protected void addItem(STEM11Item item)
    {
        items.add(item);
    }


    /***************************************************************************
     * Cleans the container and saves into it the items
     * located in the given container at the game beginning.
     */
    protected void initializeItems()
    {
        items.clear();
        Arrays.stream(itemNames)
              .map(STEM11Item::new)
              .forEach(items::add);
    }


    /***************************************************************************
     * Removes the given item from this container.
     *
     * @param item Item to be removed
     */
    void removeItem(STEM11Item item)
    {
        items.remove(item);
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
