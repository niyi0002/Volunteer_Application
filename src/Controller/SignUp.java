package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.DatabaseConnection;

public class SignUp {
    @FXML
    private Label SSN ;
    @FXML
    private Label userName ;
    @FXML
    private Label firstName ;
    @FXML
    private Label lastName ;
    @FXML
    private Label password ;
    @FXML
    private Label email ;
    @FXML
    private Label birthday ;
    @FXML
    private Label phoneNbr ;
    @FXML
    private Label address ;

    @FXML
    private TextField ssnText;
    @FXML
    private TextField usernameText ;
    @FXML
    private TextField firstNameText ;
    @FXML
    private TextField lastNameText ;
    @FXML
    private PasswordField passwordField ;
    @FXML
    private TextField emailText ;
    @FXML
    private TextField birthdayText ;
    @FXML
    private TextField phoneNbrText ;
    @FXML
    private TextField addressText ;
    @FXML
    private Button register ;
    @FXML
    private Button returnButton ;

    Stage dialogStage = new Stage();
    Scene scene;

    @FXML private void handleRegister(ActionEvent event){
        DatabaseConnection app = new DatabaseConnection();
        app.insert( ssnText.getText() , usernameText.getText(),firstNameText.getText(), lastNameText.getText(), passwordField.getText(), emailText.getText(),birthdayText.getText(),
                phoneNbrText.getText(),addressText.getText());


    }

    @FXML private void handleReturn(ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            dialogStage.close();
            scene = new Scene(FXMLLoader.load(getClass().getResource("../View/DefaultPage.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
