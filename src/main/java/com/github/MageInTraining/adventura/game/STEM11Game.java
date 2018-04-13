/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura.game;

import com.github.MageInTraining.adventura.DATA.Data;

import eu.pedu.adv16w_fw.game_txt.BasicActions;
import eu.pedu.adv16w_fw.game_txt.IBag;
import eu.pedu.adv16w_fw.game_txt.IGame;
import eu.pedu.adv16w_fw.game_gui.IBagG;
import eu.pedu.adv16w_fw.game_gui.IGSMFactoryG;
import eu.pedu.adv16w_fw.game_gui.IGameG;
import eu.pedu.adv16w_fw.game_gui.IListener;
import eu.pedu.adv16w_fw.game_gui.ISpaceG;
import eu.pedu.adv16w_fw.game_gui.Icon;
import eu.pedu.adv16w_fw.game_txt.IGSMFactory;

import java.net.URL;
import java.util.Collection;



/*******************************************************************************
 * Instances of the {@code STEM11Game} class are responsible for the game
 * logics. They are able to accept individual commands and provide information
 * on current state of the game and its parts.
 * <p>
 * The game class has to be defined as a singleton and,
 * besides methods declared in the {@link IGame} interface,
 * it has to define the {@code getInstance()} static factory method.
 * Fulfilling of this condition cannot be verified by the compiler, but they
 * can by verified by test utilizing the associated scenario manager.
 * <p>
 * {@code STEM11Game} instances represent prototypes of game instances
 * which are not yet fully defined and serve only for completion
 * of the scenario managers features, which needs cooperation with the game.
 *
 * @author  Milan STEHLÍK
 * @version 2017-Winter
 */
public class STEM11Game extends ANamed implements IGameG, ISTEM11Prototype
{
//== CONSTANT CLASS FIELDS =====================================================

    /** The crate keeping the mandatory action names. */
    /*private static final BasicActions BASIC_ACTIONS =
                     new BasicActions(STEM11Texts.aGOTO, STEM11Texts.aTAKE,
                                      STEM11Texts.aPUT_DOWN,
                                      STEM11Texts.aHELP, STEM11Texts.aEXIT);*/
	
	private static final BasicActions BASIC_ACTIONS =
            new BasicActions("tsMOVE", "tsPICK_UP", "tsPUT_DOWN",
                                    "tsHELP", "tsEND");

    /** The reference to the only instance (singleton) of this game. */
    private static final STEM11Game SINGLETON = new STEM11Game();
    
    /** Tovární třída, jejíž instancemi jsou tovární objekty poskytující
     *  instanci správce scénářů i hry, jejíž scénáře daný správce spravuje. */
    private final static Class<? extends IGSMFactoryG> FACTORY_CLASS =
                                                        STEM11GSMFactory.class;



//== VARIABLE CLASS FIELDS =====================================================



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE FIELDS ==================================================
//== VARIABLE INSTANCE FIELDS ==================================================



//##############################################################################
//== CONSTRUCTORS AND FACTORY METHODS ==========================================

    /***************************************************************************
     * The factory method returning the only instance of the given game.
     *
     * @return The instance of the given game
     */
    public static STEM11Game getInstance()
    {
        return SINGLETON;
    }


    /***************************************************************************
     * The private constructor defining the only instance of the game class.
     * Because it is private, it has to be defined despite its body is empty.
     */
    private STEM11Game()
    {
        super("Epický quest přípravy pečeného kurete");
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Returns information if the game is currently running.
     * The once started game cannot be started again.
     * If you want to start the game again you have to finish it firstly.
     *
     * @return If the game is running, it returns {@code true},
     *         otherwise it returns {@code false}
     */
    @Override
    public boolean isAlive()
    {
        return STEM11AAction.isAlive(); 
    }
    
    /***************************************************************************
     * Vrátí class-objekt tovární třídy, jejíž instance umějí zprostředkovat
     * získání všech klíčových objektů aplikace.
     *
     * @return Class-objekt tovární třídy
     */
    @Override
    public Class<? extends IGSMFactoryG> getFactoryClass()
    {
        return FACTORY_CLASS;
    }


    /***************************************************************************
     * Returns the bag to which the player will save the taken items.
     *
     * @return The bag to which the player saves the taken items
     */
    @Override
    public IBagG getBag()
    {
        return STEM11Bag.getInstance();
    }


    /***************************************************************************
     * Returns the collection of all actions usable in the game.
     *
     * @return The collection of all actions usable in the game
     */
    @Override
    public Collection<STEM11AAction> getAllActions()
    {
        return STEM11AAction.getAllActions();
    }


    /***************************************************************************
     * Returns the crate with names of mandatory actions, i.e. actions for
     * <ul>
     *   <li>moving into another space,</li>
     *   <li>taking item from the space and putting it into the bag,</li>
     *   <li>taking item from the bag and putting it down
     *       in the current space,</li>
     *   <li>asking for help,</li>
     *   <li>immediate game termination.</li>
     * </ul>
     *
     * @return The crate with names of mandatory actions
     */
    @Override
    public BasicActions getBasicActions()
    {
        return BASIC_ACTIONS;
    }


    /***************************************************************************
     * Returns the world in which the game takes place.
     *
     * @return The world in which the game takes place
     */
    @Override
    public STEM11World getWorld()
    {
        return STEM11World.getInstance();
    }



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Processes the given command and returns the answer to the user.
     *
     * @param command The entered command
     * @return The answer of the game after processing the command
     */
    @Override
    public String executeCommand(String command)
    {
        return STEM11AAction.executeCommand(command);
    }


    /***************************************************************************
     * Ends the whole game and returns the allocated resources.
     */
    @Override
    public void stop()
    {
        STEM11AAction.stopGame();
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
    

    @Override
    public URL getHelpURL() {
        return Data.class.getResource("Help.html");
    }

    @Override
    public Icon getMap() {
        return new Icon(Data.class.getResource("SpaceMap.png"));
    }

    @Override
    public Icon getPlayer() {
        return new Icon(Data.class.getResource("Player.png"));
    }
}
