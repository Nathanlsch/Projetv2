/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2.gui;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;


/**
 *
 * @author lasch
 */
public class MainMenu extends MenuBar{
    
    private mainPane main;
    
    public MainMenu(mainPane main){
        this.main = main;
       
        Menu file = new Menu("Fichier");
        MenuItem nouveau = new MenuItem("Nouveau");
        nouveau.setOnAction((t) -> {
            this.main.getControleur().menuNew(t);
        });
        MenuItem save = new MenuItem("Enregistrer");
        nouveau.setOnAction((t) -> {
            this.main.getControleur().menuSave(t);
        });
        MenuItem saveAs = new MenuItem("Enregistrer sous...");
        nouveau.setOnAction((t) -> {
            this.main.getControleur().menuSaveAs(t);
        });
        MenuItem ouvrir = new MenuItem("Ouvrir");
        nouveau.setOnAction((t) -> {
            this.main.getControleur().menuOpen(t);
        });
        file.getItems().addAll(nouveau,save,saveAs,ouvrir);
        this.getMenus().addAll(file);
        
    }
    
}
