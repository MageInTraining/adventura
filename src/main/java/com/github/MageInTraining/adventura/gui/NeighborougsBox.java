

package com.github.MageInTraining.adventura.gui;

import eu.pedu.adv16w_fw.game_gui.IGameG;
import eu.pedu.adv16w_fw.game_gui.ISpaceG;
import eu.pedu.adv16w_fw.game_txt.ISpace;
import java.util.Collection;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Třída představují VBox ve kterém jsou umístěny tlačíka sousedů současného 
 * prostoru
 * @author Jan Novosad
 */
class NeighborougsBox extends VBox implements IInitializable
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

    /** Současná hra */
    private IGameG currentGame;
    
//##########################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    /**
     * Kontruktor
     * @param gui Hlavní manažer gui
     */
    public NeighborougsBox(GUI gui)
    {
        this.gui = gui;
        this.setAlignment(Pos.TOP_CENTER);
    }
//== ABSTRAKTNÍ METODY =====================================================
//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =================================

//#########################################################################
//== ABSTRACT GETTERS AND SETTERS ==========================================
//== OSTATNÍ NESOUKROMÉ METODY INSTANCÍ ====================================   
    
    /**
     * Inicializace objektu při sputění hry
     * @param game Hra pro kterou se má objekt inicializovat 
     */
    @Override
    public void initializeFor(IGameG game)
    {
        this.currentGame = game;
    }
    
    /**
     * Metoda která smaže obsah boxu a vytvoří v něm nové tlačítka sousedů 
     * odpovídajcí současnému stavu hry.
     **/
    public void refresh()
    {
        this.getChildren().clear();

        //získání kolekce se sousedy
        ISpaceG currentSpace = gui.getGame().getWorld().getCurrentSpace();
        Collection<? extends ISpaceG> neighbors = currentSpace.getNeighbors();
        
        Label neighboroughsLabel = new Label("Místnosti");
        neighboroughsLabel.setFont(Font.font(null, FontWeight.BOLD, 13));
        neighboroughsLabel.setAlignment(Pos.CENTER);
        neighboroughsLabel.setMinWidth(120);
        
        this.getChildren().add(neighboroughsLabel);
        
        //vytvoření sousedů a jejich nasázení do boxu
        for(ISpace mistnost:neighbors)
        {
            NeighboroughButton button = 
                                new NeighboroughButton(mistnost.getName(), gui);
            button.setMaxWidth(140);
            this.getChildren().add(button);
        }
    }

    
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ====================================

//##########################################################################
//== INTERNÍ DATOVÉ TYPY ===================================================

    
}
