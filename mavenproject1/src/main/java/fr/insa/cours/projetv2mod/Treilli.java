/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 *
 * @author francois
 */
public abstract class Treilli {

    /**
     * null si aucun Groupe
     */
    private Groupe groupe;
    
    public static Numeroteur<Treilli> num = new Numeroteur();
    
    public static ArrayList<Treilli> Save = new ArrayList<Treilli>();

    public Groupe getGroupe() {
        return groupe;
    }

    void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
    
   /* public abstract double maxX();
    public abstract double minX();
    public abstract double maxY();
    public abstract double minY();*/
    
    //public abstract double distancePoint(Point p);
    
   // public abstract void draw(GraphicsContext gc);
    
    public abstract void dessine(GraphicsContext context);
    
    public abstract double distancePoint(Point p2);

    public abstract void dessineSelection(GraphicsContext context); 
        
    public abstract void Identificateur(Numeroteur<Treilli> num);
   
    public abstract void save(Writer w) throws IOException;
    
    public void sauvegarde(File fout) throws IOException{
        try (BufferedWriter bout = new BufferedWriter(new FileWriter(fout))){
            this.save(bout);
        }
    }
    
    public static Treilli lecture(File fin) throws IOException {
        Treilli derniere = null;
        try(BufferedReader bin = new BufferedReader(new FileReader(fin))) {
            String line;
            while((line = bin.readLine())!= null && line.length() != 0){
                String[] bouts = line.split(";");
                if(bouts[0].equals("Point")){
                    int id = Integer.parseInt(bouts[1]);
                    double px = Double.parseDouble(bouts[2]);
                    double py = Double.parseDouble(bouts[3]);
                    Color col = FigureSimple.parseColor(bouts[4],bouts[5],bouts[6]);
                    Point np = new Point(id ,px, py, col);
                    num.associe(id, np);
                    derniere = np;
                } else if (bouts[0].equals("SegmentTerrain")){
                    int id = Integer.parseInt(bouts[1]);
                    int idP1 = Integer.parseInt(bouts[2]);
                    int idP2 = Integer.parseInt(bouts[3]);
                    Color col = FigureSimple.parseColor(bouts[4],bouts[5],bouts[6]);
                    Point p1 = (Point) num.getObj(idP1);
                    Point p2 = (Point) num.getObj(idP2);
                    SegmentTerrain ns = new SegmentTerrain(id ,p1, p2, col);
                    num.associe(id, ns);
                    derniere = ns;
                } else if (bouts[0].equals("TriangleTerrain")){
                    int id = Integer.parseInt(bouts[1]);
                    int idP1 = Integer.parseInt(bouts[2]);
                    int idP2 = Integer.parseInt(bouts[3]);
                    int idP3 = Integer.parseInt(bouts[4]);
                    int idS1 = Integer.parseInt(bouts[5]);
                    int idS2 = Integer.parseInt(bouts[6]);
                    int idS3 = Integer.parseInt(bouts[7]);
                    Color col = FigureSimple.parseColor(bouts[8],bouts[9],bouts[10]);
                    Point p1 = (Point) num.getObj(idP1);
                    Point p2 = (Point) num.getObj(idP2);
                    Point p3 = (Point) num.getObj(idP3);
                    SegmentTerrain s1 = (SegmentTerrain) num.getObj(idS1);
                    SegmentTerrain s2 = (SegmentTerrain) num.getObj(idS2);
                    SegmentTerrain s3 = (SegmentTerrain) num.getObj(idS3);
                    TriangleTerrain nt = new TriangleTerrain(id ,p1, p2, p3, s1, s2, s3, col);
                    num.associe(id, nt);  
                    derniere = nt;
                } else if (bouts[0].equals("NoeudSimple")){
                    int id = Integer.parseInt(bouts[1]);
                    double px = Double.parseDouble(bouts[2]);
                    double py = Double.parseDouble(bouts[3]);
                    Color col = FigureSimple.parseColor(bouts[4],bouts[5],bouts[6]);
                    NoeudSimple nns = new NoeudSimple(id ,px, py, col);
                    num.associe(id, nns);
                    derniere = nns;
                } else if (bouts[0].equals("AppuiSimple")){
                    int id = Integer.parseInt(bouts[1]);
                    int idT1 = Integer.parseInt(bouts[2]);
                    TriangleTerrain t1 = (TriangleTerrain) num.getObj(idT1);
                    int p1 = Integer.parseInt(bouts[3]);
                    double alpha = Double.parseDouble(bouts[4]);
                    Color col = FigureSimple.parseColor(bouts[5],bouts[6],bouts[7]);
                    AppuiSimple nas = new AppuiSimple(id ,t1, p1, alpha, col);
                    num.associe(id, nas);
                    derniere = nas;
                } else if (bouts[0].equals("AppuiDouble")){
                    int id = Integer.parseInt(bouts[1]);
                    int idT1 = Integer.parseInt(bouts[2]);
                    TriangleTerrain t1 = (TriangleTerrain) num.getObj(idT1);
                    int p1 = Integer.parseInt(bouts[3]);
                    double alpha = Double.parseDouble(bouts[4]);
                    Color col = FigureSimple.parseColor(bouts[5],bouts[6],bouts[7]);
                    AppuiDouble nad = new AppuiDouble(id ,t1, p1, alpha, col);
                    num.associe(id, nad);
                    derniere = nad;
                } else if (bouts[0].equals("AppuiEncastre")){
                    int id = Integer.parseInt(bouts[1]);
                    int idT1 = Integer.parseInt(bouts[2]);
                    TriangleTerrain t1 = (TriangleTerrain) num.getObj(idT1);
                    int p1 = Integer.parseInt(bouts[3]);
                    double alpha = Double.parseDouble(bouts[4]);
                    Color col = FigureSimple.parseColor(bouts[5],bouts[6],bouts[7]);
                    AppuiEncastre nae = new AppuiEncastre(id ,t1, p1, alpha, col);
                    num.associe(id, nae);
                    derniere = nae;
                } else if (bouts[0].equals("Barre")){
                    int id = Integer.parseInt(bouts[1]);
                    int idN1 = Integer.parseInt(bouts[2]);
                    int idN2 = Integer.parseInt(bouts[3]);
                    Color col = FigureSimple.parseColor(bouts[4],bouts[5],bouts[6]);
                    Noeud n1 = (Noeud) num.getObj(idN1);
                    Noeud n2 = (Noeud) num.getObj(idN2);
                    Barre nb = new Barre(id ,n1, n2, col);
                    num.associe(id, nb);
                    derniere = nb;
                } else if (bouts[0].equals("Groupe")){
                    int id = Integer.parseInt(bouts[1]);
                    Groupe ng = new Groupe(id);
                    num.associe(id, ng);
                    for ( int i =2; i< bouts.length; i++){
                        int idSous = Integer.parseInt(bouts[i]);
                        Treilli tre = num.getObj(idSous);
                        ng.add(tre);
                    } 
                    derniere = ng;
                }
            }
        }
        return derniere;
    }

        
        
    

    /**
     * @return the num
     */
    public Numeroteur<Treilli> getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(Numeroteur<Treilli> num) {
        this.num = num;
    }
        
    
        
    
    
}
