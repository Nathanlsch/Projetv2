/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2.gui;


import fr.insa.cours.projetv2mod.Treilli;
import fr.insa.cours.projetv2mod.Groupe;
import java.util.List;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;

/**
 *
 * @author lasch
 */
public class DessinCanvas extends Pane{
    
    private mainPane main;
    private Canvas realCanvas;
    
    public DessinCanvas(mainPane main){
        this.main = main;
        this.realCanvas = new Canvas(this.getWidth(), this.getHeight());
        this.getChildren().add(this.realCanvas);
        System.out.println("w= "+this.getWidth()+"h= "+this.getHeight());
        this.realCanvas.heightProperty().bind(this.heightProperty());
        this.realCanvas.heightProperty().addListener((o) -> {
          System.out.println("w= "+this.realCanvas.getWidth()+"h= "+this.realCanvas.getHeight());
          this.redrawAll();
        });
        this.realCanvas.widthProperty().bind(this.widthProperty());
        this.realCanvas.widthProperty().addListener((o) -> {
          this.redrawAll();
        });
        this.realCanvas.setOnMouseClicked((t) -> {
            Controleur control = this.main.getControleur();
            control.clicDansZoneDessin(t);
        });
        this.redrawAll();
    }
    
    public void redrawAll(){
    
    GraphicsContext context = this.realCanvas.getGraphicsContext2D();
    Groupe model = this.main.getModel();
    model.dessine(context);
    List<Treilli> select = this.main.getControleur().getSelection();
    if(! select.isEmpty()){
        for (Treilli f : select){
            f.dessineSelection(context);
        }
    }
    }
    
    
}
