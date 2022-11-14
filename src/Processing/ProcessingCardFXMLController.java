/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Processing;

import Chef.ChefFXMLController;
import UtilityFolder.DBFuntions;
import UtilityFolder.OrderClass;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author siddh
 */
public class ProcessingCardFXMLController implements Initializable {

    @FXML
    private CheckBox COOK;

    @FXML
    private CheckBox RDP;

    @FXML
    private Label cQTY;

    @FXML
    private Label cType;

    @FXML
    private Label orderID;

    @FXML
    private Label orderID1;

    @FXML
    private Label pCStyle;

    @FXML
    private Label pCrust;

    @FXML
    private Label pMeat;

    @FXML
    private Label pName;

    @FXML
    private Label pQTY;

    @FXML
    private Label pSize;

    @FXML
    private Label pVeggie;

    @FXML
    private Label sides;
    
    @FXML
    private Label pickupTime;
    
    @FXML
    private Label orderStat;
    
    @FXML
    private HBox mainBox;

    
    private boolean Processing;
    private OrderClass order;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void setOrder(OrderClass order){
        this.order = order;
        orderID.setText(order.getOrderID()+"");
        orderID1.setText(order.getUserName());
        pickupTime.setText(order.getTime());
        pName.setText(order.getPizzaName());
        pSize.setText(order.getPizzaSize());
        pQTY.setText("x"+order.getPizzaQty());
        pCrust.setText(order.getCrust());
        pCStyle.setText(order.getCookStyle());
        cType.setText(order.getCheeseType());
        cQTY.setText(order.getCheeseQty());
        
       
        List<String> meat = order.getMeat();
        String temp = "";
        for(int i = 0; i < meat.size(); i++){
            if(i != meat.size()-1){
                temp += (meat.get(i)+", ");
            }
            else{
                temp+=(meat.get(i));
            }
        }
       
        
        if(meat.size() == 0){
             pMeat.setText("No MEAT ADDED");
        }
        else{
             pMeat.setText(temp);
        }
        
        temp = "";
        pVeggie.setText("");
        List<String> veggies = order.getVeggies();
        for(int i = 0; i < veggies.size(); i++){
            if(i != veggies.size()-1){
                temp += (veggies.get(i)+", ");
            }
            else{
                temp += (veggies.get(i));
            }
        }
        if(veggies.size() == 0){
             pMeat.setText("No VEGGIES ADDED");
        }
        else{
            pVeggie.setText(temp);
        }
        
        if(order.getSide1().equals("") && order.getSide2().equals("")){
            sides.setText("");
        }
        
        else if(order.getSide1().equals("") && !order.getSide2().equals("")){
            //orderText += " stuffed on flavoured "+ order.getCrust()+" layered on top with "+ order.getCheeseQty() + " " + order.getCheeseType() + " Cheese" + ", served with a delicious side of " + order.getSide2();
            sides.setText(order.getSide2());
        }
        
        else if(!order.getSide1().equals("") && order.getSide2().equals("")){
             //orderText += " stuffed on flavoured "+ order.getCrust()+" layered on top with "+ order.getCheeseQty() + " " + order.getCheeseType() + " Cheese" + ", served with a delicious side of " + order.getSide1();
             sides.setText(order.getSide1());
        }
        
        else{
            //orderText += " stuffed on flavoured "+ order.getCrust()+" layered on top with "+ order.getCheeseQty() + " " + order.getCheeseType() + " Cheese" + ", served with "+order.getSide1()+" and " + order.getSide2();
            sides.setText(order.getSide1() + " and " + order.getSide2());
        }
        
        
    }
    
    public void setType(boolean value){
        if(value){
            RDP.setDisable(true);
        }
        else{
            COOK.setDisable(true);
        }
    }
    
    public void setProceesing() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Processing/adminProcessingFXML.fxml"));
        AnchorPane newPane = loader.load();//new AnchorPane(parent);
        AdminProcessingFXMLController controller = loader.getController();
        if(COOK.isSelected()){    
            controller.addToPush(order.getOrderID()+"");
            mainBox.setBackground(new Background(new BackgroundFill(Color.DARKGRAY,CornerRadii.EMPTY,Insets.EMPTY)));
        }
        else{
            controller.removeToPush(order.getOrderID()+"");
            mainBox.setBackground(new Background(new BackgroundFill(Color.FLORALWHITE,CornerRadii.EMPTY,Insets.EMPTY)));
        }
    }
    
    public void setCooking() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Chef/ChefFXML.fxml"));
        AnchorPane newPane = loader.load();//new AnchorPane(parent);
        ChefFXMLController controller = loader.getController();
        if(RDP.isSelected()){    
            controller.addToPush(order.getOrderID()+"");
            mainBox.setBackground(new Background(new BackgroundFill(Color.DARKGRAY,CornerRadii.EMPTY,Insets.EMPTY)));
        }
        else{
            controller.removeToPush(order.getOrderID()+"");
            mainBox.setBackground(new Background(new BackgroundFill(Color.FLORALWHITE,CornerRadii.EMPTY,Insets.EMPTY)));
        }
    }
    
    public void rdp(){
        RDP.setSelected(true);
        RDP.setDisable(true);
        COOK.setSelected(true);
        COOK.setDisable(true);
        orderStat.setText("Set Order Status : Picked-Up!");
        
    }
  
}
