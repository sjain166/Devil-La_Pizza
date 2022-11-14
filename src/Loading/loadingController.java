/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Loading;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author siddh
 */
public class loadingController implements Initializable {

    @FXML
    private TextField textBox1;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button button1;
    
    @FXML
    private Button manageButton;

    public void load(ActionEvent event) {

    }

    public void changeScene(ActionEvent event) throws IOException {

        if (((Button) event.getSource()).getText().equals("Manage")) {
            Parent mainScene = FXMLLoader.load(getClass().getResource("/adminLogin/adminLogin_FXML.fxml"));
            Scene newScene = new Scene(mainScene);
            Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            newStage.setScene(newScene);
            newStage.show();
        } else {
            Parent mainScene = FXMLLoader.load(getClass().getResource("/Login/Login_FXML.fxml"));
            Scene newScene = new Scene(mainScene);
            Stage newStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            newStage.setScene(newScene);
            newStage.show();

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        button1.setDisable(true);
        progressBar.setStyle("-fx-accent: #E63946");
        new Thread(() -> {
            for (int i = 0; i <= 100; i = i + 10) {
                final int position = i;
                Platform.runLater(() -> {
                    progressBar.setProgress(position / 100.0);
                    System.out.println("Index: " + position);
                });
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            
            manageButton.setDisable(false);
            button1.setDisable(false);
            
        }).start();

//        try {
//            changeScene();
//        } catch (IOException ex) {
//            Logger.getLogger(loadingController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

}
