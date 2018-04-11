/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura.game;

import eu.pedu.adv16w_fw.game_gui.IBagG;
//import eu.pedu.adv16w_fw.game_txt.IBag;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;

/*******************************************************************************
 * Instance of the {@code STEM11Bag} class represents the handbag,
 * to which the players store the items picked up in individual spaces,
 * so that they could be moved to other spaces and/or used.
 * The disposal site has a final capacity defining the maximal permitted
 * sum of weights of items occuring in the repository.
 * <p>
 *
 * @author  Milan STEHLÍK
 * @version 2017-Winter
 */
class STEM11Bag  implements IBagG
{
//== CONSTANT CLASS FIELDS =====================================================


    /** The only instance of the bag in the game. */
    private static final STEM11Bag SINGLETON = new STEM11Bag();



//== VARIABLE CLASS FIELDS =====================================================



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE FIELDS ==================================================
//== VARIABLE INSTANCE FIELDS ==================================================

    private static final int Kapacita = 4;
    private int Zaplneni = 0;
    private Collection<STEM11Item> items;
    private Collection<STEM11Item> exportedItems;

//##############################################################################
//== CONSTRUCTORS AND FACTORY METHODS ==========================================

    /***************************************************************************
     * Factory method returning the only existing instance of the game.
     *
     * @return The instance of the given game
     */
    static STEM11Bag getInstance()
    {
        return SINGLETON;
    }
    
    /***************************************************************************
     * Konstruktor seznam věci v batohu
     */
    public STEM11Bag()
    {
        this.items         = new ArrayList<>();
        this.exportedItems = Collections.unmodifiableCollection(items);
    }

//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Vloží věc do batohu, pokud se tam vejde
     * @return true když se věc vloží, false, pokud se nevložila
     * @param objekt třídy Vec
     */
    public boolean tryAddItem (STEM11Item item) {
        if (Zaplneni < Kapacita ) {
            items.add(item);
            Zaplneni++;
            return true;
        }
        return false;
    }
    
    /***************************************************************************
     * Vrátí kapacitu batohu, tj. maximální povolený součet vah objektů,
     * které je možno současně uložit do batohu.
     *
     * @return Kapacita batohu
     */
    @Override
    public int getCapacity()
    {
        return Kapacita;
    }
    
    /***************************************************************************
     * Vrátí kolekci objektů uložených v batohu.
     *
     * @return Kolekce objektů v batohu
     */
    @Override
    public Collection<STEM11Item> getItems() 
    {
        return exportedItems;
    }

    /***************************************************************************
     * Metoda inicializující batoh.
     * Protože v této hře má hráč na počátku hry prázdný batoh,
     * stačí pouze vyčistit kolekci {@link #items}.
     */
    void initialize()
    {
        items.clear();
    }

    /***************************************************************************
     * Odebere zadaný předmět ze své kolekce batohu.
     *
     * @param item Odebíraný předmět
     */
    void removeItem(STEM11Item item)
    {
        Zaplneni--;
        items.remove(item);
    }  
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================
//##############################################################################
//== NESTED DATA TYPES =========================================================
}
