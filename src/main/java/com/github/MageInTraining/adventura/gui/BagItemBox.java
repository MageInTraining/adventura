
package com.github.MageInTraining.adventura.gui;

import com.github.MageInTraining.adventura.DATA.Data;
import eu.pedu.adv16w_fw.game_gui.IBagG;
import eu.pedu.adv16w_fw.game_gui.IGameG;
import eu.pedu.adv16w_fw.game_gui.IItemG;
import eu.pedu.adv16w_fw.game_gui.Icon;
import java.util.Collection;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * Třída představují VBox ve kterém jsou umístěny tlačíka věcí nalézajcí se 
 * v batohu.
 * @author Jan Novosad
 */
class BagItemBox extends VBox implements IInitializable
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
     * @param gui Hlavní manažer GUI
     */
    public BagItemBox(GUI gui)
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
     * Metoda která smaže obsah boxu a vytvoří v něm popisek a  nové tlačítka 
     * předmětů odpovídajcí současnému stavu hry.
     **/
    public void refresh()
    {
        this.getChildren().clear();

        IBagG bag = gui.getGame().getBag();
        Collection<? extends IItemG> itemsInBag = bag.getItems();
        
        //LABEL
        Label bagItemsLabel = new Label("Věci v baťohu");
        bagItemsLabel.setFont(Font.font(null, FontWeight.BOLD, 13));
        bagItemsLabel.setAlignment(Pos.CENTER);
        bagItemsLabel.setMinWidth(120);
        
        this.getChildren().add(bagItemsLabel);
             
        
        //vytvoření prázdného místa pod nadpisem pokud není v batohu žádná věc
        if (itemsInBag.isEmpty())
        {
            this.getChildren().add(new Label (""));
            return;
        }
        
        
        //vytvoření tlačítek se věcmi a jejich nasázení do boxu
        for(IItemG vec:itemsInBag)
        {
            String nazevVeci = vec.getName().toLowerCase();
            Icon obrazek = new Icon(Data.class.getResource(nazevVeci + ".png"));
            ItemButton button = new ItemButton(nazevVeci, obrazek, gui);
            this.getChildren().add(button);
        }
    }

    
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ====================================

//##########################################################################
//== INTERNÍ DATOVÉ TYPY ===================================================

    
}
