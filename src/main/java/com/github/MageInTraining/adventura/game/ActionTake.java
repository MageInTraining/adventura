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
class ActionTake extends STEM11AAction
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
    ActionTake()
    {
        super (STEM11Texts.aTAKE,
        "Přesune předmět ze současného prostoru do tašky.\n"
      + "Přesouvá pouze přesouvatelné předměty.\n"
      + "Jinak se nic nestane "
      + "a bude nahlášena chyba.");
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
        STEM11Space   currentRoom   =
                                    STEM11World.getInstance().getCurrentSpace();
        Optional<STEM11Item> oItem = currentRoom.getOItem(itemName);
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
