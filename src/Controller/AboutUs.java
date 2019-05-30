package Controller;

import Model.Volunteer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jdk.jshell.execution.Util;
import sample.ChangeScene;
import sample.DatabaseConnection;

import java.io.IOException;

public class AboutUs {

    @FXML
    private Label labelAboutUs;
    @FXML
    private Text textAboutUs;

    @FXML
    private Button mainButton;


    Stage dialogStage = new Stage();
    Scene scene;

    @FXML
    private void handleMainButton(ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            dialogStage.close();
            scene = new Scene(FXMLLoader.load(getClass().getResource("../View/DefaultPage.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
