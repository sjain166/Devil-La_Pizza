/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Cart;

import UtilityFolder.OrderClass;
import UtilityFolder.userDetails;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

/**
 * FXML Controller class
 *
 * @author siddh
 */






public class Bill_FXMLController implements Initializable {

    
    
    @FXML
    private Label bPrice;

    @FXML
    private Label gTotal;

    @FXML
    private Label mPrice;

    @FXML
    private Label oNum;

    @FXML
    private Label orderNumber;

    @FXML
    private Label pCookStyle;

    @FXML
    private Label pCrust;

    @FXML
    private TextArea pMeat;

    @FXML
    private Label pName;

    @FXML
    private Label pQty;

    @FXML
    private Label pSize;

    @FXML
    private TextArea pVeggie;

    @FXML
    private Label sPrice;

    @FXML
    private Label side1;

    @FXML
    private Label side2;

    @FXML
    private Label tPrice;

    @FXML
    private Label uName;

    @FXML
    private Label vPrice;
    
    private userDetails user;
    
    private static final DecimalFormat df = new DecimalFormat("0.00");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void setBill(OrderClass order , int orderNumber){
        this.orderNumber.setText(orderNumber+"");
        uName.setText(order.getUserName());
        pName.setText(order.getPizzaName());
        pSize.setText(order.getPizzaSize());
        pQty.setText("x"+order.getPizzaQty());
        pCrust.setText(order.getCrust());
        pCookStyle.setText(order.getCookStyle());
        
        pMeat.setText("");
        List<String> meat = order.getMeat();
        for(int i = 0; i < meat.size(); i++){
            if(i != meat.size()-1){
                pMeat.appendText(meat.get(i)+", ");
            }
            else{
                pMeat.appendText(meat.get(i));
            }
        }
        
        if(meat.size() == 0){
             pMeat.setText("No MEAT ADDED");
        }
        
        pVeggie.setText("");
        List<String> veggies = order.getVeggies();
        for(int i = 0; i < veggies.size(); i++){
            if(i != veggies.size()-1){
                pVeggie.appendText(veggies.get(i)+", ");
            }
            else{
                pVeggie.appendText(veggies.get(i));
            }
        }
        if(veggies.size() == 0){
             pMeat.setText("No VEGGIES ADDED");
        }
        
        
        side1.setText(order.getSide1());
        side2.setText(order.getSide2());
        
        double bPrice = order.getBasePrice();
        double vPrice = (order.getVeggies().size() - order.getAllowedVeggies());
        double mPrice = (order.getMeat().size() - order.getAllowedmeat()) * 2;
        double sPrice = order.getSidePrice();
        
        this.bPrice.setText(df.format(bPrice)+"");
        this.vPrice.setText(df.format(vPrice)+"");
        this.mPrice.setText(df.format(mPrice)+"");
        this.sPrice.setText(df.format(sPrice)+"");
        
        double totalTax = ( (bPrice +  vPrice + mPrice + sPrice) * 5.6 ) / 100;
        this.tPrice.setText(df.format(totalTax)+"");
        double gTotal = ( (bPrice +  vPrice + mPrice + sPrice) * 105.6 ) / 100;
        
        this.gTotal.setText(df.format(gTotal)+"");
        
        order.setGrandTotal(gTotal);
        
    }

    public void setUser(userDetails user){
        this.user = user;
    }
   
    
}
