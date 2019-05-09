package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DatabaseConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class UserProfile implements Initializable {

    DatabaseConnection app =new DatabaseConnection();

    @FXML
    private Label username;
    @FXML
    private Label ssn;
    @FXML
    private Label birthday;
    @FXML
    private Label name;
    @FXML
    private Label email;
    @FXML
    private Label phoneNumber;
    @FXML
    private Label address;
    @FXML
    private Label userProfile;

    @FXML
    private Button EditButton;

    @FXML
    private TextField txtName; //done
    @FXML
    private TextField txtUsername;  //Done
    @FXML
    private TextField txtPhnNbr;    //Done
    @FXML
    private TextField txtBirthday;  //Done
    @FXML
    private TextField txtAddress;   //Done
    @FXML
    private TextField txtEmail; //Done
    @FXML
    private TextField txtSsn;   //Done


    Stage dialogStage = new Stage();
    Scene scene;
    String user=SignIn.getCurrentUser();

    public void displayInfo(){



/*
        String SSN=rs.getString(1);
        String username=rs.getString(2);
        String firstName=rs.getString(3);
        String lastName=rs.getString(4);
        String email=rs.getString(5);
        String birthday=rs.getString(6);
        String phoneNbr=rs.getString(7);
        String address=rs.getString(8);

        //här ska jag kalla på metoden i DatabaseConnection
*/
    }



    @FXML
    void editProfile(ActionEvent event) {

        Node node = (Node) event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        try {
            //Insert right fxml file for edit
            scene = new Scene(FXMLLoader.load(getClass().getResource("../View/EditProfile.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialogStage.setScene(scene);
        dialogStage.show();


    }

    @FXML
    private void handleReturnButton(ActionEvent event) throws  IOException {
        Node node = (Node) event.getSource();
        dialogStage = (Stage) node.getScene().getWindow();
        dialogStage.close();
        try {
            //Insert right fxml file for edit
            scene = new Scene(FXMLLoader.load(getClass().getResource("../View/UserMenu.fxml")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            txtName.setText(app.getFirstName(user));
            txtUsername.setText(app.getUserName(user));
            txtPhnNbr.setText(app.getPhoneNbr(user));
            txtEmail.setText(app.getEmail(user));
            txtBirthday.setText(app.getBirthday(user));
            txtAddress.setText(app.getAddress(user));
            txtSsn.setText(app.getSecurityNbr(user));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
