/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-�?�
 */
package com.github.MageInTraining.adventura.game;

import eu.pedu.adv16w_fw.game_txt.IGame;
import eu.pedu.adv16w_fw.game_txt.INamed;
import java.util.Map;
import java.util.HashMap;



/*******************************************************************************
 * Instance třídy {@code ANamed} představují rodičovské podobjekty
 * instancí tříd pojmenovaných objektů,
 * tj. tříd implementujících interfejs {@link INamed}.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2015-Podzim
 */
public abstract class STEM11KureGame implements INamed
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

    /** Název dané instance. */
    private final String name;




//== VARIABLE INSTANCE ATTRIBUTES ==============================================



//##############################################################################
//== CONSTUCTORS AND FACTORY METHODS ===========================================

    /***************************************************************************
     * Vytvoří rodičovský podobjekt instance objektu se zadaným názvem.
     * Konstruktor přitom zkontroluje, že zadávaný název není prázdný odkaz
     * ani prázdný řetězec, a že není-li pojmenovávaný objekt objektem hry,
     * tak je jednoslovný, tj. neobsahuje bílé znaky.
     *
     * @param name Název dané instance
     */
    public STEM11KureGame(String name)
    {
        if ((name == null)  ||  name.isEmpty()) {
            throw new IllegalArgumentException(
                        "\nJako název objektu nesmí být zadán "
                      + "prádzný odkaz ani prázdný řetězec");
        }
        if (! (this instanceof IGame)         &&
            ( (! name.equals(name.trim())) ||
              (name.split("\\s").length > 1)  ))
        {
            throw new IllegalArgumentException(
                        "\nNázvy objektů musejí být jednoslovné, "
                      + "tj. nesmějí obsahovat bílé znaky - Zadáno: «"
                      + name + '»');
        }
        this.name = name;        
    }


    
    

//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Vrátí název dané instance.
     *
     * @return Název instance
     */
    @Override
    public String getName()
    {
        return name;
    }



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Vrátí textový podpis dané instance tvořený názvem instance.
     *
     * @return Název instance
     */
    @Override
    public String toString()
    {
        return name;
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
