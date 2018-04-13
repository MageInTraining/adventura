/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura.game;

//import static com.github.MageInTraining.adventura.game.STEM11Texts.*;
//import static com.github.MageInTraining.adventura.game.STEM11Item.*;
import com.github.MageInTraining.adventura.game.Area;

import eu.pedu.adv16w_fw.game_txt.INamed;
//import eu.pedu.adv16w_fw.game_txt.IWorld;
import eu.pedu.adv16w_fw.game_gui.IWorldG;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/*******************************************************************************
 * An instance of the {@code STEM11World} class represents the game world.
 * It should be defined as a singleton.
 * It is responsible for arrangement of individual spaces and keeps information,
 * in which space the player is just situated.
 * <p>
 * In this game the world is ...
 *
 * @author  Milan STEHLÍK
 * @version 2017-Winter
 */
class STEM11World implements IWorldG
{
//== CONSTANT CLASS FIELDS =====================================================

    /** The only instance (singleton) of this world. */
    private static final STEM11World SINGLETON = new STEM11World();

//== VARIABLE CLASS FIELDS =====================================================
//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================
//##############################################################################
//== CONSTANT INSTANCE FIELDS ==================================================

    private final Collection<Area> Areas;
    private final Collection<Area> exportedAreas;
    public final Area startingArea;

//== VARIABLE INSTANCE FIELDS ==================================================

    private Area currentArea;

//##############################################################################
//== CONSTRUCTORS AND FACTORY METHODS ==========================================

    /***************************************************************************
     * The factory method returning the only existing instance of the game.
     *
     * @return The only instance of the given game
     */
    static STEM11World getInstance()
    {
        return SINGLETON;
    }

    /***************************************************************************
     * Soukromý konstruktor definující jedinou instanci.
     * Protože je soukromý, musí být definován, i když má prázdné tělo.
     */
    private STEM11World()
    {
        Areas         = new ArrayList<>();
        exportedAreas = Collections.unmodifiableCollection(Areas);
        startingArea = new Area("BYT",
                           new String[] {"SIDLISTE"},
                           new String[] {"penize", "objednavka", "trouba"},
                           new Point (330 ,210 ));
        Areas.add(startingArea);
        Areas.add(new Area("SIDLISTE",
                           new String[] {"BYT", "CENTRUM", "OBCHODAK"},
                           new String[] {},
                           new Point (280 ,70 )));
        Areas.add(new Area("CENTRUM",
                            new String[] {"SIDLISTE", "OBCHODAK", "KNIHKUPECTVI", "ENERGETICKA_SPOLECNOST"},
                            new String[] {"bankomat"},
                            new Point (90 ,120 )));
        Areas.add(new Area("KNIHKUPECTVI",
                            new String[] {"CENTRUM"},
                            new String[] {"prodavacka"},
                            new Point (110 ,260 )));
        Areas.add(new Area("ENERGETICKA_SPOLECNOST",
                            new String[] {"CENTRUM"},
                            new String[] {"urednik" },
                           new Point (280 ,370 )));
        Areas.add(new Area("OBCHODAK",
                            new String[] {"SIDLISTE", "CENTRUM", "MASNA", "OVOCE_A_ZELENINA"},
                            new String[] {"Rozbyta_hracka", 
                                          "Dvere_do_garaze"},
                            new Point (560 ,230 )));
        Areas.add(new Area("MASNA",
                            new String[] {"OBCHODAK"},
                            new String[] {"kure"},
                            new Point (560 ,70 )));
        Areas.add(new Area("OVOCE_A_ZELENINA",
                            new String[] {"OBCHODAK"},
                            new String[] {"brambory" },
                            new Point (560 , 370)));
    }

//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Vrátí kolekci odkazů na všechny prostory vystupující ve hře.
     *
     * @return Kolekce odkazů na všechny prostory vystupující ve hře
     */
    @Override
    public Collection<Area> getAllSpaces()
    {
        return exportedAreas;
    }


    /***************************************************************************
     * Vrátí odkaz na aktuální prostor,
     * tj. na prostor, v němž se hráč pravé nachází.
     *
     * @return Prostor, v němž se hráč pravé nachází
     */
    @Override
    public Area getCurrentSpace()
    {
        return currentArea;
    }


    /***************************************************************************
     * Nastaví zadaný prostor jako aktuální,
     * tj. jako prostor, v němž se hráč právě nachází.
     *
     * @param destinationRoom Nastavovaný prostor
     */
    void setCurrentArea(Area destinationArea)
    {
        currentArea = destinationArea;
    }
    
    /***************************************************************************
     * Vrátí prostor se zadaným názvem zabalený v objektu typu {@link Optional}.
     *
     * @param name Název požadovaného prostoru
     * @return Zabalený prostor se zadaným názvem
     */
    public Optional<Area> getOArea(String name)
    {
        Optional<Area> result = INamed.getO(name, Areas);
        return result;
    }

//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Metoda inicializující svět hry.
     * Nejprve inicializuje všechny prostory
     * a pak nastaví výchozí aktuální prostor.
     */
     void initialize(){
         Areas.forEach(Area::initialize);
         currentArea = startingArea;
     }
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
