/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.cours.projetv2.gui;

import fr.insa.cours.projetv2mod.TypeDeBarre;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import static fr.insa.cours.projetv2mod.CatalogueDeBarre.listTypeDeBarre;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.util.Callback;

/**
 *
 * @author lasch
 */
public class VueTypeDeBarre extends BorderPane{
    
    private TableView<TypeDeBarre> tableau;
    
    
    private ObservableList<TypeDeBarre> getList(ArrayList<TypeDeBarre> lf) {
      
       ObservableList<TypeDeBarre> list = FXCollections.observableArrayList();
     for(TypeDeBarre t : lf) {
         list.add(t);
     }
     
      return list;
    }
    
    public VueTypeDeBarre(){
    
    tableau = new TableView<TypeDeBarre>();
    tableau.setMinSize(1000, 300);
        
    TableColumn<TypeDeBarre, String> nomC = new TableColumn("Nom");
    TableColumn<TypeDeBarre, Double> coutC = new TableColumn ("Coût au metre");
    TableColumn<TypeDeBarre, Double> longMinC = new TableColumn("Longueur minimum");
    TableColumn<TypeDeBarre, Double> longMaxC = new TableColumn("Longueur maximum");
    TableColumn<TypeDeBarre, Double> maxTensionC = new TableColumn("Tension maximum");
    TableColumn<TypeDeBarre, Double> maxCompressionC = new TableColumn ("Compression maximum");
    
    nomC.setCellValueFactory(new PropertyValueFactory<>("nom"));
    coutC.setCellValueFactory(new PropertyValueFactory<>("coutAuMetre"));
    longMinC.setCellValueFactory(new PropertyValueFactory<>("longueurMin"));
    longMaxC.setCellValueFactory(new PropertyValueFactory<>("longueurMax"));
    maxTensionC.setCellValueFactory(new PropertyValueFactory<>("resistanceMaxTension"));
    maxCompressionC.setCellValueFactory(new PropertyValueFactory<>("resistanceMaxCompression"));
   
    ObservableList<TypeDeBarre> list = getList(listTypeDeBarre);
    tableau.setItems(list);
 
   
    tableau.getColumns().addAll(nomC, coutC, longMinC,longMaxC,maxTensionC,maxCompressionC);
    
    HBox conteneur = new HBox(tableau);
    this.setTop(conteneur);
        
    }
    
    
    
 
     
 
    
}
