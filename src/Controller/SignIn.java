package Controller;

import Model.Volunteer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.ChangeScene;
import sample.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignIn {

    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private Button signInButton;
    @FXML
    private Button returnButton;

    private static String currentUser;
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ChangeScene cs = new ChangeScene();


    public SignIn() {
        connection = DatabaseConnection.dbConnect();

    }

    @FXML
    private void handleSignIn(ActionEvent event) throws IOException {
        String userName1 = userName.getText().toString();
        String password2 = password.getText().toString();

        Volunteer volunteer = new Volunteer();
        volunteer.setUsername(userName1);
        volunteer.setPassword(password2);
        String roles = "volunteer";

        String sql = "SELECT * FROM volunteers WHERE userName = ? and password = ? and role = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName1);
            preparedStatement.setString(2, password2);
            preparedStatement.setString(3, roles);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {

                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Input invalid");
                errorAlert.setContentText("Username or password is incorrect");
                errorAlert.showAndWait();
                System.out.println("Failed");

            } else {
                System.out.println("Succesful");
                setCurrentUser(userName1);
                cs.sceneHandler("../View/UserMenu.fxml", event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        @FXML
        private void handleReturnButton (ActionEvent event) throws IOException {
            cs.sceneHandler("../View/DefaultPage.fxml", event);

        }

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(String currentMethodUser) {
        currentUser = currentMethodUser;
    }


    }


