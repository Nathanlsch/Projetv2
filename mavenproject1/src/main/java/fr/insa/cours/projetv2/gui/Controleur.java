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
import fr.insa.cours.projetv2mod.Treilli;
import fr.insa.cours.projetv2mod.Groupe;
import fr.insa.cours.projetv2mod.Noeud;
import fr.insa.cours.projetv2mod.NoeudSimple;
import fr.insa.cours.projetv2mod.Numeroteur;
import fr.insa.cours.projetv2mod.Point;
import fr.insa.cours.projetv2mod.SegmentTerrain;
import fr.insa.cours.projetv2mod.Terrain;
import fr.insa.cours.projetv2mod.TriangleTerrain;
import fr.insa.cours.projetv2mod.TypeDeBarre;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author lasch
 */
public class Controleur {
    
    private Point pclick;
    private Point pclick2;
    private Treilli proche;
    private double MAX_VALUE = 50;
    private Point p1;
    private Point p2;
    private Point p3;
    private Cursor cursor;
    
    private List<Treilli> selection;
    
    private mainPane vue;
    private CreationTypeDeBarre creabarre;
    private ModificationTypeDeBarre modifbarre;
    private VueTypeDeBarre vueBarre;
    private int etat;
    private Numeroteur num;
    
    public Controleur(mainPane vue){
        this.vue = vue;
        this.selection = new ArrayList<>();
        this.changeEtat(80);
        this.num = this.vue.getModel().getNum();
    }
    
    public void changeEtat(int nouvelEtat){
       if(nouvelEtat == 10){
           this.selection.clear();
           this.vue.redrawAll();
       }
       if(nouvelEtat == 70){
           this.selection.clear();
       }
       if(nouvelEtat == 80){
          vue.setCursor(Cursor.DEFAULT);
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
         if(dansTerrain(px,py,this.vue.getModel().getTerrain())){
            p1 = new Point(px,py);
            this.changeEtat(21);
         }  else {
            System.out.println("pas dans le terrain");
         }
       } else if (this.etat==21){
         double px = t.getX();
         double py = t.getY();
         if(dansTerrain(px,py,this.vue.getModel().getTerrain())){
            p2 = new Point(px,py);
            this.changeEtat(22);
         }  else {
            System.out.println("pas dans le terrain");
         }  
       } else if (this.etat==22){
         double px = t.getX();
         double py = t.getY();
         if(dansTerrain(px,py,this.vue.getModel().getTerrain())){
            p3 = new Point(px,py);
            Groupe model = this.vue.getModel();
            TriangleTerrain t1 = new TriangleTerrain(p1,p2,p3);
            model.getTerrain().getContientTriangle().add(t1);
            model.add(t1);
            this.vue.redrawAll(); 
            this.changeEtat(20);
         }  else {
            System.out.println("pas dans le terrain");
         }
         
       } else if (this.etat==30){
           pclick = new Point(t.getX(), t.getY());
           Treilli proche = this.vue.getModel().plusProche(pclick, MAX_VALUE);
           if(proche instanceof TriangleTerrain){
              int p1 = ((TriangleTerrain) proche).distancePointInt(pclick);
              SegmentTerrain segment = ((TriangleTerrain) proche).distancePointSegment(pclick);
              Point p = segment.distancePointDonnePoint(pclick);
              Groupe model = this.vue.getModel();
              double alpha = ((segment.getDebut().distancePoint(p))/segment.getDebut().distancePoint(segment.getFin()));
              model.add(new AppuiSimple((TriangleTerrain) proche,p1,alpha));
              this.vue.redrawAll();
              this.changeEtat(30);
           }
           
       } else if (this.etat==40){
           pclick = new Point(t.getX(), t.getY());
           Treilli proche = this.vue.getModel().plusProche(pclick, MAX_VALUE);
           if(proche instanceof TriangleTerrain){
              int p1 = ((TriangleTerrain) proche).distancePointInt(pclick);
              SegmentTerrain segment = ((TriangleTerrain) proche).distancePointSegment(pclick);
              Point p = segment.distancePointDonnePoint(pclick);
              Groupe model = this.vue.getModel();
              double alpha = ((segment.getDebut().distancePoint(p))/segment.getDebut().distancePoint(segment.getFin()));
              model.add(new AppuiDouble((TriangleTerrain) proche,p1,alpha));
              this.vue.redrawAll();
              this.changeEtat(40);  
           }
           
       } else if (this.etat==50){
           pclick = new Point(t.getX(), t.getY());
           Treilli proche = this.vue.getModel().plusProche(pclick, MAX_VALUE);
           if(proche instanceof TriangleTerrain){
              int p1 = ((TriangleTerrain) proche).distancePointInt(pclick);
              SegmentTerrain segment = ((TriangleTerrain) proche).distancePointSegment(pclick);
              Point p = segment.distancePointDonnePoint(pclick);
              Groupe model = this.vue.getModel();
              double alpha = ((segment.getDebut().distancePoint(p))/segment.getDebut().distancePoint(segment.getFin()));
              model.add(new AppuiEncastre((TriangleTerrain) proche,p1,alpha));
              this.vue.redrawAll();
              this.changeEtat(50);
            }
           
       } else if (this.etat==60){    
       double px = t.getX();
       double py = t.getY();
       if(dansTerrain(px,py,this.vue.getModel().getTerrain())){
          Groupe model = this.vue.getModel();
          model.add(new NoeudSimple(px,py));
          this.vue.redrawAll();  
       } else {
           System.out.println("Pas dans terrain");
       }
        } else if (this.etat==70){ 
            pclick = new Point(t.getX(), t.getY());
            Treilli proche = this.vue.getModel().NoeudplusProche(pclick, MAX_VALUE);
            if(proche instanceof Noeud){
                this.selection.add(proche);
                System.out.println(this.selection.get(0));
                this.vue.redrawAll(); 
                this.changeEtat(71);
            }
        } else if (this.etat==71){ 
            pclick = new Point(t.getX(), t.getY()); 
            Treilli proche = this.vue.getModel().NoeudplusProche(pclick, MAX_VALUE);
            if(proche instanceof Noeud){
                Groupe model = this.vue.getModel();
                model.add(new Barre((Noeud) this.selection.get(0),(Noeud) proche));
                this.selection.clear();
                this.vue.redrawAll(); 
                this.changeEtat(70);
            }  
        } else if (this.etat==80){
          pclick = new Point(t.getX(), t.getY()); 
          this.changeEtat(81);
        } else if(this.etat == 81) {
          pclick2 = new Point(t.getX(), t.getY());
          Terrain terrain = new Terrain(pclick,pclick2);
          vue.getModel().setTerrain(terrain);
          vue.getModel().add(terrain);
          this.vue.redrawAll(); 
          this.changeEtat(10);
        }
     
       
        }   
           
           
       
    
    

