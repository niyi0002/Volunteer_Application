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

public class AdminMenu {

    @FXML
    private Button sendEmail;

    @FXML
    private Button volunteersButton;

    @FXML
    private Button addEventsButton;

    @FXML
    private Button signOutButton;

    @FXML
    private Button viewEventsButton;

    ChangeScene cs = new ChangeScene();

    @FXML
    void handleAddEvents(ActionEvent event) throws IOException {

           cs.sceneHandler("../View/AddEvent.fxml", event);


    }

    @FXML
    void handleViewEvents(ActionEvent event) throws  IOException{
        cs.sceneHandler("../View/ViewAndEditEvents.fxml", event);



    }

    @FXML
    void handleViewVolunteers(ActionEvent event) throws IOException {
        cs.sceneHandler("../View/ListOfVolunteers.fxml", event);

        }




    @FXML
    void handleSignOutButton(ActionEvent event) throws  IOException {
        cs.sceneHandler("../View/DefaultPage.fxml", event);

    }


    @FXML
    void handleSendEmail(ActionEvent event) throws IOException{
        cs.sceneHandler("../View/Email.fxml", event);
    }



}
