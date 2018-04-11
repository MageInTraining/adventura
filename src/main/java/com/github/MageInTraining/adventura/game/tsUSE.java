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
class tsUSE extends STEM11AAction
{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================

    private static final tsUSE SINGLETON = new tsUSE();

//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================

//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE ATTRIBUTES ==============================================
    /***************************************************************************
     * Tovární metoda vracející odkaz na jedninou existující instanci dané hry.
     *
     * @return Instance dané hry
     */
    static tsUSE getInstance()
    {
        return SINGLETON;
    }

//== VARIABLE INSTANCE ATTRIBUTES ==============================================



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Vytvoří novou instanci daného příkazu.
     */
    tsUSE()
    {
        super ("tsUSE",
               "Pouzijes vec");     
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
            return STEM11Texts.mNOTHING_TO_USE;
        }
        String itemName      = arguments[1];
        Area   currentSpace   =
                                    STEM11World.getInstance().getCurrentSpace();
        Optional<STEM11Item> oItem = INamed.getO(itemName, currentSpace.getItems());
        if (! oItem.isPresent()) {
            return STEM11Texts.mNOT_HERE + itemName;
        }
        if (STEM11Texts.BANKOMAT.equalsIgnoreCase(itemName)){
            STEM11Item item = new STEM11Item(STEM11Texts.PENIZE);
            STEM11Bag bag = STEM11Bag.getInstance();
            boolean added = bag.tryAddItem(item);
            if (added) {
//                currentSpace.removeItem(item);
                return STEM11Texts.mUSED;
            }
            else {
            return STEM11Texts.mBAG_FULL;
            }
        }

        if (STEM11Texts.KUCHARKA.equalsIgnoreCase(itemName)){
            STEM11State.setKnowRecipe(true);
            return STEM11Texts.mUSED;
        }

/*        if (STEM11Texts.TROUBA.equalsIgnoreCase(itemName) &&
            STEM11State.isElectricityPaid() == true &&
            STEM11State.knowRecipe() == true){
            
            STEM11Bag            bag = STEM11Bag.getInstance();
            Optional<STEM11Item> oItem3 = bag.tryAddItem(item);
            Optional<STEM11Item> oItem4 = bag.tryAddItem
                                                (STEM11Texts.CIBULE_A_BRAMBORY);
            if ( oItem3.isPresent() && oItem4.isPresent() ) {
                return STEM11Texts.mUSED;
            }
            else{
                return STEM11Texts.mNOT_HAVE;
            }
        }*/

        return STEM11Texts.mUNUSABLE;
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================
//##############################################################################
//== NESTED DATA TYPES =========================================================
}
