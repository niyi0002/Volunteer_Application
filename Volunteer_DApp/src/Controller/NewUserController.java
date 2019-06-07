package Controller;

import model.Volunteer;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;

public class NewUserController implements Initializable {

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private Label errMsgLabel;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField saveInfoTextField;

    @FXML
    private DatePicker birthday;

    @FXML
    private ImageView imageView;

    private File imageFile;

    public void chooseImageButtonPushed(ActionEvent event){

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");

        FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("Image File(*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("Image File(*.png)", "*.png");

        fileChooser.getExtensionFilters().addAll(jpgFilter, pngFilter);

        String userDirectoryString = System.getProperty("user.home") + "\\Pictures";
        File userDirectory = new File(userDirectoryString);

        if (!userDirectory.canRead())
            userDirectory = new File(System.getProperty("user.home"));

        fileChooser.setInitialDirectory(userDirectory);


        imageFile = fileChooser.showOpenDialog(stage);

        if (imageFile.isFile()){
            try {
                BufferedImage bufferedImage = ImageIO.read(imageFile);
                Image img = SwingFXUtils.toFXImage(bufferedImage, null);
                imageView.setImage(img);


            }
            catch (IOException e){
                System.err.println(e.getMessage());
            }
        }



    }




    public void saveVolunteerButtonPushed(ActionEvent event){ // create a methode which reads from the scene and try to create a new instance of a volunteer
         // once volunteer has been created succesfully it will be updated in the database

        try
        {

            Volunteer volunteer = new Volunteer( firstNameTextField.getText(), lastNameTextField.getText(), phoneNumberTextField.getText(),
                   birthday.getValue());

            errMsgLabel.setText(""); // don't show errors
            volunteer.insertIntoDB();

    }
        catch(Exception e){
         //   errMsgLabel.setText(e.getMessage());

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb){

        errMsgLabel.setText("");

        try{
            imageFile = new File(".src/images/Person.png");
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);

        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }

    }


}
