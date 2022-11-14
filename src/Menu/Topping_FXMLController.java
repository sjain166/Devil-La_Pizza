/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Menu;

import UtilityFolder.OrderClass;
import UtilityFolder.userDetails;
import java.awt.CardLayout;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author siddh
 */
public class Topping_FXMLController implements Initializable {

    @FXML
    private RadioButton asiago;

    @FXML
    private RadioButton basil;

    @FXML
    private VBox byo;

    @FXML
    private RadioButton cheese;

    @FXML
    private ComboBox cheeseQtySelection;

    @FXML
    private Label cheeseSelected;

    @FXML
    private ComboBox cheeseSelection;

    @FXML
    private VBox chi;

    @FXML
    private CheckBox chicken;

    @FXML
    private ToggleGroup cookStyle;

    @FXML
    private ToggleGroup crust;

    @FXML
    private RadioButton garlic;

    @FXML
    private CheckBox ham;

    @FXML
    private RadioButton hand;

    @FXML
    private RadioButton herb;

    @FXML
    private CheckBox jalapenoT;

    @FXML
    private VBox m;

    @FXML
    private CheckBox mushroomT;

    @FXML
    private CheckBox olivesT;

    @FXML
    private RadioButton onionC;

    @FXML
    private CheckBox onionsT;

    @FXML
    private RadioButton pan;

    @FXML
    private VBox pe;

    @FXML
    private CheckBox pep;

    @FXML
    private CheckBox pepperG;

    @FXML
    private CheckBox pepperT;

    @FXML
    private Label pizzaD;

    @FXML
    private Label pizzaD1;

    @FXML
    private Label pizzaD11;

    @FXML
    private Label qty;

    @FXML
    private Slider qtySlider;

    @FXML
    private CheckBox salami;

    @FXML
    private Label size;

    @FXML
    private Slider sizeSlider;

    @FXML
    private CheckBox spinachT;

    @FXML
    private RadioButton tandoor;

    @FXML
    private CheckBox tomatoT;

    @FXML
    private FlowPane toppingContainer;

    @FXML
    private VBox vd;

    @FXML
    private ImageView pizzaDisplay;

    @FXML
    private ImageView vegAndNonveg;

    @FXML
    private Label cheeseQty;

    @FXML
    private Label pizzaD111;

    @FXML
    private VBox gb;

    @FXML
    private VBox cs;

    @FXML
    private VBox soda;

    @FXML
    private VBox cw;

    @FXML
    private VBox ff;

    @FXML
    private ImageView side1;

    @FXML
    private ImageView side2;

    @FXML
    private Label side1Text;

    @FXML
    private Label side2Text;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Label pizzaRate;

    private int sideCount = 0;

    private userDetails user;

    String selectedPizza = "";

    //private List<String> list = new ArrayList<>();
    ObservableList<String> typesOfCheese = FXCollections.observableArrayList("American", "Cheddar", "Monzzarella");
    ObservableList<String> qtyOfCheese = FXCollections.observableArrayList("Less", "Regular", "Extra");
    //String[] items = {"American" , "Cheddar" , "Monzzarella"};

    public void setCheese() {
        String selected = cheeseSelection.getValue().toString();
        cheeseSelected.setText(selected + "");
        cheeseQty.setText(cheeseQtySelection.getValue().toString());
        pizzaD111.setText(cheeseQtySelection.getValue().toString() + " : " + cheeseSelection.getValue().toString());
        checkVegOrNonVeg();
    }

    public void setQtyCheese() {
        String selected = cheeseSelection.getValue().toString();
        cheeseSelected.setText(selected + "");
        cheeseQty.setText(cheeseQtySelection.getValue().toString());
        pizzaD111.setText(cheeseQtySelection.getValue().toString() + " : " + cheeseSelection.getValue().toString());
        checkVegOrNonVeg();
        setRate();
    }

