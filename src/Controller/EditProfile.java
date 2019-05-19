package Controller;

import Model.Volunteer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.ChangeScene;
import sample.DatabaseConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditProfile implements Initializable {

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    String username = SignIn.getCurrentUser();
    DatabaseConnection databaseConnection = new DatabaseConnection();
    ChangeScene cs = new ChangeScene();



    @FXML
    private TextField lastNameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField phoneNbrField;

    @FXML
    private Button returnButton;

    @FXML
    private Button updateButton;

    @FXML
    private TextField firstNameField;



    @FXML
    void handleUpdateButton(ActionEvent event) throws SQLException {


        String SSN = "[0-9]{8}-[0-9]{4}";
        String NAME = "[a-zA-Z]";
        String PHONE = "[0-9]{10}";

        DatabaseConnection databaseConnection = new DatabaseConnection();
        Volunteer volunteer = new Volunteer();
        String user = SignIn.getCurrentUser();


        if (!firstNameField.getText().equals(databaseConnection.getFirstName(user)) && firstNameField.getText().matches(NAME)) {
            volunteer.setFirstname(firstNameField.getText());
            databaseConnection.updateFirstName(user, volunteer);
        } else {alertBox1();}

        if (!lastNameField.getText().equals(databaseConnection.getLastName(user)) && lastNameField.getText().matches(NAME)) {
            volunteer.setLastname(lastNameField.getText());
            databaseConnection.updateLastName(user, volunteer);
        } else {alertBox1();}

        if (!addressField.getText().equals(databaseConnection.getPhoneNbr(user))) {
            volunteer.setAddress(addressField.getText());
            databaseConnection.updateAddress(user, volunteer);
        }

        if (!phoneNbrField.getText().equals(databaseConnection.getPhoneNbr(user)) && phoneNbrField.getText().matches(PHONE)) {
            volunteer.setPhoneNbr(phoneNbrField.getText());
            databaseConnection.updatePhoneNbr(user, volunteer);
        }else{alertBox2();}

    }

    private void alertBox1() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Input invalid");
        errorAlert.setContentText("First name/last name can not contain digits.");
        errorAlert.showAndWait();
        System.out.println("Failed");

    }

    private void alertBox2() {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Input invalid");
        errorAlert.setContentText("Phone number must only contain 10 digits.");
        errorAlert.showAndWait();
        System.out.println("Failed");

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            firstNameField.setText(databaseConnection.getFirstName(username));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            lastNameField.setText(databaseConnection.getLastName(username));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            addressField.setText(databaseConnection.getAddress(username));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            phoneNbrField.setText(databaseConnection.getPhoneNbr(username));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void handleReturnButton (ActionEvent event) throws IOException {
        cs.sceneHandler("../View/UserProfile.fxml", event);
    }
}




