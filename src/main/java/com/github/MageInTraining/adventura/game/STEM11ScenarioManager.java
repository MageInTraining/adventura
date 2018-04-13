/* The file is saved in UTF-8 codepage.
 * Check: «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */
package com.github.MageInTraining.adventura.game;

import eu.pedu.adv16w_fw.game_gui.IGSMFactoryG;
import eu.pedu.adv16w_fw.scenario.AScenarioManagerG;
import eu.pedu.adv16w_fw.scenario.AScenarioManager;
import eu.pedu.adv16w_fw.scenario.ScenarioStep;
import eu.pedu.adv16w_fw.scenario.TypeOfScenario;

import static eu.pedu.adv16w_fw.scenario.TypeOfStep.*;



/*******************************************************************************
 * Instance of the {@code RUPScenarioManagerLit} class serves as
 * scenario manager, that has to manage the scenarios of the associated game.
 * These scenarios should allow to test and demonstrate
 * the functionality of the associated game.
 * <p>
 * Each manager has to offer:
 * <ul>
 *   <li>The <b>happy scenario</b> (the basic successful one)
 *     demonstrating certain successful path through the game possibilities
 *     leading to the game goal.
 *   </li>
 *   <li>The <b>mistake scenario</b>
 *     demonstrating the game reaction to the wrongly entered commands.
 *   </li>
 * </ul>
 * <p>
 * Individual managed scenarios have to differ by their names;
 * the names of the happy scenario and the mistake one
 * are firmly pre-determined and cannot be arbitrarily set.
 * <p>
 * Individual scenarios are iterable and "streamable" sequences of steps
 * specified by instances of the framework class
 * {@link eu.pedu.adv16w_fw.scenario.ScenarioStep},
 * to which the designed game should associated.
 * <p>
 * Scenario manager is a singleton, that is responsible
 * for all scenarios concerning the game associated with it.
 *
 * @author  Milan STehlík
 * @version 2017-Winter
 */
