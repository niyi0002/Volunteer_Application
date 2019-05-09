package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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

    Stage dialogStage = new Stage();
    Scene scene;


    @FXML
    private void signIn(ActionEvent event){
        Node node = (Node) event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("../View/AdminOrVolunteer.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialogStage.setScene(scene);
        dialogStage.show();

    }

    @FXML
    private void signUp(ActionEvent event){

        Node node = (Node) event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("../View/SignUp.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialogStage.setScene(scene);
        dialogStage.show();

    }

    @FXML
    private void aboutUs(ActionEvent event){

        Node node = (Node) event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        try {
            scene = new Scene(FXMLLoader.load(getClass().getResource("../View/AboutUs.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialogStage.setScene(scene);
        dialogStage.show();

    }
}
