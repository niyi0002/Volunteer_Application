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

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ListOfVolunteers implements Initializable {

    @FXML
    private TableView<User> volunteerTable;
    @FXML
    private TableColumn<User, Integer> volunteerIDColumn; // for every column we have to spacify
    @FXML
    private TableColumn<User, String> firstNameColumn;
    @FXML
    private TableColumn<User, String> lastNameColumn;
    @FXML
    private TableColumn<User, String> phoneColumn;
    @FXML
    private TableColumn<User, LocalDate> birthdayColumn;
    @FXML
    private TableColumn<User, Integer> securityNumberColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DatabaseConnection app = new DatabaseConnection();


        // set factory where they retrive information from
        volunteerIDColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("VolunteerID"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("firstName")); // look for firstName, last name ....
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("lastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<User, String>("phoneNumber"));
        securityNumberColumn.setCellValueFactory(new PropertyValueFactory<User, Integer>("securityNbr"));
        birthdayColumn.setCellValueFactory(new PropertyValueFactory<User, LocalDate>("birthday"));// now it knows where to find the information
        //app.insert();

        try {

            loadUsers(); // method to access the database
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }

    public void loadUsers() throws SQLException { // load volunteers and add them in to the database
        ObservableList<User> users = FXCollections.observableArrayList();

        Connection conn = null; // create a connection
        Statement statement = null;// create a statement
        ResultSet resultSet = null;// create a result set and this is what we get return from the database

        try {

            // connect the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Volunteers", "volunteer_application", "12345678Bam!");
            // create a statement
            statement = conn.createStatement();
            //create the sql query
            resultSet = statement.executeQuery("SELECT * FROM volunteers");
                  // resultSet = statement.executeQuery("SELECT * FROM user
                    //  Where role = 'volunteer' "); //

            while (resultSet.next()) {
                // create a volunteer object from each record

                User newUser = new User(resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("phoneNbr"),
                        resultSet.getString("securityNbr"),
                        resultSet.getDate("birthday").toLocalDate());

                newUser.setUserID(resultSet.getString("userID"));
                /* newVolunteer.setUserProfile(new File(resultSet.getString("imageFile"))); */
                users.add(newUser);// add list of the volunteers


            }

            volunteerTable.getItems().addAll(users);// get all the existing items
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