public class STEM11ScenarioManager extends AScenarioManagerG
                               implements IAuthorMe //ISTEM11Prototype
{
//== CONSTANT CLASS FIELDS =====================================================
	
    /** Tovární třída, jejíž instancemi jsou tovární objekty poskytující
     *  instanci správce scénářů i hry, jejíž scénáře daný správce spravuje. */
    private final static 
            Class<? extends IGSMFactoryG> FACTORY_CLASS =
                                         STEM11GSMFactory.class;
 
    /** Name of the scenario with steps for testing mandatory actions. */
    private static final String REQUIRED_STEPS_SCENARIO_NAME = "REQUIRED";


    /***************************************************************************
     * The initial game step identical for all scenarios.
     * <p>
     * Constructor of the full-fledged {@link ScenarioStep} class instance
     * requires the following parameters:
     <pre> {@code
TypeOfStep typeOfStep; //Type of the given scenario step
String     command;    //Command realizing this scenario step
String     message;    //Message written after entering the command
String     space;      //Current space after entering the command
String[]   neighbors;  //Neighbors of the current space (= exits)
String[]   items;      //Items occuring in the current space
String[]   bag;        //Current bag content
     }</pre>
     =======================================================================<br>
     * Scenario steps have to comply with the following requirements:
     * <ul>
     *   <li>None the items may contain the {@code null} value.</li>
     *   <li>With the exception of {@code command} no string may be
     *     empty (i.e. {@code ""})</li>
     *   <li>Empty string may occur neither as an item in the array
     *     {@code neighbors}, nor {@code items} nor {@code bag}</li>
     * </ul>
     * <br>
     **************************************************************************/
     public static final ScenarioStep START_STEP =
        new ScenarioStep(0, tsSTART, "", //Název spouštěcího příkazu = ""
            "Jsi ve svém bytě. Nefunguje elektřina."
            ,
            "Byt",
            new String[] { "Sídliště" },
            new String[] { "penize", "objednavka", "trouba" },
            new String[] {}
        );


    /***************************************************************************
     * Steps of the happy scenario
     * describing the expectable successful game running. It is not necessary
     * for the scenario compiled of these steps to be the shortest possible
     * (it implies, that it has not to be the true basic successful scenario),
     * but it has to comply with all marginal conditions of the assignment,
     * i.e. it has to contain minimal number of steps,
     * pass through the required minimal number of spaces
     * and demonstrate using of all required actions.
     */
    private static final ScenarioStep[] HAPPY_SCENARIO_STEPS =
    {
        START_STEP,

        /*new ScenarioStep(1, tsTAKE, "tsTAKE penize",
            "Vzal jsi předmět: penize"
            ,
            "Byt",
            new String[] { "Sídliště" },
            new String[] { "objednavka", "trouba" },
            new String[] { "penize" }
        )
        ,

        new ScenarioStep(2, tsTAKE, "tsTAKE objednavka",
            "Vzal jsi předmět: objednavka"
            ,
            "Byt",
            new String[] { "Sídliště" },
            new String[] { "trouba" },
            new String[] { "penize", "objednavka" }
        )
        ,

        new ScenarioStep(3, tsMOVE, "tsMOVE Sídliště",
            "Přesunul jsi se do místnosti: Sídliště"
            ,
            "Sídliště",
            new String[] { "Centrum", "Obchoďák", "Byt" },
            new String[] {  },
            new String[] { "penize", "objednavka" }
        )
        ,

        new ScenarioStep(4, tsMOVE, "tsMOVE Centrum",
            "Přesunul jsi se do místnosti: Centrum"
            ,
            "Centrum",
            new String[] { "Sídliště", "Obchoďák", "Energetická_společnost",
            "Knihkupectví"},
            new String[] { "bankomat" },
            new String[] { "penize", "objednavka" }
        )
        ,
        new ScenarioStep(5, tsMOVE, "tsMOVE Knihkupectví",
            "Přesunul jsi se do místnosti: Knihkupectví"
            ,
            "Knihkupectví",
            new String[] { "Centrum"},
            new String[] { "prodavacka" },
            new String[] { "penize", "objednavka" }
        )
        ,

        new ScenarioStep(6, tsNON_STANDARD2, "tsGIVE  Objednávka_do_"
                        + "knihkupectví prodavacka",
            "Dal jsi prodavacka objednavka a "
                    + "obdržel jsi kucharka"
            ,
            "Knihkupectví",
            new String[] { "Centrum"},
            new String[] { "prodavacka" },
            new String[] { "penize", "kucharka" }
        )
        ,

        new ScenarioStep(7, tsMOVE, "tsMOVE  Centrum",
            "Přesunul jsi se do místnosti: Centrum"
            ,
            "Centrum",
            new String[] { "Sídliště", "Obchoďák", "Energetická_společnost",
            "Knihkupectví"},
            new String[] { "bankomat" },
            new String[] { "penize", "kucharka" }
        )
        ,

                new ScenarioStep(8, tsMOVE, "tsMOVE  Energetická_společnost",
            "Přesunul jsi se do místnosti: Energetická_společnost"
            ,
            "Energetická_společnost",
            new String[] { "Centrum"},
            new String[] { "urednik" },
            new String[] { "penize", "kucharka" }
        )
        ,

                new ScenarioStep(9, tsNON_STANDARD1, "tsTALK  urednik",
            "Dej urednikovi platbu za elektřinu"
            ,
            "Energetická_společnost",
            new String[] { "Centrum"},
            new String[] { "urednik" },
            new String[] { "penize", "kucharka" }
        )
        ,

                new ScenarioStep(10, tsNON_STANDARD2, "tsGIVE  penize urednik",
            "Zaplatil jsi elektřinu"
            ,
            "Energetická_společnost",
            new String[] { "Centrum"},
            new String[] { "urednik" },
            new String[] { "kucharka" }
        )
        ,

                new ScenarioStep(11, tsMOVE, "tsMOVE  Centrum",
            "Přesunul jsi se do místnosti: Centrum"
            ,
            "Centrum",
            new String[] { "Sídliště", "Obchoďák", "Energetická_společnost",
            "Knihkupectví"},
            new String[] { "bankomat" },
            new String[] { "kucharka" }
        )
        ,

                new ScenarioStep(12, tsMOVE, "tsMOVE  Sídliště",
            "Přesunul jsi se do místnosti: Sídliště"
            ,
            "Sídliště",
            new String[] { "Centrum", "Obchoďák", "Byt"},
            new String[] {  },
            new String[] { "kucharka" }
        )
        ,

                new ScenarioStep(13, tsMOVE, "tsMOVE  Byt",
            "Přesunul jsi se do místnosti: Byt"
            ,
            "Byt",
            new String[] { "Sídliště"},
            new String[] { "trouba" },
            new String[] { "kucharka" }
        )
        ,

                new ScenarioStep(14, tsPUT_DOWN, "tsPUT_DOWN  kucharka",
            "Položil jsi předmět: kucharka"
            ,
            "Byt",
            new String[] { "Sídliště"},
            new String[] { "trouba", "kucharka" },
            new String[] {  }
        )
        ,

                new ScenarioStep(15, tsNON_STANDARD1, "tsUSE  kucharka",
            "Úspěšně jsi použil předmět"
            ,
            "Byt",
            new String[] { "Sídliště"},
            new String[] { "trouba", "kucharka" },
            new String[] {  }
        )
        ,

                new ScenarioStep(16, tsMOVE, "tsMOVE  Sídliště",
            "Přesunul jsi se do místnosti: Sídliště"
            ,
            "Sídliště",
            new String[] { "Centrum", "Obchoďák", "Byt"},
            new String[] {  },
            new String[] {  }
        )
        ,

                new ScenarioStep(17, tsMOVE, "tsMOVE  Obchoďák",
            "Přesunul jsi se do místnosti: Obchoďák"
            ,
            "Obchoďák",
            new String[] { "Centrum", "Sídliště", "Masna", "Ovoce_a_zelenina"},
            new String[] { "bankomat" },
            new String[] {  }
        )
        ,

                new ScenarioStep(18, tsNON_STANDARD1, "tsUSE  bankomat",
            "Úspěšně jsi použil předmět"
            ,
            "Obchoďák",
            new String[] { "Centrum", "Sídliště", "Masna", "Ovoce_a_zelenina"},
            new String[] { "bankomat" },
            new String[] { "penize" }
        )
        ,
                new ScenarioStep(19, tsMOVE, "tsMOVE Masna",
            "Přesunul jsi se do místnosti: Masna"
            ,
            "Masna",
            new String[] { "Obchoďák"},
            new String[] { "kure" },
            new String[] { "penize" }
        )
        ,

                new ScenarioStep(20, tsNON_STANDARD2, "tsBUY kure penize",
            "Koupil jsi: kure"
            ,
            "Masna",
            new String[] { "Obchoďák"},
            new String[] { "kure" },
            new String[] { "kure" }
        )
        ,

                new ScenarioStep(21, tsMOVE, "tsMOVE Obchoďák",
            "Přesunul jsi se do místnosti: Obchoďák"
            ,
            "Obchoďák",
            new String[] { "Centrum", "Sídliště", "Masna", "Ovoce_a_zelenina"},
            new String[] { "bankomat" },
            new String[] { "kure" }
        )
        ,

                new ScenarioStep(22, tsNON_STANDARD1, "tsUSE bankomat",
            "Úspěšně jsi použil předmět"
            ,
            "Obchoďák",
            new String[] { "Centrum", "Sídliště", "Masna", "Ovoce_a_zelenina"},
            new String[] { "bankomat" },
            new String[] { "kure", "penize" }
        )
        ,

        new ScenarioStep(23, tsMOVE, "tsMOVE Ovoce_a_zelenina",
            "Přesunul jsi se do místnosti: Ovoce_a_zelenina"
            ,
            "Ovoce_a_zelenina",
            new String[] { "Obchoďák"},
            new String[] { "brambory" },
            new String[] { "kure", "penize" }
        )
        ,

        new ScenarioStep(24, tsNON_STANDARD2, "tsBUY brambory penize",
            "Koupil jsi: brambory"
            ,
            "Ovoce_a_zelenina",
            new String[] { "Obchoďák"},
            new String[] { "brambory" },
            new String[] { "kure", "brambory" }
        )
        ,
                new ScenarioStep(25, tsMOVE, "tsMOVE Obchoďák",
            "Přesunul jsi se do místnosti: Obchoďák"
            ,
            "Obchoďák",
            new String[] { "Centrum", "Sídliště", "Masna", "Ovoce_a_zelenina"},
            new String[] { "bankomat" },
            new String[] { "kure", "brambory" }
        )
        ,
                new ScenarioStep(26, tsMOVE, "tsMOVE  Sídliště",
            "Přesunul jsi se do místnosti: Sídliště"
            ,
            "Sídliště",
            new String[] { "Centrum", "Obchoďák", "Byt"},
            new String[] {  },
            new String[] { "kure", "brambory" }
        )
        ,

                new ScenarioStep(27, tsMOVE, "tsMOVE  Byt",
            "Přesunul jsi se do místnosti: Byt"
            ,
            "Byt",
            new String[] { "Sídliště"},
            new String[] { "trouba", "kucharka" },
            new String[] { "kure", "brambory" }
        )
        ,

//                new ScenarioStep(28, tsNON_STANDARD2, "tsGIVE  kure trouba",
//            "Strčil jsi omyté kure do trouby"
//            ,
//            "Byt",
//            new String[] { "Sídliště"},
//            new String[] { "trouba", "kucharka" },
//            new String[] { "brambory" }
//        )
//        ,
//
//        new ScenarioStep(29, tsNON_STANDARD2, "tsGIVE  brambory "
//                        + "trouba",
//            "Strčil jsi omyté a nakrájené brambory do trouby"
//            ,
//            "Byt",
//            new String[] { "Sídliště"},
//            new String[] { "trouba", "kucharka" },
//            new String[] {  }
//        )
//        ,*/
         new ScenarioStep(28, tsNON_STANDARD1, "tsUSE  trouba",
            "Úspěšně jsi použil předmět"
            ,
            "Byt",
            new String[] { "Sídliště"},
            new String[] { "trouba", "kucharka" },
            new String[] { "kure", "brambory" }
        )
        ,
        
        new ScenarioStep(29, tsEND, "end",
            "Konec hry.\n" +
            "Díky za pozornost."
            ,
            "Byt",
            new String[] { "Sídliště" },
            new String[] { "trouba", "kucharka" },
            new String[] { "kure", "brambory" }
        )

    };


    /** Step testing the incorrect game starting is defined separately,
     *  so that the indexes of the following steps could be simply adjusted. */
    private static final ScenarioStep WRONG_START =
    new ScenarioStep(-1,
            tsNOT_START, "Start",
            "První příkaz je startovací.\n" +
"              Neběžící hra může být zapnuta startovacím příkazem."
            ,
            "",
            new String[] {},
            new String[] {},
            new String[] {}
        );


    static { ScenarioStep.setIndex(1); }


    /***************************************************************************
     * Mistake scenario defining reactions
     * to mandatory set of types of incorrectly given commands.
     */
    private static final ScenarioStep[] MISTAKE_SCENARIO_STEPS =
    {
        WRONG_START,

        START_STEP,

        new ScenarioStep(1, tsUNMOVABLE, "tsTAKE trouba",
            "Zadaná akce nebyla provedena.\n" +
            "Nemůžeš sebrat zadaný předmět, tvoje taška je plná."
            ,
            "Byt",
            new String[] { "Sídliště" },
            new String[] { "penize", "objednavka", "trouba" },
            new String[] {  }
        )
        ,

   new ScenarioStep(2, tsUNKNOWN   , "tsUNKNOWN penize",
        "Zadaná akce nebyla provedena.\n" +
        "Tento příkaz neznám.\n" +
        "Pro nápovědu zadej tsHELP"
            ,
            "Byt",
            new String[] { "Sídliště" },
            new String[] {  "penize", "objednavka", "trouba"  },
            new String[] {  }
        ),        

   new ScenarioStep(3, tsBAD_ITEM , "tsTAKE kure",
            "Zadaná akce nebyla provedena.\n" +
            "Zadanáý předmět není v této oblasti: kure"
            ,
            "Byt",
            new String[] { "Sídliště" },
            new String[] {  "penize", "objednavka", "trouba" },
            new String[] {  }
        ),               

   new ScenarioStep(4, tsPUT_DOWN_WA    , "tsPUT_DOWN",
            "Zadaná akce nebyla provedena.\n" +
            "Nezadal jsi co má být položeno"
            ,
            "Byt",
            new String[] { "Sídliště" },
            new String[] {  "penize", "objednavka", "trouba" },
            new String[] {  }
        ),           

   new ScenarioStep(5, tsHELP , "tsHELP",
            "Pomož si a bude ti pomoženo -JK"
            ,
            "Byt",
            new String[] { "Sídliště" },
            new String[] {  "penize", "objednavka", "trouba" },
            new String[] {  }
        ), 

   new ScenarioStep(6, tsNOT_IN_BAG    , "tsPUT_DOWN kure",
            "Zadaná akce nebyla provedena.\n" +
            "Předmět není v tašce: kure"
            ,
            "Byt",
            new String[] { "Sídliště" },
            new String[] {  "penize", "objednavka", "trouba" },
            new String[] {  }
        ),  

   new ScenarioStep(7, tsMOVE_WA , "tsMOVE",
            "Zadaná akce nebyla provedena.\n" +
            "Nezadal jsi kam se přesunout"
            ,
            "Byt",
            new String[] { "Sídliště"  },
            new String[] {  "penize", "objednavka", "trouba" },
            new String[] {  }
        ),        
        

   new ScenarioStep(8, tsBAD_NEIGHBOR , "tsMOVE Vedle",
            "Zadaná akce nebyla provedena.\n" +
            "Zadaná oblast s touto nesousedí této oblasti: vedle"
            ,
            "Byt",
            new String[] { "Sídliště" },
            new String[] {  "penize", "objednavka", "trouba" },
            new String[] {  }
        ),    
        

   new ScenarioStep(9, tsTAKE , "tsTAKE penize",
            "Vzal jsi předmět: penize"
            ,
            "Byt",
            new String[] { "Sídliště" },
            new String[] {  "objednavka", "trouba" },
            new String[] { "penize" }
        ),

   new ScenarioStep(10, tsTAKE , "tsTAKE objednavka",
            "Vzal jsi předmět: objednavka"
            ,
            "Byt",
            new String[] { "Sídliště" },
            new String[] { "trouba" },
            new String[] { "penize", "objednavka" }
        ),

    new ScenarioStep(11, tsMOVE, "tsMOVE  Sídliště",
            "Přesunul jsi se do místnosti: Sídliště"
            ,
            "Sídliště",
            new String[] { "Centrum", "Obchoďák", "Byt"},
            new String[] {  },
            new String[] { "penize", "objednavka"  }
        )
        ,

                new ScenarioStep(12, tsMOVE, "tsMOVE  Obchoďák",
            "Přesunul jsi se do místnosti: Obchoďák"
            ,
            "Obchoďák",
            new String[] { "Centrum", "Sídliště", "Masna", "Ovoce_a_zelenina"},
            new String[] { "bankomat" },
            new String[] { "penize", "objednavka"  }
        )
        ,

                new ScenarioStep(13, tsMOVE, "tsMOVE Masna",
            "Přesunul jsi se do místnosti: Masna"
            ,
            "Masna",
            new String[] { "Obchoďák"},
            new String[] { "kure" },
            new String[] { "penize", "objednavka"  }
        )
        ,

                new ScenarioStep(14, tsNON_STANDARD2, "tsBUY kure penize",
            "Koupil jsi: kure"
            ,
            "Masna",
            new String[] { "Obchoďák"},
            new String[] { "kure" },
            new String[] { "kure", "objednavka"  }
        )
        ,

                new ScenarioStep(15, tsMOVE, "tsMOVE Obchoďák",
            "Přesunul jsi se do místnosti: Obchoďák"
            ,
            "Obchoďák",
            new String[] { "Centrum", "Sídliště", "Masna", "Ovoce_a_zelenina"},
            new String[] { "bankomat" },
            new String[] { "kure", "objednavka" }
        )
        ,

                new ScenarioStep(16, tsNON_STANDARD1, "tsUSE bankomat",
            "Úspěšně jsi použil předmět"
            ,
            "Obchoďák",
            new String[] { "Centrum", "Sídliště", "Masna", "Ovoce_a_zelenina"},
            new String[] { "bankomat" },
            new String[] { "kure", "penize", "objednavka" }
        )
        ,

                new ScenarioStep(17, tsMOVE, "tsMOVE Ovoce_a_zelenina",
            "Přesunul jsi se do místnosti: Ovoce_a_zelenina"
            ,
            "Ovoce_a_zelenina",
            new String[] { "Obchoďák"},
            new String[] { "brambory" },
            new String[] { "kure", "penize", "objednavka" }
        )
        ,

                new ScenarioStep(18, tsBAG_FULL, "tsTAKE brambory",
            "Zadaná akce nebyla provedena.\n" +
            "Nemůžeš sebrat zadaný předmět, tvoje taška je plná."
            ,
            "Ovoce_a_zelenina",
            new String[] { "Obchoďák"},
            new String[] { "brambory" },
            new String[] { "kure","penize", "objednavka" }
        )
        ,

                new ScenarioStep(19, tsEMPTY, "",
            "Zadal jsi prázdný příkaz.\n" +
            "Ten můžeš zadat jen při startu hry.\n" +
            "Pro nápovědu zadej tsHELP"
            ,
            "Ovoce_a_zelenina",
            new String[] { "Obchoďák" },
            new String[] { "brambory" },
            new String[] { "kure","penize", "objednavka" }
            )
            , 

            new ScenarioStep(20, tsTAKE_WA , "tsTAKE",
            "Zadaná akce nebyla provedena.\n" +
            "Nezadal jsi co má být sebráno"
            ,
            "Ovoce_a_zelenina",
            new String[] { "Obchoďák"  },
            new String[] {  "brambory" },
            new String[] { "kure","penize", "objednavka" }
        )
        ,

        new ScenarioStep(21, tsEND, "end",
            "Konec hry.\n" +
            "Díky za pozornost."
            ,
            "Ovoce_a_zelenina",
            new String[] { "Obchoďák" },
            new String[] { "brambory" },
            new String[] { "kure","penize", "objednavka" }
        )
    };

    /***************************************************************************
     * Scenario steps determined for verification of mandatory actions,
     * precisely actions for moving to a space, taking and putting an item,
     * writing the help and prematured game termination.
     */
    private static final ScenarioStep[] REQUIRED_STEPS =
    {
        START_STEP
        ,
        new ScenarioStep(1, tsHELP , "tsHELP",
            "Pomož si a bude ti pomoženo -JK"
            + "\ntsHELP"
            + "\nNapíše pomoc s popisem všech příkazů."
            ,
            "Byt",
            new String[] { "Sídliště" },
            new String[] {  "penize", "objednavka", "trouba" },
            new String[] {  }
        )
        ,
        new ScenarioStep(2, tsTAKE, "tsTAKE penize",
            "Vzal jsi předmět: penize"
            ,
            "Byt",
            new String[] { "Sídliště" },
            new String[] { "objednavka", "trouba" },
            new String[] { "penize" }
        )
        ,
        new ScenarioStep(3, tsPUT_DOWN, "tsPUT_DOWN  penize",
            "Položil jsi předmět: penize"
            ,
            "Byt",
            new String[] { "Sídliště"},
            new String[] { "penize", "objednavka", "trouba" },
            new String[] {  }
        )
        ,
        new ScenarioStep(4, tsMOVE, "tsMOVE Sídliště",
            "Přesunul jsi se do místnosti: Sídliště"
            ,
            "Sídliště",
            new String[] { "Centrum", "Obchoďák", "Byt" },
            new String[] {  },
            new String[] {  }
        )
        ,
        new ScenarioStep(5, tsEND, "end",
            "Konec hry.\nDíky za pozornost."
            ,
            "Sídliště",
            new String[] { "Centrum", "Obchoďák", "Byt" },
            new String[] {  },
            new String[] {  }
        )
        ,
    };


    /** The only instance of this class.
     *  It manages all scenarios of the associated game. */
    private static final STEM11ScenarioManager MANAGER =
                                          new STEM11ScenarioManager();



//== VARIABLE CLASS FIELDS =====================================================



//##############################################################################
//== STATIC INITIALIZER (CLASS CONSTRUCTOR) ====================================

    /***************************************************************************
     * Static constructor is placed before definitions of constants
     * {@link #AGE}, {@link #THIS_YEAR} and {@link #BORN_YEAR}
     * and once again before the definition of a constant
     * {@link MISTAKE_SCENARIO_STEPS}.
     * Such initialization should be before each further constant
     * defining the steps of the following scenario.
     */



//== CLASS GETTERS AND SETTERS =================================================
//== OTHER NON-PRIVATE CLASS METHODS ===========================================
//== PRIVATE AND AUXILIARY CLASS METHODS =======================================



//##############################################################################
//== CONSTANT INSTANCE FIELDS ==================================================
//== VARIABLE INSTANCE FIELDS ==================================================



//##############################################################################
//== CONSTRUCTORS AND FACTORY METHODS ==========================================

    /***************************************************************************
     * Returns scenario manager - the only instance of this class.
     *
     * @return Scenario manager
     */
    public static STEM11ScenarioManager getInstance()
    {
        return MANAGER;
    }


    /***************************************************************************
     * Creates an instance representing the game scenario manager.
     * Within the constructor framework it is suitable to create all scenarios
     * and seal the scenario manager after it.
     * <p>
     * Individual managed scenarios have to differ by their names,
     * the names of the happy scenario and the mistake one
     * are firmly pre-determined and cannot be changed.
     * Names given to them in the
     * {@link #addScenario(String, TypeOfScenario, ScenarioStep...)} method
     * are therefore only formal, because the called method assignes to them
     * the names defined in advance in appropriate constants.
     * <p>
     *´Contract of the
     * {@link #addScenario(String, TypeOfScenario, ScenarioStep...)} method
     * requires so that the happy scenario, i.e. scenario of the
     * {@link TypeOfScenario.scHAPPY}) type, would be added as the first one,
     * and the mistake scenario, i.e. the scenario of the
     * {@link MISTAKE_SCENARIO_NAME} type, as the second one.
     * The order of the subsequently added scenarios is not decisive.
      */
    private STEM11ScenarioManager()
    {
        super(FACTORY_CLASS);

        addScenario(HAPPY_SCENARIO_NAME,
                    TypeOfScenario.scHAPPY,    HAPPY_SCENARIO_STEPS);
        addScenario(MISTAKE_SCENARIO_NAME,
                    TypeOfScenario.scMISTAKES, MISTAKE_SCENARIO_STEPS);
        addScenario(REQUIRED_STEPS_SCENARIO_NAME,
                    TypeOfScenario.scGENERAL,  REQUIRED_STEPS);
        seal();
    }



//== ABSTRACT METHODS ==========================================================
//== INSTANCE GETTERS AND SETTERS ==============================================
//== OTHER NON-PRIVATE INSTANCE METHODS ========================================
//== PRIVATE AND AUXILIARY INSTANCE METHODS ====================================



//##############################################################################
//== NESTED DATA TYPES =========================================================



//##############################################################################
//== TEST METHODS AND CLASSES ==================================================

    /***************************************************************************
     * Method verifying the given scenario manager and the associated game
     * by scenarios of this manager.
     * <p>
     * The scenario manager is verified if it complies
     * with the following conditions:
     * <ul>
     *   <li>It knows to return properly formated name of the game author
     *       and his/her ID.</li>
     *   <li>It defines the happy scenario and the mistake one.</li>
     *   <li>The mistake scenario has the following properties:
     *     <ul>
     *       <li>Starting command, the name of which is an empty string</li>
     *       <li>Minimal required number of steps</li>
     *       <li>Minimal number of visited spaces</li>
     *       <li>Minimal number of "glimpsed" spaces</li>
     *       <li>Minimal number of own (optional) actions</li>
     *       <li>Usage of actions for moving from the current space
     *         to a neighboring space, taking item and putting down item</li>
     *       <li>Cross consistence of actions and states after execution
     *         of the actions</li>
     *     </ul>
     *   </li>
     *   <li>The mistake scenario has the following properties:
     *     <ul>
     *       <li>Incorrect starting by a not empty command,</li>
     *       <li>Starting command the name of which is an empty string</li>
     *       <li>Usage of all mandatory error step types defined in the<br>
     *         {@link eu.pedu.adv16w_fw.scenario.TypeOfStep} enum type</li>
     *       <li>Asking for a help</li>
     *       <li>Premature game termination</li>
     *     </ul>
     *   </li>
     * </ul>
     * <p>
     * The game is verified if it can be played exactly
     * as is planned in scenarios.
     *
     * @param args Command line parameters - unused.
     */
    public static void main(String[] args)
    {
        //Tests if the scenario manager and its scenarios
        //comply with requirements
//%U+ «A107
//        MANAGER.autoTest();

        //Simulates playing the game according to happy scenario
//        MANAGER.getHappyScenario().simulate();

        //Game testing made gradually according to both mandatory scenarios,
        //the happy scenario is passed twice one after the other
//        MANAGER.testGame();

        //Game testing according to scenarios with the given names
//        MANAGER.testGameByScenarios(REQUIRED_STEPS_SCENARIO_NAME);

        //Playing the game according to the scenario with the given name
//        MANAGER.playGameByScenario("???");

        System.exit(0);
    }
    
    @Override
    public String getAuthorName() {
        return AUTHOR_NAME;
      //  throw new UnsupportedOperationException("Not supported yet.");
      //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getAuthorID() {
        return AUTHOR_ID;
       // throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Class<? extends IGSMFactoryG> getFactoryClass() {
        return STEM11GSMFactory.class;
       // throw new UnsupportedOperationException("Not supported yet.");
        //To change body of generated methods, choose Tools | Templates.
    }

}
