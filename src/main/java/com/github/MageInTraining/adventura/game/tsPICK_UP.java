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
class tsPICK_UP extends STEM11AAction
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
    tsPICK_UP()
    {
        super ("tsPICK_UP",
               "Přesune zadaný objekt z prostoru do batohu.");
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Removes the item given in an argumenant from the current space (room)
     * and puts it into the bag.
     * But it requires so that the item with the given name<br>
     * a) would be located in the current space,<br>
     * b) could be picked up and<br>
     * c) would fit into the bag.<br>
     * Otherwise nothing will be done and the command is reported as wrong.
     *
     * @param arguments Parameters of the command
     * @return The message text written after accomplishing the command
     */
    @Override
    public String execute(String... arguments)
    {
        if (arguments.length < 2) {
            return STEM11Texts.mNO_TAKE_ITEM;
        }
        String itemName      = arguments[1];
        Area   currentRoom   =
                                    STEM11World.getInstance().getCurrentSpace();
        Optional<STEM11Item> oItem = INamed.getO(itemName, currentRoom.getItems());
        if (! oItem.isPresent()) {
            return STEM11Texts.mNOT_HERE + itemName;
        }
        STEM11Item item = oItem.get();
        STEM11Bag bag = STEM11Bag.getInstance();
        boolean added = bag.tryAddItem(item);
        if (added) {
            currentRoom.removeItem(item);
            return STEM11Texts.mTAKEN + itemName;
        }
        else {
            return STEM11Texts.mBAG_FULL;
        }
    }




//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
