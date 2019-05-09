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

public class UserMenu {
    @FXML
    private Button userProfile;

    @FXML
    private Button events;

    @FXML
    private Button schedule;

    @FXML
    private Button donate;

    @FXML
    private Button signOut;

    ChangeScene cs = new ChangeScene();

    @FXML
    void handleDonate(ActionEvent event) throws IOException {
      cs.sceneHandler("../View/Donate.fxml",event);
    }

    @FXML
    void handleEvents(ActionEvent event) throws IOException{
        cs.sceneHandler("../View/Events.fxml",event);
    }

    @FXML
    void handleSchedule(ActionEvent event) throws IOException {
        cs.sceneHandler("../View/Schedule.fxml",event);
    }

    @FXML
    void handleUserProfile(ActionEvent event) throws IOException {
        cs.sceneHandler("../View/UserProfile.fxml",event);
    }
    @FXML
    void handleViewHistory(ActionEvent event) throws IOException {
        cs.sceneHandler("../View/ViewHistory.fxml",event);
    }

    @FXML
    void handleSignOut(ActionEvent event) throws IOException {
        cs.sceneHandler("../View/DefaultPage.fxml",event);
    }
}
