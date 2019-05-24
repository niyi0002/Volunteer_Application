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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import sample.ChangeScene;
import sample.DatabaseConnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class UserProfile implements Initializable {

    DatabaseConnection app = new DatabaseConnection();

    ChangeScene cs= new ChangeScene();

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
    @FXML
    private ImageView pp;

    String user=DefaultPage.getCurrentUser();

    @FXML
    void editProfile(ActionEvent event) {

        try {
            cs.sceneHandler("../View/EditProfile.fxml", event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleReturnButton(ActionEvent event) throws  IOException {

        cs.sceneHandler("../View/UserMenu.fxml", event);

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

        try {
            Image image = new Image(new FileInputStream("src/icons/users-17.png"));
            pp.setImage(image);
            pp.setFitWidth(250);
            pp.setFitHeight(250);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
