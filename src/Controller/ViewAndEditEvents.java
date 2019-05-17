package Controller;

import Model.Event;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sample.ChangeScene;
import sample.DatabaseConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class ViewAndEditEvents implements Initializable {

    @FXML
    private TableView<Event> table;

    @FXML
    private TableColumn<Event, Integer> eventId;

    @FXML
    private TableColumn<Event, String> eventName;

    @FXML
    private TableColumn<Event, String> date;

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
    private Button goBack;

    DatabaseConnection db = new DatabaseConnection();
    ChangeScene cs = new ChangeScene();
    private ObservableList<Event> eventObservableList = db.eventInformation();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        comboBox.getItems().add("Ascending order");
        comboBox.getItems().add("Descending order");
        eventId.setVisible(false);

        this.eventId.setCellValueFactory(new PropertyValueFactory("eventID"));
        this.eventName.setCellValueFactory(new PropertyValueFactory("eventName"));
        this.date.setCellValueFactory(new PropertyValueFactory("eventDate"));
        this.time.setCellValueFactory(new PropertyValueFactory("eventTime"));
        this.info.setCellValueFactory(new PropertyValueFactory("eventInfo"));
        this.organizer.setCellValueFactory(new PropertyValueFactory("eventOrganizer"));
        this.country.setCellValueFactory(new PropertyValueFactory("country"));
        this.city.setCellValueFactory(new PropertyValueFactory("city"));
        //editEvent();

        this.table.setItems(eventObservableList);
        this.table.setEditable(true);
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
            if (choice == "Ascending order") {
                eventName.setSortType(TableColumn.SortType.ASCENDING);
                eventId.setSortType(TableColumn.SortType.ASCENDING);

                table.setItems(sortedData);
                table.getSortOrder().add(eventName);

            } else if (choice == "Descending order") {
                eventName.setSortType(TableColumn.SortType.DESCENDING);
                eventId.setSortType(TableColumn.SortType.DESCENDING);

                table.setItems(sortedData);
                table.getSortOrder().add(eventName);
            }
        });

    }

    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {

        cs.sceneHandler("../View/AdminMenu.fxml",event);

    }

    public void editEvent(){
        eventName.setCellFactory(TextFieldTableCell.forTableColumn());
        eventName.setOnEditCommit(event -> {
            Event event2 = event.getRowValue();
            event2.setEventName(event.getNewValue());
            db.updateEventName(event.getNewValue(),event.getRowValue().getEventID());
        });

        // date.setCellFactory(TextFieldTableCell.forTableColumn());
        date.setCellFactory(TextFieldTableCell.forTableColumn());
        date.setOnEditCommit(event -> {
            Event event2 = event.getRowValue();
            event2.setEventDate(Date.valueOf(event.getNewValue()));
            db.updateEventDate(Date.valueOf(event.getNewValue()), event.getRowValue().getEventID());
        });

        time.setCellFactory(TextFieldTableCell.forTableColumn());
        time.setOnEditCommit(event -> {
            Event event2 = event.getRowValue();
            event2.setEventTime(event.getNewValue());
            db.updateEventTime(event.getNewValue(),event.getRowValue().getEventID());
        });
        info.setCellFactory(TextFieldTableCell.forTableColumn());
        info.setOnEditCommit(event -> {
            Event event2 = event.getRowValue();
            event2.setEventInfo(event.getNewValue());
            db.updateEventInfo(event.getNewValue(),event.getRowValue().getEventID());
        });
        organizer.setCellFactory(TextFieldTableCell.forTableColumn());
        organizer.setOnEditCommit(event -> {
            Event event2 = event.getRowValue();
            event2.setEventOrganizer(event.getNewValue());
            db.updateEventOrganizer(event.getNewValue(),event.getRowValue().getEventID());
        });
        country.setCellFactory(TextFieldTableCell.forTableColumn());
        country.setOnEditCommit(event -> {
            Event event2 = event.getRowValue();
            event2.setCountry(event.getNewValue());
            db.updateCountry(event.getNewValue(),event.getRowValue().getEventID());
        });
        city.setCellFactory(TextFieldTableCell.forTableColumn());
        city.setOnEditCommit(event -> {
            Event event2 = event.getRowValue();
            event2.setCity(event.getNewValue());
            db.updateCity(event.getNewValue(),event.getRowValue().getEventID());
        });

    }


}
