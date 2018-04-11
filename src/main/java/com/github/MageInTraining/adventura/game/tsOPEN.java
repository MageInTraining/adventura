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
class tsOPEN extends AAction
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
    tsOPEN()
    {
        super ("tsOPEN",
               "Otevre dvere pokud mas k nim klic");
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
        if (arguments.length < 2) {
            return "Nelze otevrit";
        }
        String itemName      = arguments[1];
        Area   currentArea   = World.getInstance().getCurrentSpace();
        Optional<Item> oItem = INamed.getO(itemName, currentArea.getItems());
        if (! oItem.isPresent()) {
            return "Nelze otevrit " + itemName;
        }    
         Bag bag = Bag.getInstance();
         switch (itemName){
            case "dvere_do_pokoje":
                    itemName="Klic_od_pokoje";
                    Optional<Item> oItem2 = INamed.getO(itemName,
                                            bag.getItems());
                    if (! oItem2.isPresent()) {
                        return "Neni v batohu spravny klic";
                    }
                    Item item2 = oItem2.get();
                    bag.removeItem(item2);
                    setOpendPokoj(true);
                    return "Otevrel jsi dvere do pokoje";
                    
            case "dvere_do_pracovny":
                    itemName="Klic_od_pracovny";
                    Optional<Item> oItem3 = INamed.getO(itemName,
                                            bag.getItems());
                    if (! oItem3.isPresent()) {
                        return "Neni v batohu spravny klic";
                    }
                    Item item3 = oItem3.get();
                    bag.removeItem(item3);
                    setOpendPracovna(true);
                    return "Otevrel jsi dvere do pracovny";
            
            case "dvere_do_garaze":
                    itemName="Klic_od_garaze";
                    Optional<Item> oItem4 = INamed.getO(itemName,
                                            bag.getItems());
                    if (! oItem4.isPresent()) {
                        return "Neni v batohu spravny klic";
                    }
                    Item item4 = oItem4.get();
                    bag.removeItem(item4);
                    setOpendGaraz(true);
                    return "Otevrel jsi dvere do garaze";
            case "auto":
                    itemName="Klice_od_auta";
                    Optional<Item> oItem5 = INamed.getO(itemName,
                                            bag.getItems());
                    if (! oItem5.isPresent()) {
                        return "Neni v batohu spravny klic";
                    }
                    Item item5 = oItem5.get();
                    bag.removeItem(item5);
                    setOpendAuto(true);
                    return "Otevrel jsi dvere do auta";
            }
        return "nejde otevrit"; 
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
