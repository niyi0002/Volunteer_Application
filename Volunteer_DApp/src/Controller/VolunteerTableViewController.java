package Controller;

import model.Volunteer;
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
import java.util.ResourceBundle;

public class VolunteerTableViewController implements Initializable {

    @FXML
    private TableView<Volunteer> volunteerTable;
    @FXML
    private TableColumn<Volunteer, Integer> volunteerIDColumn; // for every column we have to spacify
    @FXML
    private TableColumn<Volunteer, String> firstNameColumn;
    @FXML
    private TableColumn<Volunteer, String> lastNameColumn;
    @FXML
    private TableColumn<Volunteer, String> phoneColumn;
    @FXML
    private TableColumn<Volunteer, LocalDate> birthdayColumn;

    public VolunteerTableViewController() {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        // set factory where they retrive information from
        volunteerIDColumn.setCellValueFactory(new PropertyValueFactory<Volunteer, Integer>("VolunteerID"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("firstName")); // look for firstName, last name ....
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("lastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("phoneNumber"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<Volunteer, LocalDate>("birthday"));// now it knows where to find the information


        try {

            loadVolunteers(); // method to access the database
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    public void loadVolunteers() throws SQLException { // load volunteers and add them in to the database
        ObservableList<Volunteer> volunteers = FXCollections.observableArrayList();

        Connection conn = null; // create a connection
        Statement statement = null;// create a statement
        ResultSet resultSet = null;// create a result set and this is what we get return from the database

        try {

            // connect the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Volunteers", "volunteer_application", "12345678Bam!");
            // create a statement
            statement = conn.createStatement();
            //create the sql query
            resultSet = statement.executeQuery("SELECT * FROM volunteers"); //
            //          resultSet = statement.executeQuery("SELECT * FROM user
            //          where role = 'volunteer' "); //

            while (resultSet.next()) {
                // create a volunteer object from each record

                Volunteer newVolunteer = new Volunteer(resultSet.getString("firstName"),
                                                       resultSet.getString("lastName"),
                                                       resultSet.getString("phoneNumber"),
                                                       resultSet.getDate("birthday").toLocalDate());

                newVolunteer.setVolunteerID(resultSet.getInt("VolunteerID"));
                newVolunteer.setUserProfile(new File(resultSet.getString("imageFile")));
                volunteers.add(newVolunteer);// add list of the volunteers


            }

            volunteerTable.getItems().addAll(volunteers);// get all the existing items
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        finally {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();

            if (resultSet != null)
                resultSet.close();

        }

    }




    }

