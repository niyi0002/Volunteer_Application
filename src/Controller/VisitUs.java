package Controller;
/**
import Model.Volunteer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import jdk.jshell.execution.Util;
import sample.ChangeScene;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class VisitUs implements Initializable {

    @FXML
    private void getGitHub(){
        Util.goLink("https://github.com/niyi0002/Volunteer_Application");

    }
    @FXML
    private void goInsta(){
        Util.goLink("https://www.instagram.com/");
    }
    @FXML
    private void goFacebook(){
        Util.goLink("https://www.facebook.com/groups/575269509664055/");
    }
    @FXML
    private void goYouTube(){
        Util.goLink("https://www.youtube.com/");

    }
    @FXML
    private void goTwitter(){
        Util.getLink("https://www.twitter.com/")
    }

/**
    Hyperlink link = new Hyperlink();
    link.setText("https://www.youtube.com/");

    link.setOnAction(new EventHandler<ActionEvent>()){
        @Override
                public void handle(ActionEvent e){
            System.out.println("This link is clicked");
        }
    }
 **/
      /** private void jButtonActionHandlerjava.awt.event.ActionEvent) {
           Desktop browser = Desktop.getDesktop();
           try {
               browser.browse(new URI("https://www.youtube.com/"));
           }
           catch (IOException e){

           }
           catch (URISyntaxException e){

       }
       private void jButton1ActionHandler(java.awt.event.ActionEvent) {
       Desktop browser = Desktop.getDesktop();
       try {
       browser.browse(new URI("https://www.youtube.com/"));
       }
       catch (IOException e){

       }
       catch (URISyntaxException e){
       }
       private void jButton2ActionHandler(java.awt.event.ActionEvent) {
       Desktop browser = Desktop.getDesktop();
       try {
       browser.browse(new URI("https://www.Instagram.com/"));
       }
       catch (IOException e){

       }
       catch (URISyntaxException e){

       }
       private void jButton3ActionHandler(java.awt.event.ActionEvent) {
       Desktop browser = Desktop.getDesktop();
       try {
       browser.browse(new URI("https://www.Facebook.com/"));
       }
       catch (IOException e){

       }
       catch (URISyntaxException e){}
       **/
      /**





    @Override
    public void initialize(URL location, ResourceBundle resources) {


    } ChangeScene cs = new ChangeScene();
    private String user = DefaultPage.getCurrentUser();
    Volunteer volunteer = new Volunteer();

    @FXML
    private void goLink(javafx.event.ActionEvent event) throws IOException {

        cs.sceneHandler("../View/VisitUs.fxml", event);
    }
    @FXML
    private void handleReturnButton(javafx.event.ActionEvent event) throws IOException {

        cs.sceneHandler("../View/UserMenu.fxml",event);

    }



}
**/