    public void setToppings(ActionEvent event) throws IOException {
        try {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource("/Menu/Toppings.fxml"));
            HBox pane = fXMLLoader.load();
            Menu_FXMLController toppingController = fXMLLoader.getController();
            toppingController.setData("Tomato");
            toppingContainer.getChildren().add(pane);
        } catch (Exception e) {
            System.out.println(e);
        }
        checkVegOrNonVeg();

    }

    public void setSize() {

        double value = sizeSlider.getValue();
        if (value == 0.0) {
            size.setText("Size: Small");
        } else if (value == 1.5) {
            size.setText("Size: Medium");
        } else if (value == 3.0) {
            size.setText("Size: Large");
        } else {
            size.setText("*Pizza Size*");
        }
        setRate();
    }

    public void setQuantity() {
        System.out.println(qtySlider.getValue());
        qty.setText("QTY: " + (int) qtySlider.getValue());
        setRate();
    }

    public void remove(ActionEvent event) {
//        ObservableList<Node> paneList = toppingContainer.getChildren();
//        System.out.println(paneList.size());
//        
//        for(int i=0 ; i < paneList.size() ; i++){
//            //System.out.println("run");
//            toppingContainer.getChildren().remove(paneList.get(i));
//        }  
    }

    @FXML
    private void handleOnMouseClicked() {
        System.out.println("Set Pizza");

        vd.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                setDefault();
                File file = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\Veggie Pizza.png");
                Image image = new Image(file.toURI().toString());
                pizzaDisplay.setImage(image);
                pizzaD.setText("Veggie Delight");
                garlic.setSelected(true);
                pan.setSelected(true);

                cheeseSelection.setValue("Cheddar");
                cheeseQtySelection.setValue("Regular");

                onionsT.setSelected(true);
                onionsT.setDisable(true);

                jalapenoT.setSelected(true);
                jalapenoT.setDisable(true);

                tomatoT.setSelected(true);
                tomatoT.setDisable(true);

                olivesT.setSelected(true);
                olivesT.setDisable(true);

                //pizzaRate.setText("12.00");
                selectedPizza = "vd";

                setCrust();
                setCookStyle();
                setCheese();
                try {
                    getSelectedToppings();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        });

        m.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                setDefault();
                File file = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\Margerita pizza.png");
                Image image = new Image(file.toURI().toString());
                pizzaDisplay.setImage(image);
                pizzaD.setText("Margherita");

                cheese.setSelected(true);
                tandoor.setSelected(true);
                cheeseSelection.setValue("Monzzarella");
                cheeseQtySelection.setValue("Extra");
                tomatoT.setSelected(true);
                tomatoT.setDisable(true);
                //pizzaRate.setText("10.00");
                selectedPizza = "m";

                setCrust();
                setCookStyle();
                setCheese();
                try {
                    getSelectedToppings();
                } catch (IOException e) {
                    System.out.println(e);
                }

            }
        });

        byo.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                setDefault();
                File file = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\Cheese pizza.png");
                Image image = new Image(file.toURI().toString());
                pizzaDisplay.setImage(image);
                pizzaD.setText("Custom Pizza");
                //pizzaRate.setText("8.00");
                selectedPizza = "byo";

            }
        });

        chi.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                setDefault();
                File file = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\chicken pizza.png");
                Image image = new Image(file.toURI().toString());
                pizzaDisplay.setImage(image);
                pizzaD.setText("Chicken Pizza");

                herb.setSelected(true);
                pan.setSelected(true);
                chicken.setSelected(true);
                chicken.setDisable(true);

                cheeseSelection.setValue("American");
                cheeseQtySelection.setValue("Regular");

                jalapenoT.setSelected(true);
                jalapenoT.setDisable(true);
                mushroomT.setSelected(true);
                mushroomT.setDisable(true);
                jalapenoT.setDisable(true);
                //pizzaRate.setText("15.00");
                selectedPizza = "chi";

                setCrust();
                setCookStyle();
                setCheese();
                try {
                    getSelectedToppings();
                } catch (IOException e) {
                    System.out.println(e);
                }

            }
        });

        pe.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                setDefault();
                File file = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\Pepperoni pizza.png");
                Image image = new Image(file.toURI().toString());
                pizzaDisplay.setImage(image);
                pizzaD.setText("Pepperoni Blast");

                basil.setSelected(true);
                tandoor.setSelected(true);
                pep.setSelected(true);
                pep.setDisable(true);

                cheeseSelection.setValue("Cheddar");
                cheeseQtySelection.setValue("Regular");

                tomatoT.setSelected(true);
                tomatoT.setDisable(true);
                olivesT.setSelected(true);
                olivesT.setDisable(true);
                //pizzaRate.setText("15.00");
                selectedPizza = "pe";

                setCrust();
                setCookStyle();
                setCheese();
                try {
                    getSelectedToppings();
                } catch (IOException e) {
                    System.out.println(e);
                }

            }
        });
        checkVegOrNonVeg();
        setRate();

    }

    public void setDefault() {
        System.out.println("I am in set Default");
        chicken.setDisable(false);
        chicken.setSelected(false);

        ham.setDisable(false);
        ham.setSelected(false);

        salami.setDisable(false);
        salami.setSelected(false);

        pep.setDisable(false);
        pep.setSelected(false);

        onionsT.setSelected(false);
        onionsT.setDisable(false);

        jalapenoT.setSelected(false);
        jalapenoT.setDisable(false);

        olivesT.setSelected(false);
        olivesT.setDisable(false);

        mushroomT.setSelected(false);
        mushroomT.setDisable(false);

        spinachT.setSelected(false);
        spinachT.setDisable(false);

        tomatoT.setSelected(false);
        tomatoT.setDisable(false);

        pepperG.setSelected(false);
        pepperG.setDisable(false);

        pepperT.setSelected(false);
        pepperT.setDisable(false);

        checkVegOrNonVeg();
        setCrust();
        setCookStyle();
        setCheese();
        try {
            getSelectedToppings();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void setSides() {

        

        gb.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                File file = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\breadsticks.png");
                Image image = new Image(file.toURI().toString());

                if (side1.getImage() == null) {
                    side1.setImage(image);
                    side1Text.setText("Garlic BreadSticks");
                } else if(side2.getImage() == null) {
                    side2.setImage(image);
                    side2Text.setText("Garlic BreadSticks");
                }
                else{
                    side1.setImage(image);
                    side1Text.setText("Garlic BreadSticks");
                }
                setRate();

            }
        });

        cs.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                File file = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\cheesesticks.png");
                Image image = new Image(file.toURI().toString());

                if (side1.getImage() == null) {
                    side1.setImage(image);
                    side1Text.setText("Cheese Sticks");
                } else if(side2.getImage() == null) {
                    side2.setImage(image);
                    side2Text.setText("Cheese Sticks");
                }
                else{
                   side1.setImage(image);
                   side1Text.setText("Cheese Sticks"); 
                }
                setRate();

            }
        });

        soda.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                File file = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\coke.png");
                Image image = new Image(file.toURI().toString());

                if (side1.getImage() == null) {
                    side1.setImage(image);
                    side1Text.setText("Soda");
                } else if(side2.getImage() == null) {
                    side2.setImage(image);
                    side2Text.setText("Soda");
                }
                else{
                     side1.setImage(image);
                    side1Text.setText("Soda");
                }
                setRate();
            }
        });

        cw.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                File file = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\wings.png");
                Image image = new Image(file.toURI().toString());

                if (side1.getImage() == null) {
                    side1.setImage(image);
                    side1Text.setText("Chicken Wings");
                } else if(side2.getImage() == null) {
                    side2.setImage(image);
                    side2Text.setText("Chicken Wings");
                }
                else {
                    side1.setImage(image);
                    side1Text.setText("Chicken Wings");
                }
                setRate();

            }
        });

        ff.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                File file = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\fries.png");
                Image image = new Image(file.toURI().toString());

                if (side1.getImage() == null) {
                    side1.setImage(image);
                    side1Text.setText("Fries");
                } else if(side2.getImage() == null) {
                    side2.setImage(image);
                    side2Text.setText("Fries");
                }
                else{
                    side1.setImage(image);
                    side1Text.setText("Fries");
                }
                
                setRate();
            }
        });

        side1.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                side1.setImage(null);
                side1Text.setText("*Side 1*");
                setRate();
            }
        });

        side2.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                
                side2.setImage(null);
                side2Text.setText("*Side 2*");
                setRate();
            }
        });

    }

    public void addToCart(ActionEvent event) throws IOException {
        if (pizzaDisplay.getImage() == null) {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Please Select a Pizza to Continue :)");
            //errorAlert.setContentText("You Must Select Atleast 1 Pizza to Add to Cart");
            errorAlert.showAndWait();
            return;
        }

        List<String> list = new ArrayList<>();
        if (chicken.isSelected()) {
            list.add("Chicken");
        }

        if (pep.isSelected()) {
            list.add("Pepperoni");
        }

        if (ham.isSelected()) {
            list.add("Ham");
        }

        if (salami.isSelected()) {
            list.add("Salami");
        }

        List<String> veggies = new ArrayList<>();
        if (onionsT.isSelected()) {
            veggies.add("Onions");
        }

        if (jalapenoT.isSelected()) {
            veggies.add("Jalapeno");
        }

        if (olivesT.isSelected()) {
            veggies.add("Olives");
        }

        if (mushroomT.isSelected()) {
            veggies.add("Mushrooms");
        }

        if (spinachT.isSelected()) {
            veggies.add("Spinach");
        }

        if (tomatoT.isSelected()) {
            veggies.add("Tomato");
        }

        if (pepperT.isSelected()) {
            veggies.add("Pepper");
        }

        if (pepperG.isSelected()) {
            veggies.add("Pineapple");
        }

        String s1, s2;

        if (side1.getImage() == null) {
            s1 = "";
        } else {
            s1 = side1Text.getText();
        }

        if (side2.getImage() == null) {
            s2 = "";
        } else {
            s2 = side2Text.getText();
        }

        String pizzaSize = size.getText().substring(6, size.getText().length());
        int pizzaQty = Integer.parseInt(qty.getText().substring(5, qty.getText().length()));
        System.out.println(pizzaSize + " --" + pizzaQty);
        
        int aVeggies = 0, aMeat = 0;
        double basePrice = 0.00;
        
        switch (selectedPizza) {
            case "vd":
                aVeggies = 4;
                basePrice = 12.00;
                break;
            case "m":
                aVeggies = 1;
                basePrice = 10.00;
                break;
            case "byo":
                basePrice = 8.00;
                break;
            case "chi":
                aVeggies = 2;
                aMeat = 1;
                basePrice = 15.00;
                break;
            case "pe":
                aVeggies = 2;
                aMeat = 1;
                basePrice = 15.00;
                break;
        }
        
        String pSize = size.getText().substring(6, size.getText().length());
        if (pSize.equals("Medium")) {
            basePrice += 2.00;
        }

        if (pSize.equals("Large")) {
            basePrice += 4.00;
        }

        int pQty = Integer.parseInt(qty.getText().substring(5, qty.getText().length()));

        basePrice = pQty * basePrice;
        
        
        
        OrderClass currOrder = new OrderClass(user.getFirstName(), pizzaSize, pizzaQty, pizzaD.getText(), veggies, pizzaD1.getText(), pizzaD11.getText(), list, cheeseSelected.getText(), cheeseQty.getText(), s1, s2 , aVeggies , aMeat, basePrice ,sideCost());
        currOrder.printOrder();
        user.addOrder(currOrder);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Cart/Cart_FXML.fxml"));
        AnchorPane newPane = loader.load();
        Cart.Cart_FXMLController controller = loader.getController();
        controller.setUser(user);
        mainPane.getChildren().setAll(newPane);

    }

    public void setCrust() {
        if (garlic.isSelected()) {
            pizzaD1.setText("Garlic Crust");
        } else if (cheese.isSelected()) {
            pizzaD1.setText("Cheese Crust");
        } else if (herb.isSelected()) {
            pizzaD1.setText("Herb and Cheese Crust");
        } else if (basil.isSelected()) {
            pizzaD1.setText("Basil Blend Crust");
        } else if (asiago.isSelected()) {
            pizzaD1.setText("Asiago Cheese Crust");
        } else if (onionC.isSelected()) {
            pizzaD1.setText("Onion Crust");
        } else {
            pizzaD1.setText("*Crust*");
        }
        checkVegOrNonVeg();

    }

    public void setCookStyle() {
        if (pan.isSelected()) {
            pizzaD11.setText("Fresh Pan Pizza");
        } else if (tandoor.isSelected()) {
            pizzaD11.setText("Tandoor Pizza");
        } else if (hand.isSelected()) {
            pizzaD11.setText("Hand Tossed");
        } else {
            pizzaD11.setText("*Cooking Style*");
        }
        checkVegOrNonVeg();
    }

    public void getSelectedToppings() throws IOException {

        toppingContainer.getChildren().clear();
        List<String> list = new ArrayList<>();
        if (chicken.isSelected()) {
            list.add("Chicken");
        }

        if (pep.isSelected()) {
            list.add("Pepperoni");
        }

        if (ham.isSelected()) {
            list.add("Ham");
        }

        if (salami.isSelected()) {
            list.add("Salami");
        }

        if (onionsT.isSelected()) {
            list.add("Onions");
            System.out.println("Onions");
        }

        if (jalapenoT.isSelected()) {
            list.add("Jalapeno");
            System.out.println("Jalapeno");
        }

        if (olivesT.isSelected()) {
            list.add("Olives");
            System.out.println("Olives");
        }

        if (mushroomT.isSelected()) {
            list.add("Mushrooms");
            System.out.println("Mushrooms");
        }

        if (spinachT.isSelected()) {
            list.add("Spinach");
            System.out.println("Spinach");
        }

        if (tomatoT.isSelected()) {
            list.add("Tomato");
            System.out.println("Tomato");
        }

        if (pepperT.isSelected()) {
            list.add("Pepper");
            System.out.println("Red Pepper");
        }

        if (pepperG.isSelected()) {
            list.add("Pineapple");
            System.out.println("Green Pepper");
        }

        System.out.println("\n");

        for (int i = 0; i < list.size(); i++) {
            FXMLLoader fXMLLoader = new FXMLLoader();
            fXMLLoader.setLocation(getClass().getResource("/Menu/Toppings.fxml"));
            HBox pane = fXMLLoader.load();
            Menu_FXMLController toppingController = fXMLLoader.getController();
            toppingController.setData(list.get(i));
            toppingContainer.getChildren().add(pane);
        }

        setRate();
        checkVegOrNonVeg();
    }

    public void checkVegOrNonVeg() {
        if (chicken.isSelected() || ham.isSelected() || salami.isSelected() || pep.isSelected()) {
            File file = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\nonveg.png");
            Image image = new Image(file.toURI().toString());
            vegAndNonveg.setImage(image);
        } else {
            File file = new File("C:\\Users\\siddh\\Documents\\NetBeansProjects\\LaPizza\\src\\Resources\\veg.jpg");
            Image image = new Image(file.toURI().toString());
            vegAndNonveg.setImage(image);
        }
        setRate();
    }

    public void setRate() {
        double basicRate = 0.00; //Double.parseDouble(pizzaRate.getText());

        switch (selectedPizza) {
            case "vd":
                basicRate = 12.00;
                break;
            case "m":
                basicRate = 10.00;
                break;
            case "byo":
                basicRate = 8.00;
                break;
            case "chi":
                basicRate = 15.00;
                break;
            case "pe":
                basicRate = 15.00;
                break;
        }

        int extraVeggies = totalVeggies();
        int extraMeat = totalMeat();

        if (extraVeggies != 0) {
            basicRate += (extraVeggies * 1.00);
        }
        if (extraMeat != 0) {
            basicRate += (extraMeat * 2.00);
        }

        if (cheeseQty.getText().equals("Extra") && !selectedPizza.equals("m")) {
            basicRate += 2.00;
        }

        String pSize = size.getText().substring(6, size.getText().length());
        if (pSize.equals("Medium")) {
            basicRate += 2.00;
        }

        if (pSize.equals("Large")) {
            basicRate += 4.00;
        }

        int pQty = Integer.parseInt(qty.getText().substring(5, qty.getText().length()));

        basicRate = pQty * basicRate;
        System.out.println(sideCost());
        basicRate += sideCost();
        pizzaRate.setText("" + basicRate);

    }

    public int totalVeggies() {
        int counter = 0;

        if (onionsT.isSelected() && !onionsT.isDisable()) {
            counter++;
        }

        if (jalapenoT.isSelected() && !jalapenoT.isDisable()) {
            counter++;
        }

        if (olivesT.isSelected() && !olivesT.isDisable()) {
            counter++;
        }

        if (mushroomT.isSelected() && !mushroomT.isDisable()) {
            counter++;
        }

        if (spinachT.isSelected() && !spinachT.isDisable()) {
            counter++;
        }

        if (tomatoT.isSelected() && !tomatoT.isDisable()) {
            counter++;
        }

        if (pepperT.isSelected() && !pepperT.isDisable()) {
            counter++;
        }

        if (pepperG.isSelected() && !pepperG.isDisable()) {
            counter++;
        }

        return counter;
    }

    public int totalMeat() {
        int counter = 0;
        if (chicken.isSelected() && !chicken.isDisable()) {
            counter++;

        }

        if (pep.isSelected() && !pep.isDisable()) {
            counter++;
        }

        if (ham.isSelected() && !ham.isDisable()) {
            counter++;
        }

        if (salami.isSelected() && !salami.isDisable()) {
            counter++;
        }

        return counter;

    }

    public double sideCost() {
        String s1 = side1Text.getText();
        String s2 = side2Text.getText();

        double totalCost = 0.0;
        switch (s1) {
            case "Garlic BreadSticks":
                totalCost += 8.00;
                break;
            case "Cheese Sticks":
                totalCost += 6.00;
                break;
            case "Soda":
                totalCost += 2.00;
                break;
            case "Chicken Wings":
                totalCost += 10.00;
                break;
            case "Fries":
                totalCost += 4.00;
                break;
        }

        switch (s2) {
            case "Garlic BreadSticks":
                totalCost += 8.00;
                break;
            case "Cheese Sticks":
                totalCost += 6.00;
                break;
            case "Soda":
                totalCost += 2.00;
                break;
            case "Chicken Wings":
                totalCost += 10.00;
                break;
            case "Fries":
                totalCost += 4.00;
                break;
        }

        return totalCost;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cheeseSelection.setValue("Monzzarella");
        cheeseSelection.setItems(typesOfCheese);

        cheeseQtySelection.setValue("Regular");
        cheeseQtySelection.setItems(qtyOfCheese);

        toppingContainer.setVgap(2);
        toppingContainer.setHgap(2);

        //System.out.println(user.getFirstName());
        setSides();
        handleOnMouseClicked();
        checkVegOrNonVeg();
    }

    public void setUser(userDetails user) {
        this.user = user;
    }

}
