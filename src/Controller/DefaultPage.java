package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.ChangeScene;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DefaultPage {

    @FXML
    private Button signinButton;

    @FXML
    private Button SignupButton;

    @FXML
    private Button infoButton;

    ChangeScene cs= new ChangeScene();


    @FXML
    private void signIn(ActionEvent event){

        try {
            cs.sceneHandler("../View/AdminOrVolunteer.fxml",event);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @FXML
    private void signUp(ActionEvent event){
        try {
            cs.sceneHandler("../View/SignUp.fxml", event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void aboutUs(ActionEvent event){

        try {
            cs.sceneHandler("../View/AboutUs.fxml",event);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
