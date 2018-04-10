/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura.game;

import java.util.Optional;



/*******************************************************************************
 * {@code ActionPickUp} instances process the commands
 * that take away items from the current space and puts them into the bag.
 * </p>
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2016-Winter
 */
class ActionPutDown extends STEM11AAction
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
     * Creates an action instance for taking
     * the item away from the bag and putting it into the current space.
     */
    ActionPutDown()
    {
        super (STEM11Texts.aPUT_DOWN,
        "Přesune předmět z tašky do současného prostoru");
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Removes the item given in an argumenant from the bag
     * and puts it into the current space (room).
     * But it requires that the given named item would be located in the bag.
     * Otherwise nothing will be done and the command is reported as wrong.
     *
     * @param arguments Parameters of the command
     * @return The message text written after accomplishing the command
     */
    @Override
    public String execute(String... arguments)
    {
        if (arguments.length < 2) {
            return STEM11Texts.mNO_PUT_ITEM;
        }
        String      itemName = arguments[1];
        STEM11Bag            bag = STEM11Bag.getInstance();
        Optional<STEM11Item> oItem = bag.getOItem(itemName);
        if (! oItem.isPresent()) {
            return STEM11Texts.mNOT_IN_BAG + itemName;
        }
        STEM11Item item = oItem.get();
        bag.removeItem(item);
        STEM11Space  currentRoom   =
                                    STEM11World.getInstance().getCurrentSpace();
        currentRoom.addItem(item);
        return STEM11Texts.mPUT_DOWN + item.getName();
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
