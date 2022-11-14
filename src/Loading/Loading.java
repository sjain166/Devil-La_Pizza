/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package Loading;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author siddh
 */
public class Loading extends Application {
    
    @Override
    public void start(Stage primaryStage) {
          
        try{
            Parent root = FXMLLoader.load(getClass().getResource("Loading_FXML.fxml"));  
            Scene scene = new Scene(root, 1370, 880);
            primaryStage.setResizable(false);
            primaryStage.setTitle("Devils' La'Pizza");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
