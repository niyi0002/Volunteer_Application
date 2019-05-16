package Controller;

import Model.Volunteer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import sample.ChangeScene;

import java.io.IOException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Email {

    @FXML
    private TextField txtFrom;

    @FXML
    private TextField txtTo;

    @FXML
    private TextArea txtMessage;

    @FXML
    private Button buttonSend;

    @FXML
    private Button returnBotton;

    ChangeScene cs = new ChangeScene();


    // Only change this part.
    private static String ourEmail = "VolunteerApplication123";  // GMail user name (just the part before "@gmail.com")
    private static String ourEmailsPassword = "volunteerapp"; // GMail password (Maybe make one just for this project team)
    private static String emailTitle = "Volunteer Application"; // Add the title of the e-mail here.

    @FXML
    void sendMessage(ActionEvent event) {
        String reci=txtTo.getText().toString();
        String mess=txtMessage.getText().toString();

        String recipientEmailString=reci;
        String messageToBeSent=mess;


        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", ourEmail);
        props.put("mail.smtp.password", ourEmailsPassword);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        Message message = new MimeMessage(session);

        String[] recipientEmail = new String[]{
                recipientEmailString}; //Change the String into a String[]
        try {
            message.setFrom(new InternetAddress(ourEmail));

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
            transport.connect(host, ourEmail, ourEmailsPassword);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }

    }

    @FXML
    void returnAction(ActionEvent event) {
        try {
            cs.sceneHandler("../View/AdminMenu.fxml",event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*

        public static void sendEmail( String messageToBeSent) {

        String recipientEmailString=tx;


        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", ourEmail);
        props.put("mail.smtp.password", ourEmailsPassword);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        String[] recipientEmail = new String[]{
                recipientEmailString}; //Change the String into a String[]
        try {
            message.setFrom(new InternetAddress(ourEmail));

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
            transport.connect(host, ourEmail, ourEmailsPassword);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }



    }
    */

}
