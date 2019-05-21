package Controller;

import javafx.fxml.Initializable;
import Model.Event;
import Model.User_Has_Events;
import Model.Volunteer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.ChangeScene;
import sample.DatabaseConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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



    private DatabaseConnection db = new DatabaseConnection();
    private ChangeScene cs = new ChangeScene();
    private Event  event1 = new Event();
    private String user = DefaultPage.getCurrentUser();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String personID = null;
        try{
            personID = db.getSecurityNbr(user);
        } catch (SQLException e){
            e.printStackTrace();
        }
        ObservableList<User_Has_Events> eventObservableList = db.historyInformation(personID);

        this.eventColumn.setCellValueFactory(new PropertyValueFactory("eventName"));
        this.dateColumn.setCellValueFactory(new PropertyValueFactory("history"));
        this.timeColumn.setCellValueFactory(new PropertyValueFactory("eventTime"));


        this.tableView.setItems(eventObservableList);


    }


    @FXML
    private void handleReturnButton (ActionEvent event) throws IOException {

        cs.sceneHandler("../View/UserMenu.fxml",event);

    }




}
