package Controller;

import Model.Donation;
import Model.Volunteer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    ChangeScene cs = new ChangeScene();


    @FXML
    void handleDonate(ActionEvent event) throws SQLException {

        LocalDate date = LocalDate.now();
        Donation donation = new Donation();
        Volunteer volunteer = new Volunteer();
        DatabaseConnection db = new DatabaseConnection();
        String user = SignIn.getCurrentUser();
        String volunteerID = db.getSecurityNbr(user);
        volunteer.setSecurityNbr(volunteerID);

        donation.setDonation(amount.getText());
        donation.setDonationDate(Date.valueOf(date));
        db.insertDonation( donation, volunteer);



    }

    @FXML
    void handleGoBack(ActionEvent event) throws IOException {
        cs.sceneHandler("../View/UserMenu.fxml",event);
    }
}
