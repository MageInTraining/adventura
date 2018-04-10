/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura.game;

import eu.pedu.adv16w_fw.game_txt.IGame;
import eu.pedu.adv16w_fw.game_txt.INamed;



/*******************************************************************************
 * Instances of the abstract class {@code ANamed} represent
 * parent sub-objects of named objects,
 * i.e. of instances of classes implementing the {@link INamed} interface.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2016-Summer
 */
public abstract class ANamed implements INamed
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

    /** Name of this instance. */
    private final String name;



//== VARIABLE INSTANCE FIELDS ==================================================



//##############################################################################
//== CONSTRUCTORS AND FACTORY METHODS ==========================================

    /***************************************************************************
     * Creates a parent sub-object of the instance with the given name.
     * The constructor checks that the given name is neither {@code null}
     * nor the empty string, and if the named object is not the game itself,
     * its name does not contain any whitespaces.
     *
     * @param name Name of the creating instance
     */
    public ANamed(String name)
    {
        if ((name == null)  ||  name.isEmpty()) {
            throw new IllegalArgumentException(
                        "\nThe object name may be neither null "
                      + "nor an empty string");
        }
        if (! (this instanceof IGame)         &&
            ( (! name.equals(name.trim())) ||
              (name.split("\\s").length > 1)  ))
        {
            throw new IllegalArgumentException(
                      "\nNames may not contain any whitespaces - Entered: «"
                    + name + '»');
        }
        this.name = name;
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Returns the name of the instance.
     *
     * @return Name of the instance
     */
    @Override
    public String getName()
    {
        return name;
    }



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Returns the textual signature of this instance
     * which is equal to its name.
     *
     * @return Name of the instance
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
