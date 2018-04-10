
package com.github.MageInTraining.adventura.gui;

import eu.pedu.adv16w_fw.game_gui.IGameG;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Třída představující okno s logem do kterého se zaznamenává děj hry
 * @author Jan Novosad
 */
class LogWindow extends Stage implements IInitializable
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
    
    /** Textová oblast logu */
    private final TextArea logArea;

    
//== PROMĚNNÉ ATRIBUTY INSTANCÍ ============================================

    /** Současná hra */
    private IGameG currentGame;
    
    /** Panel s menu pro odškrtnutí položky */
    private MenuPane menuPane;
    
    /** zda se má log uložit*/
    private boolean save = false;
    
    
//##########################################################################
//== KONSTRUKTORY A TOVÁRNÍ METODY =========================================
    /**
     * Kontruktor
     * @param gui Hlavní manažer GUI
     */
    public LogWindow(GUI gui)
    {
        this.gui = gui;
        this.logArea = new TextArea();
        this.logArea.setEditable(false);
    }
    
//== ABSTRAKTNÍ METODY =====================================================
//== PŘÍSTUPOVÉ METODY VLASTNOSTÍ INSTANCÍ =================================
    /**
     * Vrátí zda je nastaveno uložení hry
     * @return 
     */
    public boolean isSave()
    {return save;}

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
        logBegin();
    }
    
    public void addToLog(String text)
    {
        logArea.appendText(text);
    }
    
    /**
     * Uloží obsah logArea od souborou. Ukládá je do Logs složky a soubory 
     * se ukládájí pro každé spuštění hry
     */
    public void saveToFile()
    {
        //připojí konec logu
        logEnd();
        //nahradí znaky které nemohou být v souboru
        String currentDate = currentDate().replaceAll("[.:]", "-");
        //vytvoří cesty pro složku a log
        File directory = new File("Logs");
        String fileName = currentGame.getName() + " - " + currentDate;
        File logFile = new File(directory,fileName + ".txt");
        //pokud nexistuuje složka tak ji vytvoří
        if(!directory.exists() || !directory.isDirectory())
        {
        directory.mkdir();
        }
        //zápis
        try{
            PrintWriter output = new PrintWriter (new FileWriter(logFile));
            //přetransformuje text tak aby konce řádku fungovaly - nahradí \n
            //správným oddělovačem nezávislým na platformně
            String logText = logArea.getText().replaceAll("\\n",
                                        System.getProperty("line.separator"));
            //zapíše do souboru
            output.print(logText);
            output.close();
            System.out.println("Úspěšně zapsáno do souboru !");
            }
        catch (IOException e)
            {
            System.err.println("Chyba při zápisu");
            }
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
        
        this.setTitle("Log");
        this.setX(primaryStage.getX() + offsetX);
        this.setY(primaryStage.getY() + offsetY);
        
        primaryStage.xProperty().addListener(p -> 
                                    this.setX(primaryStage.getX() + offsetX));
        primaryStage.yProperty().addListener(p -> 
                                    this.setY(primaryStage.getY() + offsetY)); 
        
        this.setOnCloseRequest(p -> menuPane.getLogItem()
                                                .setSelected(false));
        
        
        CheckBox checkBox = new CheckBox();
        checkBox.setSelected(save);
        checkBox.setOnAction(e -> save=!save);
        
        Label saveLabel = new Label("Uložit log ze hry: ");
        saveLabel.setPadding(new Insets(10));
        
        HBox upperBox = new HBox(saveLabel,checkBox);
        upperBox.setAlignment(Pos.CENTER_LEFT);
             
        BorderPane logPane = new BorderPane();
        logPane.setTop(upperBox);
        logPane.setCenter(logArea);
        
        Scene logScene = new Scene(logPane, 640, 480);
        
        this.setScene(logScene);
    }
    
    private String currentDate()
    {
        String dateFormat = "EEEEEE d.MMM yyyy H:mm:ss";
        Calendar calendar = Calendar.getInstance();
        DateFormat formatData = new SimpleDateFormat(dateFormat);
        return formatData.format(calendar.getTime());
    }
    
    private void logBegin()
    {
        String zacatekLogu = 
                "========================================"
            + "\nLog hry - " + currentGame.getName()
            + "\nAutor " + currentGame.getAuthorID() + " - " 
                                                + currentGame.getAuthorName()
            + "\nDefinována ve třídě: " + currentGame.getClass().getName()
            + "\nZačátek hraní: " + currentDate()
            + "\n========================================"; 
        logArea.appendText(zacatekLogu);
    }
    
    private void logEnd()
    {
        String konecLogu = 
              "\n========================================"
            + "\nUkončena hra - " + currentGame.getName()
            + "\nKonec hraní: " + currentDate()
            + "\n========================================"; 
        logArea.appendText(konecLogu);
    
    }
//##########################################################################
//== INTERNÍ DATOVÉ TYPY ===================================================

    
  
}
