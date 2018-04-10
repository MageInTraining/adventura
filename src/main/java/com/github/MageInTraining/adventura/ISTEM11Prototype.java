/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura;

import com.github.MageInTraining.adventura.STEM11GSMFactory;
import eu.pedu.adv16w_fw.game_txt.IGSMFactory;
import eu.pedu.adv16w_fw.game_txt.IGSMFactoryProduct;



/*******************************************************************************
 * Instances of interface {@code IAuthorPrototype} know to return on request
 * the name and identification string of the author of its class;
 * these values are saved in their static constants
 * {@link #AUTHOR_NAME} a {@link #AUTHOR_ID}.
 *
 * @author  Milan STEHLÍK
 * @version 2016-Summer
 */
public interface ISTEM11Prototype extends IGSMFactoryProduct
{
//== STATIC CONSTANTS ==========================================================

    /** The name of the author in format <b>SURNAME First-name</b>,
     * i.e. firstly the surname in capital letters followed by first name,
     * with only the first capital letter, other letters will be small.
     * If the program author has more first names, he can quote all of them. */
    String AUTHOR_NAME = "STEHLÍK Milan";

    /** Author's identification string written in CAPITAL LETTERS.
     *  It is mostly the login into the school information system. */
    String AUTHOR_ID = "STEM11";

    /** Factory class the instances of which are factory objects providing
     *  the instance of the game, its associated scenario manager
     *  and the textual user interface created by this author. */
    Class<? extends IGSMFactory> FACTORY_CLASS = STEM11GSMFactory.class;



//== STATIC METHODS ============================================================



//##############################################################################
//== ABSTRACT GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Returns the program author name in format <b>SURNAME First-name</b>,
     * i.e. firstly the surname in capital letters followed by first name,
     * with only the first capital letter, other letters will be small.
     * If the program author has more first names, he can quote all of them.
     *
     * @return Program author name in format SURNAME First-name
     */
    @Override
    default public String getAuthorName()
    {
        return AUTHOR_NAME;
    }


    /***************************************************************************
     * Returns the program author identification string
     * written in CAPITAL LETTERS.
     * It is mostly the login into the school information system.
     *
     * @return Identification string (login) of the program author
     */
    @Override
    default public String getAuthorID()
    {
        return AUTHOR_ID;
    }


    /***************************************************************************
     * Returns the class-object of the factory class, the instances of which
     * can mediate receiving of all key objects of the application,
     * the part of which also the mother class of this instance is.
     *
     * @return The class-object of the factory class
     */
    @Override
    default public Class<? extends IGSMFactory> getFactoryClass()
    {
        return FACTORY_CLASS;
    }



//== OTHER ABSTRACT METHODS ====================================================
//== DEFAULT GETTERS AND SETTERS ===============================================
//== OTHER DEFAULT METHODS =====================================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
