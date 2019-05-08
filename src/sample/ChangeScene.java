package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChangeScene {

    public ChangeScene(){

    }
    @FXML
    public void sceneHandler(String sceneName, ActionEvent event) throws IOException {

        Stage dialogStage ;
        Scene scene ;
        try {
            Node node = (Node) event.getSource();
            dialogStage = (Stage) node.getScene().getWindow();
            dialogStage.close();
            scene = new Scene(FXMLLoader.load(getClass().getResource(sceneName)));
            dialogStage.setScene(scene);
            dialogStage.show();

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
