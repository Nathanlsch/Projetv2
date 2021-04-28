/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;

/**
 *
 * @author francois
 */
public abstract class Treilli {

    /**
     * null si aucun Groupe
     */
    private Groupe groupe;
    
    public static Numeroteur<Treilli> num = new Numeroteur();
    
    public static ArrayList<Treilli> Save = new ArrayList<Treilli>();

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
        
    public abstract void Identificateur(Numeroteur<Treilli> num);
   
    public abstract void save(Writer w) throws IOException;
    
    public void sauvegarde(File fout) throws IOException{
        try (BufferedWriter bout = new BufferedWriter(new FileWriter(fout))){
            this.save(bout);
        }
    }
        
        
    

    /**
     * @return the num
     */
    public Numeroteur<Treilli> getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(Numeroteur<Treilli> num) {
        this.num = num;
    }
        
    
        
    
    
}
