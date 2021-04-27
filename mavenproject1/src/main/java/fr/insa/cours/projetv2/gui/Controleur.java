/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2.gui;

import fr.insa.cours.projetv2mod.AppuiDouble;
import fr.insa.cours.projetv2mod.AppuiEncastre;
import fr.insa.cours.projetv2mod.AppuiSimple;
import fr.insa.cours.projetv2mod.Barre;
import fr.insa.cours.projetv2mod.Figure;
import fr.insa.cours.projetv2mod.Groupe;
import fr.insa.cours.projetv2mod.Noeud;
import fr.insa.cours.projetv2mod.NoeudSimple;
import fr.insa.cours.projetv2mod.Point;
import fr.insa.cours.projetv2mod.SegmentTerrain;
import fr.insa.cours.projetv2mod.TriangleTerrain;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author lasch
 */
public class Controleur {
    
    private Point pclick;
    private Figure proche;
    private double MAX_VALUE = 50;
    private Point p1;
    private Point p2;
    private Point p3;
    
    private List<Figure> selection;
    
    private mainPane vue;
    private int etat;
    
    public Controleur(mainPane vue){
        this.vue = vue;
        this.selection = new ArrayList<>();
    }
    
    public void changeEtat(int nouvelEtat){
       if(nouvelEtat == 10){
           this.selection.clear();
           this.vue.redrawAll();
       }
       if(nouvelEtat == 70){
           this.selection.clear();
       }
       this.etat = nouvelEtat; 
       }

    void clicDansZoneDessin(MouseEvent t) {
       
       if(this.etat==10){
           pclick = new Point(t.getX(), t.getY());
           proche = this.vue.getModel().plusProche(pclick, MAX_VALUE);
           
          if(proche != null){
              if(t.isShiftDown()){
                  this.selection.add(proche);
              } else if (t.isControlDown()) {
                 if(this.selection.contains(proche)){
                     this.selection.remove(proche);
                 } else {
                     this.selection.add(proche);
                 }
                 } else {
                  this.selection.clear();
                  this.selection.add(proche);
              }
              this.vue.redrawAll();
          }
           
       } else if (this.etat==20){
         double px = t.getX();
         double py = t.getY(); 
          p1 = new Point(px,py);
         this.changeEtat(21);
       } else if (this.etat==21){
         double px = t.getX();
         double py = t.getY(); 
         this.changeEtat(22);
          p2 = new Point(px,py);  
       } else if (this.etat==22){
         double px = t.getX();
         double py = t.getY(); 
          p3 = new Point(px,py);
         Groupe model = this.vue.getModel();
         TriangleTerrain t1 = new TriangleTerrain(p1,p2,p3);
         model.add(t1);
         this.vue.redrawAll(); 
         this.changeEtat(20);
         
       } else if (this.etat==30){
           pclick = new Point(t.getX(), t.getY());
           Figure proche = this.vue.getModel().plusProche(pclick, MAX_VALUE);
           if(proche instanceof TriangleTerrain){
              int p1 = ((TriangleTerrain) proche).distancePointInt(pclick);
              SegmentTerrain segment = ((TriangleTerrain) proche).distancePointSegment(pclick);
              Point p = segment.distancePointDonnePoint(pclick);
              int id = 1;
              Groupe model = this.vue.getModel();
              double alpha = ((segment.getDebut().distancePoint(p))/segment.getDebut().distancePoint(segment.getFin()));
              model.add(new AppuiSimple(id,(TriangleTerrain) proche,p1,alpha));
              this.vue.redrawAll();
              this.changeEtat(30);
           }
           
       } else if (this.etat==40){
           pclick = new Point(t.getX(), t.getY());
           Figure proche = this.vue.getModel().plusProche(pclick, MAX_VALUE);
           if(proche instanceof TriangleTerrain){
              int p1 = ((TriangleTerrain) proche).distancePointInt(pclick);
              SegmentTerrain segment = ((TriangleTerrain) proche).distancePointSegment(pclick);
              Point p = segment.distancePointDonnePoint(pclick);
              int id = 1;
              Groupe model = this.vue.getModel();
              double alpha = ((segment.getDebut().distancePoint(p))/segment.getDebut().distancePoint(segment.getFin()));
              model.add(new AppuiDouble(id,(TriangleTerrain) proche,p1,alpha));
              this.vue.redrawAll();
              this.changeEtat(40);  
           }
           
       } else if (this.etat==50){
           pclick = new Point(t.getX(), t.getY());
           Figure proche = this.vue.getModel().plusProche(pclick, MAX_VALUE);
           if(proche instanceof TriangleTerrain){
              int p1 = ((TriangleTerrain) proche).distancePointInt(pclick);
              SegmentTerrain segment = ((TriangleTerrain) proche).distancePointSegment(pclick);
              Point p = segment.distancePointDonnePoint(pclick);
              int id = 1;
              Groupe model = this.vue.getModel();
              double alpha = ((segment.getDebut().distancePoint(p))/segment.getDebut().distancePoint(segment.getFin()));
              model.add(new AppuiEncastre(id,(TriangleTerrain) proche,p1,alpha));
              this.vue.redrawAll();
              this.changeEtat(50);
            }
           
       } else if (this.etat==60){    
       double px = t.getX();
       double py = t.getY();
       int id = 1;
       Groupe model = this.vue.getModel();
       model.add(new NoeudSimple(id,px,py));
       this.vue.redrawAll();  
       
        } else if (this.etat==70){ 
            pclick = new Point(t.getX(), t.getY());
            Figure proche = this.vue.getModel().NoeudplusProche(pclick, MAX_VALUE);
            if(proche instanceof Noeud){
                this.selection.add(proche);
                System.out.println(this.selection.get(0));
                this.changeEtat(71);
            }
        } else if (this.etat==71){ 
            pclick = new Point(t.getX(), t.getY()); 
            Figure proche = this.vue.getModel().plusProche(pclick, MAX_VALUE);
            if(proche instanceof Noeud){
                int id = 1;
                Groupe model = this.vue.getModel();
                System.out.println(this.selection.get(0));
                model.add(new Barre(id,(Noeud) this.selection.get(0),(Noeud) proche));
                this.vue.redrawAll(); 
                this.changeEtat(70);
            }
            
            
        }
     
       
        }   
           
           
       
    
    

    public List<Figure> getSelection() {
        return selection;
    }

    void boutonSelect(ActionEvent t) {
        this.changeEtat(10);
    }

    void boutonTriangleTerrain(ActionEvent t) {
        this.changeEtat(20);
    }

    void AppuiSimple(ActionEvent t) {
        this.changeEtat(30);
    }

    void AppuiDouble(ActionEvent t) {
        this.changeEtat(40);
    }

    void AppuiEncastre(ActionEvent t) {
        this.changeEtat(50);
    }

    void NoeudSimple(ActionEvent t) {
        this.changeEtat(60);
    }

    void Barre(ActionEvent t) {
        this.changeEtat(70);
    }
    }

