/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura.game;

import java.util.Arrays;
import java.util.stream.Collectors;



/*******************************************************************************
 * The {@code Texts} library class serves as a box for text constants,
 * that are used at various places of the program.
 * By centralizing the definitions of these text strings we can easily reach,
 * that texts, that should be identical at various program places,
 * really will be identical.
 *
 * @author  Milan STEHLÍK
 * @version 2017-Winter
 */
class STEM11Texts
{
//== CONSTANT CLASS FIELDS =====================================================

/** Name of the program's author. */
static final String AUTHOR_NAME = "STEHLÍK Milan";

/** Author's ID (mostly login). */
static final String AUTHOR_ID = "STEM11";

/** Names of used spaces (rooms in this game) - without prefix. */
        static final String
        BYT                         = "Byt",
        SIDLISTE                    = "Sídliště",
        CENTRUM                     = "Centrum",
        KNIHKUPECTVI                = "Knihkupectví",
        ENERGETICKA_SPOLECNOST      = "Energetická_společnost",
        OBCHODAK                    = "Obchoďák",
        MASNA                       = "Masna",
        OVOCE_A_ZELENINA            = "Ovoce_a_zelenina";


/** Names of used items - without prefix. */
static final String
        penize                      = "penize",
        objednavka  = "objednavka",
        trouba                      = "trouba",
        bankomat                    = "bankomat",
        prodavacka                  = "prodavacka",
        kucharka                    = "kucharka",
        urednik                     = "urednik",
        kure                        = "kure",
        brambory           = "brambory";


/** Names of used actions - prefix {@code 'a'}. */
static final String
        aHELP        = "tsHELP",
        aGOTO        = "tsMOVE",
        aPUT_DOWN    = "tsPUT_DOWN",
        aTAKE        = "tsTAKE",
        aEXIT        = "end",
        aGIVE        = "tsGIVE",
        aUSE         = "tsUSE",
        aTALK        = "tsTALK",
        aBUY         = "tsBUy";


/** Format of the message supplement informing about the current state - 
 *  prefix {@code 's'}, format prefix is {@code 'f'}. */
static final String
        sNEIGHBORS   = "Sousedi: ",
        sITEMS       = "Předměty:     ",
        sBAG         = "Taška:       ";


/** Messages written as reaction to the command
 * running the mandatory actions - prefix {@code 'm'}. */
static final String
        mNOT_START = "První příkaz je startovací."
        + "\nNeběžící hra může být zapnuta startovacím příkazem.",
        
        mWELCOME_MSG = 
        "Jsi ve svém bytě. Nefunguje elektřina.",
        
        mANCO           = "Zadaná akce nebyla provedena.\n",
        mASK_HELP       = "\nPro nápovědu zadej tsHELP",

        mEMPTY_CMD      = "Zadal jsi prázdný příkaz.\n"
        + "Ten můžeš zadat jen při startu hry." + mASK_HELP,
        mNO_CMD         = mANCO + "Tento příkaz neznám." + mASK_HELP,

        mMOVED          = "Přesunul jsi se do místnosti: ",
        mNO_TARGET      = mANCO + "Nezadal jsi kam se přesunout",
        mNOT_NEIGHBOR   = mANCO + "Zadaná oblast s touto nesousedí "
                                + "této oblasti: ",
        
        mTAKEN          = "Vzal jsi předmět: ",
        mNO_TAKE_ITEM   = mANCO + "Nezadal jsi co má být sebráno",
        mHEAVY_ITEM     = mANCO + "Zadaný předmět nemůže být sebrán, je moc těžký: ",
        mNOT_HERE       = mANCO + "Zadanáý předmět není v této oblasti: ",
        mBAG_FULL       = mANCO + "Nemůžeš sebrat zadaný předmět, " 
                            + "tvoje taška je plná.",
        
        mPUT_DOWN       = "Položil jsi předmět: ",
        mNO_PUT_ITEM    = mANCO + "Nezadal jsi co má být položeno",
        mNOT_IN_BAG     = mANCO + "Předmět není v tašce: ",
       
        mGIVE           = "Dal jsi ",
        mRECIVED        = " a obdržel jsi ",
        mNOT_GIVE       ="Nezadal jsi, co nebo komu má bý dáno",
        
        mNOT_HAVE       = "Nemáš všechny potřebné komponenty",
        
        mUSED           ="Úspěšně jsi použil předmět",
        mUNUSABLE       ="Tento předmět nelze použít",
        mNOTHING_TO_USE ="Nezadal jsi, co chceš použít",
        
        mNOT_TALK       = "Nezadal jsi, s kým chceš mluvit",
        mGIMMME_MONEY   = "Dej urednikovi platbu za elektřinu",
        mPAID           = "Zaplatil jsi elektřinu",
        
        mBOUGHT         = "Koupil jsi: ",
                
        mHELP           = "Pomož si a bude ti pomoženo -JK",
        
        mGOOD_BYE       = "Konec hry.\nDíky za pozornost.";



    /** Parts of internal errors (exceptions) messages. */

    public static final String
        eAACTION_1   = "\nAkce «"
        ,
        eAACTION_2   = "» už byl vytvořen"
        ,
        eITEM_PREFIX = "\nNeznámý prefix: «"
        ,
        eEND = null;



//== VARIABLE CLASS FIELDS =====================================================



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================

    /***************************************************************************
     * Returns a string containing the entered names separated with commas.
     *
     * @param názvy Names that are necessary to be unified
     * @return The resulting string from unified entered names
     */
    static String cm(String... názvy)
    {
        String result = Arrays.stream(názvy)
                .collect(Collectors.joining(", "));
    return result;
    }



//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE FIELDS ==================================================
//== VARIABLE INSTANCE FIELDS ==================================================



//##############################################################################
//== CONSTRUCTORS AND FACTORY METHODS ==========================================
    
    /** Private constructor preventing the instance creating.*/
    private STEM11Texts() {}



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}