/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

import static fr.insa.cours.projetv2mod.Treilli.Save;
import java.io.IOException;
import java.io.Writer;
import javafx.scene.paint.Color;

/**
 *
 * @author Steven
 */
public abstract class Noeud extends FigureSimple{
   
    private int id;
    
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
        

}
