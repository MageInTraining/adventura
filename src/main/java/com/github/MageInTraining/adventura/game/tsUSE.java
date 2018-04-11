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
class tsUSE extends AAction
{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
private  boolean zvonil;
private  boolean volal;
private  boolean spal ;

    private static final tsUSE SINGLETON = new tsUSE();

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
    static tsUSE getInstance()
    {
        return SINGLETON;
    }

//== VARIABLE INSTANCE ATTRIBUTES ==============================================



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Vytvoří novou instanci daného příkazu.
     */
    tsUSE()
    {
        super ("tsUSE",
               "Pouzijes vec");     
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
            return "Nelze pouzit";
        }
        String itemName      = arguments[1];
        Area   currentArea   = World.getInstance().getCurrentSpace();
        Optional<Item> oItem = INamed.getO(itemName, currentArea.getItems());
        if (! oItem.isPresent()) {
            return "Nelze pouzit " + itemName;
        }    
        Item item = oItem.get();
        
        switch (itemName) {
            case "zvonek":
            if (! zvonil){
                zvonil = true;    
                Item recep = new Item("Recepcni");
                currentArea.addItem(recep);
                return "Prisel recepcni";
            }
                else {
                    return "Nic se nestalo";
            }
            
            case "telefon":
            if (! volal){
                Item naradi = new Item("Naradi");
                Bag bag = Bag.getInstance();
                boolean added = bag.tryAddItem(naradi);
                if (added) {
                    volal = true;  
                    return "Zavolal jsi odtahovku";
                } else {
                    return "Batoh plny";
                }
            }
                else{
                    return "Telefon je hluchy";
            }
           
            case "auto":
            if (! getOpendAuto()){
            return "Auto neni otevreno";
            }
            AAction.stopGame();
            return "Odejel jsi s autem pryc GAME OVER";
            
            case "postel":
            if (! spal){
            Optional<Area> oDestination = INamed.getO("Jidelna",
                                          World.getInstance().getAllSpaces());
            Area destinationArea = oDestination.get();   
            World.getInstance().setCurrentArea(destinationArea);
            Item maj = new Item("Majitel");
            Item rod = new Item("Rodina");
            destinationArea.addItem(maj);
            destinationArea.addItem(rod);
            spal = true;
            return "Sel jsi spat a rano ses presunul do jidelny"; 
        }else{
             AAction.stopGame();
          return "Prepadli te ve spanku a objetovali starym bohum GAME OVER";
        }
        }
        return "Nelze pouzit";
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================

    void initialize(){
        zvonil = false;
        volal  = false;
        spal   = false;
    
    }

//##############################################################################
//== NESTED DATA TYPES =========================================================
}
