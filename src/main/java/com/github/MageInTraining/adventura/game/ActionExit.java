/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura.game;



/*******************************************************************************
 * {@code ActionExit} instances process the commands
 * which require the prematured game termination.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2016-Winter
 */
class ActionExit extends STEM11AAction
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
     * prematured termination of the game.
     */
    ActionExit()
    {
        super (STEM11Texts.aEXIT,
               "Poděkuje a ukončí hru");
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * The method ends the game and says thanks to the player.
     *
     * @param arguments Parameters of the command - not used here
     * @return The message text written after accomplishing the command
     */
    @Override
    public String execute(String... arguments)
    {
        STEM11AAction.stopGame();
        return STEM11Texts.mGOOD_BYE;
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
