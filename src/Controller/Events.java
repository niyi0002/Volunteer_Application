package Controller;

import sample.DatabaseConnection;
import Model.Event;
import Model.Volunteer;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.ChangeScene;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

public class Events implements Initializable {

    @FXML
    private TableView<Event> table;

    @FXML
    private TableColumn<Event, Integer> eventId;

    @FXML
    private TableColumn<Event, String> eventName;

    @FXML
    private TableColumn<Event, Date> date;

    @FXML
    private TableColumn<Event, String> time;

    @FXML
    private TableColumn<Event, String> info;

    @FXML
    private TableColumn<Event, String> organizer;

    @FXML
    private TableColumn<Event, String> country;

    @FXML
    private TableColumn<Event, String> city;
    @FXML
    private TextField enterID;
    @FXML
    private TextField search;

    @FXML
    private Label displayRow;
    @FXML
    private ComboBox<String> comboBox;

    @FXML
    private Button register;

    @FXML
    private Button goBack;

    DatabaseConnection db = new DatabaseConnection();
    ChangeScene cs = new ChangeScene();
    private ObservableList<Event> eventObservableList = db.eventInformation();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        comboBox.getItems().add("Ascending order");
        comboBox.getItems().add("Descending order");

        this.eventId.setCellValueFactory(new PropertyValueFactory("eventID"));
        this.eventName.setCellValueFactory(new PropertyValueFactory("eventName"));
        this.date.setCellValueFactory(new PropertyValueFactory("eventDate"));
        this.time.setCellValueFactory(new PropertyValueFactory("eventTime"));
        this.info.setCellValueFactory(new PropertyValueFactory("eventInfo"));
        this.organizer.setCellValueFactory(new PropertyValueFactory("eventOrganizer"));
        this.country.setCellValueFactory(new PropertyValueFactory("country"));
        this.city.setCellValueFactory(new PropertyValueFactory("city"));


        this.table.setItems(eventObservableList);

        FilteredList<Event> filteredData = new FilteredList<>(eventObservableList, p -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(event -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (event.getEventName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (event.getCountry().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });
        SortedList<Event> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);

        comboBox.setOnAction((e) -> {
            String choice = comboBox.getSelectionModel().getSelectedItem();
            if (choice == "Ascending order"){
                eventName.setSortType(TableColumn.SortType.ASCENDING);
                eventId.setSortType(TableColumn.SortType.ASCENDING);

                table.setItems(sortedData);
                table.getSortOrder().add(eventName);

            }
            else if (choice == "Descending order"){
                eventName.setSortType(TableColumn.SortType.DESCENDING);
                eventId.setSortType(TableColumn.SortType.DESCENDING);

                table.setItems(sortedData);
                table.getSortOrder().add(eventName);
            }
        });
    }


    @FXML
    private void handleRegister(ActionEvent event) throws SQLException {
        Volunteer volunteer = new Volunteer();
        DatabaseConnection db = new DatabaseConnection();
        Event  event1 = new Event();
        int eventid = Integer.parseInt(enterID.getText());
        String user = SignIn.getCurrentUser();
        String personID = db.getSecurityNbr(user);
        volunteer.setSecurityNbr(personID);
        event1.setEventID(eventid);
        event1.setEventDate(java.sql.Date.valueOf(db.getEventDate(eventid)));
        db.registerToAnEvent(personID,eventid,event1,volunteer);

    }
    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {

        cs.sceneHandler("../View/UserMenu.fxml",event);

    }


}
