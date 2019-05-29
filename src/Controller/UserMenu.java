package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import sample.ChangeScene;
import sample.DatabaseConnection;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;

public class UserMenu implements Initializable {
    @FXML
    private Button userProfile;

    @FXML
    private Button events;

    @FXML
    private Button schedule;

    @FXML
    private Button donate;

    @FXML
    private Button signOut;

    @FXML
    private ImageView menu;


    @FXML
    private TextField txtFrom;

    @FXML
    private TextField txtTo;

    @FXML
    private TextArea txtMessage;

    @FXML
    private Button buttonSend;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button returnButton;

    @FXML
    private AnchorPane emailButton;

    @FXML
    private Button Addfunds;

    ChangeScene cs = new ChangeScene();
    DatabaseConnection app = new DatabaseConnection();

    String user=DefaultPage.getCurrentUser();
  //  private static String ourEmail= "VolunteerApplication123";  // GMail user name (just the part before "@gmail.com")
   // private static String ourEmailsPassword = "volunteerapp"; // GMail password (Maybe make one just for this project team)
    private static String emailTitle = "Volunteer Application"; // Add the title of the e-mail here.


    @FXML
    void sendMessage(ActionEvent event) {
        String reci = txtTo.getText().toString();
        String mess = txtMessage.getText().toString();

        String recipientEmailString = reci;
        String messageToBeSent = mess;


        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", txtFrom.getText());
        props.put("mail.smtp.password", txtPassword.getText());
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        Message message = new MimeMessage(session);

        String[] recipientEmail = new String[]{
                recipientEmailString}; //Change the String into a String[]
        try {
            message.setFrom(new InternetAddress(txtFrom.getText()));

            InternetAddress[] toAddress = new InternetAddress[recipientEmail.length];
            // To get the array of addresses
            for (int i = 0; i < recipientEmail.length; i++) {
                toAddress[i] = new InternetAddress(recipientEmail[i]);
            }

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(emailTitle);
            message.setText(messageToBeSent);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, txtFrom.getText(), txtPassword.getText());
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
        System.out.println("email sent");

    }

    @FXML
    void handleDonate(ActionEvent event) throws IOException {
      cs.sceneHandler("../View/Donate.fxml",event);
    }

    @FXML
    void email(ActionEvent event) throws IOException {
        cs.sceneHandler("../View/userEmail.fxml",event);
    }

    @FXML
    void returnButton(ActionEvent event) throws IOException {
        cs.sceneHandler("../View/UserMenu.fxml",event);
    }

    @FXML
    void handleEvents(ActionEvent event) throws IOException{
        cs.sceneHandler("../View/Events.fxml",event);
    }

    @FXML
    void handleSchedule(ActionEvent event) throws IOException {
        cs.sceneHandler("../View/Schedules.fxml",event);
    }

    @FXML
    void handleUserProfile(ActionEvent event) throws IOException {
        cs.sceneHandler("../View/UserProfile.fxml",event);
    }
    @FXML
    void handleViewHistory(ActionEvent event) throws IOException {
        cs.sceneHandler("../View/ViewHistory.fxml",event);
    }

    @FXML
    void handleSignOut(ActionEvent event) throws IOException {
        cs.sceneHandler("../View/DefaultPage.fxml",event);
    }

    @FXML
    void addFunds(ActionEvent event) throws IOException {
        cs.sceneHandler("../View/addFund.fxml",event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            txtFrom.setText(app.getEmail(user));
            Image image = new Image(new FileInputStream("src/icons/vol-hand.png"));
            menu.setImage(image);
            menu.setFitWidth(250);
            menu.setFitHeight(250);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
