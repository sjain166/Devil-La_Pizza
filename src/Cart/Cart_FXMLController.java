/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Cart;

import UtilityFolder.DBFuntions;
import UtilityFolder.OrderClass;
import UtilityFolder.userDetails;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import org.joda.time.DateTime;

/**
 * FXML Controller class
 *
 * @author siddh
 */
public class Cart_FXMLController implements Initializable {

    @FXML
    private VBox billContainer;

    @FXML
    private TextField hoursText;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TextField minText;

    @FXML
    private Button orderButton;

    @FXML
    private Button orderButton2;

    @FXML
    private TextField removeID;

    @FXML
    private Button removeOrder;

    @FXML
    private ScrollPane scrollPane;
    
    @FXML
    private Label timeConfirmation;
    
    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    private userDetails user;

    List<OrderClass> orders;

    ObservableList<HBox> bills = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new Thread(() -> {
            Platform.runLater(() -> {
                try {
                    setBill();
                } catch (IOException ex) {
                    Logger.getLogger(Cart_FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();

        //scrollPane.setStyle("-fx-background:  #1D3557;");
    }

    public void setBill() throws IOException {

        billContainer.getChildren().clear();
        orders = user.getOrderList();

        for (int i = 0; i < orders.size(); i++) {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource("/Cart/Bill_FXML.fxml"));
            HBox pane = fXMLLoader.load();
            Bill_FXMLController controller = fXMLLoader.getController();
            controller.setBill(orders.get(i), i + 1);
            controller.setUser(user);
            billContainer.getChildren().add(pane);
            Separator sep = new Separator(Orientation.HORIZONTAL);
            billContainer.getChildren().add(sep);
            bills.add(pane);
        }

        System.out.println(bills.size());

    }

    public void setUser(userDetails user) {
        this.user = user;
    }

    public void RemoveOrder() throws IOException {
        
        int orderNumberToDelete = Integer.parseInt(removeID.getText()) - 1;
        if(orderNumberToDelete < 0 || orderNumberToDelete >= user.getOrderList().size()){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Please Enter a Valid Order-ID :)");
            //errorAlert.setContentText("You Must Select Atleast 1 Pizza to Add to Cart");
            errorAlert.showAndWait();
            removeID.setText("");
            return;
        }
        user.getOrderList().remove(Integer.parseInt(removeID.getText()) - 1);
        removeID.setText("");
        setBill();

    }
    
    public String inputTime(){
        
            DateTime time = new DateTime();
            int currHours = time.getHourOfDay();
            int currMin = time.getMinuteOfHour();
            
            if(hoursText.getText().isEmpty() || minText.getText().isEmpty()){
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Please Enter a Pickup Time :) ");
                errorAlert.showAndWait();
                hoursText.setText("");
                minText.setText("");
                return "";
            }
            
            int inputHours = Integer.parseInt(hoursText.getText());
            int inputMin = Integer.parseInt(minText.getText());
            
            if(inputHours < 0 || inputHours >= 24 || inputMin < 0 || inputMin > 60){
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Please Enter a Valid Pickup Time :) ");
                errorAlert.showAndWait();
                hoursText.setText("");
                minText.setText("");
                return "";
            }
            
            DateTime updatedTime = time.plusMinutes(30);
            
            int updatedHours = updatedTime.getHourOfDay();
            int updatedMin = updatedTime.getMinuteOfHour();
            
            if(inputHours < updatedHours){
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Please give us atleast 30 minutes to prepare your complete order :)");
                errorAlert.setContentText("Thank You for Your Patience :)");
                errorAlert.showAndWait();
                hoursText.setText("");
                minText.setText("");
                return "";
            }
            
            else if(inputHours == updatedHours && inputMin < updatedMin){
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Please give us atleast 30 minutes to prepare your complete order :)");
                errorAlert.setContentText("Thank You for Your Patience :)");
                errorAlert.showAndWait();
                hoursText.setText("");
                minText.setText("");
                return "";
            }
            //String s = String.format("%02d", someNumber);
            String confirmTime =""; 
      
            if(inputHours >= 12 && inputHours <=24){
                confirmTime = String.format("%02d", inputHours) +":" + String.format("%02d", inputMin) + " P.M.";
                timeConfirmation.setText("Your Picktime Time is -> " + confirmTime);
            }
            else{
                confirmTime = String.format("%02d", inputHours) +":" + String.format("%02d", inputMin) + " A.M.";
                timeConfirmation.setText("Your Picktime Time is -> " + confirmTime);
            }
            
            return confirmTime;
            
            
    }
    
    
    public void placeOrder() throws ClassNotFoundException, IOException {

        if (timeConfirmation.getText().equals("")) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Please set-up a Valid Pick-up Time for Your Order :)");
            errorAlert.setContentText("Thank You for Your Patience :)");
            errorAlert.showAndWait();
            return;
        }
        
        if(inputTime().equals("")){
           return; 
        }

        double finalCost = 0.00;

        for (OrderClass currOrder : orders) {
            finalCost += currOrder.getGrandTotal();
        }
        
        
        finalCost = Double.parseDouble(df.format(finalCost));
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Place Order");
        alert.setHeaderText("Are you sure want to place the order of : $" + finalCost);
        alert.setContentText("To Confirm Press 'OK' \nTo Cancel/Change order Press 'Cancel'");
        
        // option != null.
        Optional<ButtonType> option = alert.showAndWait();
        boolean placeOrder;
        if(option.get() == ButtonType.OK){
            proceedToPlaceOrder(finalCost);
        }
        
        orders.clear();

    }
    
    
    public void proceedToPlaceOrder(double finalCost) throws ClassNotFoundException, IOException{
        DBFuntions db = new DBFuntions();
        double newBalance = user.getBalance() - finalCost;
        if( newBalance >= 0){
            db.transaction(newBalance, user.getUserID());
            
            for (OrderClass currOrder : orders) {
                db.pushOrder(currOrder , user.getUserID() , inputTime());   
            }
            
            for (int i = 0 ; i < user.getOrderList().size() ; i++) {
                user.getOrderList().remove(i);
            }
 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/orderConfirmation/orderConfirmation.fxml"));
            AnchorPane newPane = loader.load();
            orderConfirmation.OrderConfirmationController controller = loader.getController();
            controller.setOrderConfirmation(true);
            mainPane.getChildren().setAll(newPane);
        }
        else{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/orderConfirmation/orderConfirmation.fxml"));
            AnchorPane newPane = loader.load();
            orderConfirmation.OrderConfirmationController controller = loader.getController();
            controller.setOrderConfirmation(false);
            mainPane.getChildren().setAll(newPane);
        }
    }
    
    
}
