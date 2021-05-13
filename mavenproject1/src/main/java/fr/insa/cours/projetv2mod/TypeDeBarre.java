/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

import java.io.IOException;
import java.io.Writer;
import javafx.scene.canvas.GraphicsContext;
import static fr.insa.cours.projetv2mod.Treilli.Save;
import static fr.insa.cours.projetv2mod.CatalogueDeBarre.listTypeDeBarre;

/**
 *
 * @author celiajoy
 */
public class TypeDeBarre extends Treilli {
    
    private int id;
    private String nom;
    private double coutAuMetre;
    private double longueurMin;
    private double longueurMax;
    private double resistanceMaxTension;
    private double resistanceMaxCompression;


    public TypeDeBarre(int id, double coutAuMetre, double longueurMin, double longueurMax, double resistanceMaxTension, double resistanceMaxCompression) {
        this.id = id;
        this.coutAuMetre = coutAuMetre;
        this.longueurMin = longueurMin;
        this.longueurMax = longueurMax;
        this.resistanceMaxTension = resistanceMaxTension;
        this.resistanceMaxCompression = resistanceMaxCompression;
        listTypeDeBarre.add(this);
    }
    
    public TypeDeBarre(String nom, double coutAuMetre, double longueurMin, double longueurMax, double resistanceMaxTension, double resistanceMaxCompression) {
        this.Identificateur(num);
        this.nom = nom;
        this.coutAuMetre = coutAuMetre;
        this.longueurMin = longueurMin;
        this.longueurMax = longueurMax;
        this.resistanceMaxTension = resistanceMaxTension;
        this.resistanceMaxCompression = resistanceMaxCompression;
        listTypeDeBarre.add(this);
    }

    public int getId() {
        return id;
    }
    
    
    public double getCoutAuMetre()
    {
        return this.coutAuMetre;
    }
    
    public double getLongueurMin()
    {
        return this.longueurMin;
    }
    
    public double getLongueurMax()
    {
        return this.longueurMax;
    }

    public double getResistanceMaxTension() {
        return resistanceMaxTension;
    }
    
    
    public double getResistanceMaxCompression()
    {
        return this.resistanceMaxCompression;
    }
    
  
    public void ResistanceMaxCompression(double resistanceMaxCompression)
    {
        this.setResistanceMaxCompression(resistanceMaxCompression);
    } 

    @Override
    public String toString() {
        return "TypeDeBarre;" + this.getId() + ";" + this.getLongueurMin() +";"+ this.getResistanceMaxTension()+";"+ this.getResistanceMaxCompression();
    }
    
    public void Identificateur(Numeroteur<Treilli> num) {
        this.setId(num.creeID(this));
    }

    @Override
    public void dessine(GraphicsContext context) {
        
    }

    @Override
    public double distancePoint(Point p2) {
        return 1000000;
    }

    @Override
    public void dessineSelection(GraphicsContext context) {
        
    }

    @Override
    public void save(Writer w) throws IOException {
        
        if(! Save.contains(this)){
             Save.add(this);
             w.append("TypeDeBarre;"+this.getId()+";"+this.getNom()+";"+this.getCoutAuMetre()+";"+this.getLongueurMin()+
                    ";"+this.getLongueurMax()+";"+this.getResistanceMaxTension()+";"+this.getResistanceMaxCompression()+"\n");
        }
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param coutAuMetre the coutAuMetre to set
     */
    public void setCoutAuMetre(double coutAuMetre) {
        this.coutAuMetre = coutAuMetre;
    }

    /**
     * @param longueurMin the longueurMin to set
     */
    public void setLongueurMin(double longueurMin) {
        this.longueurMin = longueurMin;
    }

    /**
     * @param longueurMax the longueurMax to set
     */
    public void setLongueurMax(double longueurMax) {
        this.longueurMax = longueurMax;
    }

    /**
     * @param resistanceMaxTension the resistanceMaxTension to set
     */
    public void setResistanceMaxTension(double resistanceMaxTension) {
        this.resistanceMaxTension = resistanceMaxTension;
    }

    /**
     * @param resistanceMaxCompression the resistanceMaxCompression to set
     */
    public void setResistanceMaxCompression(double resistanceMaxCompression) {
        this.resistanceMaxCompression = resistanceMaxCompression;
    }
    
  
    
    
}
