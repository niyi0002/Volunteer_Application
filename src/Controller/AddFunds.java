package Controller;

import Model.Volunteer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import sample.ChangeScene;
import sample.DatabaseConnection;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

public class AddFunds implements Initializable {

    @FXML
    private Button hundra;

    @FXML
    private Button trehundra;

    @FXML
    private Button femhundra;

    @FXML
    private Button tusen;

    @FXML
    private Button returnButton;

    ChangeScene cs = new ChangeScene();
    private String user=DefaultPage.getCurrentUser();
    int balance;
    DatabaseConnection databaseConnection = new DatabaseConnection();
    Volunteer volunteer = new Volunteer();



    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    private void handleReturnButton(javafx.event.ActionEvent event) throws IOException {

        cs.sceneHandler("../View/UserMenu.fxml",event);

    }


    @FXML
    void handleAdd100kr(javafx.event.ActionEvent event)  throws IOException{
        int hundra=100;

        balance=balance+hundra;

        volunteer.setBalance(String.valueOf(balance));
        databaseConnection.updateBalance(user,volunteer);
    }


    @FXML
    void handleAdd300kr(javafx.event.ActionEvent event)  throws IOException{
        int trehundra=300;
        balance=balance+trehundra;

        volunteer.setBalance(String.valueOf(balance));

        databaseConnection.updateBalance(user,volunteer);
    }

    @FXML
    void handleAdd500kr(javafx.event.ActionEvent event) throws IOException {

        int femhundra=500;
        balance=balance+femhundra;

        volunteer.setBalance(String.valueOf(balance));

        databaseConnection.updateBalance(user,volunteer);
    }
    @FXML
    void handleAdd1000kr(javafx.event.ActionEvent event)  throws IOException{

        int tusen=1000;
        balance=balance+tusen;

        volunteer.setBalance(String.valueOf(balance));

        databaseConnection.updateBalance(user,volunteer);
    }

}
