
package com.github.MageInTraining.adventura.gui;

import com.github.MageInTraining.adventura.gui.MenuPane;
import eu.pedu.adv16w_fw.game_gui.IGameG;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 * Třída představující okno s nápovědou
 * @author Jan Novosad
 */
class HelpWindow extends Stage implements IInitializable
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
    
    /** Panel s menu pro odškrtnutí položky */
    private MenuPane menuPane;
    
    
//##########################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    /**
     * Kontruktor
     * @param gui Hlavní manažer GUI
     */
    public HelpWindow(GUI gui)
    {
        this.gui = gui;
 
    }
    
//== ABSTRAKTNÍ METODY =====================================================
//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =================================

//#########################################################################
//== ABSTRACT GETTERS AND SETTERS ==========================================
//== OSTATNÍ NESOUKROMÉ METODY INSTANCÍ ==================================== 
    /**
     * Inicializace objektu při sputění hry. Načtení nápovědy do webEnginu
     * @param game Hra pro kterou se má objekt inicializovat 
     */
    @Override
    public void initializeFor(IGameG game)
    {
        this.currentGame = game;
        menuPane = gui.getMenuPane();
        setUpWindow();
    }
//== SOUKROMÉ A POMOCNÉ METODY INSTANCÍ ====================================
    /**
     * Nastaví okno podle našich požadavků. Nevolá se v konstruktoru protože 
     * pro její funkci je třeba mít již nastavenou hru pomocí 
     * metody initializeFor
     */
    private void setUpWindow()
    {
        Double offsetX = 655d;
        Double offsetY = 0d;
        Stage primaryStage = gui.getPrimaryStage();
        
        this.setTitle("Nápověda");
        this.setX(primaryStage.getX() + offsetX);
        this.setY(primaryStage.getY() + offsetY);
        
        primaryStage.xProperty().addListener(p -> 
                                    this.setX(primaryStage.getX() + offsetX));
        primaryStage.yProperty().addListener(p -> 
                                    this.setY(primaryStage.getY() + offsetY));
        
        
        
        this.setOnCloseRequest(p -> menuPane.getHelpItem()
                                                .setSelected(false));
        
        WebView webHelp = new WebView();
        WebEngine webEngine = webHelp.getEngine();
        webEngine.load(currentGame.getHelpURL().toString()); 
        
        Scene helpScene = new Scene(webHelp, 640, 480);
        
        this.setScene(helpScene);
    }
//##########################################################################
//== INTERNÍ DATOVÉ TYPY ===================================================
  
}
