package Controller;

import Model.Event;
import Model.Volunteer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class Events implements Initializable {



        @FXML
        private TableColumn<Event, Integer> eventIDColumn;

        @FXML
        private TableColumn<Event, String> eventNameColumn;

        @FXML
        private TableColumn<Event, LocalTime> eventTimeColumn;

        @FXML
        private TableColumn<Event, LocalDate> eventDateColumn;

        @FXML
        private TableColumn<Event, String> eventInfoColumn;

        @FXML
        private TableColumn<Event, String> eventCountryColumn;

        @FXML
        private TableColumn<Event, String> eventCityColumn;

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // eventID.setCellValueFactory(new PropertyValueFactory<Volunteer, Integer>("VolunteerID"));
        eventIDColumn.setCellValueFactory(new PropertyValueFactory<Event, Integer>("eventID"));
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
        eventTimeColumn.setCellValueFactory(new PropertyValueFactory<Event, LocalTime>("eventTime"));
        eventDateColumn.setCellValueFactory(new PropertyValueFactory<Event, LocalDate>("eventDate"));
        eventInfoColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("eventInfo"));
        eventInfoColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("eventInfo"));
        eventCountryColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("eventCountry"));
        eventCityColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("eventCity"));


    }

    public void loadEvent() throws SQLException { // load volunteers and add them in to the database
        ObservableList<Volunteer> volunteers = FXCollections.observableArrayList();

        Connection con = null; // create a connection
        Statement statement = null;// create a statement
        ResultSet resultSet = null;// create a result set

        try {
            con = DriverManager.getConnection("jdbc:mysql//localhost::3306/Volunteers", "student", "12345678Bam!");

            statement = con.createStatement();


            resultSet = statement.executeQuery("SELECT * FROM events");


            while (resultSet.next()) {
                Volunteer newVolunteer = new Volunteer( // this is done once I create the database
                Event newEvent = new Event();
                //    resultSet.getString("eventID"),
                //  resultSet.getString("eventName"),
                //  resultSet.getTime("eventTime"),
                        //  resultSet.getDate("eventDate"),
                        //  resultSet.getString("eventInfo"),
                        //  resultSet.getString("country"),
                        //  resultSet.getString("city"),

                newVolunteer.setVolunteerID(resultSet.getInt("VolunteerID"));
                Event newEvent = new Event(resultSet.getInt("EventID");
                volunteers.add(newVolunteer);


            }
            Events.getItems().addAll(events);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (resultSet != null)
                resultSet.close();
        }
    }
}
