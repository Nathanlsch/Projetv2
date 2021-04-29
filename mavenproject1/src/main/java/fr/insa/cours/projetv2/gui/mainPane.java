/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2.gui;

import fr.insa.cours.projetv2mod.Groupe;
import java.io.File;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 *
 * @author lasch
 */
public class mainPane extends BorderPane {
    
    private Groupe model;
    private Controleur controleur;
     private Stage inStage;
    private File curFile;
    
    private MainMenu menu;
    
    //Variable Dessin
    private DessinCanvas cvtest;
    
    //Variable Bouton
    private RadioButton Select;
    private RadioButton TriangleTerrain;
    private RadioButton AppuiSimple;
    private RadioButton AppuiDouble;
    private RadioButton AppuiEncastre;
    private RadioButton NoeudSimple;
    private RadioButton Barre;
    private TextArea test;
    
public mainPane(Stage inStage) {
        this(inStage, new Groupe());
    }

    public mainPane(Stage inStage, Groupe model) {
        this(inStage, null, model);
    }

   
    public mainPane(Stage inStage, File fromFile, Groupe model){ 
    
    this.inStage = inStage;
    this.curFile = fromFile;
    this.test = new TextArea();
    test.setPrefHeight(200);
    test.setPrefWidth(200);
    test.setEditable(false);
 
    this.setRight(this.test);
    
    this.model = model;
    this.controleur = new Controleur(this);
    
    this.menu = new MainMenu(this);
    this.setTop(this.menu);
    
   
   //Boite gauche
   Select = new RadioButton("Select");
   Select.setOnAction((t) -> {
       this.controleur.boutonSelect(t);
   });
   TriangleTerrain = new RadioButton("Triangle Terrain");
   TriangleTerrain.setOnAction((t) -> {
       this.controleur.boutonTriangleTerrain(t);
   });
   AppuiSimple = new RadioButton("Appui Simple");
   AppuiSimple.setOnAction((t) -> {
       this.controleur.AppuiSimple(t);
   });
   AppuiDouble = new RadioButton("Appui Double");
   AppuiDouble.setOnAction((t) -> {
       this.controleur.AppuiDouble(t);
   });
   AppuiEncastre = new RadioButton("Appui Encastré");
   AppuiEncastre.setOnAction((t) -> {
       this.controleur.AppuiEncastre(t);
   });
   NoeudSimple = new RadioButton("Noeud Simple");
   NoeudSimple.setOnAction((t) -> {
       this.controleur.NoeudSimple(t);
   });
   
   Barre = new RadioButton("Barre");
   Barre.setOnAction((t) -> {
       this.controleur.Barre(t);
   });
   
   ToggleGroup bgEtat = new ToggleGroup();
   Select.setToggleGroup(bgEtat);
   AppuiSimple.setToggleGroup(bgEtat);
   AppuiDouble.setToggleGroup(bgEtat);
   AppuiEncastre.setToggleGroup(bgEtat);
   NoeudSimple.setToggleGroup(bgEtat);
   TriangleTerrain.setToggleGroup(bgEtat);
   Barre.setToggleGroup(bgEtat);
   Select.setSelected(true);
   
   
   VBox gauche = new VBox(Select,TriangleTerrain, AppuiSimple,AppuiDouble , AppuiEncastre, NoeudSimple, Barre);
   gauche.setMaxSize(200, 200);
   gauche.setMinSize(200, 200);
   this.setLeft(gauche);
   
   
   //Debut dessin 
    this.cvtest = new DessinCanvas(this);
    this.setCenter(this.cvtest);
    
    
    this.cvtest.setOnMouseClicked((t) -> {
       System.out.println("click");
    });
   
  
}  

    public void redrawAll(){
        this.cvtest.redrawAll();
    }

    /**
     * @return the model
     */
    public Groupe getModel() {
        return model;
    }

    /**
     * @return the controleur
     */
    public Controleur getControleur() {
        return controleur;
    }

    /**
     * @param controleur the controleur to set
     */
    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }
    
    public Stage getInStage() {
        return inStage;
    }
    
    public File getCurFile() {
        return curFile;
    }
    
     public void setCurFile(File curFile) {
        this.curFile = curFile;
    }

    /**
     * @return the test
     */
    public TextArea getTest() {
        return test;
    }
    
}
