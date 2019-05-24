package Controller;

import Model.Volunteer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.ChangeScene;
import sample.DatabaseConnection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;


public class DefaultPage implements Initializable {

    @FXML
    private Button signinButton;

    @FXML
    private Button SignupButton;

    @FXML
    private Button infoButton;
    @FXML
    private Hyperlink signUp;
    @FXML
    private TextField handleUsername;
    @FXML
    private TextField handlePassword;
    @FXML
    private Label username;
    @FXML
    private Label pass1;

    ChangeScene cs = new ChangeScene();
    private static String currentUser;
    Connection connection;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public DefaultPage() {
        connection = (Connection) DatabaseConnection.dbConnect();

    }

    @FXML
    private void handleSignIn(ActionEvent event) {

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
            if (resultSet.next()) {
                roles = resultSet.getString(3);
                System.out.println(roles);
                if (roles.equals("volunteer")) {
                    System.out.println(roles);
                    System.out.println(volunteer.getUsername() + " logged in.");
                    setCurrentUser(userName);
                    System.out.println(userName);
                    cs.sceneHandler("../View/UserMenu.fxml", event);
                } else {
                    cs.sceneHandler("../View/AdminMenu.fxml", event);
                }
            } else {
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
            cs.sceneHandler("../View/AboutUs.fxml", event);
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Image image = new Image(new FileInputStream("src/icons/user-icon-png-8.jpg"));
            ImageView imageView = new ImageView(image);
            username.setGraphic(imageView);
            imageView.setFitHeight(35);
            imageView.setFitWidth(35);

            Image image1 = new Image(new FileInputStream("src/icons/hey.png"));
            ImageView imageView1 = new ImageView(image1);
            pass1.setGraphic(imageView1);
            imageView1.setFitHeight(35);
            imageView1.setFitWidth(35);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}