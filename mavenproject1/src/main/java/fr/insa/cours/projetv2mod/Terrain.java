/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

//import fr.insa.cours.projet_treilli.*;
import fr.insa.cours.projetv2.recup.Lire;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author lasch
 */
public class Terrain extends FigureSimple{
    
    private double minX;
    private double maxX;
    private double minY;
    private double maxY; 
    private int id;
    
    private List<TriangleTerrain> contientTriangle;

    @Override
    public String toString() {
        return "ZoneConstructible;"+ minX + ";" + maxX + ";" + minY + ";" + maxY;
    }

    public Terrain(double minX,double maxX,double minY,double maxY) {
        super(Color.BLACK);
        this.Identificateur(num);
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.contientTriangle = new ArrayList<TriangleTerrain>();
    }
     
    public Terrain(int id,double minX,double maxX,double minY,double maxY, Color col) {
        super(col);
        this.id = id;
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.contientTriangle = new ArrayList<TriangleTerrain>();
    }
    
    public Terrain(Point p1, Point p2) {
        super(Color.GREEN);
        this.Identificateur(num);
        this.minX = p1.getPx();
        this.maxX = p2.getPx();
        this.minY = p1.getPy();
        this.maxY = p2.getPy();
        this.contientTriangle = new ArrayList<TriangleTerrain>();
    }
    
    
    public static Terrain DemandeTerrain(){
        
        System.out.println("Donner l'abscisse minimum");
           double minX = Lire.d();
           System.out.println("Donner l'abscisse maximum");
           double maxX = Lire.d();
           System.out.println("Donner l'ordonée minimum");
           double minY = Lire.d();
           System.out.println("Donner l'ordonnée maximum");
           double maxY = Lire.d();
           
           Terrain terrain = new Terrain(minX,maxX,minY,maxY);
           System.out.println(terrain.toString());
           
          return terrain;
           
    }

    @Override
    public void dessine(GraphicsContext context) {
        context.strokeLine(this.maxX,this.maxY,this.minX,this.maxY);
        context.strokeLine(this.maxX,this.maxY,this.maxX,this.minY);
        context.strokeLine(this.minX,this.minY,this.maxX,this.minY);
        context.strokeLine(this.minX,this.minY,this.minX,this.maxY);
        context.setStroke(Color.GREEN);
        
    }

    @Override
    public double distancePoint(Point p2) {
        
        return 10000000;
    }

    @Override
    public void dessineSelection(GraphicsContext context) {
        
    }

    @Override
    public void Identificateur(Numeroteur<Treilli> num) {
        this.id = num.creeID(this);
    }

    @Override
    public void save(Writer w) throws IOException {
        if(! Save.contains(this)){
            Save.add(this);
            for(TriangleTerrain tri : this.contientTriangle){
                tri.save(w);
            }
            w.append("Terrain;"+this.id+";"+this.minX+";"+this.maxX+";"+this.minY+";"+this.maxY+";"+FigureSimple.saveColor(this.getCouleur())+";");
            for(int i=0; i<this.contientTriangle.size();i++){
                w.append(num.getID(this.contientTriangle.get(i))+";");
            }
            w.append("\n");
        }
    }
 

    /**
     * @return the contientTriangle
     */
    public List<TriangleTerrain> getContientTriangle() {
        return contientTriangle;
    }
       
    
}
