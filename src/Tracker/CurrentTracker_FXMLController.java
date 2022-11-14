/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Tracker;

import UtilityFolder.DBFuntions;
import UtilityFolder.OrderClass;
import UtilityFolder.userDetails;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author siddh
 */
public class CurrentTracker_FXMLController implements Initializable {
        @FXML
    private CheckBox PKD;

    @FXML
    private HBox mainBox;

    @FXML
    private Label orderID;

    @FXML
    private Label orderID1;

    @FXML
    private Label orderID2;

    @FXML
    private Label orderID3;

    @FXML
    private ImageView orderStatGIF;

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
    private Label side1;

    @FXML
    private Label side2;
    
    @FXML
    private ImageView image;
    
    
    private userDetails user;
    
    private OrderClass order;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }


    public void setOrder(OrderClass order){
        this.order = order;
        orderID1.setText(order.getOrderID()+"");
        //orderDesc.setText(setOrderDes(order));
        String pizzaName =order.getPizzaName();
        
        
        File file1;
        Image img1;
        
        switch(pizzaName){
            case "Veggie Delight":
                 file1 = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\Veggie Pizza.png");
                 img1 = new Image(file1.toURI().toString());
                 image.setImage(img1);
                 break;
            case "Margharita":
                 file1 = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\Margerita pizza.png");
                 img1 = new Image(file1.toURI().toString());
                 image.setImage(img1);
                 break;
            case "Custom Pizza":
                 file1 = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\Cheese pizza.png");
                 img1 = new Image(file1.toURI().toString());
                 image.setImage(img1);
                 break;
            case "Chicken Pizza":
                 file1 = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\chicken pizza.png");
                 img1 = new Image(file1.toURI().toString());
                 image.setImage(img1);
                 break;
                 
            case "Pepperoni Blast":
                 file1 = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\Pepperoni pizza.png");
                 img1 = new Image(file1.toURI().toString());
                 image.setImage(img1);
                 break; 
                
        }
        pName.setText(pizzaName);
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
             pMeat.setText("NO MEAT ADDED");
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
        
        
        
        String oStat = order.getOrderStat();
        File file;
        Image image; 
        switch(oStat){
            case "PLC":
                 file = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\PLC.gif");
                 image = new Image(file.toURI().toString());
                 orderStatGIF.setImage(image);
                 break;
            case "RDP":
                 file = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\PCK.gif");
                 image = new Image(file.toURI().toString());
                 orderStatGIF.setImage(image);
                 PKD.setDisable(false);
                 break;
            case "COOK":
                 file = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\COOK.gif");
                 image = new Image(file.toURI().toString());
                 orderStatGIF.setImage(image);
                 break;
            case "PKD":
                 file = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\PKD.gif");
                 image = new Image(file.toURI().toString());
                 orderStatGIF.setImage(image);
                 break;  
        }
        
    }
    
    public void setUser(userDetails user){
        this.user = user; 
    }
    
    public String setOrderDes(OrderClass order){
        String orderText = "Hello "+ user.getFirstName()+"."+"Your "+order.getPizzaQty()+" portion of "+ order.getPizzaSize()+" "+ order.getPizzaName() + " cooked as "+order.getCookStyle()+ " with ";
        
        if(order.getMeat().size() == 0 && order.getVeggies().size() == 0){
            orderText += "No Toppings of Veggies and meat,";
        }
        else{
            for(String str : order.getMeat()){
                orderText += str+",";
            }
            for(String str : order.getVeggies()){
                orderText += str+",";
            }
            orderText += " and many more exotic and authentic hidden flavours,";
        }
        
        if(order.getSide1().equals("") && order.getSide2().equals("")){
            orderText += " stuffed on flavoured "+ order.getCrust()+" layered on top with "+ order.getCheeseQty() + " " + order.getCheeseType() + " Cheese.";
        }
        
        else if(order.getSide1().equals("") && !order.getSide2().equals("")){
            orderText += " stuffed on flavoured "+ order.getCrust()+" layered on top with "+ order.getCheeseQty() + " " + order.getCheeseType() + " Cheese" + ", served with a delicious side of " + order.getSide2();
        }
        
        else if(!order.getSide1().equals("") && order.getSide2().equals("")){
             orderText += " stuffed on flavoured "+ order.getCrust()+" layered on top with "+ order.getCheeseQty() + " " + order.getCheeseType() + " Cheese" + ", served with a delicious side of " + order.getSide1();
        }
        
        else{
            orderText += " stuffed on flavoured "+ order.getCrust()+" layered on top with "+ order.getCheeseQty() + " " + order.getCheeseType() + " Cheese" + ", served with "+order.getSide1()+" and " + order.getSide2();
        }
        
        return orderText;
            
    }
    
    public void orderPickedUp() throws ClassNotFoundException{
        DBFuntions db = new DBFuntions();
        db.setToPickedUp(order.getOrderID());
        mainBox.setDisable(true);
    }
    
}
