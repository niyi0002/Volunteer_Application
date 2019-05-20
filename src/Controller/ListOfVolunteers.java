package Controller;


import sample.DatabaseConnection;
import Model.Volunteer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import sample.ChangeScene;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;



public class ListOfVolunteers implements Initializable {


    @FXML
    private TableView<Volunteer> table;

    @FXML
    private TableColumn<Volunteer, String> volunteer_name;

    @FXML
    private TableColumn<Volunteer, String> volunteer_lastname;

    DatabaseConnection db = new DatabaseConnection();
    ChangeScene cs = new ChangeScene();
    private ObservableList<Volunteer> userList = db.volunteerInfo();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.volunteer_name.setCellValueFactory(new PropertyValueFactory("firstname"));
        this.volunteer_lastname.setCellValueFactory(new PropertyValueFactory("lastname"));
        this.table.setItems(userList);
        table.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                int index = table.getSelectionModel().getSelectedIndex();
                Volunteer volunteer = table.getItems().get(index);
                popUpBox(volunteer);

            }
        });
    }

    public void popUpBox(Volunteer volunteer){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Volunteer Information");
        alert.setHeaderText(String.valueOf(volunteer));
        alert.setContentText("Choose your option.");

        ButtonType buttonTypeOne = new ButtonType("Delete");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne , buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            // ... user chose "One"
         //   db.deleteVolunteer(volunteer.getUsername());
            table.refresh();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }

    @FXML
    private void handleGoBack(ActionEvent event) throws IOException {
        cs.sceneHandler("../View/AdminMenu.fxml",event);
    }
}



