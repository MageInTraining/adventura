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
class tsGIVE extends STEM11AAction
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
    tsGIVE()
    {
        super ("tsGIVE",
               "Preda urcitou vec urcite osobe");
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Metoda realizující reakci hry na zadání daného příkazu.
     * Počet parametrů je závislý na konkrétním příkazu,
     * např. příkazy <i>konec</i> a <i>nápověda</i> nemají parametry,
     * příkazy <i>jdi</i> a <i>seber</i> mají jeden parametr
     * příkaz <i>použij</i> muže mít dva parametry atd.
     *
     * @param arguments Parametry příkazu;
     *                  jejich počet muže byt pro každý příkaz jiný
     * @return Text zprávy vypsané po provedeni příkazu
     */
    @Override
    public String execute(String... arguments)
    {
     if (arguments.length < 3) {    
        return STEM11Texts.mNOT_GIVE; 
    }
     String      itemCommodity = arguments[1];
     String      itemReciver = arguments[2];
    
    STEM11Bag              bag = STEM11Bag.getInstance();
    Optional<STEM11Item> oItem = INamed.getO(itemCommodity, bag.getItems());
        if (! oItem.isPresent()) {
            return STEM11Texts.mNOT_HAVE + itemCommodity;
        }
    Area   currentArea   = STEM11World.getInstance().getCurrentSpace();
    Optional<STEM11Item> oItem2 = INamed.getO(itemReciver, currentArea.getItems());
        if (! oItem.isPresent()) {
            return "Nelze dat vec " + itemReciver;    
    }
        if ( STEM11Texts.OBJEDNAVKA_DO_KNIHKUPECTVI.equalsIgnoreCase(itemCommodity)
                && 
                STEM11Texts.PRODAVACKA.equalsIgnoreCase(itemReciver)){
                
                    STEM11Item kniha = new STEM11Item(STEM11Texts.KUCHARKA);
                    boolean added = bag.tryAddItem(kniha);
                    if (added) {
                        STEM11Item objednavka = oItem.get();
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
                STEM11Item penize = oItem.get();
                bag.removeItem(penize);
                return STEM11Texts.mPAID;
            }
            return STEM11Texts.mNOT_HAVE;
            }

//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
