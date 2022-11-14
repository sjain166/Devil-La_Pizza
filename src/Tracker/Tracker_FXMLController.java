/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Tracker;

import UtilityFolder.DBFuntions;
import UtilityFolder.OrderClass;
import UtilityFolder.userDetails;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author siddh
 */
public class Tracker_FXMLController implements Initializable {
    @FXML
    private ScrollPane scrollPane;
    
    @FXML
    private VBox orderContainer;
    
    private userDetails user;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        new Thread(() -> {
            Platform.runLater(() -> {
                try {
                    setOrders();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Tracker_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Tracker_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
        
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
       
        
        
    }
    
    public void setUser(userDetails user){
        this.user = user;
    }
    
    public void setOrders() throws ClassNotFoundException, IOException{
        orderContainer.getChildren().clear();
        DBFuntions db = new DBFuntions();
        List<OrderClass> orders = db.getCurrentOrder(user.getUserID());
        System.out.println(orders.size());
        for(OrderClass o : orders){
            
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource("/Tracker/CurrentTracker_FXML.fxml"));
            HBox pane = fXMLLoader.load();
            CurrentTracker_FXMLController controller = fXMLLoader.getController();
            controller.setUser(user);
            controller.setOrder(o);
            orderContainer.getChildren().add(pane);
            
        }  
    }
    
    
    public void setPastOrder() throws ClassNotFoundException, IOException{
        orderContainer.getChildren().clear();
        DBFuntions db = new DBFuntions();
        List<OrderClass> orders = db.getUserPastOrder(user.getUserID());
        for(OrderClass o : orders){
            
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource("/Tracker/CurrentTracker_FXML.fxml"));
            HBox pane = fXMLLoader.load();
            CurrentTracker_FXMLController controller = fXMLLoader.getController();
            controller.setUser(user);
            controller.setOrder(o);
            orderContainer.getChildren().add(pane);
            
        }   
    }
    
    
    

    
    
}
