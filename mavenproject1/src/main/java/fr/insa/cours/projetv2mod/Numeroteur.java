/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2mod;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author lasch
 */
public class Numeroteur<TO> {
    
    private TreeMap<Integer,TO> idversObjet;
    private Map<TO,Integer> objetversId;
    
    private int prochainID;
    
    public Numeroteur() {
        this(0);
    }
    
    public Numeroteur(int prochainId) {
        this.prochainID = prochainId;
        this.idversObjet = new TreeMap<>();
        this.objetversId = new HashMap<>();
    }
    
    public int creeID(TO obj) {
        if(this.objetversId.containsKey(obj)){
            throw new Error("Objet" + obj + " déja dans le numéroteur");
        }
        this.idversObjet.put(this.prochainID, obj);
        this.objetversId.put(obj, this.prochainID);
        this.prochainID = this.prochainID + 1;
        return this.prochainID-1;
    }
   
    public boolean objExist(TO obj){
        return this.objetversId.containsKey(obj);
    }
    
    public int getID(TO obj) {
        if(this.objExist(obj)){
            return this.objetversId.get(obj);
        } else {
            throw new Error("Objet "+ obj+" inconnu dans numéroteur");
        }
    }
    
     public int getOucreeID(TO obj) {
        if(this.objExist(obj)){
            return this.objetversId.get(obj);
        } else {
            return this.creeID(obj);
        }
    }
     
    public TO getObj(int id){
       if(! this.idExist(id)){
            throw new Error("Identificateur non existant");
        } else { 
           return this.idversObjet.get(id);
       }
    }
    
    public boolean idExist(int id){
        return this.idversObjet.containsKey(id);
    }
            
    public void associe(int id,TO obj){
        if(this.idExist(id)){
            throw new Error("Identificateur existant");
        } else {
            this.idversObjet.put(id, obj);
            this.objetversId.put(obj, id);
        }
    }
    
    
}
