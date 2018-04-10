/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura.game;

import eu.pedu.adv16w_fw.game_txt.INamed;
import eu.pedu.adv16w_fw.game_txt.IWorld;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;


import static com.github.MageInTraining.adventura.game.STEM11Texts.*;
import static com.github.MageInTraining.adventura.game.STEM11Item.*;



/*******************************************************************************
 * An instance of the {@code STEM11World} class represents the game world.
 * It should be defined as a singleton.
 * It is responsible for arrangement of individual spaces and keeps information,
 * in which space the player is just situated.
 * <p>
 * In this game the world is ...
 *
 * @author  Milan STEHLÍK
 * @version 2017-Winter
 */
class STEM11World implements IWorld
{
//== CONSTANT CLASS FIELDS =====================================================

    /** The only instance (singleton) of this world. */
    private static final STEM11World SINGLETON = new STEM11World();



//== VARIABLE CLASS FIELDS =====================================================



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE FIELDS ==================================================

    /** The collection of all spaces (mostly rooms) in this world. */
    private final Collection<STEM11Space> rooms;

    /** The immutable collection of all spaces (mostly rooms) in this world
     *  that continuously maps the {@link #rooms} collection content. */
    private final Collection<STEM11Space> exportedRooms;

    /** Room in which the game begins. */
    private final STEM11Space startingRoom;



//== VARIABLE INSTANCE FIELDS ==================================================

    /** The space, in which the player is just situated */
    private STEM11Space currentSpace;


//##############################################################################
//== CONSTRUCTORS AND FACTORY METHODS ==========================================

    /***************************************************************************
     * The factory method returning the only existing instance of the game.
     *
     * @return The only instance of the given game
     */
    static STEM11World getInstance()
    {
        return SINGLETON;
    }


    /***************************************************************************
     * The private constructor creating the only instance of the space world.
     * Within this manager definition it creates all game spaces.
     */
    private STEM11World()
    {
        rooms         = new ArrayList<>();
        exportedRooms = Collections.unmodifiableCollection(rooms);
        startingRoom  = new STEM11Space(BYT,
                            new String[] {SIDLISTE},
                            PENIZE, OBJEDNAVKA_DO_KNIHKUPECTVI, TROUBA);
        rooms.add(startingRoom);
        rooms.add(new STEM11Space(SIDLISTE,
                           new String[] {BYT, CENTRUM, OBCHODAK}
                           ));
        rooms.add(new STEM11Space(CENTRUM,
                           new String[] {SIDLISTE, OBCHODAK, KNIHKUPECTVI,
                                         ENERGETICKA_SPOLECNOST},
                           BANKOMAT));
        rooms.add(new STEM11Space(KNIHKUPECTVI,
                           new String[] {CENTRUM},
                           PRODAVACKA));
        rooms.add(new STEM11Space(ENERGETICKA_SPOLECNOST,
                           new String[] {CENTRUM},
                           UREDNIK));
        rooms.add(new STEM11Space(OBCHODAK,
                           new String[] {SIDLISTE, CENTRUM, MASNA,
                                         OVOCE_A_ZELENINA},
                           BANKOMAT));
        rooms.add(new STEM11Space(MASNA,
                           new String[] {OBCHODAK},
                           KURE));
        rooms.add(new STEM11Space(OVOCE_A_ZELENINA,
                           new String[] {OBCHODAK},
                           CIBULE_A_BRAMBORY));
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Returns the collection of all spaces of the game.
     *
     * @return Collection of all spaces performing in the game
     */
    @Override
    public Collection<STEM11Space> getAllSpaces()
    {
        return exportedRooms;
    }


    /***************************************************************************
     * Returns the current space,
     * i.e. to the space in which the player is just situated.
     *
     * @return The space in which the player is just situated
     */
    @Override
    public STEM11Space getCurrentSpace()
    {
        return currentSpace;
    }

    /***************************************************************************
     * Sets the given space as the current one,
     * i.e. the space, in which the player is just situated.
     *
     * @param destinationRoom The set space
     */
    void setCurrentSpace(STEM11Space destinationRoom)
    {
        currentSpace = destinationRoom;
    }


    /***************************************************************************
     * Returns the space (room) with the given name
     * wrapped in the {@link Optional}.
     *
     * @param name Name of the required space
     * @return The wrapped space with the given name
     */
    public Optional<STEM11Space> getORoom(String name)
    {
        Optional<STEM11Space> result = INamed.getO(name, rooms);
        return result;
    }



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * The method initializing the game world.
     * Firstly it initializes all spaces
     * and then it sets the initial current space.
     */
    void initialize()
    {
        rooms.forEach(STEM11Space::initialize);
        currentSpace = startingRoom;
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
