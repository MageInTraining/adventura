/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura.game;

import eu.pedu.adv16w_fw.game_txt.INamed;

import java.util.Optional;



/*******************************************************************************
 * {@code ActionGoTo} instances process the commands
 * that move the player from the current space to the given neighboring one.
 * </p>
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2016-Winter
 */
class ActionMove extends STEM11AAction
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
//== VARIABLE INSTANCE FIELDS ==================================================



//##############################################################################
//== CONSTRUCTORS AND FACTORY METHODS ==========================================

    /***************************************************************************
     * Creates the action instance for
     * moving the player from the current space to the given neighboring one.
     */
    ActionMove()
    {
        super (STEM11Texts.aGOTO,
        "It moves the player to the neighboring room given in an argument.\n"
      + "But it requires so that this room would be a neighbor "
      + "of the current room,\n"
      + "Otherwise nothing will be done "
      + "and the error message will be reported.");
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Moves the player into the space (room) given in an argumenant.
     * Requires that this space has to be a neighbor of the current space,
     * otherwise nothing will be done and the command is reported as wrong.
     *
     * @param arguments Parameter of the command
     * @return The message text written after accomplishing the command
     */
    @Override
    public String execute(String... arguments)
    {
        if (arguments.length < 2) {
            return STEM11Texts.mNO_TARGET;
        }
        String destinationName = arguments[1];
        STEM11Space   currentRoom     =
                                    STEM11World.getInstance().getCurrentSpace();
        Optional<STEM11Space> oDestination = INamed.getO(destinationName,
                                                  currentRoom.getNeighbors());
        if (! oDestination.isPresent()) {
            return STEM11Texts.mNOT_NEIGHBOR + destinationName;
        }
        STEM11Space destinationRoom = oDestination.get();
        STEM11World.getInstance().setCurrentSpace(destinationRoom);
        return STEM11Texts.mMOVED + destinationRoom.getName();
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
