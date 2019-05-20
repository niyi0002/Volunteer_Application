package Controller;

import Model.Volunteer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.DatabaseConnection;

import java.sql.Date;

public class SignUp {
    @FXML
    private Label SSN ;
    @FXML
    private Label userName ;
    @FXML
    private Label firstName ;
    @FXML
    private Label lastName ;
    @FXML
    private Label password ;
    @FXML
    private Label email ;
    @FXML
    private Label birthday ;
    @FXML
    private Label phoneNbr ;
    @FXML
    private Label address ;

    @FXML
    private TextField ssnText;
    @FXML
    private TextField usernameText ;
    @FXML
    private TextField firstNameText ;
    @FXML
    private TextField lastNameText ;
    @FXML
    private PasswordField passwordField ;
    @FXML
    private TextField emailText ;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField phoneNbrText ;
    @FXML
    private TextField addressText ;
    @FXML
    private Button register ;
    @FXML
    private Button returnButton ;
    Volunteer volunteer = new Volunteer();
    Stage dialogStage = new Stage();
    Scene scene;

    @FXML private void handleRegister(ActionEvent event){
        DatabaseConnection db = new DatabaseConnection();
      java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(datePicker.getValue());
        // insert three new rows
        String SSN = "[0-9]{8}-[0-9]{4}";
        String NAME = "[a-zA-Z]";
        String PHONE = "[0-9]{10}";
        if (ssnText.getText().matches(SSN)){
            volunteer.setSecurityNbr(ssnText.getText());
        }else {alertBox();}

        volunteer.setUsername(usernameText.getText());
        volunteer.setPassword(passwordField.getText());
        if (firstNameText.getText().matches(NAME) && lastNameText.getText().matches(NAME)) {
            volunteer.setFirstname(firstNameText.getText());
            volunteer.setLastname(lastNameText.getText());
        }else {alertBox();}
        volunteer.setEmail(emailText.getText());
        volunteer.setAddress(addressText.getText());
        if (phoneNbrText.getText().matches(PHONE)) {
            volunteer.setPhoneNbr(phoneNbrText.getText());
        }else {alertBox();}
        volunteer.setBirthday(gettedDatePickerDate);
        volunteer.setRole("volunteer");
        db.insert(volunteer);


    }

    @FXML private void handleReturn(ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            dialogStage.close();
            scene = new Scene(FXMLLoader.load(getClass().getResource("../View/DefaultPage.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private   void alertBox(){

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Input invalid");
        errorAlert.setContentText("Username or password is incorrect");
        errorAlert.showAndWait();
        System.out.println("Failed");
    }
}
