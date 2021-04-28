/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

import java.util.Map;
import javafx.scene.paint.Color;


/**
 *
 * @author francois
 */
public abstract class FigureSimple extends Treilli {
    
    private int id;
    private Color couleur;

    public FigureSimple(Color couleur) {
        this.couleur = couleur;
 
    }

    public Color getCouleur() {
        return couleur;
    }

    public void setCouleur(Color couleur) {
        this.couleur = couleur;
    }
    
    public static String saveColor(Color c){
        return c.getRed()+";"+c.getGreen()+";"+c.getBlue();
    }
    
    
}
