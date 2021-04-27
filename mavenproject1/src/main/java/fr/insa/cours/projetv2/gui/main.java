package fr.insa.cours.projetv2.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
/**
 *
 * @author lasch
 */
public class main extends Application {
    
    public static void main(String[] args) {
        launch();
    }
    

    @Override
    public void start(Stage stage) {
        stage.setTitle("Projetv2");
        Group root = new Group();
        BorderPane bp = new BorderPane();
        mainPane main = new mainPane();
        MainMenu menu = new MainMenu(main);
        bp.setTop(menu);
        bp.setCenter(main);
        Scene sc = new Scene(bp,800,600);
        stage.setScene(sc);
        stage.show();
    }

}
