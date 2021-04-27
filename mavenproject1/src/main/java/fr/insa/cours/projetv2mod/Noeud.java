/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

import javafx.scene.paint.Color;

/**
 *
 * @author Steven
 */
public abstract class Noeud extends FigureSimple{
    
    private int id;

    public Noeud(int id) {
        super(Color.BLACK);
        this.id = id;
             
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public abstract Point getcoordAppui();

}
