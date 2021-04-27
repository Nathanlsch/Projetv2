/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

import fr.insa.cours.projetv2.recup.Lire;


/**
 *
 * @author francois
 */
public class Point  {

    private double px;
    private double py;

    public Point() {
        this.px=0;
        this.py=0;
    }

    public Point(double px, double py) {
        this.px=px;
        this.py=py;
     }

    public double getPx() {
        return this.px;
    }
    
     public double getPy() {
        return this.py;
    }

    public void setPx(double px) {
        this.px = px;
    }

    public double distancePoint(Point p2) {
        double dx = this.px - p2.px;
        double dy = this.py - p2.py;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public Point milieu(Point p2) {
        Point res;
        res = new Point();
        res.px = (this.px + p2.px) / 2;
        res.py = (this.py + p2.py) / 2;
        return res;
    }

    public String toString() {
        return "{Point ; abs : " + this.px + " ; ord : " + this.py + "}" ;
    }

    public static void testDistance() {
        Point p1;
        Point point2;
        Point pp, mi;
        p1 = new Point(0, 0);
        point2 = new Point(1, 2);
        pp = new Point(1, 1);
        double dist = p1.distancePoint(point2);
        double dist2 = pp.distancePoint(p1);
        System.out.println("distance : " + dist);
        mi = p1.milieu(pp);
        System.out.println("milieu : " + mi);
    }

    public double minX() {
        return this.px;
    }
    
    public static void testConstructeur() {
        Point p1;
        Point p2;
        Point p3;
        p1 = new Point();
        p2 = new Point(1, 2);
        p3 = new Point(0, 0);
        System.out.println("p1 = " + p1);
        System.out.println("p2 = " + p2);
        System.out.println("p3 = " + p3);
    }

    public static void main(String[] args) {
//        testDistance();
testConstructeur();
    }
public static Point DemandePoint(){
    
    System.out.println("Donner l'abscisse");
    double abs = Lire.d();
    System.out.println("Donner l'ordonée");
    double ord = Lire.d();
    Point point = new Point(abs,ord);
    return point;
  
}

}
