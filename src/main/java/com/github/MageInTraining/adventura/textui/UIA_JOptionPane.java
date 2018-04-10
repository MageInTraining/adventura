/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura.textui;

import eu.pedu.adv16w_fw.game_txt.IGame;
import eu.pedu.adv16w_fw.game_txt.IUI;

import com.github.MageInTraining.adventura.ISTEM11Prototype;
import com.github.MageInTraining.adventura.game.STEM11Game;

import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JOptionPane;



/*******************************************************************************
 * The {@code UIA_JOptionPane} instances realize the user's interface
 * that utilizes services of the class {@link JOptionPane}.
 *
 * @author  Milan Stehlík
 * @version 2017-Winter
 */
public class UIA_JOptionPane implements IUI, ISTEM11Prototype
{
//\CC== CLASS CONSTANTS (CONSTANT CLASS/STATIC ATTRIBUTES/FIELDS) ==============
//\CV== CLASS VARIABLES (VARIABLE CLASS/STATIC ATTRIBUTES/FIELDS) ==============



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
     * that utilizes services of the class {@link JOptionPane}.
     */
    public UIA_JOptionPane()
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
        this.game = game;
        Component parent = new JFrame();
        parent.setLocation(300, 300);
        parent.setVisible(true);

        String command;
        String answer  = game.executeCommand("");
        do {
            command = JOptionPane.showInputDialog(parent, answer);
            answer  = game.executeCommand(command);
        } while (game.isAlive());
        JOptionPane.showMessageDialog(parent, answer);
        System.exit(0);
    }



//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================



//##############################################################################
//== MAIN METHOD ===============================================================

    /***************************************************************************
     * Method starting the default game
     * with the user's interface utilizing services
     * of the class {@link JOptionPane}.
     *
     * @param args Parameters of the command line - up-to-now unused
     */
    public static void main(String[] args)
    {
        new UIA_JOptionPane().startGame();
}
}
