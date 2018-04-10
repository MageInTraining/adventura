/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura.textui;

import eu.pedu.adv16w_fw.game_txt.IGame;
import eu.pedu.adv16w_fw.game_txt.IUI;

import com.github.MageInTraining.adventura.ISTEM11Prototype;
import com.github.MageInTraining.adventura.game.STEM11Game;

import java.util.Scanner;



/*******************************************************************************
 * The {@code UIB_Console} instances realize the user's interface
 * that utilizes the standard input and output.
 *
 * @author  Rudolf PECINOVSKÝ
 * @version 2016-Winter
 */
public class UIB_Console implements IUI, ISTEM11Prototype
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

    /** Currently played game. */
    private IGame game;



//##############################################################################
//== CONSTRUCTORS AND FACTORY METHODS ==========================================

    /***************************************************************************
     * Creates object realizing the user's interface
     * that utilizes the standard input and output.
     */
    public UIB_Console()
    {
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Returns the currently played game.
     *
     * @return The currently played game
     */
    @Override
    public IGame getGame()
    {
        return game;
    }



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================

    /***************************************************************************
     * Starts the the default game
     * and runs the communication between this game and the user.
     */
    @Override
    public void startGame()
    {
        startGame(STEM11Game.getInstance());
    }


    /***************************************************************************
     * Starts the the given game
     * and runs the communication between this game and the user.
     *
     * @param game The game, which should be run
     */
    @Override
    public void startGame(IGame game)
    {
        Scanner scanner = new Scanner(System.in);
        String  command = "";
        String  answer;
        for(;;) {
            answer  = game.executeCommand(command);
            System.out.println(answer);
            if (! game.isAlive()) { break; }    //---------->
            command =  scanner.nextLine();
        }
    }
//    public void startGame(IGame game)
//    {
//        Scanner scanner = new Scanner(System.in);
//        String  command = "";
//        String  answer  = game.executeCommand("");
//        System.out.println(answer);
//        do {
//            command =  scanner.nextLine();
//            answer  = game.executeCommand(command);
//            System.out.println(answer);
//        } while (game.isAlive());
//    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================



//##############################################################################
//== MAIN METHOD ===============================================================

    /***************************************************************************
     * Method starting the default game
     * with the user's interface utilizing services
     * of the standard input and output and the class {@link Scanner}.
     *
     * @param args Parameters of the command line - up-to-now unused
     */
    public static void main(String[] args)
    {
        new UIB_Console().startGame();
    }
}
