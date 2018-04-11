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
class tsTALK extends AAction
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
            return "Nelze mluvit";
        }
        String itemName      = arguments[1];
        Area   currentArea   = World.getInstance().getCurrentSpace();
        Optional<Item> oItem = INamed.getO(itemName, currentArea.getItems());
        if (! oItem.isPresent()) {
            return "Nelze mluvit s " + itemName;
        }    
        Item item = oItem.get();
        
        switch (itemName) {    
            case "recepcni":
            if (! mluvilRecepcni){
                Item klic = new Item("Klic_od_pokoje");
                Bag bag = Bag.getInstance();
                boolean added = bag.tryAddItem(klic);
                if (added) {
                    mluvilRecepcni=true;
                    return "Promluvil jsis s recepcnim a ten ti dal pokoj";
                } else {
                    return "Batoh plny";
                }
            }   else{
                return "Recepcni te ignoruje"; 
            }
        
            case "chlapec":
            return "Maminka mi rikala abych nemluvim s cizimy lidmy";
            
            case "majitel":
            if (! mluvilMajitel){
                mluvilMajitel = true;
                return "Vypadate unavene jdete si odpocinout";
            }else{
                return "Majitel vas ignoruje";
            }
            
            case "rodina":
            if (! mluvilRodina){
                mluvilRodina = true;
                return "Ten by sel taky Karle";
            }else{
                return "Rodice chlapce te ignoruji";
            }
    }
    return "nelze mluvit"; 
    }

//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================
    void initialize(){
        mluvilRecepcni= false;
        mluvilMajitel = false;
        mluvilRodina  = false;
    }
//##############################################################################
//== NESTED DATA TYPES =========================================================
}
