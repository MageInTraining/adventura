/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.MageInTraining.adventura.gui;

import eu.pedu.adv16w_fw.game_gui.IGameG;
import javafx.scene.control.TextArea;

/**
 *
 * @author Argen
 */
class OutPutPane extends TextArea implements IInitializable{
    public OutPutPane()
    {
        this.setEditable(false); 
    }
    
   @Override
    public void initializeFor(IGameG game)
    {    
        this.appendText("\n" 
                       +"------------------------------------------------------"
                       + "\nStartuje se hra " + game.getName()
                       + "\nAutorID: " +game.getAuthorID()
                       +"\nAutor: " +game.getAuthorName()
                       + "\n Definovanou ve třídě: " + game.getClass().getName()
                       +"\n" 
                       +"------------------------------------------------------"
        );    } 
}
