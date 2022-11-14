/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Login;

import Cart.Cart_FXMLController;
import Menu.Topping_FXMLController;
import MyAccount.MyAccountFXMLController;
import UtilityFolder.DBFuntions;
import UtilityFolder.userDetails;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author siddh
 */
public class Login_FXMLController implements Initializable {

    @FXML
    private TextField OTPInput;

    @FXML
    private Button account;

    @FXML
    private TextField asuid;

    @FXML
    private BorderPane borderPane;

    @FXML
    private Button cPassButton;

    @FXML
    private Button cart;

    @FXML
    private AnchorPane changePane;

    @FXML
    private TextField emailInput;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private Button menuButton;

    @FXML
    private TextField passWord;

    @FXML
    private TextField passWord2;

    @FXML
    private PasswordField password;

    @FXML
    private Button rOTPButton;

    @FXML
    private Button trackerButton;

    @FXML
    private Label userName;

    @FXML
    private VBox resetPassBox;

    private int userID;
    private String pass;
    private DBFuntions db;
    private userDetails user = null;
    private int OTP;

    public void goTo(ActionEvent event) throws IOException {
        String buttonClicked = ((Button) event.getSource()).getText();

        switch (buttonClicked) {
            case "Logout":
                user = null;
                Parent mainScene = FXMLLoader.load(getClass().getResource("/Loading/Loading_FXML.fxml"));
                Scene newScene = new Scene(mainScene);
                Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                newStage.setScene(newScene);
                newStage.show();
            case "Menu":
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu/Menu_FXML.fxml"));
                AnchorPane newPane = loader.load();//new AnchorPane(parent);
                Topping_FXMLController controller2 = loader.getController();
                controller2.setUser(user);
                borderPane.setCenter(newPane);
                break;

            case "Tracker":
                loader = new FXMLLoader(getClass().getResource("/Tracker/Tracker_FXML.fxml"));
                newPane = loader.load();//new AnchorPane(parent);
                Tracker.Tracker_FXMLController controller5 = loader.getController();
                controller5.setUser(user);
                borderPane.setCenter(newPane);
                break;
            case "Account":
                loader = new FXMLLoader(getClass().getResource("/MyAccount/MyAccountFXML.fxml"));
                newPane = loader.load();//new AnchorPane(parent);
                MyAccountFXMLController controller3 = loader.getController();
                controller3.setUser(user);
                borderPane.setCenter(newPane);
                break;
            case "Cart":
                loader = new FXMLLoader(getClass().getResource("/Cart/Cart_FXML.fxml"));
                newPane = loader.load();//new AnchorPane(parent);
                Cart_FXMLController controller4 = loader.getController();
                controller4.setUser(user);
                borderPane.setCenter(newPane);
                break;
        }

    }

    public void login(ActionEvent event) throws IOException, ClassNotFoundException {

        //BFuntions db = new DBFuntions();
        try {
            userID = Integer.parseInt(asuid.getText());
        } catch (Exception e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Wrong USER-ID or PASSWORD");
            errorAlert.setContentText("Please Try Again or Click Forgot Password :)");
            errorAlert.showAndWait();
            asuid.setText("");
            password.setText("");
            return;
        }
        pass = password.getText();

        // System.out.println(userID + "\n" + pass);
        // System.out.println(db.checkUser(userID, pass));
//        System.out.println(pass.length());
        if (db.checkUser(userID, pass)) {
            user = db.createUser(userID);
            userName.setText(user.getFirstName());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Menu/Menu_FXML.fxml"));
            AnchorPane newPane = loader.load();//new AnchorPane(parent);
            Topping_FXMLController controller = loader.getController();
            controller.setUser(user);
            trackerButton.setDisable(false);
            menuButton.setDisable(false);
            account.setDisable(false);
            cart.setDisable(false);
            borderPane.setCenter(newPane);
        } else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Wrong USER-ID or PASSWORD");
            errorAlert.setContentText("Please Try Again or Click Forgot Password :)");
            errorAlert.showAndWait();
            asuid.setText("");
            password.setText("");
            return;
        }

        //((Stage)button.getScene().getWindow()).setScene(new Scene(parent, 1370, 800));
    }

    public void initialize(URL url, ResourceBundle rb) {
        try {
            db = new DBFuntions();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void requestOTP() throws ClassNotFoundException {
        Random rd = new Random();
        OTP = rd.nextInt(10000 + 1);

        if (db.sendOTP(OTP, emailInput.getText().trim())) {
            cPassButton.setDisable(false);
            emailInput.setEditable(false);
        }
    }

    public void checkOTP() {
        if (OTP == Integer.parseInt(OTPInput.getText())) {
            if (passWord.getText().equals(passWord2.getText())) {
                if (db.changePassword(passWord.getText().trim(), emailInput.getText().trim())) {
                    Alert errorAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    errorAlert.setHeaderText("Your Password has been Changed!!!");
                    errorAlert.showAndWait();
                    cPassButton.setDisable(true);
                    emailInput.setEditable(true);
                    OTPInput.setText("");
                    passWord.setText("");
                    passWord2.setText("");
                    resetPassBox.setVisible(false);
                } else {
                    OTPInput.setText("");
                    passWord.setText("");
                    passWord2.setText("");
                }
            } else {
                OTPInput.setText("");
                passWord.setText("");
                passWord2.setText("");
            }
        } else {
            OTPInput.setText("");
            passWord.setText("");
            passWord2.setText("");
        }
    }
    
    public void resetPassword(){
        resetPassBox.setVisible(true);
    }
    


}
