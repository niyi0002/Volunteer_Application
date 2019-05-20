package Controller;

import Model.Volunteer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.ChangeScene;
import sample.DatabaseConnection;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class DefaultPage {

    @FXML
    private Button signinButton;

    @FXML
    private Button SignupButton;

    @FXML
    private Button infoButton;
    @FXML
    private Hyperlink signUp ;
    @FXML
    private TextField handleUsername;
    @FXML
    private TextField handlePassword;

    ChangeScene cs= new ChangeScene();
    private static String currentUser;
    Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public DefaultPage(){
        connection = (Connection) DatabaseConnection.dbConnect();

    }

    @FXML
    private void handleSignIn(ActionEvent event){

        Volunteer volunteer = new Volunteer();
        String userName = handleUsername.getText();
        String password1 = handlePassword.getText();
        volunteer.setUsername(userName);
        volunteer.setPassword(password1);
        String roles = null;


        String sql = "SELECT userName , password ,role FROM volunteers WHERE userName = ? and password = ?  ";

        try {
            preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            preparedStatement.setString(1, volunteer.getUsername());
            preparedStatement.setString(2, volunteer.getPassword());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                roles = resultSet.getString(3);
                System.out.println(roles);
                if (roles.equals("volunteer")){
                    System.out.println(roles);
                    System.out.println(volunteer.getUsername()+" logged in.");
                    setCurrentUser(userName);
                    System.out.println(userName);
                    cs.sceneHandler("../View/UserMenu.fxml",event);
                }
                else {
                    cs.sceneHandler("../View/AdminMenu.fxml",event);
                }
            }
            else {
                alertBox();
                System.out.println(volunteer.getRole());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


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


    public void handleAboutUs(ActionEvent event) {
        try {
            cs.sceneHandler("../View/AboutUs.fxml",event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleSignUp(ActionEvent event) {
        try {
            cs.sceneHandler("../View/SignUp.fxml", event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
