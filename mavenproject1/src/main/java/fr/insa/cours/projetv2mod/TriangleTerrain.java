/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

import fr.insa.cours.projetv2.recup.Lire;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;



/**
 *
 * @author lasch
 */
public class TriangleTerrain extends FigureSimple{
   
    private Point Point1;
    private Point Point2;
    private Point Point3;
    private SegmentTerrain Segment1;
    private SegmentTerrain Segment2;
    private SegmentTerrain Segment3;
    private int id;
 
    
    public TriangleTerrain (Point p1,Point p2, Point p3){
    super(Color.BLACK);
    this.Identificateur(num);
    this.Point1 = p1;
    this.Point2 = p2;
    this.Point3 = p3;
    this.Segment1 = new SegmentTerrain(p1, p2);
    this.Segment2 = new SegmentTerrain(p2, p3);
    this.Segment3 = new SegmentTerrain(p3, p1);
}

    public Point getPoint1() {
        return Point1;
    }

    public Point getPoint2() {
        return Point2;
    }

    public Point getPoint3() {
        return Point3;
    }

    public SegmentTerrain getSegment1() {
        return Segment1;
    }

    public SegmentTerrain getSegment2() {
        return Segment2;
    }

    public SegmentTerrain getSegment3() {
        return Segment3;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Triangle;("+Point1.getPx() + ";" + Point1.getPy() + ");(" +Point2.getPx() + ";" + Point2.getPy() + ");(" + +Point3.getPx() + ";" + Point3.getPy() + ")";
    }
   
   public static TriangleTerrain DemandeTriangle(){
        
        System.out.println("Créer le point 1");
           Point p1 = Point.DemandePoint();
           System.out.println("Créer le point 2");
           Point p2 = Point.DemandePoint();
           System.out.println("Créer le point 3");
           Point p3 = Point.DemandePoint();
           System.out.println("Donner id");
           TriangleTerrain triangle = new TriangleTerrain(p1,p2,p3);
           triangle.id = Lire.s();
           
           System.out.println(triangle.toString());
           return triangle;
           
    } 

    @Override
    public void dessine(GraphicsContext context) {
        this.Segment1.dessine(context);
        this.Segment2.dessine(context);
        this.Segment3.dessine(context);
    }

    @Override
    public double distancePoint(Point p2) {
        double d1 = this.Segment1.distancePoint(p2);
        double d2 = this.Segment2.distancePoint(p2);
        double d3 = this.Segment3.distancePoint(p2);
        double min1 = Math.min(d1,d2);
        double min2 = Math.min(d1,d3);
        double min = Math.min(min1,min2);
  
        return min;
    }
    
    public SegmentTerrain distancePointSegment(Point p2) {
        
        double d1 = this.Segment1.distancePoint(p2);
        double d2 = this.Segment2.distancePoint(p2);
        double d3 = this.Segment3.distancePoint(p2);
        if((d1<d2)&&(d1<d3)){    
            return this.Segment1;
        } else if((d2<d1)&&(d2<d3)){    
            return this.Segment2;
        } else if((d3<d2)&&(d3<d1)){    
            return this.Segment3;
        } else {
            return null;
        }   
    }
    
    public int distancePointInt(Point p2) {
        double d1 = this.Segment1.distancePoint(p2);
        double d2 = this.Segment2.distancePoint(p2);
        double d3 = this.Segment3.distancePoint(p2);
        if((d1<d2)&&(d1<d3)){    
            return 1;
        } if((d2<d1)&&(d2<d3)){    
            return 2;
        } if((d3<d2)&&(d3<d1)){    
            return 3;
        } else {
            return 0;
        }       
    }

    @Override
    public void dessineSelection(GraphicsContext context) {
        this.Segment1.dessineSelection(context);
        this.Segment2.dessineSelection(context);
        this.Segment3.dessineSelection(context);
    }
    
    @Override
    public void Identificateur(Numeroteur<Treilli> num) {
        this.id = num.creeID(this);
    }
   
}

