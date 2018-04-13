/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.MageInTraining.adventura.gui;

import eu.pedu.adv16w_fw.game_gui.IGameG;
import eu.pedu.adv16w_fw.game_gui.Icon;
import java.awt.Point;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Argen
 */
public class MapWindow extends Stage implements IInitializable
{
   private final GUI GUI;
            /** Současná hra */
    private IGameG currentGame;
    
    /** Grafický prvek představující hráče */
    private Icon player;
    
    /** Grafický prvek představující mapu */
    private Icon map;
    
    /** Lišta s menu*/
    private MenuPane menuPane;
    

    public MapWindow(GUI gui )
        {
            this.GUI = gui;
        }
 
  public void movePlayer (IGameG game)
        {
            Point playerPos = currentGame.getWorld().getCurrentSpace()
                                                        .getPosition();
            player.setX(playerPos.getX());
            player.setY(playerPos.getY());
        }   
    
     @Override
    public void initializeFor(IGameG game)
    {
        this.currentGame = game;
        map = currentGame.getMap();
        player = currentGame.getPlayer();
        menuPane = GUI.getMenuPane();
        setUpWindow();
    } 
    
  private void setUpWindow()
    {
        Double offsetX = 655d;
        Double offsetY = 5d;
        Stage primaryStage = GUI.getPrimaryStage();
        
        this.setTitle("Mapa Města");
        this.setX(primaryStage.getX() + offsetX);
        this.setY(primaryStage.getY() + offsetY);
        
        primaryStage.xProperty().addListener(p -> 
                                    this.setX(primaryStage.getX() + offsetX));
        primaryStage.yProperty().addListener(p -> 
                                    this.setY(primaryStage.getY() + offsetY));
        
        
//        
        this.setOnCloseRequest(p -> menuPane.getMapItem()
                                            .setSelected(false));
        //

        Group mapGroup = new Group();

       
        mapGroup.getChildren().add(map);
        mapGroup.getChildren().add(player);


        Scene mapScene = new Scene(mapGroup);

        this.setScene(mapScene);
        this.setResizable(true);
        this.setWidth(720);
        this.setHeight(720);
        
   
    }   
    
}
