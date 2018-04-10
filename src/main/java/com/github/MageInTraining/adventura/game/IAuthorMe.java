/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-�?�
 */
package com.github.MageInTraining.adventura.game;

import eu.pedu.adv16w_fw.game_gui.IGSMFactoryProductG;



/*******************************************************************************
 * Instance interfejsu {@code IAuthorPrototype} umějí na požádání vrátit
 * jméno a identifikační řetězec autora/autorky své třídy;
 * tyto hodnoty mají uloženy ve svých statických konstantách
 * {@link #AUTHOR_NAME} a {@link #AUTHOR_ID}.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2015-Podzim
 */
public interface IAuthorMe extends IGSMFactoryProductG
{
//== STATIC CONSTANTS ==========================================================

    /** Jméno autora/autorky ve formátu <b>P�?ÍJMENÍ Křestní</b>,
     * tj. nejprve příjmení psané velkými písmeny a za ním křestní jméno,
     * u nějž bude velké pouze první písmeno a ostatní písmena budou malá.
     * Má-li autor programu více křestních jmen, může je uvést všechna. */
    String AUTHOR_NAME = "STEHLÍK Milan";

    /** Identifikační řetězec autora/autorky zapsaný VELKÝMI PÍSMENY.
     *  Tímto řetězcem bývá většinou login do informačního systému školy. */
    String AUTHOR_ID = "STEM11";



//== STATIC METHODS ============================================================



//##############################################################################
//== ABSTRACT GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Vrátí jméno autora/autorky programu ve formátu <b>P�?ÍJMENÍ Křestní</b>,
     * tj. nejprve příjmení psané velkými písmeny a za ním křestní jméno,
     * u nějž bude velké pouze první písmeno a ostatní písmena budou malá.
     * Má-li autor programu více křestních jmen, může je uvést všechna.
     *
     * @return Jméno autora/autorky programu ve tvaru P�?ÍJMENÍ Křestní
     */
    @Override
    default public String getAuthorName()
    {
        return AUTHOR_NAME;
    }


    /***************************************************************************
     * Vrátí identifikační řetězec autora/autorky programu
     * zapsaný VELKÝMI PÍSMENY.
     * Tímto řetězcem bývá většinou login do informačního systému školy.
     *
     * @return Identifikační řetězec autora/autorky programu
     */
    @Override
    default public String getAuthorID()
    {
        return AUTHOR_ID;
    }



//== OTHER ABSTRACT METHODS ====================================================
//== DEFAULT GETTERS AND SETTERS ===============================================
//== OTHER DEFAULT METHODS =====================================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
