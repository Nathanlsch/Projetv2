package fr.insa.cours.projetv2mod;

import fr.insa.cours.projetv2.recup.Lire;
import static fr.insa.cours.projetv2mod.Treilli.Save;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
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
    private int id;
    private Terrain terrain;
    
    public Groupe() {
        this.contient = new ArrayList<Treilli>();
        this.Identificateur(num);
    }
    
    public Groupe(int id) {
        this.contient = new ArrayList<Treilli>();
        this.id = id;
    }
    
    public boolean ContientNoeud(){
        boolean i = true;
        for(Treilli t : this.getContient()){
            if(t instanceof Noeud){
                i =false;
            }
        }
        return i;
    }
    

    public void add(Treilli f) {
        if (f.getGroupe() != this) {
            if (f.getGroupe() != null) {
                throw new Error("figure déja dans un autre groupe");
            }
            this.getContient().add(f);
            f.setGroupe(this);
        }
    }
    
    public void remove(Treilli f) {
        if (f.getGroupe() != this) {
            throw new Error("la figure n'est pas dans le groupe");
        }
        this.getContient().remove(f);
        f.setGroupe(null);
    }
    
    public void removeAll(List<Treilli> lf) {
        for(Treilli f : lf) {
            this.remove(f);
        }
    }
    
    public void clear() {
        List<Treilli> toRemove = new ArrayList<>(this.getContient());
        this.removeAll(toRemove);
    }

    public int size() {
        return this.getContient().size();
    }
    
 
    public Treilli plusProche(Point p, double distMax) {
        if (this.getContient().isEmpty()) {
            return null;
        } else {
            Treilli fmin = this.getContient().get(0);
            double min = fmin.distancePoint(p);
            if (fmin instanceof Noeud){
                min = min-0.5*min;
            }
            for (int i =1;i<this.getContient().size(); i++){
             Treilli fcur = this.getContient().get(i);
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
        if (this.getContient().isEmpty()) {
            return new Point(0,0).distancePoint(p);
        } else {
            double dist = this.getContient().get(0).distancePoint(p);
            for (int i = 1; i < this.getContient().size(); i++) {
                double cur = this.getContient().get(i).distancePoint(p);
                if (cur < dist) {
                    dist = cur;
                }
            }
            return dist;
        }
  }
        
    @Override
    public void dessine(GraphicsContext context) {
        for(Treilli f : this.getContient()){
            f.dessine(context);
        }
    }

    @Override
    public void dessineSelection(GraphicsContext context) {
       for(Treilli f : this.getContient()){
            f.dessineSelection(context); 
    }
   }

    
    public Treilli NoeudplusProche(Point p, double distMax) {
        int i = 0;
        Treilli fmin = new Point();
        double min = 0;
        if (ContientNoeud()) {
            return null;
        } else {
            for(int z=0;z<this.getContient().size();z++){
            if( this.getContient().get(i) instanceof Noeud){
               fmin = this.getContient().get(i);
               min = fmin.distancePoint(p); 
               z=   this.getContient().size();
            } else {
               i=i+1;
            }
            }
            for (int y =i;y<this.getContient().size(); y++){
                if(this.getContient().get(y) instanceof Noeud){
                  Treilli fcur = this.getContient().get(y);
                  double cur = fcur.distancePoint(p);  
                  if(cur<min){
                  min = cur;
                  fmin = fcur;
                  }
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
    public void Identificateur(Numeroteur<Treilli> num) {
        this.id = num.creeID(this);
    }
    
    @Override
    public void save(Writer w) throws IOException {
        if(! Save.contains(this)){
            Save.add(this);
        for(Treilli t : this.getContient()){
            t.save(w);
        }
        w.append("Groupe;"+this.id);
        for(Treilli t : this.getContient()){
            w.append(";"+num.getID(t));
        }
        w.append("\n");
        }
    }
    
    public static String indente(String toIndente, String prefix) {
        return prefix + toIndente.replaceAll("\n", "\n" + prefix);
    }
    
 @Override
    public String toString() {
        String res = "Groupe {\n";
        for (int i = 0; i < this.getContient().size(); i++) {
            res = res + indente(this.getContient().get(i).toString(), "  ") + "\n";
        }
        return res + "}";
    }

    /**
     * @return the terrain
     */
    public Terrain getTerrain() {
        return terrain;
    }

    /**
     * @param terrain the terrain to set
     */
    public void setTerrain(Terrain terrain) {
        this.terrain = terrain;
    }

    
    /**
     * @return the contient
     */
    public List<Treilli> getContient() {
        return contient;
    }

    @Override
    public boolean supr(GraphicsContext context) {
        return false;
    }

    @Override
    public String afficheInfo() {
        return "";
    }
        
    }
    
    
    


