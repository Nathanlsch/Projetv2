/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author Steven
 */
public class Appui extends Noeud {
    
    public static double RAYON_IN_DRAW = 5;
    private TriangleTerrain triangleTerrain; 
    private int p1;
    private double posSurSegment;

     public Appui(TriangleTerrain triangleTerrain, int p1, double posSurSegment) {
        super();
        this.Identificateur(num);
        this.triangleTerrain = triangleTerrain;
        this.p1 = p1;
        this.posSurSegment = posSurSegment;
    }

    public TriangleTerrain getTriangleTerrain() {
        return triangleTerrain;
    }

    public int getP1() {
        return p1;
    }

    public double getPosSurSegment() {
        return posSurSegment;
    }
   
 

    @Override
    public void dessine(GraphicsContext context) {
        Point p = this.getcoordAppui(this.getP1(),this.getTriangleTerrain(),this.getPosSurSegment());
        context.setFill(this.getCouleur());
        context.fillOval(p.getPx()-RAYON_IN_DRAW, p.getPy()-RAYON_IN_DRAW, 2*RAYON_IN_DRAW, 2*RAYON_IN_DRAW);  
    }

    @Override
    public double distancePoint(Point p2) {
        Point p = this.getcoordAppui(this.getP1(),this.getTriangleTerrain(),this.getPosSurSegment());
        double dx = p.getPx() - p2.getPx();
        double dy = p.getPy() - p2.getPy();
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public void dessineSelection(GraphicsContext context) {
        Point p = this.getcoordAppui(this.getP1(),this.getTriangleTerrain(),this.getPosSurSegment());
        context.setFill(Color.WHITE);
        context.fillOval(p.getPx()-RAYON_IN_DRAW, p.getPy()-RAYON_IN_DRAW, 2*RAYON_IN_DRAW, 2*RAYON_IN_DRAW);  
    }

 
    public Point getcoordAppui(int p1,TriangleTerrain t ,double alpha ) {
        SegmentTerrain seg = new SegmentTerrain();
        
        if(p1 == 1){
             seg = t.getSegment1();
        }
        if(p1 == 2){
             seg = t.getSegment2();
        }
        if(p1 == 3){
             seg = t.getSegment3();
        }
        double px = seg.getDebut().getPx()+ alpha*(seg.getFin().getPx()-seg.getDebut().getPx());
        double py = seg.getDebut().getPy()+ alpha*(seg.getFin().getPy()-seg.getDebut().getPy());
        Point res = new Point(px,py);
        
       return res;
    }

    @Override
    public Point getcoordAppui() {
       Point res =  this.getcoordAppui(p1, triangleTerrain, posSurSegment);
       return res;
    }

    @Override
    public void Identificateur(Numeroteur<Treilli> num) {
        this.setId(num.creeID(this));
    }

       
    
    
}
