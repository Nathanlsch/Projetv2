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
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
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
    
    public static Numeroteur<Treilli> num2 = new Numeroteur();
    
    public static ArrayList<Treilli> Save = new ArrayList<Treilli>();

    public Groupe getGroupe() {
        return groupe;
    }

    void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }
    
    public void clear() {
        List<Treilli> toRemove = new ArrayList<>(this.Save);
        this.removeAll(toRemove);
    }
    
    public void removeAll(List<Treilli> lf) {
        for(Treilli f : lf) {
            this.remove(f);
        }
    }
    
     public void remove(Treilli f) {
        if (f.getGroupe() != this) {
            throw new Error("la figure n'est pas dans le groupe");
        }
        this.Save.remove(f);
        f.setGroupe(null);
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
    
    //public abstract void supr();
   
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
                    System.out.println("check 1");
                    num2.associe(id, np);
                    System.out.println("check 2");
                    derniere = np;
                } else if (bouts[0].equals("SegmentTerrain")){
                    int id = Integer.parseInt(bouts[1]);
                    int idP1 = Integer.parseInt(bouts[2]);
                    int idP2 = Integer.parseInt(bouts[3]);
                    Color col = FigureSimple.parseColor(bouts[4],bouts[5],bouts[6]);
                    Point p1 = (Point) num2.getObj(idP1);
                    Point p2 = (Point) num2.getObj(idP2);
                    SegmentTerrain ns = new SegmentTerrain(id ,p1, p2, col);
                    num2.associe(id, ns);
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
                    Point p1 = (Point) num2.getObj(idP1);
                    Point p2 = (Point) num2.getObj(idP2);
                    Point p3 = (Point) num2.getObj(idP3);
                    SegmentTerrain s1 = (SegmentTerrain) num2.getObj(idS1);
                    SegmentTerrain s2 = (SegmentTerrain) num2.getObj(idS2);
                    SegmentTerrain s3 = (SegmentTerrain) num2.getObj(idS3);
                    TriangleTerrain nt = new TriangleTerrain(id ,p1, p2, p3, s1, s2, s3, col);
                    num2.associe(id, nt);
                    derniere = nt;
                } else if (bouts[0].equals("NoeudSimple")){
                    int id = Integer.parseInt(bouts[1]);
                    double px = Double.parseDouble(bouts[2]);
                    double py = Double.parseDouble(bouts[3]);
                    Color col = FigureSimple.parseColor(bouts[4],bouts[5],bouts[6]);
                    NoeudSimple nns = new NoeudSimple(id ,px, py, col);
                    num2.associe(id, nns);
                    derniere = nns;
                } else if (bouts[0].equals("AppuiSimple")){
                    int id = Integer.parseInt(bouts[1]);
                    int idT1 = Integer.parseInt(bouts[2]);
                    System.out.println("check Triangle2");
                    TriangleTerrain t1 = (TriangleTerrain) num2.getObj(idT1);
                    System.out.println("check Triangle3");
                    int p1 = Integer.parseInt(bouts[3]);
                    double alpha = Double.parseDouble(bouts[4]);
                    Color col = FigureSimple.parseColor(bouts[5],bouts[6],bouts[7]);
                    AppuiSimple nas = new AppuiSimple(id ,t1, p1, alpha, col);
                    
                    num2.associe(id, nas);
                    
                    derniere = nas;
                } else if (bouts[0].equals("AppuiDouble")){
                    int id = Integer.parseInt(bouts[1]);
                    int idT1 = Integer.parseInt(bouts[2]);
                    TriangleTerrain t1 = (TriangleTerrain) num2.getObj(idT1);
                    int p1 = Integer.parseInt(bouts[3]);
                    double alpha = Double.parseDouble(bouts[4]);
                    Color col = FigureSimple.parseColor(bouts[5],bouts[6],bouts[7]);
                    AppuiDouble nad = new AppuiDouble(id ,t1, p1, alpha, col);
                    num2.associe(id, nad);
                    derniere = nad;
                } else if (bouts[0].equals("AppuiEncastre")){
                    int id = Integer.parseInt(bouts[1]);
                    int idT1 = Integer.parseInt(bouts[2]);
                    TriangleTerrain t1 = (TriangleTerrain) num2.getObj(idT1);
                    int p1 = Integer.parseInt(bouts[3]);
                    double alpha = Double.parseDouble(bouts[4]);
                    Color col = FigureSimple.parseColor(bouts[5],bouts[6],bouts[7]);
                    AppuiEncastre nae = new AppuiEncastre(id ,t1, p1, alpha, col);
                    num2.associe(id, nae);
                    derniere = nae;
                } else if (bouts[0].equals("Barre")){
                    int id = Integer.parseInt(bouts[1]);
                    int idN1 = Integer.parseInt(bouts[2]);
                    int idN2 = Integer.parseInt(bouts[3]);
                    Color col = FigureSimple.parseColor(bouts[4],bouts[5],bouts[6]);
                    Noeud n1 = (Noeud) num2.getObj(idN1);
                    Noeud n2 = (Noeud) num2.getObj(idN2);
                    Barre nb = new Barre(id ,n1, n2, col);
                    num2.associe(id, nb);
                    derniere = nb;
                } else if (bouts[0].equals("Groupe")){
                    int id = Integer.parseInt(bouts[1]);
                    Groupe ng = new Groupe(id);
                    num2.associe(id, ng);
                    for ( int i =2; i< bouts.length; i++){
                        int idSous = Integer.parseInt(bouts[i]);
                        Treilli tre = num2.getObj(idSous);
                        ng.add(tre);
                        if(tre instanceof Terrain){
                            ng.setTerrain((Terrain) tre);
                        }
                    }
                    Save.clear();
                    derniere = ng;
                } else if(bouts[0].equals("Terrain")){
                    int id = Integer.parseInt(bouts[1]);
                    double pxmin = Double.parseDouble(bouts[2]);
                    double pxmax = Double.parseDouble(bouts[3]);
                    double pymin = Double.parseDouble(bouts[4]);
                    double pymax = Double.parseDouble(bouts[5]);
                    Color col = FigureSimple.parseColor(bouts[6],bouts[7],bouts[8]);
                    Terrain terrain = new Terrain(id,pxmin,pxmax,pymin,pymax,col);
                    num2.associe(id, terrain);
                    for ( int i =9; i< bouts.length; i++){
                        int idSous = Integer.parseInt(bouts[i]);
                        Treilli tri = num2.getObj(idSous);
                            terrain.getContientTriangle().add((TriangleTerrain) tri);
                    }
                    derniere = terrain;
                } else if (bouts[0].equals("TypeDeBarre")){
                    int id = Integer.parseInt(bouts[1]);
                    String nom = bouts[2];
                    double cout = Double.parseDouble(bouts[3]);
                    double longMin = Double.parseDouble(bouts[4]);
                    double longMax = Double.parseDouble(bouts[5]);
                    double maxTension = Double.parseDouble(bouts[6]);
                    double maxCompression = Double.parseDouble(bouts[7]);
                    TypeDeBarre ntdb = new TypeDeBarre(id,nom,cout,longMin,longMax,maxTension,maxCompression);
                    num2.associe(id, ntdb);
                }
        }
        num = num2;
        return derniere;
    }
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
   
    public static int getNombreBarre() {
        int nbrBarre = 0;
        for (int i=0; i < num.getIdversObjet().size();i++) {
            if (num.getObj(i) instanceof Barre) {
            nbrBarre++;
        }
        }
        return nbrBarre;
    }
    
   public static Matrice force(){
    //Pour  chaque noeud S_i trouver les barres B_i qui passent par S_i
        for(int i = 0; i< num.getIdversObjet().size();i++){
            Matrice Systeme = new Matrice(0,getNombreBarre()+2);
            
            if(num.getObj(i) instanceof Noeud){
                Noeud Noeud = (Noeud) num.getObj(i);
               //Pour chaque barre
               for (int j=0; i < Noeud.getBarreAssos().size(); j++) {
                   
                //Si c'est un noeud simple
                if (Noeud instanceof NoeudSimple) {
                    //POUR 1 NOEUD SIMPLE
                        //On crée une première matrice système, correspondant à la première barre associée
                        Matrice SystemeBarre = new Matrice(1,2);
                        Barre Barrei = Noeud.getBarreAssos().get(i);
                        Noeud nDepartBarrei = Barrei.getNdepart();
                        Noeud nFinBarrei = Barrei.getNfin();

                        double AngleBarrex = nDepartBarrei.getcoordAppui().getAngleOriente(nFinBarrei.getcoordAppui());
                        Systeme.set(0,0,Math.cos(AngleBarrex));
                        Systeme.set(0,1,Math.sin(AngleBarrex));

                        //Pour les autres barres associées, on va concaténer les colonnes pour le système
                        for (int y=1; i < Noeud.getBarreAssos().size(); y++) {
                        Matrice SystemeBarrei = new Matrice(1,2);
                                Barre Barrey = Noeud.getBarreAssos().get(y);
                                Noeud nDepartBarrey = Barrey.getNdepart();
                                Noeud nFinBarrey = Barrey.getNfin();

                                double AngleBarrey = nDepartBarrey.getcoordAppui().getAngleOriente(nFinBarrey.getcoordAppui());
                                SystemeBarrei.set(0,0,Math.cos(AngleBarrey));
                                SystemeBarrei.set(0,1,Math.sin(AngleBarrey));

                                SystemeBarre.concatCol(SystemeBarrei);
                            }
                        //Ensuite on concatène Rx et Ry 
                        //>> Problème si la grande matrice est de taille plus grande, Rx et Ry ne sont pas à la bonne place
                        //Ils devraient être à la dernière colonne de la grande matrice
                        Matrice SystemeR = new Matrice (1,2);
                        SystemeBarre.concatCol(SystemeR);
                        
                        //On obtient une matrice de [nbarres + 1] [nBarres + 1]
                        //Il faut concaténer à la grande matrice 
                        Systeme.concatLig(SystemeBarre);
                }
                    
                }
                 
               }
            }
  
    public int testForce(){
      int nombreNS=0;
      int nombreNB=0;
      int nombreNAS=0;
      int nombreNAD=0;
      int nombreN=0;
      boolean testAE=false;
      Set<Integer> set = this.num.parcours();
      for(Integer key: set){
           if(num.getObj(key) instanceof NoeudSimple){
               nombreNS = nombreNS + 1;
               nombreN = nombreN+1;
               
           }
           if(num.getObj(key) instanceof Barre){
               nombreNB = nombreNB + 1;         
           }
           
           if(num.getObj(key) instanceof AppuiDouble){
               nombreNAD = nombreNAD + 1;
               nombreN = nombreN+1;
               
           }
           if(num.getObj(key) instanceof AppuiSimple){
               nombreNAS = nombreNAS + 1;
               nombreN = nombreN+1;
               
           }
           if(num.getObj(key) instanceof AppuiEncastre){
               testAE = true;  
               nombreN = nombreN+1;
           }
      }
      
      if((2*nombreN == nombreNB+nombreNAS+2*nombreNAD)&&(testAE == false)){
          return 2*nombreN;
      } else {
          return 0;
      }  
   } 
  
    public int BarreMax(){
        System.out.println("Etape3");
      int barre;
      int barreMax=0;
      Set<Integer> set = this.num.parcours();
      for(Integer key: set){ 
          if((num.getObj(key) instanceof NoeudSimple)||(num.getObj(key) instanceof AppuiSimple)||(num.getObj(key) instanceof AppuiDouble)){
              barre = ((Noeud) num.getObj(key)).nombreBarre();
              if(barreMax<barre){
                  barreMax = barre;
              }
          }
      }
      return barreMax;
    }
    
    public void ajout(int[][] tab, int id,int ligne){
        System.out.println("Etape2");
        System.out.println(ligne);
        if(ligne==1){
            int i=0;
            while(tab[ligne][i]!=0){
                i=i+2;
            }
            tab[ligne][i]=id;
            tab[ligne][i+1]=id;
            System.out.println(tab);
        } else if (ligne==0){
            int y=0;
            while((tab[ligne][y]!=0)&&(tab[ligne][y]!=id)){
                y=y+1;
                System.out.println(y);
            }
            
            System.out.println(y);
            tab[ligne][y]=id;
            System.out.println(tab);
        }
    }
    
    public int numCol(int[][] tab, int id){
        int i=0;
        while(tab[0][i]!=id){
            i=i+1;
        }
        return i;
    }

    public void Force(){
        if(this.testForce() !=0){
            int ligne =0;
            Matrice res = new Matrice(this.testForce(), this.testForce());
            int nbMax = BarreMax();
            int[][] info = new int[2][this.testForce()];
            Set<Integer> set = this.num.parcours();
            System.out.println("Etape1");
            for(Integer key: set){
               if(num.getObj(key) instanceof NoeudSimple){
                   ajout(info,key,1);
                   for(Barre barre : ((Noeud)num.getObj(key)).getBarreAssos()){
                       int id = barre.getId();
                       ajout(info,id,0);
                       double valeur = barre.getNdepart().getAngleOriente(barre.getNfin());
                       int col = numCol(info,id);
                       res.set(ligne, col, Math.cos(valeur));
                       res.set(ligne+1, col, Math.sin(valeur)); 
     
                   }
                ligne = ligne+2;   
               }
               if(num.getObj(key) instanceof AppuiSimple){
                   ajout(info,key,1);
                   for(Barre barre : ((Noeud)num.getObj(key)).getBarreAssos()){
                       int id = barre.getId();
                       ajout(info,id,0);
                       double valeur = barre.getNdepart().getAngleOriente(barre.getNfin());
                       int col = numCol(info,id);
                       res.set(ligne, col, Math.cos(valeur));
                       res.set(ligne+1, col, Math.sin(valeur)); 
                       
                   }
                   ligne = ligne+2;
               }
               if(num.getObj(key) instanceof AppuiDouble){
                   ajout(info,key,1);
                   for(Barre barre : ((Noeud)num.getObj(key)).getBarreAssos()){
                       int id = barre.getId();
                       ajout(info,id,0);
                       double valeur = barre.getNdepart().getAngleOriente(barre.getNfin());
                       int col = numCol(info,id);
                       res.set(ligne, col, Math.cos(valeur));
                       res.set(ligne+1, -col, Math.sin(valeur));  
                       
                   }
                   ligne = ligne+2;
               }
            
            }
        System.out.println(res);
            
            
            
            
            
        } else {
            System.out.println("force du treilli non calculable");
        }
    }


  
      
  }  
        
    
    

