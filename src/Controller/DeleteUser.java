package Controller;

import com.mysql.jdbc.Connection;
import javafx.fxml.Initializable;
import sample.DatabaseConnection;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeleteUser implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        String securityNbr;


        //Button för Delete User

        Connection con =(Connection) DatabaseConnection.dbConnect();

        try (ResultSet rs = con.createStatement().executeQuery("DELETE FROM volunteer_application.volunteers WHERE securityNbr = '';")) {
        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }
}
