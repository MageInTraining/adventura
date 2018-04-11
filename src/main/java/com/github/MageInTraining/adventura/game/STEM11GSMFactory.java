/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura.game;

import eu.pedu.adv16w_fw.game_gui.IGSMFactoryG;
import eu.pedu.adv16w_fw.scenario.AScenarioManagerG;

import com.github.MageInTraining.adventura.textui.UIA_JOptionPane;

import eu.pedu.adv16w_fw.game_txt.IGSMFactory;
import eu.pedu.adv16w_fw.game_txt.IUI;
import eu.pedu.adv16w_fw.scenario.AScenarioManager;



/*******************************************************************************
 * Instances of the {@code STEM11GSMFactory } class represent
 * the factory objects which are able to deliver the game instance,
 * an instance of scenario manager of this game
 * and an instance of the text user interface.
 * As long as some of these objects are not yet fully defined,
 * the methods throw the
 * {@link eu.pedu.adv16w_fw.utilities.UncompletedMethodException}.
 *
 * @author  Milan STEHLÍK
 * @version 2016-Summer
 */
public class STEM11GSMFactory implements IGSMFactoryG, ISTEM11Prototype
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
//== VARIABLE INSTANCE FIELDS ==================================================



//##############################################################################
//== CONSTRUCTORS AND FACTORY METHODS ==========================================

    /***************************************************************************
     * Creates the factory object providing the key application objects.
     */
    public STEM11GSMFactory()
    {
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Returns the instance of the scenario manager.
     *
     * @return Required scenario manager
     */
    @Override
    public AScenarioManagerG getScenarioManager()
    {
        return STEM11ScenarioManager.getInstance();
    }


   /***************************************************************************
     * Returns the instance of text version of the game.
     *
     * @return Required game
     */

    @Override
    public STEM11Game getGame()
    {
        return STEM11Game.getInstance();
    }


    /***************************************************************************
     * Returns the object executing the user interface.
     *
     * @return Required user interface
     */
/*    @Override
    public IUI getUI()
    {
        IUI ui = 
//                null;
//                 new UIA_JOptionPane();
//                 new UIB_Console();
//                 new UIC_GamePlayer(new UIC_GamePlayer.ByJOptionPane());
//                 new UIC_GamePlayer(new UIC_GamePlayer.ByScanner());
//                 new UID_Multiplayer(new UID_Multiplayer.ByJOptionPane());
//                 new UID_Multiplayer(new UID_Multiplayer.ByScanner());
        return ui;
    }
*/


//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
