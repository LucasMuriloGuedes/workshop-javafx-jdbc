/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package worshop.javafx.jdbc;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

/**
 *
 * @author Lucas Murilo
 */
public class WorshopJavafxJdbc extends Application {
    
    private static Scene mainScene;
    
@Override
public void start(Stage primaryStage) { 
    try { 
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MainView.fxml")); 
        ScrollPane scrollPane = loader.load(); 
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        
        mainScene = new Scene(scrollPane); 
        primaryStage.setScene(mainScene); 
        primaryStage.setTitle("Sample JavaFX application"); 
        primaryStage.show(); 
     } catch (IOException e) { 
        e.printStackTrace(); 
     } 
    } 

    public WorshopJavafxJdbc() {
    }

    public static Scene getMainScene(){
        return mainScene;
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
