/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura.game;



/*******************************************************************************
 * The library class {@code State} is a box for various flags,
 * that should be remembered in the game course.

 *
 * @author  Milan STEHLÍK
 * @version 2017-Winter
 */
public class STEM11State
{
//== CONSTANT CLASS FIELDS =====================================================
//== VARIABLE CLASS FIELDS =====================================================

    /** Flag of paid electricity bill. */
    private static boolean electricityPaid;


    /** Flag of having money. */
    private static boolean haveMoney;



    /** Flag of having bookstore order. */
    private static boolean haveBookstoreOrder;


    /** Flag of knowing the recipe. */
    private static boolean knowRecipe;
    
    /** Flag of cooking the chicken. */
    private static boolean cookedChicken;



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================
//== CLASS GETTERS AND SETTERS =================================================

    /***************************************************************************
     * Returns information if the electricity bill is paid.
     *
     * @return {@code true} if he has paid the bill, otherwise {@code false}
     */
    static boolean isElectricityPaid()
    {
        return electricityPaid;
    }


    /***************************************************************************
     * Sets the flag indicating if the electricity bill is paid.
     *
     * @param electricityPaid The set value
     */
    static void setElectricityPaid(boolean electricityPaid)
    {
        STEM11State.electricityPaid = electricityPaid;
    }


    /***************************************************************************
     * Returns information if the player has money.
     *
     * @return {@code true} if he has money, otherwise {@code false}
     */
    static boolean haveMoney()
    {
        return haveMoney;
    }



    /***************************************************************************
     * Sets the flag indicating if the player has money.
     *
     * @param haveMoney The set value
     */
    static void setHaveMoney(boolean haveMoney)
    {
        STEM11State.haveMoney = haveMoney;
    }


    /***************************************************************************
     * Returns information if the player has bookstore order.
     *
     * @return {@code true} if he has the bookstore order,
     * otherwise {@code false}
     */
    static boolean haveBookstoreOrder()
    {
        return haveBookstoreOrder;
    }


    /***************************************************************************
     * Sets the flag indicating if the player has money.
     *
     * @param haveMoney The set value
     */
    static void setHaveBookstoreOrder(boolean haveBookstoreOrder)
    {
        STEM11State.haveBookstoreOrder = haveBookstoreOrder;
    }


    /***************************************************************************
     * Returns information if the player has money.
     *
     * @return {@code true} if he has money, otherwise {@code false}
     */
    static boolean knowRecipe()
    {
        return knowRecipe;
    }


    /***************************************************************************
     * Sets the flag indicating if the player has money.
     *
     * @param haveMoney The set value
     */
    static void setKnowRecipe(boolean knowRecipe)
    {
        STEM11State.knowRecipe = knowRecipe;
    }
    
    /***************************************************************************
     * Returns information if the player has money.
     *
     * @return {@code true} if he has money, otherwise {@code false}
     */
    static boolean isChickenCooked()
    {
        return cookedChicken;
    }


    /***************************************************************************
     * Sets the flag indicating if the player has money.
     *
     * @param haveMoney The set value
     */
    static void setChickenCooked(boolean cookedChicken)
    {
        STEM11State.cookedChicken = cookedChicken;
    }



//== OTHER NON-PRIVATE CLASS METHODS ===========================================

    /***************************************************************************
     * Initializes all attributes keeping information
     * concerning the current state of the game and its parts.
     */
    static void initialize()
    {
        electricityPaid    = false;
        haveMoney = false;
        haveBookstoreOrder= false;
        knowRecipe = false;
        cookedChicken = false;
    }



//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE FIELDS ==================================================
//== VARIABLE INSTANCE FIELDS ==================================================



//##############################################################################
//== CONSTRUCTORS AND FACTORY METHODS ==========================================

    /***************************************************************************
     * Private constructor preventing to create an instance.
     */
    private STEM11State()
    {
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================
}
