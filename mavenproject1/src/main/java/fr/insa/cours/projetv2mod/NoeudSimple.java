/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

import static fr.insa.cours.projetv2mod.Treilli.Save;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Steven
 */
public class NoeudSimple extends Noeud {
    
    
    public static double RAYON_IN_DRAW = 5;
    private double px;
    private double py; 
     
    public NoeudSimple (double px, double py) {
    this.Identificateur(num);
    this.px = px;
    this.py = py; 
    this.setBarreAssos(new ArrayList<Barre>());
    
}
    
    public NoeudSimple (int id, double px, double py, Color col) {
        super(col);
        this.px = px;
        this.py = py;
        this.setId(id);
        this.setBarreAssos(new ArrayList<Barre>());
    }

    @Override
    public String toString() {
        return "NoeudSimple;" + this.getId() + ";(" + this.getPx() + "," + this.getPy() + ')';
    } 

    @Override
    public void dessine(GraphicsContext context) {
        context.setFill(this.getCouleur());
        context.fillOval(this.getPx()-RAYON_IN_DRAW, this.getPy()-RAYON_IN_DRAW, 2*RAYON_IN_DRAW, 2*RAYON_IN_DRAW);   
    }

    /**
     * @return the px
     */
    public double getPx() {
        return px;
    }

    /**
     * @return the py
     */
    public double getPy() {
        return py;
    }

    @Override
    public double distancePoint(Point p2) {
        double dx = this.px - p2.getPx();
        double dy = this.py - p2.getPy();
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public void dessineSelection(GraphicsContext context) {
        context.setFill(Color.WHITE);
        context.fillOval(this.getPx()-RAYON_IN_DRAW, this.getPy()-RAYON_IN_DRAW, 2*RAYON_IN_DRAW, 2*RAYON_IN_DRAW);
    }

    @Override
    public Point getcoordAppui() {
        Point res = new Point(this.px,this.py);
        return res;
    }
    
    @Override
    public void Identificateur(Numeroteur<Treilli> num) {
        this.setId(num.creeID(this));
    }
    
    @Override
    public void save(Writer w) throws IOException {
        if(! Save.contains(this)){
            Save.add(this);
        w.append("NoeudSimple;"+this.getId()+";"+this.px+";"+this.py+";"+ FigureSimple.saveColor(this.getCouleur())+"\n");
        }
    }

    @Override
    public boolean dansTerrain(Terrain terrain) {
        if(((terrain.getMinX()<this.px)&&(this.px<terrain.getMaxX()))&&((terrain.getMinY()<this.py)&&(this.py<terrain.getMaxY()))){
           return true; 
        } else {
            return false;
        }
    }
  
}