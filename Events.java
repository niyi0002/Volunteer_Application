package Controller;

import Model.Event;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.DatabaseConnection;

import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class Events implements Initializable {


    @FXML
    private TableView<Event> eventTable;

    @FXML
    private TableColumn<Event, Integer> eventID;

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



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        eventID.setCellValueFactory(new PropertyValueFactory<Event, Integer>("EventID")); // event is the type of data we have
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("eventName"));
        eventTimeColumn.setCellValueFactory(new PropertyValueFactory<Event, LocalTime>("eventTime"));
        eventDateColumn.setCellValueFactory(new PropertyValueFactory<Event, LocalDate>("eventDate"));
        eventInfoColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("eventInfo"));
        eventInfoColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("eventInfo"));
        eventCountryColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("eventCountry"));
        eventCityColumn.setCellValueFactory(new PropertyValueFactory<Event, String>("eventCity"));

        try {
            loadEvent();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }

    public void loadEvent() throws SQLException { // load volunteers from database and load them into the tableview object
        ObservableList<Event> events = FXCollections.observableArrayList();

        Connection con = null; // create a connection
        Statement statement = null;// create a statement
        ResultSet resultSet = null;// create a result set

        try {
            con = DatabaseConnection.dbConnect();

            //con = DriverManager.getConnection("jdbc:mysql//localhost:3306/Volunteers", "volunteer_application", "Bam12345678!");
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM events");


            while (resultSet.next()) {
              Event newEvent = new Event(
                          resultSet.getString("eventID"),
                          resultSet.getString("eventName"),
                          resultSet.getDate("eventDATE").toLocalDate(),
                          resultSet.getTime("eventTIME").toLocalTime(),
                          resultSet.getString("eventInfo"),
                          resultSet.getString("eventOrganizer"),
                          resultSet.getString("country"),
                          resultSet.getString("city"));

                newEvent.setEventID(resultSet.getInt("EventID"));

                events.add(newEvent);


            }
            eventTable.getItems().addAll(events);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        } finally {
            if (con != null)
                con.close();

            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }
    }
}
