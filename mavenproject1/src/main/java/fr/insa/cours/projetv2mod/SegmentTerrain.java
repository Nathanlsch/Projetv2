/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

//import fr.insa.cours.projet_treilli.*;

import java.io.IOException;
import java.io.Writer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;



/**
 *
 * @author francois
 */
public class SegmentTerrain extends FigureSimple {
    
    private Point debut;
    private Point fin;
    private int id;
    
    
    
    public SegmentTerrain(){
        super(Color.BLACK);
        this.Identificateur(num);
        this.debut = new Point();
        this.fin = new Point();
    }
    
    public SegmentTerrain(Point debut,Point fin){
        super(Color.BLACK);
        this.Identificateur(num);
        this.debut = debut;
        this.fin = fin;
    }
    
    public double longueur() {
        SegmentTerrain s2;
        return this.getDebut().distancePoint(this.getFin());
    }
    
    @Override
    public double distancePoint(Point p) {
        double x1 = this.getDebut().getPx();
        double y1 = this.getDebut().getPy();
        double x2 = this.getFin().getPx();
        double y2 = this.getFin().getPy();
        double x3 = p.getPx();
        double y3 = p.getPy();
        double up = ((x3 - x1) * (x2 - x1) + (y3 - y1) * (y2 - y1))
                / (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        if (up < 0) {
            return this.getDebut().distancePoint(p);
        } else if (up > 1) {
            return this.getFin().distancePoint(p);
        } else {
            Point p4 = new Point(x1 + up * (x2 - x1),
                    y1 + up * (y2 - y1));
            return p4.distancePoint(p);
        }
    }
    
    public Point distancePointDonnePoint(Point p) {
        double x1 = this.getDebut().getPx();
        double y1 = this.getDebut().getPy();
        double x2 = this.getFin().getPx();
        double y2 = this.getFin().getPy();
        double x3 = p.getPx();
        double y3 = p.getPy();
        double up = ((x3 - x1) * (x2 - x1) + (y3 - y1) * (y2 - y1))
                / (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        if (up < 0) {
            return this.getDebut();
        } else if (up > 1) {
            return this.getFin();
        } else {
            Point p4 = new Point(x1 + up * (x2 - x1),
                    y1 + up * (y2 - y1));
            return p4;
        }
    }
    
    public String toString() {
        return "[" + this.getDebut() +
                "," + this.getFin().toString() + "]";
    }
    
    public static void main(String[] args) {
        SegmentTerrain s1;
        s1 = new SegmentTerrain(new Point(1,2),new Point(3,4));
        System.out.println("s1 = " + s1);
        System.out.println("longueur de s1 : " + s1.longueur());
        Point mil = s1.getDebut().milieu(s1.getFin());
        double abmil = mil.getPx();
        s1.getDebut().setPx(-3);
        System.out.println("s1 nouveau = " + s1);
    }

    public double minX() {
        return Math.min(this.getDebut().minX(), this.getFin().minX());
    }

    @Override
    public void dessine(GraphicsContext context) {
        context.strokeLine(this.getDebut().getPx(),this.getDebut().getPy(),this.getFin().getPx(),this.getFin().getPy());
        context.setStroke(Color.GREEN);
    }

    @Override
    public void dessineSelection(GraphicsContext context) {
        context.strokeLine(this.getDebut().getPx(),this.getDebut().getPy(),this.getFin().getPx(),this.getFin().getPy());
        context.setStroke(Color.RED);
    }

    /**
     * @return the debut
     */
    public Point getDebut() {
        return debut;
    }

    /**
     * @param debut the debut to set
     */
    public void setDebut(Point debut) {
        this.debut = debut;
    }

    /**
     * @return the fin
     */
    public Point getFin() {
        return fin;
    }
    
    @Override
    public void Identificateur(Numeroteur<Treilli> num) {
        this.id = num.creeID(this);
    }
    
    @Override
    public void save(Writer w) throws IOException {
        if(! Save.contains(this)){
        this.debut.save(w);
        this.fin.save(w);
        Save.add(this);
        w.append("SegmentTerrain;"+this.getId()+";"+this.debut.getId()+";"+this.fin.getId()+";"
                + FigureSimple.saveColor(this.getCouleur())+"\n");
        }
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }
    
}
