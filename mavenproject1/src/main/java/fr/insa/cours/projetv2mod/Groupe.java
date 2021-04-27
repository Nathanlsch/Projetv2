package fr.insa.cours.projetv2mod;

import fr.insa.cours.projetv2.recup.Lire;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.canvas.GraphicsContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author lasch
 */


public class Groupe extends Treilli {
    
    private List<Treilli> contient;
    
    public Groupe() {
        this.contient = new ArrayList<Treilli>();
    }

    public void add(Treilli f) {
        if (f.getGroupe() != this) {
            if (f.getGroupe() != null) {
                throw new Error("figure déja dans un autre groupe");
            }
            this.contient.add(f);
            f.setGroupe(this);
        }
    }
    
    public void remove(Treilli f) {
        if (f.getGroupe() != this) {
            throw new Error("la figure n'est pas dans le groupe");
        }
        this.contient.remove(f);
        f.setGroupe(null);
    }
    
    public void removeAll(List<Treilli> lf) {
        for(Treilli f : lf) {
            this.remove(f);
        }
    }
    
    public void clear() {
        List<Treilli> toRemove = new ArrayList<>(this.contient);
        this.removeAll(toRemove);
    }

    public int size() {
        return this.contient.size();
    }
    
 public static Groupe groupeTest() {
        NoeudSimple n1 = new NoeudSimple(11,15);
        System.out.println(n1.getId());
        NoeudSimple n2 = new NoeudSimple(100, 20);
        System.out.println(n2.getId());
        NoeudSimple n3 = new NoeudSimple(120, 100);
        NoeudSimple n4 = new NoeudSimple(10, 110);
        NoeudSimple n5 = new NoeudSimple(60, 50);
        Point p1 = new Point(10,10);
        System.out.println(p1.getId());
        Point p2 = new Point(100, 10);
        System.out.println(p2.getId());
        Point p3 = new Point(100, 100);
        Point p4 = new Point(10, 100);
        Point p5 = new Point(50, 50);
        TriangleTerrain t1 = new TriangleTerrain(p1,p2,p3);
        TriangleTerrain t2 = new TriangleTerrain(p1,p3,p4);
        TriangleTerrain t3 = new TriangleTerrain(p5,p2,p1);
        Groupe res = new Groupe();
        res.add(n1);
        res.add(n2);
        res.add(n3);
        res.add(n5);
        res.add(t3);
        res.add(t2);
        res.add(t1);
        
        return res;
    }
 
    public Treilli plusProche(Point p, double distMax) {
        if (this.contient.isEmpty()) {
            return null;
        } else {
            Treilli fmin = this.contient.get(0);
            double min = fmin.distancePoint(p);
            if (fmin instanceof Noeud){
                min = min-0.5*min;
            }
            for (int i =1;i<this.contient.size(); i++){
             Treilli fcur = this.contient.get(i);
             double cur = fcur.distancePoint(p);
             if (fcur instanceof Noeud){
                cur = cur-0.5*cur;
            }
             if(cur<min){
                 min = cur;
                 fmin = fcur;
             }
            }
            if (min <= distMax) {
                return fmin;
            }else {
                return null;
            }
        }
    }
            
    @Override
  public double distancePoint(Point p) {
        if (this.contient.isEmpty()) {
            return new Point(0,0).distancePoint(p);
        } else {
            double dist = this.contient.get(0).distancePoint(p);
            for (int i = 1; i < this.contient.size(); i++) {
                double cur = this.contient.get(i).distancePoint(p);
                if (cur < dist) {
                    dist = cur;
                }
            }
            return dist;
        }
  }
        
  public static void menuTexte() {
        int rep = -1;
        while (rep != 0) {
            System.out.println("1) créer un terrain");
            System.out.println("2) ajouter un triangle terrain");
            System.out.println("3) ajouter un appui");
            System.out.println("4) afficher le rectangle englobant");
            System.out.println("0) quitter");
            System.out.println("votre choix : ");
            rep = Lire.i();
            if (rep == 1) {
              
                Terrain terrain = Terrain.DemandeTerrain();
                 
            } else if (rep == 2) {
                
                TriangleTerrain triangle = TriangleTerrain.DemandeTriangle();
                //add(triangle);
                
            } else if (rep == 3) {
                
                
            } else if (rep == 4) {
                
                
            }
        }
    }  

    
    public static void main(String[] args) {
        groupeTest();
    }

    @Override
    public void dessine(GraphicsContext context) {
        for(Treilli f : this.contient){
            f.dessine(context);
        }
    }

    @Override
    public void dessineSelection(GraphicsContext context) {
       for(Treilli f : this.contient){
            f.dessineSelection(context); 
    }
   }

    public Treilli NoeudplusProche(Point pclick, double MAX_VALUE) {
        int z=0;
        double min=0;
        Treilli fmin = new SegmentTerrain();
        if (this.contient.isEmpty()) {
            return null;
        } else {
            for(int y=0;y<this.contient.size();y++){
                if(this.contient.get(z) instanceof Noeud){
                    fmin = this.contient.get(0);
                    min = fmin.distancePoint(pclick);
                    y=this.contient.size(); 
                } else {
                    z=z+1;
                }
            }
        }
        for (int i =z ;i<this.contient.size(); i++){
             Treilli fcur = this.contient.get(i);
            if (fcur instanceof Noeud){
                double cur = fcur.distancePoint(pclick);
                 if(cur<min){
                 min = cur;
                 fmin = fcur;
                }
             }
            }
            if (min <= MAX_VALUE) {
                return fmin;
            }else {
                return null;
            }
        }

    @Override
    public void Identificateur(Numeroteur<Treilli> num) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }


