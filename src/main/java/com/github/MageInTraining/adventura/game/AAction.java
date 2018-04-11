/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-�?�
 */
package com.github.MageInTraining.adventura.game;

import eu.pedu.adv16w_fw.game_txt.IAction;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



/*******************************************************************************
 * Třída {@code EmptyAAction} je společným rodičem všech tříd, jejichž instance
 * mají na starosti interpretaci příkazů zadávaných uživatelem hrajícím hru.
 * Název spouštěné akce bývá většinou první slovo řádku zadávaného
 * z klávesnice a další slova pak bývají interpretována jako parametry.
 * <p>
 * Můžete ale definovat příkaz, který odstartuje konverzaci
 * (např. s osobou přítomnou v místnosti) a tím přepne systém do režimu,
 * v němž se zadávané texty neinterpretují jako příkazy,
 * ale předávají se definovanému objektu až do chvíle,
 * kdy uživatel rozhovor ukončí a objekt rozhovoru přepne hru zpět
 * do režimu klasických příkazů.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2015-Podzim
 */
abstract class AAction extends STEM11KureGame implements IAction
{
//== CONSTANT CLASS ATTRIBUTES =================================================
//== VARIABLE CLASS ATTRIBUTES =================================================
private static boolean isAlive;



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
static {
    isAlive = false;
    OpendPokoj = false;
    OpendPracovna = false;
    OpendGaraz = false;
    OpendAuto = false;
    NAME_2_ACTION = new HashMap<>();
    new tsPICK_UP();
    new tsMOVE();
    new tsHELP();
    new tsPUT_DOWN();
    new tsEND();
   // new tsUSE();
    new tsREPAIR();
    new tsGIVE();
   // new tsTALK();
    new tsOPEN();
}


//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE ATTRIBUTES ==============================================

    /** Stručný popis dané akce. */
    private final String description;
    private static final Map<String, AAction> NAME_2_ACTION;


//== VARIABLE INSTANCE ATTRIBUTES ==============================================



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

//== ABSTRACT METHODS ==========================================================

   /***************************************************************************
     * Vrátí informaci o tom, je-li hra aktuálně spuštěná.
     * Spuštěnou hru není možno pustit znovu.
     * Chceme-li hru spustit znovu, musíme ji nejprve ukončit.
     *
     * @return Je-li hra spuštěná, vrátí {@code true},
     *         jinak vrátí {@code false}
     */
        public static boolean isAlive()
    {
        return isAlive;
    }

    /***************************************************************************
     * Zabezpečí korektní ukončení hry.
     * Zapamatuje si, že hra je již ukončena.
     */
    static void stopGame()
    {
        isAlive = false;
    }
    
    /***************************************************************************
     * Vrátí kolekci všech akcí použitelných ve hře.
     *
     * @return Kolekce všech akcí použitelných ve hře
     */
    static Collection<AAction> getAllActions()
    {
        Collection<AAction> collection, result;
        collection = NAME_2_ACTION.values();
        result     = Collections.unmodifiableCollection(collection);
        return result;
    }  

    /***************************************************************************
     * Zpracuje zadaný příkaz a vrátí text zprávy pro uživatele.
     *
     * @param command Zadávaný příkaz
     * @return Textová odpověď hry na zadaný příkaz
     */
    public static String executeCommand(String command)
    {
        command = command.trim();
        String answer;
        if (isAlive){
            answer = executeCommonComand(command) ;
        } else {
            answer = startGame(command);
        }        
        return answer;
    }

        
    /***************************************************************************
    * Metoda postupně inicializuje všechny klíčové objekty hry.
    */
    private static void initialize(){
        World.getInstance().initialize();
        Bag.getInstance().initialize();
        tsUSE.getInstance().initialize();
        tsTALK.getInstance().initialize();
    }
    
    
    /***************************************************************************
     * Zjistí, jaká akce má být zadaným příkazem aktivována,
     * a jedná-li se o známou akci, provede ji.
     * 
     * @param command Zadávaný příkaz
     * @return Odpověď hry na zadaný příkaz
     */
    private static String executeCommonComand(String command)
    {
        String[] words = command.toLowerCase().split("\\s+");
        String acttionName = words[0];
        AAction action = NAME_2_ACTION.get(acttionName);
        String answer;
        if (action == null) {
            answer = Text.nez_prikaz;
        }
        else {
            answer = action.execute(words);
        }
        return answer; 
    }
    
    
    /***************************************************************************
     * Ověří, jestli je hra spouštěna správným (= prázdným) příkazem,
     * a pokud ano, spustí hru.
     *
     * @param command Příkaz spouštějící hru
     * @return Odpověď hry na zadaný příkaz
     */
    private static String startGame(String command)
    {
     String answer;
     if (command.isEmpty()) {
         answer = Text.uvitani;
         initialize();
         isAlive = true;
        }
        else {
            answer = Text.not_start;
        }
        return answer;
    }
    
    
    /***************************************************************************
     * Metoda realizující reakci hry na zadání daného příkazu.
     * Počet parametrů je závislý na konkrétní akci,
     * např. akce typu <i>konec</i> a <i>nápověda</i> nemají parametry,
     * akce typu <i>jdi</i> a <i>seber</i> mají jeden parametr
     * akce typu <i>použij</i> muže mít dva parametry atd.
     *
     * @param arguments Parametry příkazu – akce;
     *                  jejich počet muže byt pro každou akci jiný,
     *                  ale pro všechna spuštění stejné akce je stejný
     * @return Text zprávy vypsané po provedeni příkazu
     */
    @Override
    abstract
    public String execute(String... arguments)
    ;

    
    
    
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Vytvoří rodičovský podobjekt vytvářené akce.
     *
     * @param name  Název vytvářené akce = text, který musí hráč zadat
     *              jako počáteční slovo zadávaného příkazu
     * @param description Stručný popis vytvářené akce
     */
    AAction(String name, String description)
    {
        super(name);
        this.description = description;
        AAction previous = NAME_2_ACTION.put(name.toLowerCase(), this); 
        if (previous != null) {
            throw new IllegalArgumentException(
            "\nAkce s názvem «" + name + "» byl již vytvořena");
        }
    }

    /***************************************************************************
     * Vrátí popis příkazu s vysvětlením jeho funkce
     * a významu jednotlivých parametru.
     *
     * @return Popis příkazu
     */
    @Override
    public String getDescription()
    {
        return description;
    }
   
    private static boolean OpendPokoj;
    private static boolean OpendPracovna;
    private static boolean OpendGaraz;
    private static boolean OpendAuto;
    
   public boolean getOpendPokoj (){
        return OpendPokoj;
   }

   public boolean getOpendPracovna (){
        return OpendPracovna;
   }
     
   public boolean getOpendGaraz (){
       return OpendGaraz;
   }
   
   public boolean getOpendAuto (){
        return OpendAuto;
   }

   
   
     public void setOpendPokoj (boolean open){
         OpendPokoj = open;
   }

   public void setOpendPracovna (boolean open){
         OpendPracovna = open;
   }
     
   public void setOpendGaraz (boolean open){
         OpendGaraz = open;
   }
   
   public void setOpendAuto (boolean open){
         OpendAuto = open;
   }
   
   
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
