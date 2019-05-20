package sample;

import Controller.Email;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/DefaultPage.fxml"));
        primaryStage.setTitle("Volunteer Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        /*
        Main main = new Main();
        main.runProgram();
        */
        launch(args);
    }
        /*
    private void runProgram() {


        connecta TO och Message till detta
        Email.sendEmail("perhanovic98@gmail.com", "Testing message");
    }
*/

}
