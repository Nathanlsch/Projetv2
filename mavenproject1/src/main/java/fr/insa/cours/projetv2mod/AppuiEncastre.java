/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

import static fr.insa.cours.projetv2mod.NoeudSimple.RAYON_IN_DRAW;
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
public class AppuiEncastre extends Appui {

    public AppuiEncastre( TriangleTerrain triangleTerrain, int p1, double posSurSegment) {
        super(triangleTerrain, p1, posSurSegment);
        this.setBarreAssos(new ArrayList<Barre>());
    }
    
    public AppuiEncastre( int id, TriangleTerrain triangleTerrain, int p1, double posSurSegment, Color col) {
        super(id, triangleTerrain, p1, posSurSegment, col);
        this.setBarreAssos(new ArrayList<Barre>());
    }
    
    @Override
    public void dessine(GraphicsContext context) {
        Point p = this.getcoordAppui(this.getP1(),this.getTriangleTerrain(),this.getPosSurSegment());
        context.setFill(Color.BROWN);
        context.fillOval(p.getPx()-RAYON_IN_DRAW ,p.getPy()-RAYON_IN_DRAW, 2*RAYON_IN_DRAW, 2*RAYON_IN_DRAW); 
    }
    
        @Override
    public void save(Writer w) throws IOException {
        if(! Save.contains(this)){
            Save.add(this);
            this.getTriangleTerrain().save(w);
        w.append("AppuiEncastre;"+this.getId()+";"+this.getTriangleTerrain().getId()+";"+this.getP1()+";"+this.getPosSurSegment()+";"+ FigureSimple.saveColor(this.getCouleur())+"\n");
        }
    }
    
}
