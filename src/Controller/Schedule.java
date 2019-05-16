package Controller;
import Model.User;

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

public class Schedule implements Initializable {


    @FXML
    private TableView<Schedule> scheduleTableView;

    @FXML
    private TableColumn<Schedule, Integer> userIDColumn;

    @FXML
    private TableColumn<Schedule, String> causeOfEventColumn;

    @FXML
    private TableColumn<Schedule, LocalTime> scheduleLocalTimeTableColumn;

    @FXML
    private TableColumn<Schedule, LocalDate> scheduleLocalDateTableColumn;

    @FXML
    private TableColumn<Schedule, String> placeColumn;

    @FXML
    private TableColumn<Schedule, String> scheduleCountryColumn;

    @FXML
    private TableColumn<Schedule, String> scheduleCityColumn;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        userIDColumn.setCellValueFactory(new PropertyValueFactory<Schedule, Integer>("UserID")); // event is the type of data we have
        causeOfEventColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String>("causeOfEvent"));
        scheduleLocalTimeTableColumn.setCellValueFactory(new PropertyValueFactory<Schedule, LocalTime>("ScheduleTime"));
        scheduleLocalDateTableColumn.setCellValueFactory(new PropertyValueFactory<Schedule, LocalDate>("ScheduleDate"));
        scheduleCountryColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String>("scheduleCountry"));
        scheduleCityColumn.setCellValueFactory(new PropertyValueFactory<Schedule, String>("scheduleCity"));

        try {
            loadSchedule();
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }

    }

    public void loadSchedule() throws SQLException { // load volunteers from database and load them into the tableview object
        ObservableList<Schedule> schedules = FXCollections.observableArrayList();

        Connection con = null; // create a connection
        Statement statement = null;// create a statement
        ResultSet resultSet = null;// create a result set

        try {
            con = DatabaseConnection.dbConnect();

            //con = DriverManager.getConnection("jdbc:mysql//localhost:3306/Volunteers", "volunteer_application", "Bam12345678!");
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM schedules");


            while (resultSet.next()) {
                        Schedule newSchedule = new Schedule(
                        resultSet.getInt("userID"),
                        resultSet.getString("causeOfEvent"),
                        resultSet.getTime("eventTIME").toLocalTime(),
                                resultSet.getDate("eventDATE").toLocalDate(),
                        resultSet.getString("country"),
                        resultSet.getString("city"));

                newSchedule.setUserID(resultSet.getInt("userID"));

                schedules.add(newSchedule);


            }
            scheduleTableView.getItems().addAll(schedules);
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

    private void setUserID(int userID) {
    }
}
