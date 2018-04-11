/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-�?�
 */
package com.github.MageInTraining.adventura.game;

import eu.pedu.adv16w_fw.game_txt.INamed;
import java.util.Optional;


/*******************************************************************************
 * Instance třídy {@code EmptyAction} zpracovávají příkazy, které
 * ???.
 * <p>
 * Instance tříd akcí jsou efektivně jedináčci,
 * ale není třeba to v definici třídy explicitně zabezpečovat, protože vytvoření
 * a následnou správu všech akcí má starosti k tomu definovaný správce,
 * který zabezpečí, aby od každé třídy akce vznikla pouze jediná instance.
 * </p>
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2015-Podzim
 */
class tsMOVE extends STEM11AAction
{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
//== VARIABLE INSTANCE ATTRIBUTES ==============================================



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Vytvoří novou instanci daného příkazu.
     */
    tsMOVE()
    {
        super ("tsMOVE",
               "Přesune hráče do sousední místnosti zadané v parametru.");
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
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
        Area   currentRoom     =
                                    STEM11World.getInstance().getCurrentSpace();
        Optional<Area> oDestination = INamed.getO(destinationName,
                                                  currentRoom.getNeighbors());
        if (! oDestination.isPresent()) {
            return STEM11Texts.mNOT_NEIGHBOR + destinationName;
        }
        Area destinationRoom = oDestination.get();
        STEM11World.getInstance().setCurrentArea(destinationRoom);
        return STEM11Texts.mMOVED + destinationRoom.getName();
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
