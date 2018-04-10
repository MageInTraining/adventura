

package com.github.MageInTraining.adventura.gui;

import eu.pedu.adv16w_fw.game_gui.IGameG;
import eu.pedu.adv16w_fw.game_gui.Icon;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;

/**
 * Třída představující tlačitko věcí která můžou být umístěna v prostoru
 * nebo v batohu
 * @author Jan Novosad
 */
public class ItemButton extends Button
{

    
//== KONSTANTNÍ ATRIBUTY TŘÍDY =============================================
//== PROMĚNNÉ ATRIBUTY TŘÍDY ===============================================


//##########################################################################
//== STATICKÝ INICIALIZAČNÍ BLOK - STATICKÝ KONSTRUKTOR ====================
//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ TŘÍDY ====================================
//== OSTATNÍ NESOUKROMÉ METODY TŘÍDY =======================================
//== SOUKROMÉ A POMOCNÉ METODY TŘÍDY =======================================

//##########################################################################
//== KONSTANTNÍ ATRIBUTY INSTANCÍ ==========================================
    
    /** Hlavní manažer GUI*/
    private final GUI gui;
    
    /** Jméno věci které tlačítko představuje */
    private final String NAME;
    
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ============================================

//##########################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
/**
 *  Kontruktor
 * @param name jméno věci pro zobrazení popisku 
 * @param obrazek obrázek ve formě Icon
 * @param gui Hlavní gui manažer
 */
    public ItemButton(String name, Icon obrazek, GUI gui)
    {
        super(null, obrazek);
        this.setOnAction(e -> OnItemButtonEvent());
        this.gui = gui;
        this.NAME = name;
        this.setTooltip(new Tooltip(name));
    }    

//== ABSTRAKTNÍ METODY =====================================================
//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =================================

//#########################################################################
//== ABSTRACT GETTERS AND SETTERS ==========================================
//== OSTATNÍ NESOUKROMÉ METODY INSTANCÍ ====================================        
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ====================================

//##########################################################################
//== INTERNÍ DATOVÉ TYPY ===================================================
    /**
     *  Handler metoda při kliknutí na tlačítko. Na základě toho kde se 
     * tlačitko nachází tak se provede odpovidajcí příkaz získaný z přepravky
     * základních příkazů od hry. Příkazy deleguje přez commandPane
     */
    private void OnItemButtonEvent()
    {
        IGameG currentGame = gui.getGame();
        String takeActionName = currentGame.getBasicActions().TAKE_CMD_NAME;
        String putDownActionName = currentGame.getBasicActions()
                                                            .PUT_DOWN_CMD_NAME;
        
        //Pokud hra skončila, tak nebudem nic dělat
        if (currentGame.isAlive())
        {
            if (this.getParent() == gui.getItemBox())   
            {
                gui.getCommandPane().setText(takeActionName + " " + NAME);
                gui.getCommandPane().getTextFieldEventHandler()
                                .handle(new ActionEvent());

            }
            else
            {
                gui.getCommandPane().setText(putDownActionName + " " + NAME);
                gui.getCommandPane().getTextFieldEventHandler()
                                .handle(new ActionEvent());
            }
        }
    }
}
