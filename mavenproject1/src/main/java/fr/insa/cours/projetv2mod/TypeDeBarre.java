/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

/**
 *
 * @author celiajoy
 */
public class TypeDeBarre
{
    private int id;
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
    
    
  
    
    
}
