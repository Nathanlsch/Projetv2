/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import javafx.scene.paint.Color;

/**
 *
 * @author Steven
 */


public abstract class Noeud extends FigureSimple{

    /**
     * @return the BarreAssos
     */
    public List<Barre> getBarreAssos() {
        return BarreAssos;
    }

    /**
     * @param BarreAssos the BarreAssos to set
     */
    public void setBarreAssos(List<Barre> BarreAssos) {
        this.BarreAssos = BarreAssos;
    }
    
    public int nombreBarre(){
       return this.getBarreAssos().size();
    }
   
    private int id;
    private List<Barre> BarreAssos;
    
    public Noeud() {
      super(Color.BLACK);       
    }
    public Noeud(Color col) {
      super(col);       
    }
    
    public abstract Point getcoordAppui();
    
    
    @Override
    public abstract void save(Writer w) throws IOException;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    public double getAngleOriente(Noeud N2) {
            double P1x = this.getcoordAppui().getPx();
            double P1y = this.getcoordAppui().getPy();
            
            double P2x = this.getcoordAppui().getPx();
            double P2y = this.getcoordAppui().getPy();
            
            return Math.atan2(P1y - P2y, P1x - P2y);
        }
        
        

}
