/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.MageInTraining.adventura.gui;

import eu.pedu.adv16w_fw.game_gui.IGameG;
import eu.pedu.adv16w_fw.game_txt.IGame;
import eu.pedu.adv16w_fw.scenario.AScenarioManagerG;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.CheckMenuItem;


/**
 *
 * @author Argen
 */
public class MenuPane extends MenuBar implements IInitializable
{
    
    
    private IGameG currentGame;
    
    private final GUI gui;
    
    
    private final CheckMenuItem mapItem;
    
    private final CheckMenuItem helpItem;
    
    private final CheckMenuItem logItem;
      
    private  MapWindow mapWindow;
   
    private  HelpWindow helpWindow;

    private  LogWindow logWindow;    
    
    public MenuPane(GUI gui)
    {      
        
      this.gui = gui;   
  Menu gameMenu = new Menu("Hra"); 
        
        MenuItem mojeHra = new MenuItem("Moje hra");        
            mojeHra.setOnAction(e -> 
            {
                currentGame.stop();
                gui.startGame();                          
            });

        MenuItem demoGame = new MenuItem("Demo hra");
            demoGame.setOnAction(e -> 
            {
                currentGame.stop();
                gui.startGame(AScenarioManagerG.getDemoGameG());                          
            });
        
        MenuItem restart = new MenuItem("Restart hru");
            restart.setOnAction(e -> 
            {
                currentGame.stop();
                gui.startGame(currentGame);                          
            });

        MenuItem exit = new MenuItem("Konec");
            exit.setOnAction(p -> Platform.exit());
        // Nasázení položek do menu
        gameMenu.getItems().addAll(mojeHra,demoGame,restart,exit); 
        
    //OPTIONS    
        Menu options = new Menu("Moznosti");
        mapItem = new CheckMenuItem("Mapa");
            mapItem.setOnAction(p -> 
                {
                    if (mapItem.isSelected() == true) 
                        {mapWindow.show();}
                    else 
                        {mapWindow.close();}
                });   
        
        logItem = new CheckMenuItem("Log");
        logItem.setOnAction(p -> 
            {
                if (logItem.isSelected() == true) 
                    {logWindow.show();}
                else 
                    {logWindow.close();}
            });   
        options.getItems().addAll(mapItem, logItem);   
        
      //HELP     
      
        Menu help = new Menu("Pomoc");
        helpItem = new CheckMenuItem("Help");
            helpItem.setOnAction(p -> 
                {
                    if (helpItem.isSelected() == true) 
                        {helpWindow.show();}
                    else 
                        {helpWindow.close();}
                });
        help.getItems().addAll(helpItem);    
      
        
        // Nasázení všech nabídek do nabídkové lišty
        this.getMenus().addAll(gameMenu,options,help);      
        
    } 
    
     private Menu helpMenu()
    {   
        Menu helpMenu = new Menu("Pomoc");
        
      //  helpMenu.getItems().addAll(help);
      
        return helpMenu;
    }

      private Menu optionMenu()
    {   
        Menu optionsMenu = new Menu("Moznosti");
        
      // optionsMenu.getItems().addAll(showMap);
        return  optionsMenu; 
    }    
      
    public CheckMenuItem getMapItem()
    {
        return mapItem;
    }
     
    public CheckMenuItem getHelpItem()
    {
        return helpItem;
    }  
     
    public CheckMenuItem getLogItem()
    {
        return logItem;
    }
    
     @Override
    public void initializeFor(IGameG game)
    {
        currentGame = game;
        this.mapWindow = gui.getMapWindow();
        this.helpWindow = gui.getHelpWindow();
        this.logWindow= gui.getLogWindow();
    }    
}
