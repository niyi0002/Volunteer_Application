package Controller;

import javafx.fxml.Initializable;
import Model.Event;
import Model.User_Has_Events;
import Model.Volunteer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import sample.ChangeScene;
import sample.DatabaseConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class Schedules implements Initializable{


    @FXML
    private TableView<User_Has_Events> tableView;
    @FXML
    private Button backButton;
    @FXML
    private TableColumn<User_Has_Events, String> eventColumn;
    @FXML
    private TableColumn<User_Has_Events, String> timeColumn;
    @FXML
    private TableColumn<User_Has_Events, Date> dateColumn;
    @FXML
    private TableColumn<User_Has_Events, String> countryColumn;
    @FXML
    private TableColumn<User_Has_Events, String> cityColumn;



    private DatabaseConnection db = new DatabaseConnection();
    private ChangeScene cs = new ChangeScene();
    private Event  event1 = new Event();
    private String user = DefaultPage.getCurrentUser();
    private User_Has_Events userHasEvents = new User_Has_Events();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String personID = null;
        try{
            personID = db.getSecurityNbr(user);
        } catch (SQLException e){
            e.printStackTrace();
        }
        ObservableList<User_Has_Events> eventObservableList = db.historyInformation(personID);

        eventColumn.setCellValueFactory(new PropertyValueFactory("eventName"));
        dateColumn.setCellValueFactory(new PropertyValueFactory("history"));
        timeColumn.setCellValueFactory(new PropertyValueFactory("eventTime"));
        countryColumn.setCellValueFactory(new PropertyValueFactory("country"));
        cityColumn.setCellValueFactory(new PropertyValueFactory("city"));




       tableView.setItems(eventObservableList);


    }


    @FXML
    private void handleReturnButton (ActionEvent event) throws IOException {

        cs.sceneHandler("../View/UserMenu.fxml",event);

    }


    public void popUpBox(User_Has_Events userHasEvents) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Volunteer Information");
        alert.setHeaderText(String.valueOf(userHasEvents));
        alert.setContentText("Choose your option.");

        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);


    }

}