    public List<Treilli> getSelection() {
        return selection;
    }

    void boutonSelect(ActionEvent t) {
        this.changeEtat(10);
        this.vue.getTest().appendText("Bouton select"+"\n");
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
    
    void realSave(File f) {
        try {
            this.vue.getModel().sauvegarde(f);
            this.vue.setCurFile(f);
            this.vue.getInStage().setTitle(f.getName());
        } catch (IOException ex) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Problème durant la sauvegarde");
            alert.setContentText(ex.getLocalizedMessage());

            alert.showAndWait();
        } finally {
            this.changeEtat(20);
        }
    }

    void menuNouveau(ActionEvent t) {
        Stage nouveau = new Stage();
        Scene sc = new Scene(new mainPane(nouveau),800,600);
        nouveau.setScene(sc);
        nouveau.setTitle("Nouveau");
        nouveau.show();
        this.changeEtat(80);
    }
    
    
    void menuSave(ActionEvent t) {
        if (this.vue.getCurFile() == null) {
            this.menuSaveAs(t);
        } else {
            this.realSave(this.vue.getCurFile());
        }
    }
    
    void menuSaveAs(ActionEvent t) {
        FileChooser chooser = new FileChooser();
        File f = chooser.showSaveDialog(this.vue.getInStage());
        if (f != null) {
            this.realSave(f);
        }
    }
    
