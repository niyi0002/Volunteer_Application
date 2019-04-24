package sample;

import java.sql.*;

public class DatabaseConnection {
    private Connection con;
    private Statement st;
    private ResultSet rs;

    public static Connection dbConnect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/volunteer_application", "root", "root");
            return con;
        } catch (Exception ex) {
            System.out.println("the connection fails " + ex);
            return null;
        }
    }

    public void insert( String securityNbr , String userName,String firstName , String lastName , String password, String email, String birthday , String phoneNbr ,String address) {
        String sql = "INSERT INTO information(securityNbr,userName,firstName,lastName,password,email,birthday,phoneNbr,address) VALUES(?,?,?,?,?,?,?,?,?)";

        try (Connection conn = this.dbConnect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, securityNbr);
            pstmt.setString(2, userName);
            pstmt.setString(3,firstName);
            pstmt.setString(4, lastName);
            pstmt.setString(5,password);
            pstmt.setString(6,email);
            pstmt.setString(7,birthday);
            pstmt.setString(8,phoneNbr);
            pstmt.setString(9,address);
            pstmt.executeUpdate();
            System.out.println("Volunteer saved into database!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
