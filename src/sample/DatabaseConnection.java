package sample;

import Model.Event;
import Model.Volunteer;

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

    public void insert( String securityNbr , String userName,String firstName , String lastName , String password, String email, String birthday , String phoneNbr ,String address ,String role) {
        String sql = "INSERT INTO volunteers(securityNbr,userName,firstName,lastName,password,email,birthday,phoneNbr,address,role) VALUES(?,?,?,?,?,?,?,?,?,?)";

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
            pstmt.setString(10,role);
            pstmt.executeUpdate();
            System.out.println("Volunteer saved into database!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getSecurityNbr(String username) throws SQLException {
        String SSN = null;

        String query = "select securityNbr from volunteers where username = '" +username+"'";
        try (Connection connection = this.dbConnect();
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                SSN = resultSet.getString(1);
            }
        }
        return SSN;
    }
    public String getUserName(String username) throws SQLException {
        String userName = null;

        String query = "select userName from volunteers where username = '" +username+"'";
        try (Connection connection = this.dbConnect();
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                userName = resultSet.getString(1);
            }
        }
        return userName;
    }
    public String getFirstName(String username) throws SQLException {
        String firstName = null;

        String query = "select firstName from volunteers where username = '" +username+"'";
        try (Connection connection = this.dbConnect();
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                firstName = resultSet.getString(1);
            }
        }
        return firstName;
    }
    public String getLastName(String username) throws SQLException {
        String lastName = null;

        String query = "select lastName from volunteers where username = '" +username+"'";
        try (Connection connection = this.dbConnect();
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                lastName = resultSet.getString(1);
            }
        }
        return lastName;
    }
    public String getEmail(String username) throws SQLException {
        String email = null;

        String query = "select email from volunteers where username = '" +username+"'";
        try (Connection connection = this.dbConnect();
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                email = resultSet.getString(1);
            }
        }
        return email;
    }
    public String getBirthday(String username) throws SQLException {
        String birthday = null;

        String query = "select birthday from volunteers where username = '" +username+"'";
        try (Connection connection = this.dbConnect();
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                birthday = resultSet.getString(1);
            }
        }
        return birthday;
    }
    public String getPhoneNbr(String username) throws SQLException {
        String phoneNbr = null;

        String query = "select phoneNbr from volunteers where username = '" +username+"'";
        try (Connection connection = this.dbConnect();
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                phoneNbr = resultSet.getString(1);
            }
        }
        return phoneNbr;
    }
    public String getAddress(String username) throws SQLException {
        String address = null;

        String query = "select address from volunteers where username = '" +username+"'";
        try (Connection connection = this.dbConnect();
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                address = resultSet.getString(1);
            }
        }
        return address;
    }
    public void insertEvent( Event event) {
        String sql = ""+"INSERT INTO events(eventID,eventName,eventDate,eventTime,eventInfo,eventOrganizer,country,city) VALUES(?,?,?,?,?,?,?,?)";


        try (Connection conn = this.dbConnect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, event.getEventID());
            pstmt.setString(2, event.getEventName());
            pstmt.setString(3,event.getEventDate());
            pstmt.setString(4, event.getEventTime());
            pstmt.setString(5,event.getEventInfo());
            pstmt.setString(6,event.getEventOrganizer());
            pstmt.setString(7,event.getCountry());
            pstmt.setString(8,event.getCity());
            pstmt.executeUpdate();
            System.out.println("Event saved into database!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
