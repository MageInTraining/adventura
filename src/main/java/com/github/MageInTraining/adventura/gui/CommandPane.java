/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.MageInTraining.adventura.gui;

import eu.pedu.adv16w_fw.game_gui.IGameG;
import eu.pedu.adv16w_fw.game_txt.IBag;
import eu.pedu.adv16w_fw.game_txt.IGame;
import eu.pedu.adv16w_fw.game_txt.IItem;
import eu.pedu.adv16w_fw.game_txt.ISpace;
import eu.pedu.adv16w_fw.game_txt.IWorld;
import java.util.Collection;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;

/**
 *
 * @author Argen
 */
public class CommandPane extends TextField implements IInitializable {


    private OutPutPane outputPane;

    private final EventHandler<ActionEvent> textFieldEventHandler =
                                                    this::onTextFieldEvent;
    //Hra, která se právě hraje
    private IGameG currentGame;
    
    private MapWindow mapWindow;
   
    private final GUI gui;
    
    
     CommandPane(GUI gui)
    {    
        this.gui = gui;  
    }
    
     public EventHandler<ActionEvent> getTextFieldEventHandler()
    {
        return textFieldEventHandler;
    }
     
    public void initializeFor(IGameG game)
    {
        currentGame = game;
        
        outputPane = gui.getOutputPane();
        mapWindow = gui.getMapWindow();
    
        //přizaření ovladače pro vstupní pole
        this.setOnAction(textFieldEventHandler);

        //Necháme vstupní pole obživnout
        this.setEditable(true);

        //Zadání startovacího příkazu
        this.setText("");

        //puštení prázdnýho příkazu - spuštění hry
        textFieldEventHandler.handle(new ActionEvent());
        
        this.requestFocus(); 
    }    
 
    
     private void onTextFieldEvent(ActionEvent event)
    {
        String command; //Zadávaný příkaz
        String answer; //Odpověď hry
        
        //Převezme se uživatelův příkaz
        command = this.getText();
        //Příkaz se zadá hře a od hry se převezme její odpověď
        answer = currentGame.executeCommand(command);  
        answer  = decorate(answer, currentGame);

        //Vypíšeme do výstupního pole příkaz a za ním odpověď hry
        outputPane.appendText("\n\n" + command +
                             decorateAnswerLite(answer));
        
        
        //vymazání pole
        this.setText("");
       
        //přemístění hráče pokud existuje mapa
        mapWindow.movePlayer(currentGame);

        gui.getBagItemBox().refresh();
        gui.getNeighborougsBox().refresh();
        gui.getItemBox().refresh();
        
        gui.getLogWindow().addToLog("\n\n" + command +
                             decorateAnswerLite(answer));
        
                
        //Pokud hra skončila, zpasivníme vstupní pole
        if (! currentGame.isAlive())
        {
            this.setOnAction(null);
            this.setText("Hra ukončena, nelze zadat příkaz");
            this.setEditable(false);
        }
        
    }
     public String decorate(String answer, IGame game)
    {
        IBag bag = game.getBag();
        Collection<? extends IItem> bagItems = bag.getItems();

        IWorld world = game.getWorld();
        ISpace space = world.getCurrentSpace();
        Collection<? extends ISpace> neighbors  = space.getNeighbors();
        Collection<? extends IItem>  spaceItems = space.getItems();

        String result = answer
                      + "\nCurrent space: " + space
                      + "\nIts neighbors: " + neighbors
                      + "\nIts items: "     + spaceItems
                      + "\nBag content: "   + bagItems
                      + "";
        return result;
    }    
    
    
    private String decorateAnswerLite(String answer)
    { 
        //sestavení odpovědi        
        answer = "\n==================================="
                + "\n"
                + answer;

        return answer;
    }
}
