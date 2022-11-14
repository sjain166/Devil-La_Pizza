/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package orderConfirmation;

import Cart.Cart_FXMLController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author palneema
 */
public class OrderConfirmationController implements Initializable {
    @FXML
    private ImageView emoji1;

    @FXML
    private ImageView emoji2;

    @FXML
    private Label textDisplay;
    
    private boolean orderConfirmation;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Thread(() -> {
            Platform.runLater(() -> {
                setEmoji();  
            });
            try {
                Thread.sleep(10);
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
        textDisplay.setStyle("-fx-font-size: 40");
        textDisplay.setAlignment(Pos.CENTER);
       
   
    }

    public void setOrderConfirmation(boolean input){
        orderConfirmation = input;
    }
    
    
    
    public void setEmoji(){
        
        if(orderConfirmation){
            File file = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\happyeating.gif");
            Image image = new Image(file.toURI().toString());
            textDisplay.setText("Your Order is Placed Successfully!!!");
            textDisplay.setStyle("-fx-text-fill: #023020");
            emoji1.setImage(image);
            emoji2.setImage(image);
        }
        
        else{
            File file = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\giphy.gif");
            Image image = new Image(file.toURI().toString());
            textDisplay.setText("Insufficient Balance !");
            textDisplay.setStyle("-fx-text-fill: #FF0000");
            emoji1.setImage(image);
            emoji2.setImage(image);
        }
    }
    
}
