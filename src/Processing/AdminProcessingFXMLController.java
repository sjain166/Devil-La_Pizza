/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Processing;

import Tracker.Tracker_FXMLController;
import UtilityFolder.DBFuntions;
import UtilityFolder.OrderClass;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Separator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author siddh
 */
public class AdminProcessingFXMLController implements Initializable {

    @FXML
    private VBox orderContainer;
    
    @FXML
    private AnchorPane mainPane;
    
    private static List<String> orderToPush = new ArrayList<>();
    
     
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
    }


    public void setOrders() throws ClassNotFoundException, IOException{
        orderContainer.getChildren().clear();
        DBFuntions db = new DBFuntions();
        List<OrderClass> orders = db.getPlacedOrder("PLC");
        for(OrderClass o : orders){
            
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource("/Processing/processingCardFXML.fxml"));
            HBox pane = fXMLLoader.load();
            ProcessingCardFXMLController controller = fXMLLoader.getController();
            controller.setOrder(o);
            controller.setType(true);
            orderContainer.getChildren().add(pane);
        }  
    }
    
    
    public void addToPush(String orderID){
        orderToPush.add(orderID);
    }
    
    public void removeToPush(String orderID){
        if(orderToPush.contains(orderID)){
            orderToPush.remove(orderID);
        }      
    }
    
    public void commitOrders() throws ClassNotFoundException, IOException{
        DBFuntions db = new DBFuntions();
        
        for(String orderID : orderToPush){
            db.setToCook(Integer.parseInt(orderID));
        }
        
        setOrders();
    }
    

    
}
