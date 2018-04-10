

package com.github.MageInTraining.adventura.gui;

import eu.pedu.adv16w_fw.game_gui.IGameG;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

/**
 * Třída představujáící tlačitko s sousedními prostorami
 * @author Jan Novosad
 */
public class NeighboroughButton extends Button
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
    
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ============================================

//##########################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
/**
 * Kontruktor
 * @param nazev název souseda
 * @param gui hlavní manažer gui
 */
    public NeighboroughButton(String nazev, GUI gui)
    {
        super(nazev);
        this.setOnAction(e -> OnNeighboroughButtonEvent());
        this.gui = gui;
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
     *  Handler metoda při kliknutí na tlačítko. Pošle příkaz pro předchod do
     *  prostoru (delegace přez commandPane)
     */
    private void OnNeighboroughButtonEvent()
    {
        IGameG currentGame = gui.getGame();
        String moveActionName = currentGame.getBasicActions().MOVE_CMD_NAME;
        
        //Pokud hra skončila, tak nebudem nic dělat
        if ( currentGame.isAlive())
        {
            gui.getCommandPane().setText(moveActionName + " " + getText());
            gui.getCommandPane().getTextFieldEventHandler()
                            .handle(new ActionEvent());  
        }
  
    }
}
