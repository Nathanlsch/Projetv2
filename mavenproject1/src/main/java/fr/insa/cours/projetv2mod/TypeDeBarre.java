/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

import java.io.IOException;
import java.io.Writer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import static javafx.scene.paint.Color.color;
import static fr.insa.cours.projetv2mod.Treilli.Save;

/**
 *
 * @author celiajoy
 */
public class TypeDeBarre extends FigureSimple{
    
    private int id;
    private String nom;
    private double coutAuMetre;
    private double longueurMin;
    private double longueurMax;
    private double resistanceMaxTension;
    private double resistanceMaxCompression;


    public TypeDeBarre(int id, double coutAuMetre, double longueurMin, double longueurMax, double resistanceMaxTension, double resistanceMaxCompression) {
        super(Color.BLACK);
        this.id = id;
        this.coutAuMetre = coutAuMetre;
        this.longueurMin = longueurMin;
        this.longueurMax = longueurMax;
        this.resistanceMaxTension = resistanceMaxTension;
        this.resistanceMaxCompression = resistanceMaxCompression;
    }
    
    public TypeDeBarre(String nom, double coutAuMetre, double longueurMin, double longueurMax, double resistanceMaxTension, double resistanceMaxCompression) {
        super(Color.BLACK);
        this.Identificateur(num);
        this.nom = nom;
        this.coutAuMetre = coutAuMetre;
        this.longueurMin = longueurMin;
        this.longueurMax = longueurMax;
        this.resistanceMaxTension = resistanceMaxTension;
        this.resistanceMaxCompression = resistanceMaxCompression;
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
        this.resistanceMaxCompression = resistanceMaxCompression;
    } 

    @Override
    public String toString() {
        return "TypeDeBarre;" + this.getId() + ";" + this.getLongueurMin() +";"+ this.getResistanceMaxTension()+";"+ this.getResistanceMaxCompression();
    }
    
    public void Identificateur(Numeroteur<Treilli> num) {
        this.id = num.creeID(this);
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
             w.append("TypeDeBarre;"+this.getId()+";"+this.nom+";"+this.coutAuMetre+";"+this.longueurMin+
                    ";"+this.longueurMax+";"+this.resistanceMaxTension+";"+this.resistanceMaxCompression+"\n");
        }
    }
    
  
    
    
}
