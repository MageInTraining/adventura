/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-�?�
 */
package com.github.MageInTraining.adventura.game;

import eu.pedu.adv16w_fw.game_gui.ISpaceG;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;




/*******************************************************************************
 * Instance třídy {@code EmptyArea} představují prostory ve hře.
 * Dosažení prostoru si můžeme představovat jako částečný cíl,
 * kterého se hráč ve hře snaží dosáhnout.
 * Prostory mohou být místnosti, planety, životní etapy atd.
 * Prostory mohou obsahovat různé objekty,
 * které mohou hráči pomoci v dosažení cíle hry.
 * Každý prostor zná své aktuální bezprostřední sousedy
 * a ví, jaké objekty se v něm v daném okamžiku nacházejí.
 * Sousedé daného prostoru i v něm se nacházející objekty
 * se mohou v průběhu hry měnit.
 * <p>
 * V tomto programu jsou prostory ...
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2015-Podzim
 */
class Area extends STEM11KureGame implements ISpaceG
{
//== CONSTANT CLASS ATTRIBUTES =================================================
 private final String[] neighborNames;
 private final Collection<Area> neighbors;
 private final Collection<Area> exportedNeighbors;
 
 private final String[] itemNames;
 private final Collection<STEM11Item> Itemss;
 private final Collection<STEM11Item> exportedItemss;
 private Point point;


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
     *
     */
    Area(String name, String[] neighborNames, String[] itemNames,Point point)
    {
        super(name);
        this.neighborNames     = neighborNames;
        this.neighbors         = new ArrayList<>();
        this.exportedNeighbors = Collections.unmodifiableCollection(neighbors);
        this.itemNames         = itemNames;
        this.Itemss            = new ArrayList<>();
        this.exportedItemss    = Collections.unmodifiableCollection(Itemss); 
        this.point = point;
    }

   /***************************************************************************
    * Metoda inicializující daný prostor. 
    * Na základě konstruktorem zapamatovaných jmen
    * inicializuje sousedy daného prostoru a přítomné h-objekty.
    */
    void initialize()
    {
        initializeItems();
        initializeNeighbors();
    } 
    
    /***************************************************************************
     * Odebere zadaný předmět ze své kolekce předmětů.
     *
     * @param item Odebíraný předmět
     */
    void removeItem(STEM11Item item)
    {
        Itemss.remove(item);
    }    
    
   /***************************************************************************
    * Vyčistí kolekci {@link #items} a uloží do ní objekty reprezentující  
    * h-objekty vyskytující se v daném prostoru na počátku hry.
    */
    private void initializeItems(){
        Itemss.clear();
        Arrays.stream(itemNames)
              .map(STEM11Item::new)
              .forEach(Itemss::add);      
    }
    
    /***************************************************************************
     * Vyčistí kolekci {@link #neighbors} a uloží do ní předměty reprezentující
     * prostory sousedící s daným prostorem na počátku hry.
     */    
    private void initializeNeighbors(){
        STEM11World world = STEM11World.getInstance();
        neighbors.clear();
        Arrays.stream(neighborNames)
              .map(world::getOArea)
              .map(Optional<Area>::get)
              .forEach(neighbors::add);
        
    }
//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Vrátí kolekci sousedů daného prostoru, tj. kolekci prostorů,
     * do nichž je možno se z tohoto prostoru přesunout příkazem typu
     * {@link eu.pedu.adv15p_fw.scenario.TypeOfStep#tsMOVE
     * TypeOfStep.tsMOVE}.
     *
     * @return Kolekce sousedů
     */
    @Override
    public Collection<Area> getNeighbors()
    {
       return exportedNeighbors;
    }


    /***************************************************************************
     * Vrátí kolekci objektů nacházejících se v daném prostoru.
     *
     * @return Kolekce objektů nacházejících se v daném prostoru
     */
    @Override
    public Collection<STEM11Item> getItems()
    {
        return exportedItemss;
    }

    public void addItem (STEM11Item item) {
      Itemss.add(item);
    }
    
    
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================

    @Override
    public Point getPosition() {
        return point;
    }
}
