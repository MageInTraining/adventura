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
class ActionGive extends STEM11AAction
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
    ActionGive()
    {
        super(STEM11Texts.aGIVE,
        "Dá zadané postavě zadaný předmět.");
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
    if (arguments.length < 3) {    
        return STEM11Texts.mNOT_GIVE; 
    }
    
    String      itemCommodity = arguments[1];
    String      itemReciver = arguments[2];
    
    STEM11Bag                bag = STEM11Bag.getInstance();
    STEM11Space   currentSpace   = STEM11World.getInstance().getCurrentSpace();
    

    Optional<STEM11Item> oItem1 = bag.getOItem(itemCommodity);
    Optional<STEM11Item> oItem2 = currentSpace.getOItem(itemReciver);

    if (! oItem1.isPresent() && ! oItem2.isPresent()){
        return STEM11Texts.mNOT_HAVE;
    }
    if ( STEM11Texts.OBJEDNAVKA_DO_KNIHKUPECTVI.equalsIgnoreCase(itemCommodity)
        && 
        STEM11Texts.PRODAVACKA.equalsIgnoreCase(itemReciver)){
        
            STEM11Item kniha = new STEM11Item(STEM11Texts.KUCHARKA);
            boolean added = bag.tryAddItem(kniha);
            if (added) {
                STEM11Item objednavka = oItem1.get();
                bag.removeItem(objednavka);
                return STEM11Texts.mGIVE + STEM11Texts.PRODAVACKA
                        + " " + STEM11Texts.OBJEDNAVKA_DO_KNIHKUPECTVI
                        + STEM11Texts.mRECIVED + STEM11Texts.KUCHARKA;
            }
            else {
                return STEM11Texts.mBAG_FULL;
            }
    }

    if ( STEM11Texts.PENIZE.equalsIgnoreCase(itemCommodity)
        && 
        STEM11Texts.UREDNIK.equalsIgnoreCase(itemReciver)){
        
        STEM11State.setElectricityPaid(true);
        STEM11Item penize = oItem1.get();
        bag.removeItem(penize);
        return STEM11Texts.mPAID;
    }
    return STEM11Texts.mNOT_HAVE;
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
