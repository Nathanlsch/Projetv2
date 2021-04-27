/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2.gui;

import javafx.scene.control.Button;

/**
 *
 * @author lasch
 */
public class Bouton extends Button{
    
    public Bouton(String nom, double w, double h){
        super(nom);
        this.setMaxSize(w, h);
        this.setMinSize(w, h);    
    }
    
}
