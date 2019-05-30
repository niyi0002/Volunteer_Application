package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static Main instance;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../View/Events.fxml"));
        primaryStage.setTitle("Volunteer Application");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
//        Image image = new Image("/ProjectCourse/logo.png");
      //  primaryStage.getIcons().add(image);
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
