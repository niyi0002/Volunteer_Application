package Controller;

import Model.Donation;
import Model.User_Has_Events;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

public class ViewHistory implements Initializable {

    @FXML
    private TableView<User_Has_Events> history;

    @FXML
    private TableColumn<User_Has_Events, String> attenedEvents;

    @FXML
    private TableColumn<User_Has_Events, Date> date;

    @FXML
    private TableView<Donation> donationTable;

    @FXML
    private TableColumn<Donation, Date> donatedDate;

    @FXML
    private TableColumn<Donation, Integer> donateAmount;

    @FXML
    private Label label1;

    @FXML
    private Label label2;

   ChangeScene cs = new ChangeScene();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DatabaseConnection db = new DatabaseConnection();
        String user = DefaultPage.getCurrentUser();
        String personID = null;
        try {
            personID = db.getSecurityNbr(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        ObservableList<User_Has_Events> eventObservableList = db.historyInfo(personID);
        ObservableList<Donation> eventObservableList2 = db.donationInfo(personID);

        this.attenedEvents.setCellValueFactory(new PropertyValueFactory("eventName"));
        this.date.setCellValueFactory(new PropertyValueFactory("history"));
        this.donateAmount.setCellValueFactory(new PropertyValueFactory("donation"));
        this.donatedDate.setCellValueFactory(new PropertyValueFactory("donationDate"));


        this.history.setItems(eventObservableList);
        this.donationTable.setItems(eventObservableList2);

    }

    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        cs.sceneHandler("../View/UserMenu.fxml", event);

    }
    
}
