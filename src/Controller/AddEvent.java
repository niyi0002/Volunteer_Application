package Controller;

import Model.Event;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.DatabaseConnection;

public class AddEvent { @FXML
private Label timeLabel;

    @FXML
    private TextField eventId;

    @FXML
    private TextField eventName;

    @FXML
    private TextField eventTime;

    @FXML
    private TextField eventInfo;

    @FXML
    private TextField organizer;

    @FXML
    private TextField country;

    @FXML
    private TextField city;

    @FXML
    private Label idLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label informationLabel;

    @FXML
    private Label organizerLabel;

    @FXML
    private Label countryLabel;

    @FXML
    private Label cityLabel;

    @FXML
    private DatePicker eventDate;

    @FXML
    private Button addEvent;



    @FXML private void handleAddEvent(ActionEvent event){
        DatabaseConnection app = new DatabaseConnection();
        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(eventDate.getValue());
        // insert three new rows
        Event event1 = new Event();
        event1.setEventName(eventName.getText());
        event1.setEventDate(gettedDatePickerDate);
        event1.setEventTime(eventTime.getText());
        event1.setEventInfo(eventInfo.getText());
        event1.setEventOrganizer(organizer.getText());
        event1.setCountry(country.getText());
        event1.setCity(city.getText());
        app.insertEvent(event1);
    }
}