    void menuOpen(ActionEvent t) {
        FileChooser chooser = new FileChooser();
        File f = chooser.showOpenDialog(this.vue.getInStage());
        if (f != null) {
            try {
                System.out.println("Avant");
                Treilli lue = Treilli.lecture(f);
                System.out.println("Apres");
                Groupe glu = (Groupe) lue;
                Stage nouveau = new Stage();
                nouveau.setTitle(f.getName());
                Scene sc = new Scene(new mainPane(nouveau, f, glu), 800, 600);
                nouveau.setScene(sc);
                nouveau.show();
            } catch (Exception ex) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("Problème durant la sauvegarde");
                alert.setContentText(ex.getLocalizedMessage());

                alert.showAndWait();
            } finally {
                this.changeEtat(20);
            }
        }
    }

    void desactiver(ActionEvent t) {
        this.vue.getTest().setVisible(false);
    }
    
     void activer(ActionEvent t) {
        this.vue.getTest().setVisible(true);
    }

    void tt(ActionEvent t) {
        this.vue.getTest().clear();
        this.vue.getTest().appendText("Pour créer un \ntriangle terrain \ncliqué 3 fois \ndans la zone de dessin\n");
    }
    
    void appui(ActionEvent t) {
        this.vue.getTest().clear();
        this.vue.getTest().appendText("Pour créer un \nappui cliqué sur le \nsegment terrain d'un \ntriangle terrain \n");
    }
    
    void noeudSimple(ActionEvent t) {
        this.vue.getTest().clear();
        this.vue.getTest().appendText("Pour créer un \nnoeud simple \ncliqué sur la \nzone de dessin\n");
    }
    
    void barre(ActionEvent t) {
        this.vue.getTest().clear();
        this.vue.getTest().appendText("Pour créer une \nbarre cliqué \nsur deux \nnoeud\n");
    }

    void TypeDeBarre(ActionEvent t) {
        Stage nouveau = new Stage();
        Scene sc = new Scene(this.creabarre = new CreationTypeDeBarre(this.vue),400,300);
        nouveau.setScene(sc);
        nouveau.setTitle("Creation type de barre");
        nouveau.show();
    }
    
    public double convert(String test, String nom){
        double res=0;
        try {
           res = Double.parseDouble(test);
        } catch (NumberFormatException nfe){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("la case "+nom+" ne peut pas rester vide \nou mauvais format saisie\n ");
            alert.setContentText(nfe.getLocalizedMessage());
            alert.showAndWait();
        }
        return res;
    }
    
    public void test(String test, String nom){
        if(test.isEmpty()){
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("La case "+nom+" ne peut pas rester vide");
            alert.showAndWait();
        }
    }

    void ajouter(ActionEvent t) {
        
        String nom = this.creabarre.getTFnom().getText();
        String cout = this.creabarre.getTFcoutAuMetre().getText();
        String longMin = this.creabarre.getTFlongueurMin().getText();
        String longMax = this.creabarre.getTFlongueurMax().getText();
        String maxTension = this.creabarre.getTFresistanceMaxTension().getText();
        String maxCompression = this.creabarre.getTFresistanceMaxCompression().getText();
       
        test(nom, "nom");
        double dCout = convert(cout, "Coût");
        double dlongMin = convert(longMin, "longueur minimum");
        double dlongMax = convert(longMax, "longeur maximum");
        double dmaxTension = convert(maxTension, "Tension maximum");
        double dmaxCompression = convert(maxCompression, "Compression maximum");
        
        if((dCout==0)||(dlongMin==0)||(dlongMax==0)||(dmaxTension==0)||(dmaxCompression==0)||(nom.isEmpty())){
            
        } else { TypeDeBarre ntdb = new TypeDeBarre(nom, dCout, dlongMin, dlongMax, dmaxTension, dmaxCompression);
            vue.getModel().add(ntdb);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Type de barre créé !");
            alert.showAndWait();
            this.creabarre.getTFnom().setText("");
            this.creabarre.getTFcoutAuMetre().setText("");
            this.creabarre.getTFlongueurMin().setText("");
            this.creabarre.getTFlongueurMax().setText("");
            this.creabarre.getTFresistanceMaxTension().setText("");
            this.creabarre.getTFresistanceMaxCompression().setText("");
        }
    }

    void listeTypeDeBarre(ActionEvent t) {
        Stage nouveau = new Stage();
        Scene sc = new Scene(new VueTypeDeBarre(this.vue),1000,300);
        nouveau.setScene(sc);
        nouveau.setTitle("Liste type de barre");
        nouveau.show(); 
    }

    public void boutonModifier(ActionEvent t, TypeDeBarre type) {
        Stage nouveau = new Stage();
        Scene sc = new Scene(this.modifbarre = new ModificationTypeDeBarre(this.vue, type),400,300);
        nouveau.setScene(sc);
        nouveau.setTitle("Modification type de barre");
        nouveau.show();
    }

    public void boutonSup(ActionEvent t, TypeDeBarre type) {
        type.supr(); 
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText("Type de barre supprimé !");
        alert.showAndWait();
    }

    void appliquer(ActionEvent t, TypeDeBarre type) {
        String nom = this.modifbarre.getTFnom().getText();
        String cout = this.modifbarre.getTFcoutAuMetre().getText();
        String longMin = this.modifbarre.getTFlongueurMin().getText();
        String longMax = this.modifbarre.getTFlongueurMax().getText();
        String maxTension = this.modifbarre.getTFresistanceMaxTension().getText();
        String maxCompression = this.modifbarre.getTFresistanceMaxCompression().getText();
        
        test(nom, "nom");
        double dCout = convert(cout, "Coût");
        double dlongMin = convert(longMin, "longueur minimum");
        double dlongMax = convert(longMax, "longeur maximum");
        double dmaxTension = convert(maxTension, "Tension maximum");
        double dmaxCompression = convert(maxCompression, "Compression maximum");
        
         if((dCout==0)||(dlongMin==0)||(dlongMax==0)||(dmaxTension==0)||(dmaxCompression==0)||(nom.isEmpty())){
            
        } else {
             type.setNom(nom);
             type.setCoutAuMetre(dCout);
             type.setLongueurMin(dlongMin);
             type.setLongueurMax(dlongMax);
             type.setResistanceMaxTension(dmaxTension);
             type.setResistanceMaxCompression(dmaxCompression);
             Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText("Type de barre modifier !");
            alert.showAndWait();
            
         }
 
    }
    
    public boolean dansTerrain(double px, double py, Terrain terrain){
        boolean test = false;
        try{
        if(((terrain.getMinX()<px)&&(px<terrain.getMaxX()))&&((terrain.getMinY()<py)&&(py<terrain.getMaxY()))){
           test = true; 
        } else {
            //return false;
        }
        } catch (Exception e) {
            this.vue.getTest().appendText("Terrain inexistant\nCliquez deux fois\ndans la zone de dessin\npour le créer");
            this.changeEtat(80);
        }
        
        return test;
    }

    void calculdeforce(ActionEvent t) {
            vue.getModel().Force();
            
        }
             
        

        
    }
    
    
    

