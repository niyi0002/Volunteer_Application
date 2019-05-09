package Controller;

import Model.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.ChangeScene;
import sample.DatabaseConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminLogin {
    private static String currentUser ;
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button signIn;

    @FXML
    private Button back;

    @FXML
    private Label label;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label passwordLabel;

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    ChangeScene cs = new ChangeScene();

    public AdminLogin() {
        connection = (Connection) DatabaseConnection.dbConnect();

    }

    @FXML
    void handleSignIn(ActionEvent event) throws IOException {

        Admin admin = new Admin();
        String userName = username.getText();
        String password1 = password.getText();
        admin.setUsername(userName);
        admin.setPassword(password1);
        String roles = "admin";

        String sql = "SELECT * FROM volunteers WHERE username = ? and password = ? and role = ? ";

        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, admin.getUsername());
            preparedStatement.setString(2, admin.getPassword());
            preparedStatement.setString(3,roles);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                alertBox();

            } else {
                System.out.println(admin.getUsername()+" logged in.");
                setCurrentUser(userName);

                cs.sceneHandler("../View/AdminMenu.fxml",event);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }

    @FXML
    private void handleBack(ActionEvent event) throws IOException {
       cs.sceneHandler("../View/DefaultPage.fxml",event);
    }

    private void alertBox() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Login Error");
        alert.setHeaderText("Error Message: ");
        alert.setContentText("Username or password doesn't match !");

        alert.showAndWait();
    }

    public static String getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(String currentMethodUser) {
        currentUser = currentMethodUser;
    }


}
