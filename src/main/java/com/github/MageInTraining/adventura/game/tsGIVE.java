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
class tsGIVE extends AAction
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
        return "Vec nejde dat"; 
    }
    String      itemDava = arguments[1];
    String      itemKomu = arguments[2];
    
    Bag              bag = Bag.getInstance();
    Optional<Item> oItem = INamed.getO(itemDava, bag.getItems());
        if (! oItem.isPresent()) {
            return "Neni v batohu " + itemDava;
        }
    Area   currentArea   = World.getInstance().getCurrentSpace();
    Optional<Item> oItem2 = INamed.getO(itemKomu, currentArea.getItems());
        if (! oItem.isPresent()) {
            return "Nelze dat vec " + itemKomu;    
    }
    if ( itemDava.equalsIgnoreCase("hracka") ) {
        if (itemKomu.equalsIgnoreCase("chlapec")){
        Item item = oItem.get();  
        bag.removeItem(item);
        Optional<Area> oChange = INamed.getO("Recepce",
                                 World.getInstance().getAllSpaces());     
        Area changedArea = oChange.get();
        String rec ="Recepcni";
         Optional<Item> oRecep = INamed.getO(rec, changedArea.getItems());
        Item recep = oRecep.get();
        changedArea.removeItem(recep);
        
        Item key1 = new Item("Klic_od_pracovny");
        Item key2 = new Item("Klic_od_garaze");
        changedArea.addItem(key1);
        changedArea.addItem(key2);
        
        return "Promluvil sis s chlapcem a dal jsi mu hracku";
        }
    }
    return "Nelze provest";   
    }

//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
