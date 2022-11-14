/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Menu;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author siddh
 */
public class Menu_FXMLController{

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label text;
    
    private String[] colors = {"B0DDF2" , "FFEDBB" , "FFC6C6" , "E8FFC6" , "FFCDF7"};
    
    public void setData(String item){
        if( item.equals("Ham") || item.equals("Salami")  || item.equals("Chicken") || item.equals("Pepperoni")){
            text.setTextFill(Color.web("#FF0000"));
            text.setText(item);
            text.setStyle("-fx-background-color: #B0DDF2");//+ Color.web(colors[(int)(Math.random()*colors.length)]));
        }
        else{
            text.setText(item);
            //text.setStyle("-fx-background-color: "+ Color.web(colors[(int)(Math.random()*colors.length)]));
            text.setStyle("-fx-background-color: #FFEDBB");
        }
    }    
    
}
