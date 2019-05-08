/**package Controller;

public class VolunteerTable {
}

package Controller;

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
import java.util.ResourceBundle;

public class VolunteerTable implements Initializable  {
 package Controller;

 import javafx.fxml.FXML;
 import javafx.scene.control.Label;
 import javafx.scene.control.TableColumn;

 public class VolunteerTable {

@FXML
private Label VolunteerTable;

@FXML
private TableColumn<?, ?> volunteerIDColumn;

@FXML
private TableColumn<?, ?> firstNameColumn;

@FXML
private TableColumn<?, ?> lastNameColumn;

@FXML
private TableColumn<?, ?> birthDayColumn;

}


    @FXML
    private TableView<Volunteer> volunteerTable;
    @FXML
    private TableColumn<Volunteer, Integer> VolunteerIDColumn;
    @FXML
    private TableColumn<Volunteer, String> firstNameColumn;
    @FXML
    private TableColumn<Volunteer, String> lastNameColumn;
    @FXML
    private TableColumn<Volunteer, String> phoneColumn;
    @FXML
    private TableColumn<Volunteer, LocalDate> birthDayColumn;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

        volunteerID.setCellValueFactory(new PropertyValueFactory<Volunteer, Integer>("VolunteerID"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("firstName")); // spacify where and which instance we get the values
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("lastName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Volunteer, String>("phoneNumber"));
        birthDayColumn.setCellValueFactory(new PropertyValueFactory<Volunteer, LocalDate>("birthDay"));// now it knows where to find the information


        try {

            loadVolunteers();
        } catch (SQLException e) {
            System.err.println(e.getMessage());

        }
    }
    public void loadVolunteers()throws SQLException   { // load volunteers and add them in to the database
        ObservableList<Volunteer> volunteers = FXCollections.observableArrayList();

        Connection conn = null; // create a connection
        Statement statement = null;// create a statement
        ResultSet resultSet = null;// create a result set

        try{
            conn = DriverManager.getConnection("jdbc:mysql//localhost:3306/Volunteers", "Local", "Local");

            statement = conn.createStatement();


            resultSet = statement.executeQuery("SELECT * FROM volunteers");

            while (resultSet.next()){
                Volunteer newVolunteer = new Volunteer(); // this is done once I create the database
                //    resultSet.getString("firstName"),
                //  resultSet.getString("lastName"),
                //  resultSet.getString("phoneNumber"),
                //  resultSet.getDate("birthday").toLocalDate());

                newVolunteer.setVolunteerID(resultSet.getInt("VolunteerID"));
                newVolunteer.setUserProfile(new File (resultSet.getString("imageFile")));
                volunteers.add(newVolunteer);


            }

            volunteerTable.getItems().addAll(volunteers);
        }
        catch (Exception e){
            System.err.println(e.getMessage());
        }
        finally {
            if ( conn != null){
                conn.close();
            }
            if (statement != null){
                statement.close();
            }
            if (resultSet != null)
                resultSet.close();

        }

    }
}
**/