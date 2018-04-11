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
class tsTALK extends STEM11AAction
{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
private boolean mluvilRecepcni;
private boolean mluvilMajitel;
private boolean mluvilRodina;

    private static final tsTALK SINGLETON = new tsTALK();



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
    static tsTALK getInstance()
    {
        return SINGLETON;
    }
//== VARIABLE INSTANCE ATTRIBUTES ==============================================



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Vytvoří novou instanci daného příkazu.
     */
    tsTALK()
    {
        super ("tsTALK",
               "Promluvis si s osobou");
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    public String execute(String... arguments)
    {
    if (arguments.length < 2) {    
        return STEM11Texts.mNOT_TALK; 
    }
    
    String itemName      = arguments[1];
    Area   currentRoom   =
                                    STEM11World.getInstance().getCurrentSpace();
    Optional<STEM11Item> oItem = INamed.getO(itemName, currentRoom.getItems());
    if (! oItem.isPresent()) {
        return STEM11Texts.mNOT_HERE + itemName;
    }
    return STEM11Texts.mGIMMME_MONEY;
    }

//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================
//##############################################################################
//== NESTED DATA TYPES =========================================================
}
