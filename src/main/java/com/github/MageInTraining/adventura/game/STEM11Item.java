/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura.game;

import eu.pedu.adv16w_fw.game_txt.IItem;





/*******************************************************************************
 * Instances of the {@code STEM11Item} class represent the items in spaces.

 * @author  Milan STEHLÍK
 * @version 2017-Winter
 */
class STEM11Item extends ANamed implements IItem
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

    /** Weight of the item. */
    private final int weight;


//== VARIABLE INSTANCE FIELDS ==================================================



//##############################################################################
//== CONSTRUCTORS AND FACTORY METHODS ==========================================

    /***************************************************************************
     * Creates the item with the given name and other given properties.
     * These additional properties are entered through a prefix,
     * that is the first character of the given name.
     * The name of the item itself is created by the remaining letters.
     *
     * @param name The name of the created item
     */
    STEM11Item(String name)
    {
        super(name);
                  switch (name){
            case "Peníze":
            this.weight =1;
            break;
            
            case "Objednávka_do_knihkupectví":
            this.weight =1;
            break;
            
            case "Trouba":
            this.weight =10;
            break;
            case "Bankomat":
            this.weight =10;
            break;
            case "Prodavačka":
            this.weight =10;
            break;
            case "Kuchařka":
            this.weight =1;
            break;
            case "Úředník":
            this.weight =10;
            break;
            case "Kuře":
            this.weight =1;
            break;
            case "Cibule_a_brambory":
            this.weight =1;
            break;
            default:
            this.weight= 1;
            }
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Returns the item weight, and/or the corresponding characteristics.
     * The items that cannot be raised
     * have higher weight than the bag capacity is.
     *
     * @return Weight of the item
     */
    @Override
    public int getWeight()
    {
        return weight;
    }



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
