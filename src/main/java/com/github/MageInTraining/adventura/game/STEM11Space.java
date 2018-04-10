/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura.game;

import eu.pedu.adv16w_fw.game_txt.ISpace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;



/*******************************************************************************
 * Instances of the {@code EmptySpace} class represent the spaces in the game.
 *
 * We can take the space visiting as a partial goal,
 * which the player tries to reach.
 * The spaces can be rooms, planets, life stages etc.
 * The spaces can contain various items.that may help user to reach the goal.
 * Each space knows its current neighboring spaces and it knows
 * which items it currently contains.
 * The neighbors as well as the contained items can change during the game.
 * <p>
 * In this program the spaces are place in the city taht you visit
 *
 * @author  Milan STEHLÍK
 * @version 2017-Winter
 */
class STEM11Space extends AItemContainer implements ISpace
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

    /** Names of neighbors of the given space at the game beginning. */
    private final String[] neighborNames;

    /** Current neighbors of the given space. */
    private final Collection<STEM11Space> neighbors;

    /** Immutable collection of current neighbors of the given space,
     *  that continuously maps the {@link #neighbors} collection content. */
    private final Collection<STEM11Space> exportedNeighbors;



//== VARIABLE INSTANCE FIELDS ==================================================



//##############################################################################
//== CONSTRUCTORS AND FACTORY METHODS ==========================================

    /***************************************************************************
     * Creates a new space with the given name and
     * with the given names of its initial neighbors and items.
     *
     * @param name          Name of the given space
     * @param neighborNames Names of space neighbors at the game beginning
     * @param itemNames     Names of items in the space at the game beginning
     */
    STEM11Space(String name, String[] neighborNames, String... itemNames)
    {
        super(name, itemNames);
        this.neighborNames    = neighborNames;
        this.neighbors        = new ArrayList<>();
        this.exportedNeighbors= Collections.unmodifiableCollection(neighbors);
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Returns the collection of current neighbors of this space, i.e. the
     * collection of spaces, to which we can move from this space with the
     * command of the {@link eu.pedu.adv16w_fw.scenario.TypeOfStep#tsMOVE
     * TypeOfStep.tsMOVE} type.
     *
     * @return Collection of neighbors
     */
    @Override
    public Collection<STEM11Space> getNeighbors()
    {
        return exportedNeighbors;
    }



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Method initializing the given space.
     * Based on the names remembered by the constructor
     * it initializes the neighbors of the given space and its items.
     */
    void initialize()
    {
        initializeItems();
        initializeNeighbors();
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================

    /***************************************************************************
     * Cleans the collection {@link #neighbors} and saves into it the items representing
     * the spaces neighboring with the given space at the game beginning.
     */
    private void initializeNeighbors()
    {
        STEM11World apartment = STEM11World.getInstance();
        neighbors.clear();
        Arrays.stream(neighborNames)
              .map(apartment::getORoom)
              .map(Optional<STEM11Space>::get)
              .forEach(neighbors::add);
    }



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
