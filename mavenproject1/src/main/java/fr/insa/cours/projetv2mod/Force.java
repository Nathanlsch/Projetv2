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
public class Force {
    
    private Noeud noeud;
    private double normeX;
    private double normeY;
    private int Id;
    
    public Force(Noeud noeud, double normeX, double normeY) {
    this.noeud = noeud;
    this.normeX = normeX;
    this.normeY = normeY;
    
    }
    
    public void setnormeX(double normeX){
        this.normeX = normeX;
    }
    
    public void setnormeY(double normeY){
        this.normeY = normeY;
    }
    
    public void setnoeud(Noeud noeud){
        this.noeud = noeud;
    }
    
    public void setId(int Id){
        this.Id = Id;
    }
    
    public double getnormeX(){
        return this.normeX;
    }
    
    public double getnormeY(){
        return this.normeY;
    }
    
    public Noeud getnoeud(){
        return this.noeud;
    }
     
    public int getId(){
        return this.Id;
    }   
    
    @Override
    public String toString() {
        return "Force;" + this.getId() + ";" + this.getnoeud() +";"+ this.getnormeX()+";"+ this.getnormeY();
    }
}
