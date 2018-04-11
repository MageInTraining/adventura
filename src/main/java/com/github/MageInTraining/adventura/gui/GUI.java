/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.MageInTraining.adventura.gui;

import com.github.MageInTraining.adventura.game.IAuthorMe;
import com.github.MageInTraining.adventura.game.STEM11GSMFactory;

import eu.pedu.adv16w_fw.game_gui.IGSMFactoryG;


import eu.pedu.adv16w_fw.game_gui.IGameG;
import eu.pedu.adv16w_fw.game_gui.IUIG;
import eu.pedu.adv16w_fw.game_txt.IBag;
import eu.pedu.adv16w_fw.game_txt.IGSMFactory;
import eu.pedu.adv16w_fw.game_txt.IGame;
import eu.pedu.adv16w_fw.game_txt.IItem;
import eu.pedu.adv16w_fw.game_txt.ISpace;
import eu.pedu.adv16w_fw.game_txt.IWorld;
import eu.pedu.adv16w_fw.scenario.AScenarioManagerG;
import eu.pedu.adv16w_fw.utilities.UncompletedMethodException;
import java.io.InputStream;

import java.util.Collection;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;




/**
 *
 * @author Argen
 */
public class GUI extends Application implements IUIG /*, IAuthorMe*/
{
    private final MenuPane menuPane;
    private final OutPutPane outputPane;
    private final CommandPane commandPane; 
    private final MapWindow mapWindow;
    private final HelpWindow helpWindow;
    private final LogWindow logWindow;    
    private final BagItemBox bagItemBox;   
    private final ItemBox ItemBox;    
    private final NeighborougsBox neighborougsBox;
    
    private IGameG currentGame;
    private Stage primaryStage;   
    
    
    
    public GUI()
    {
        menuPane = new MenuPane(this);
        mapWindow = new MapWindow(this);
        helpWindow = new HelpWindow(this);
        logWindow = new LogWindow(this);
        outputPane = new OutPutPane();
        commandPane = new CommandPane(this);
        bagItemBox = new BagItemBox (this);
        ItemBox = new ItemBox (this);
        neighborougsBox = new NeighborougsBox (this);
    }
 
  
    @Override
    public IGameG getGame() {
        return currentGame; 
    }
    public MenuPane getMenuPane()
    {
        return menuPane; 
    }
    
    public OutPutPane getOutputPane()
    {
        return outputPane; 
    }
    public CommandPane getCommandPane()
    {
        return commandPane; 
    }
    
    public MapWindow getMapWindow()
    {
        return mapWindow; 
    }
    
    public HelpWindow getHelpWindow()
    {
        return helpWindow; 
    }
    
    public LogWindow getLogWindow()
    {
        return logWindow; 
    }
    
    public BagItemBox getBagItemBox()
    {
        return bagItemBox; 
    } 
    
    public NeighborougsBox getNeighborougsBox()
    {
        return neighborougsBox; 
    }  
    
    public Stage getPrimaryStage()
    {
        return primaryStage; 
    }   
    
    public ItemBox getItemBox()
    {
        return ItemBox; 
    }
    
    @Override
    public void executeCommand(String command) {
        currentGame.executeCommand(command);
    }
    
    @Override 
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        drawMainWindow(primaryStage);
        
        //spuštění hry
        startGame();
       
    }
    
    @Override
    public void startGame(IGame game) {
        if (game instanceof IGameG)
        {
            this.currentGame = (IGameG) game;
            
            mapWindow.initializeFor(currentGame);
            helpWindow.initializeFor(currentGame);
            menuPane.initializeFor(currentGame);
            outputPane.initializeFor(currentGame);
            logWindow.initializeFor(currentGame);
            
            bagItemBox.initializeFor(currentGame);
            ItemBox.initializeFor(currentGame);
            neighborougsBox.initializeFor(currentGame);
                        
            commandPane.initializeFor(currentGame);
            
        }
        else
        {
            throw new IllegalArgumentException("Hra není instancí IGameG"); 
        }
    }
    
   private void drawMainWindow(Stage primaryStage)
    {
        primaryStage.setTitle("Hotel na útesu");
        primaryStage.setX(0);
        primaryStage.setY(0);
    
        //Způsob uspořádání hlavní scény
        BorderPane borderPane = new BorderPane();
        
    ///DOLNÍ ČÁST 
        //pomocný kontejner pro dolní část
        Label commandLabel = new Label("Zad příkaz:");
        commandLabel.setTextAlignment(TextAlignment.CENTER);
        commandLabel.setPadding(new Insets(3));
        
        HBox BottomBox = new HBox(commandLabel,commandPane);
        BottomBox.setPadding(new Insets(2));
        BottomBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(commandPane, Priority.ALWAYS);
    ////////////    
        
    ///PRAVÁ ČÁST
        //Pomocný kontenjer pro pravou část
        VBox rightBox = new VBox(bagItemBox,neighborougsBox);
        
        //pomocný scrollbox pro pravou část
        ScrollPane ScrollRight = new ScrollPane(rightBox);
        ScrollRight.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    ///////////   
    
    ///LEVÁ ČÁST
         //pomocný scrollbox pro levou část
        ScrollPane ScrollLeft = new ScrollPane(ItemBox);
        ScrollLeft.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
    ///////////   
       
        //nasázení prvků do hlavního kontejneru scény
        borderPane.setTop(menuPane);
        borderPane.setLeft(ScrollLeft);
        borderPane.setCenter(outputPane);
        borderPane.setRight(ScrollRight);
        borderPane.setBottom(BottomBox);
        
        //vytvoření scény s borderPane jako hlavním kontejnerem
        Scene mainScene = new Scene(borderPane, 640, 480);
        //přirazení nové scény do hlavního okna 
        primaryStage.setScene(mainScene);
        primaryStage.show();

    }
    public static void main(String[] args)     
     {         
        launch(args);
     }  

    @Override
    public String getAuthorName() {
        return IAuthorMe.AUTHOR_NAME;
    }

    @Override
    public String getAuthorID() {
        return IAuthorMe.AUTHOR_ID;
    }

    @Override
    public Class<? extends IGSMFactoryG> getFactoryClass() {
                return STEM11GSMFactory.class;
    }


    }
    

    
    
