package Controller;

import com.mysql.jdbc.Connection;
import javafx.fxml.Initializable;
import sample.DatabaseConnection;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DeleteUser implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /*

        String securityNbr;


        //Button för Delete User

        Connection conn =(Connection) DatabaseConnection.dbConnect();
        String query="DELETE FROM volunteer_application.volunteers WHERE securityNbr = '?';";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id.getText());
            pstmt.executeUpdate();

            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


*/
    }

}
