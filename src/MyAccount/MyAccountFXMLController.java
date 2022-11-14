/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package MyAccount;

import UtilityFolder.userDetails;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author palneema
 */
public class MyAccountFXMLController implements Initializable {

    @FXML
    private Label uCon;

    @FXML
    private Label uMail;

    @FXML
    private Label uName;

    @FXML
    private Label uPass;

    @FXML
    private Label uRite;
    
    private userDetails user;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         new Thread(() -> {
            Platform.runLater(() -> {
                setInfo();
            });
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                System.err.println(e);
            }
        }).start();
    }
    
    public void setUser(userDetails user){
        this.user = user;
    }
    
    public void setInfo(){
        uRite.setText(user.getUserID()+"");
        uName.setText(user.getFirstName() + " " + user.getLastName());
        uMail.setText(user.geteMail());
        uCon.setText(user.getPhoneNumber()+"");
        uPass.setText("XXXXXXXXXXXXX");
    }

    
    
}
