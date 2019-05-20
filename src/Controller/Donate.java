package Controller;

import Model.Donation;
import Model.Volunteer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.ChangeScene;
import sample.DatabaseConnection;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class Donate {
    @FXML
    private TextField amount;

    @FXML
    private Label titleLabel;

    @FXML
    private Button donate;

    @FXML
    private Label infoLabel;

    @FXML
    private Button goBack;

    private ChangeScene cs = new ChangeScene();
    private Donation donation = new Donation();
    private Volunteer volunteer = new Volunteer();
    private DatabaseConnection db = new DatabaseConnection();
    private String user = DefaultPage.getCurrentUser();

    @FXML
    void handleDonate(ActionEvent event) throws SQLException {
        String AMOUNT = "[0-9]*";
        LocalDate date = LocalDate.now();
        String volunteerID = db.getSecurityNbr(user);
        if (amount.getText().matches(AMOUNT)) {
            volunteer.setSecurityNbr(volunteerID);
            donation.setDonation(amount.getText());
            donation.setDonationDate(Date.valueOf(date));
            db.insertDonation(donation, volunteer);
        }
        else {
            alertBox();
        }


    }

    @FXML
    void handleGoBack(ActionEvent event) throws IOException {
        cs.sceneHandler("../View/UserMenu.fxml",event);
    }

    private void alertBox(){

        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
        errorAlert.setHeaderText("Invalid input");
        errorAlert.setContentText("Amount must be integer!");
        errorAlert.showAndWait();
    }
}
