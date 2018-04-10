/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura.game;

import java.util.Optional;



/*******************************************************************************
 * {@code ActionTake} instances process the commands
 * that take away items from the current space and put them to the bag.
 * </p>
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2016-Winter
 */
class ActionUse extends STEM11AAction
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
     * Creates an action instance for
     * taking an item away from the current space and putting it into the bag.
     */
    ActionUse()
    {
        super (STEM11Texts.aUSE,
        "Použije předmět předem daným způsobem. Předmět nesmí být v tašce");
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
        STEM11Space   currentSpace   =
                                    STEM11World.getInstance().getCurrentSpace();
        Optional<STEM11Item> oItem1 = currentSpace.getOItem(itemName);
        if (! oItem1.isPresent()) {
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

        if (STEM11Texts.TROUBA.equalsIgnoreCase(itemName) &&
            STEM11State.isElectricityPaid() == true &&
            STEM11State.knowRecipe() == true){
            
            STEM11Bag            bag = STEM11Bag.getInstance();
            Optional<STEM11Item> oItem3 = bag.getOItem(STEM11Texts.KURE );
            Optional<STEM11Item> oItem4 = bag.getOItem
                                                (STEM11Texts.CIBULE_A_BRAMBORY);
            if ( oItem3.isPresent() && oItem4.isPresent() ) {
                return STEM11Texts.mUSED;
            }
            else{
                return STEM11Texts.mNOT_HAVE;
            }
        }

        return STEM11Texts.mUNUSABLE;
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
