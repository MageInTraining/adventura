/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura.game;

import java.util.Collection;
import java.util.stream.Collectors;

import eu.pedu.adv16w_fw.game_txt.INamed;

import java.util.Optional;


/*******************************************************************************
 * Instances of the {@code EmptyAction} class process the commands, which
 * ???.
 * <p>
 * Instances of the action classes are effectively singletons,
 * however we do not need to ensure it explicitely, because for their creation
 * and further management the specified action manager takes care
 * which ensures the only instance of each such class.
 * </p>
 *
 * @author  Milan STEHLÍK
 * @version 2017-Winter
 */
class ActionBuy extends STEM11AAction
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
     * writing the help with names and brief descriptions of accessible commands
     */
    ActionBuy()
    {
        super(STEM11Texts.aBUY,
        "Koupí zadaný předmět, pokud má hráč v tašce peníze.");
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Processes the command composed from the given words
     * and returns the game answer to the user.
     * Number of word depends on particular action, however it must be
     * at least one, because the zeroth element contains the action name.
     * The remaining words are arguments of this action and they may differ:
     * the actions of <i>end</i> and <i>help</i> type do not have any,
     * the actions of <i>go</i> and <i>take</i> type have one,
     * the actions of <i>apply</i> type ) can have two (e.g. apply key lock)
     * or three (e.g. apply key to lock) etc.
     *
     * @param arguments Action arguments –
     *                  their number can be different for each action,
     *                  but for all execution of the same action is the same
     * @return The answer of the game after processing the command
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
        Optional<STEM11Item> oItem1 = currentRoom.getOItem(itemName);
        if (! oItem1.isPresent()) {
            return STEM11Texts.mNOT_HERE + itemName;
        }
        if ( STEM11Texts.KURE.equalsIgnoreCase(itemName) ||
             STEM11Texts.CIBULE_A_BRAMBORY.equalsIgnoreCase(itemName)){
            STEM11Bag            bag = STEM11Bag.getInstance();
            Optional<STEM11Item> oItem2 = bag.getOItem(STEM11Texts.PENIZE );
            if (! oItem2.isPresent()) {
                return STEM11Texts.mNOT_HAVE;
            }
            STEM11Item penize = oItem2.get();
            bag.removeItem(penize);
            STEM11Item nakup = oItem1.get();
            bag.tryAddItem(nakup);
            return STEM11Texts.mBOUGHT + itemName;
            
        }
        return STEM11Texts.mANCO;
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
