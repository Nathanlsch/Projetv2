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
    
    private Numeroteur() {
        this(0);
    }
    
    private Numeroteur(int prochainId) {
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
        this.prochainID = this.prochainID+1;
        return this.prochainID-1;
    }
   
    public boolean objExist(TO obj){
        return this.objetversId.containsKey(obj);
    }
    
    public int getOucreeID(TO obj) {
        if(this.objExist(obj)){
            return this.objetversId.get(obj);
        } else {
            return this.creeID(obj);
        }
    }
    
    
}
