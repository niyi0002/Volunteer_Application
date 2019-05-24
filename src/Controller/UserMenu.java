package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.ChangeScene;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserMenu implements Initializable {
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

    @FXML
    private ImageView menu;

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
        cs.sceneHandler("../View/Schedules.fxml",event);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            Image image = new Image(new FileInputStream("src/icons/vol-hand.png"));
            menu.setImage(image);
            menu.setFitWidth(250);
            menu.setFitHeight(250);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
