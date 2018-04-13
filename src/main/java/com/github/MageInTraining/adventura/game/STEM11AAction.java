/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura.game;

import eu.pedu.adv16w_fw.game_txt.IAction;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


import static com.github.MageInTraining.adventura.game.STEM11Texts.*;



/*******************************************************************************
 * The {@code AAction} abstract class is a common superclass of all classes,
 * the instances of which are responsible for interpretation of commands
 * entered by the user playing the game.
 * Name of the executed action is usually the first word of the entered command.
 * The further words are interpreted as arguments.
 * <p>
 * You can define also a command that opens the conversation
 * (e.g. with a person present in the room) and thus switch to the mode,
 * in which the entered texts are not interpreted as commands,
 * but are passed to the defined object up to moment,
 * when the conversation ends and the object controlling the dialogue
 * switches the game back to the basic command mode.
 *
 * @author  Milan STEHLÍK
 * @version 2016-Summer
 */
abstract class STEM11AAction extends ANamed implements IAction
{
//== CONSTANT CLASS FIELDS =====================================================

    /** Map mediating the conversion of the action name to its instance. */
    private static final Map<String, STEM11AAction> NAME_2_ACTION;



//== VARIABLE CLASS FIELDS =====================================================

    /** Keeps information if the game is just played
     *  or only waits for being started. */
    private static boolean isAlive;



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================

    static {
        isAlive = false;
        NAME_2_ACTION = new HashMap<>();
        new tsPICK_UP();
        new tsMOVE();
        new tsHELP();
        new tsPUT_DOWN();
        new tsEND();
        new tsUSE();
        new tsGIVE();
       // new tsTALK();
            }



//== CLASS GETTERS AND SETTERS =================================================

 /***************************************************************************
  * Returns information if the game is currently running.
  * The running game cannot be started once again.
  * If we want to start the game again, we have to finish it firstly.
  *
  * @return If the game is running, it returns {@code true},
  *         otherwise it returns {@code false}
  */
    public static boolean isAlive()
    {
        return isAlive;
    }


    /***************************************************************************
     * Returns a collection of all actions that can be used in the game.
     *
     * @return A collection of all actions usable in the game
     */
    static Collection<STEM11AAction> getAllActions()
    {
        Collection<STEM11AAction> collection, result;
        collection = NAME_2_ACTION.values();
        result     = Collections.unmodifiableCollection(collection);
        return result;
    }



//== OTHER NON-PRIVATE CLASS METHODS ===========================================

/***************************************************************************
* Processes the given command and returns the answer to the user.
*
* @param command The entered command
* @return The answer of the game after processing the command
*/
    public static String executeCommand(String command)
    {
        command = command.trim();
        String answer;
        if (isAlive) {
            answer = executeCommonComand(command);
        }
        else {
            answer = startGame(command);
        }
        return answer;
    }


    /***************************************************************************
     * Ensures the correct ending of the game
     * and saves information that the game is finished.
     */
    static void stopGame()
    {
        isAlive = false;
    }



//== PRIVATE AND AUXILIARY CLASS METHODS =======================================

     /***************************************************************************
     * The method gradually initializes all key objects of the game.
     */
    private static void initialize()
    {
        STEM11World.getInstance().initialize();
        STEM11Bag    .getInstance().initialize();
        STEM11State                  .initialize();
    }


    /***************************************************************************
    * Finds out which action should be activated by the entered command,
    * and if it is a known action, it accomplishes this action.
    * Otherwise it returns the error message about the wrongly entered command.
    *
    * @param command Entered command
    * @return  Game answer to the entered command
    */
    private static String executeCommonComand(String command)
    {
        if (command.isEmpty()) {
            return STEM11Texts.mEMPTY_CMD;   //==========>
        }
        String[] words       = command.toLowerCase().split("\\s+");
        String   acttionName = words[0];
        STEM11AAction  action      = NAME_2_ACTION.get(acttionName);
        String   answer;
        if (action == null) {
            answer = STEM11Texts.mNO_CMD;
        }
        else {
            answer = action.execute(words);
        }
        return answer;
    }


/***************************************************************************
 * Verifies if the game is started with the proper (= empty) command,
 * and if yes, starts the game.
 *
 * @param command Command starting the game
 * @return  Game answer to the entered command
 */
    private static String startGame(String command)
    {
        String answer;
        if (command.isEmpty()) {
            initialize();
            answer  = STEM11Texts.mWELCOME_MSG;
            isAlive = true;
        }
        else {
            answer = STEM11Texts.mNOT_START;
        }
        return answer;
    }



//##############################################################################
//== CONSTANT INSTANCE FIELDS ==================================================

    /** Brief description of the given action. */
    private final String description;



//== VARIABLE INSTANCE FIELDS ==================================================



//##############################################################################
//== CONSTRUCTORS AND FACTORY METHODS ==========================================

    /***************************************************************************
     * Creates the parent sub-object of the created action.
     *
     * @param name  Name of the created action = text, which the player has to
     *              enter as an initial word of the entered command
     * @param description Brief description of the created action
     */
    STEM11AAction(String name, String description)
    {
        super(name);
        this.description = description;
        STEM11AAction previous = NAME_2_ACTION.put(name.toLowerCase(), this);
        if (previous != null) {
            throw new IllegalArgumentException(eAACTION_1 + name + eAACTION_2);
//                "\nAction «" + name + "» has been already created");
        }
    }



//== ABSTRACT METHODS ==========================================================

    /***************************************************************************
     * Processes the command composed from the given words
     * and returns the game answer to the user.
     * Number of word depends on particular action, however it must be
     * at least one, because the zeroth element contains the action name.
     * The remaining words are arguments of this action and they may differ:
     * the actions of <i>end</i> and <i>help</i> type do not have any,
     * the actions of <i>go</i> and <i>take</i> type have one,
     * the actions of <i>apply</i> type ) can have two (e.g. apply key lock)
     * or three (e.g. apply key to lock) etc.
     *
     * @param arguments Action arguments –
     *                  their number can be different for each action,
     *                  but for all execution of the same action is the same
     * @return The answer of the game after processing the command
     */
    @Override
    abstract
    public String execute(String... arguments)
    ;



//== INSTANCE GETTERS AND SETTERS ==============================================

    /***************************************************************************
     * Returns the action description with explanation of its function
     * and the meaning of individual parameters.
     * This description can be used as a help for usage of this action.
     *
     * @return Action description
     */
    @Override
    public String getDescription()
    {
        return description;
    }



//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
