package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminOrVolunteer {
    @FXML
    private Button volunteer ;

    @FXML
    private Button admin ;

    Stage dialogStage = new Stage();
    Scene scene ;

    @FXML
    private void handleVolunteer(ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            dialogStage.close();
            scene = new Scene(FXMLLoader.load(getClass().getResource("../View/SignIn.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleAdmin(ActionEvent event){
        try {
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            dialogStage.close();
            scene = new Scene(FXMLLoader.load(getClass().getResource("../View/AdminLogin.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

}
