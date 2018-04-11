/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura.game;

import com.github.MageInTraining.adventura.DATA.Data;

//import eu.pedu.adv16w_fw.game_txt.IItem;
import eu.pedu.adv16w_fw.game_gui.IItemG;
import eu.pedu.adv16w_fw.game_gui.Icon;

import java.net.URL;





/*******************************************************************************
 * Instances of the {@code STEM11Item} class represent the items in spaces.

 * @author  Milan STEHLÍK
 * @version 2017-Winter
 */
class STEM11Item extends STEM11KureGame implements IItemG
{
//== CONSTANT CLASS FIELDS =====================================================



//== VARIABLE CLASS FIELDS =====================================================



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================

	private String name;    
	private boolean pickable;

//##############################################################################
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
    STEM11Item(String name){
        super(name);
        switch (name){
            case "Peníze":
            	this.name = name;    
            	this.pickable =true;
            break;
            
            case "Objednávka_do_knihkupectví":
            	this.name = name;    
                this.pickable =true;
            break;
            
            case "Trouba":
            	this.name = name;    
                this.pickable =false;
            break;
            
            case "Bankomat":
            	this.name = name;    
                this.pickable =false;
            break;
            
            case "Prodavačka":
            	this.name = name;    
                this.pickable =false;
            break;
            
            case "Kuchařka":
            	this.name = name;    
                this.pickable =true;
            break;
            
            case "Úředník":
            	this.name = name;    
                this.pickable =false;
            break;
            
            case "Kuře":
            	this.name = name;    
                this.pickable =true;
            break;
            
            case "Cibule_a_brambory":
            	this.name = name;    
                this.pickable =true;
            break;
            
            }
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Vrátí váhu předmětu, resp. charakteristiku jí odpovídající.
     * Objekty, které není možno zvednout,
     * mají váhu větší, než je kapacita batohu.
     *
     * @return Váha objektu
     */
    @Override
    public int getWeight()
    {
     if (pickable) {
        return 1;
    }
     return 10;   
    }



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
    
    @Override
    public Icon getPicture() {
         URL url = Data.class.getResource("/DATA/" + name + ".png");
         return new Icon(url);
    }
}
