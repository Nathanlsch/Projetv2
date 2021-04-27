/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

//import fr.insa.cours.projet_treilli.*;
import fr.insa.cours.projetv2.recup.Lire;
import java.util.List;

/**
 *
 * @author lasch
 */
public class Terrain {
    
    private double minX;
    private double maxX;
    private double minY;
    private double maxY; 
    
    private List<TriangleTerrain> contientTriangle;

    @Override
    public String toString() {
        return "ZoneConstructible;"+ minX + ";" + maxX + ";" + minY + ";" + maxY;
    }

    public Terrain(double minX,double maxX,double minY,double maxY) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }
    
    
    public static Terrain DemandeTerrain(){
        
        System.out.println("Donner l'abscisse minimum");
           double minX = Lire.d();
           System.out.println("Donner l'abscisse maximum");
           double maxX = Lire.d();
           System.out.println("Donner l'ordonée minimum");
           double minY = Lire.d();
           System.out.println("Donner l'ordonnée maximum");
           double maxY = Lire.d();
           
           Terrain terrain = new Terrain(minX,maxX,minY,maxY);
           System.out.println(terrain.toString());
           
          return terrain;
           
    }
       
    
}
