/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author francois
 */
public abstract class Figure {

    /**
     * null si aucun Groupe
     */
    private Groupe groupe;

    public Groupe getGroupe() {
        return groupe;
    }

    void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
    
   /* public abstract double maxX();
    public abstract double minX();
    public abstract double maxY();
    public abstract double minY();*/
    
    //public abstract double distancePoint(Point p);
    
   // public abstract void draw(GraphicsContext gc);
    
    public abstract void dessine(GraphicsContext context);
    
    public abstract double distancePoint(Point p2);

    public abstract void dessineSelection(GraphicsContext context); 
        
    
        
    
    
}
