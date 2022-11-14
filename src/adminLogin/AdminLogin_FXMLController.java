/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package adminLogin;

import Cart.Cart_FXMLController;
import Menu.Topping_FXMLController;
import MyAccount.MyAccountFXMLController;
import Processing.AdminProcessingFXMLController;
import UtilityFolder.DBFuntions;
import UtilityFolder.userDetails;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author siddh
 */
public class AdminLogin_FXMLController implements Initializable {

    @FXML
    private TextField asuid;

    @FXML
    private Button chefButton;

    @FXML
    private Button loginButton;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private PasswordField password;

    @FXML
    private Button processingButton;

    @FXML
    private Label userName;
    
    @FXML
    private BorderPane borderPane;

    private DBFuntions db;
    private userDetails admin = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            db = new DBFuntions();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void login(ActionEvent event) throws IOException, ClassNotFoundException {
        int userID = 0 ; 
        try{
            userID = Integer.parseInt(asuid.getText());
        }
        catch(Exception e){

            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Wrong USER-ID or PASSWORD");
            errorAlert.setContentText("Please Try Again or Click Forgot Password :)");
            errorAlert.showAndWait();
            asuid.setText("");
            password.setText("");
            return;
        }
        String pass = password.getText();
        
        if(db.checkAdmin(userID, pass)){
            admin = db.createUser(userID);
            userName.setText(admin.getFirstName());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Processing/adminProcessingFXML.fxml"));
            AnchorPane newPane = loader.load();//new AnchorPane(parent);
            AdminProcessingFXMLController controller = loader.getController();
            chefButton.setDisable(false);
            processingButton.setDisable(false);
            borderPane.setCenter(newPane);
        }
        
        else{
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Wrong USER-ID or PASSWORD");
            errorAlert.setContentText("Please Try Again or Click Forgot Password :)");
            errorAlert.showAndWait();
            asuid.setText("");
            password.setText("");
            return;
        }
    }
    
    
    public void goTo(ActionEvent event) throws IOException{
        String buttonClicked = ((Button)event.getSource()).getText();
        System.out.println(buttonClicked);
        
        switch(buttonClicked){
            case "Logout":
                Parent mainScene = FXMLLoader.load(getClass().getResource("/Loading/Loading_FXML.fxml"));
                Scene newScene = new Scene(mainScene);
                Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                newStage.setScene(newScene);
                newStage.show();
            case "Processing Page":
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Processing/adminProcessingFXML.fxml"));
                AnchorPane newPane = loader.load();//new AnchorPane(parent);
                AdminProcessingFXMLController controller2 = loader.getController();
                borderPane.setCenter(newPane);
                break;
                
            case "Chef's Page":
                loader = new FXMLLoader(getClass().getResource("/Chef/ChefFXML.fxml"));
                newPane = loader.load();//new AnchorPane(parent);
                Chef.ChefFXMLController controller3 = loader.getController();
                borderPane.setCenter(newPane);
                break;
 
        }
    }

}
