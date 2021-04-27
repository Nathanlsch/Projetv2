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
 * @author celiajoy
 */
public class Barre extends FigureSimple{
    
    private int id;
    private Noeud ndepart;
    private Noeud nfin;
    private TypeDeBarre typeDeBarre;

    public Barre(int id, Noeud ndepart, Noeud nfin) {
        super(Color.BLACK);
        this.id = id;
        this.ndepart = ndepart; 
        this.nfin = nfin; 
    }
    
    public Barre(int id, Noeud ndepart, Noeud nfin, TypeDeBarre typeDeBarre) {
        super(Color.BLACK);
        this.id = id;
        this.ndepart = ndepart; 
        this.nfin = nfin;
        this.typeDeBarre = typeDeBarre;
        
    }

    public int getId() {
        return id;
    }

    public Noeud getNdepart() {
        return ndepart;
    }

    public Noeud getNfin() {
        return nfin;
    }

    public TypeDeBarre getTypeDeBarre() {
        return typeDeBarre;
    }
    
        @Override
    public String toString() {
        return "Barre;" + this.getId() + ";" + this.getNdepart() + ";" + this.getNdepart() + ";" + this.getTypeDeBarre() + ";" ;
    }

    
    @Override
    public void dessine(GraphicsContext context) {
        context.strokeLine(this.getNdepart().getcoordAppui().getPx(),this.getNdepart().getcoordAppui().getPy(),this.getNfin().getcoordAppui().getPx(),this.getNfin().getcoordAppui().getPy());
        context.setStroke(Color.BLACK); 
    }


    @Override
    public void dessineSelection(GraphicsContext context) {
        System.out.println("Barre select");
    }
    
   
    public double distancePoint(Point p) {
        double x1 = this.getNdepart().getcoordAppui().getPx();
        double y1 = this.getNdepart().getcoordAppui().getPy();
        double x2 = this.getNfin().getcoordAppui().getPx();
        double y2 = this.getNfin().getcoordAppui().getPy();
        double x3 = p.getPx();
        double y3 = p.getPy();
        double up = ((x3 - x1) * (x2 - x1) + (y3 - y1) * (y2 - y1))
                / (Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        if (up < 0) {
            return this.getNdepart().getcoordAppui().distancePoint(p);
        } else if (up > 1) {
            return this.getNfin().getcoordAppui().distancePoint(p);
        } else {
            Point p4 = new Point(x1 + up * (x2 - x1),
                    y1 + up * (y2 - y1));
            return p4.distancePoint(p);
        }
    }
    
}
