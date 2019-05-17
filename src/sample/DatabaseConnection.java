package sample;

import Model.Donation;
import Model.Event;
import Model.User_Has_Events;
import Model.Volunteer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;
import java.time.LocalDate;

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

    public void insert(Volunteer volunteer) {
        String sql = "" + "INSERT INTO volunteers(securityNbr,userName,password,firstName,lastName,email,address,phoneNbr,birthday,role) VALUES(?,?,?,?,?,?,?,?,?,?)";


        try (Connection conn = this.dbConnect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, volunteer.getSecurtyNbr());
            pstmt.setString(2, volunteer.getUsername());
            pstmt.setString(3, volunteer.getPassword());
            pstmt.setString(4, volunteer.getFirstname());
            pstmt.setString(5, volunteer.getLastname());
            pstmt.setString(6, volunteer.getEmail());
            pstmt.setString(7, volunteer.getAddress());
            pstmt.setString(8, volunteer.getPhoneNbr());
            pstmt.setString(9, volunteer.getBirthday());
            pstmt.setString(10, volunteer.getRole());
            pstmt.executeUpdate();
            System.out.println("Volunteer saved into database!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getSecurityNbr(String username) throws SQLException {
        String SSN = null;

        String query = "select securityNbr from volunteers where username = '" + username + "'";
        try (Connection connection = this.dbConnect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                SSN = resultSet.getString(1);
            }
        }
        return SSN;
    }

    public String getUserName(String username) throws SQLException {
        String userName = null;

        String query = "select userName from volunteers where username = '" + username + "'";
        try (Connection connection = this.dbConnect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                userName = resultSet.getString(1);
            }
        }
        return userName;
    }

    public String getFirstName(String username) throws SQLException {
        String firstName = null;

        String query = "select firstName from volunteers where username = '" + username + "'";
        try (Connection connection = this.dbConnect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                firstName = resultSet.getString(1);
            }
        }
        return firstName;
    }

    public String getLastName(String username) throws SQLException {
        String lastName = null;

        String query = "select lastName from volunteers where username = '" + username + "'";
        try (Connection connection = this.dbConnect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                lastName = resultSet.getString(1);
            }
        }
        return lastName;
    }

    public String getEmail(String username) throws SQLException {
        String email = null;

        String query = "select email from volunteers where username = '" + username + "'";
        try (Connection connection = this.dbConnect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                email = resultSet.getString(1);
            }
        }
        return email;
    }

    public String getBirthday(String username) throws SQLException {
        String birthday = null;

        String query = "select birthday from volunteers where username = '" + username + "'";
        try (Connection connection = this.dbConnect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                birthday = resultSet.getString(1);
            }
        }
        return birthday;
    }

    public String getPhoneNbr(String username) throws SQLException {
        String phoneNbr = null;

        String query = "select phoneNbr from volunteers where username = '" + username + "'";
        try (Connection connection = this.dbConnect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                phoneNbr = resultSet.getString(1);
            }
        }
        return phoneNbr;
    }

    public String getAddress(String username) throws SQLException {
        String address = null;

        String query = "select address from volunteers where username = '" + username + "'";
        try (Connection connection = this.dbConnect();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                address = resultSet.getString(1);
            }
        }
        return address;
    }

    public void insertEvent(Event event) {
        String sql = "" + "INSERT INTO events(eventID,eventName,eventDate,eventTime,eventInfo,eventOrganizer,country,city) VALUES(?,?,?,?,?,?,?,?)";


        try (Connection conn = this.dbConnect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, event.getEventID());
            pstmt.setString(2, event.getEventName());
            pstmt.setString(3, event.getEventDate());
            pstmt.setString(4, event.getEventTime());
            pstmt.setString(5, event.getEventInfo());
            pstmt.setString(6, event.getEventOrganizer());
            pstmt.setString(7, event.getCountry());
            pstmt.setString(8, event.getCity());
            pstmt.executeUpdate();
            System.out.println("Event saved into database!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertDonation(Donation donation, Volunteer volunteer) {

        String query = "" + "INSERT INTO donation(donationAmount,donationHistory,securityNbr) VALUES(?,?,?) ";

        try (Connection conn = this.dbConnect();
             PreparedStatement preparedStmt = conn.prepareStatement(query)) {

            preparedStmt.setString(1, donation.getDonation());
            preparedStmt.setString(2, donation.getDonationDate());
            preparedStmt.setString(3, volunteer.getSecurtyNbr());

            preparedStmt.executeUpdate();
            System.out.println("Inserted donation!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateFirstName(String user, Volunteer volunteer) {

        String sql = "update volunteers set firstName = ?  where userName = ?  ";


        try (Connection con = this.dbConnect();
             PreparedStatement preparedStmt = con.prepareStatement(sql)) {

            preparedStmt.setString(1, volunteer.getFirstname());
            preparedStmt.setString(2, user);

            preparedStmt.executeUpdate();
            System.out.println("First name updated!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        // execute the java preparedstatement
    }

    public void updateLastName(String user, Volunteer volunteer)  {

        String sql = "update volunteers set lastName = ?  where userName = ?  ";


        try (Connection con = this.dbConnect();
             PreparedStatement preparedStmt = con.prepareStatement(sql)) {

            preparedStmt.setString   (1, volunteer.getLastname());
            preparedStmt.setString(2,user);

            preparedStmt.executeUpdate();
            System.out.println("Last name updated!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        // execute the java preparedstatement


    }


    public void updateAddress(String user, Volunteer volunteer)  {

        String sql = "update volunteers set address = ?  where userName = ?  ";


        try (Connection con = this.dbConnect();
             PreparedStatement preparedStmt = con.prepareStatement(sql)) {

            preparedStmt.setString   (1, volunteer.getAddress());
            preparedStmt.setString(2,user);

            preparedStmt.executeUpdate();
            System.out.println("Address updated!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        // execute the java preparedstatement


    }

    public void updatePhoneNbr(String user, Volunteer volunteer)  {

        String sql = "update volunteers set phoneNbr = ?  where userName = ?  ";


        try (Connection con = this.dbConnect();
             PreparedStatement preparedStmt = con.prepareStatement(sql)) {

            preparedStmt.setString   (1, volunteer.getPhoneNbr());
            preparedStmt.setString(2,user);

            preparedStmt.executeUpdate();
            System.out.println("Phone number updated!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        // execute the java preparedstatement

    }


    public ObservableList<User_Has_Events> historyInfo(String userID){

        LocalDate date = LocalDate.now();
        ObservableList<User_Has_Events>eventHistory= FXCollections.observableArrayList();
        String query = "select eventID , history from volunteer_has_events where securityNbr = '" +userID+"' and history < '"+date+"'" ;

        try (Connection connection = this.dbConnect();
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                User_Has_Events history = new User_Has_Events();
                history.setEventID(resultSet.getInt(1));
                history.setHistory(resultSet.getDate(2));

                eventHistory.add(history);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventHistory;
    }
    public ObservableList<Donation> donationInfo(String userID){

        LocalDate date = LocalDate.now();
        ObservableList<Donation>donationHistory= FXCollections.observableArrayList();
        String query = "select donationAmount , donationHistory from donation where securityNbr = '" +userID+"'";

        try (Connection connection = this.dbConnect();
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Donation donation = new Donation();
                donation.setDonation(resultSet.getString(1));
                donation.setDonationDate(resultSet.getDate(2));


                donationHistory.add(donation);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return donationHistory;
    }
    public void registerToAnEvent(String userid , int eventid , Event event , Volunteer volunteer){

        String sql = ""+"INSERT INTO volunteer_has_events(eventID,securityNbr,history) VALUES(?,?,?)";


        try (Connection conn = this.dbConnect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, event.getEventID());
            pstmt.setString(2, volunteer.getSecurtyNbr());
            pstmt.setString(3,event.getEventDate());

            pstmt.executeUpdate();
            System.out.println("Volunteer has registered to an event!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public String getEventDate(int eventid) throws SQLException {
        String date = null;

        String query = "select eventDate from events where eventID = '" +eventid+"'";
        try (Connection connection = this.dbConnect();
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                date = resultSet.getString(1);
            }
        }
        return date ;
    }
    public ObservableList<Event> eventInformation(){
        ObservableList<Event>eventList= FXCollections.observableArrayList();
        String query = "select * from events ";
        try (Connection connection = this.dbConnect();
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Event event = new Event();
                event.setEventID(resultSet.getInt(1));
                event.setEventName(resultSet.getString(2));
                event.setEventDate(resultSet.getDate(3));
                event.setEventTime(resultSet.getString(4));
                event.setEventInfo(resultSet.getString(5));
                event.setEventOrganizer(resultSet.getString(6));
                event.setCountry(resultSet.getString(7));
                event.setCity(resultSet.getString(8));
                eventList.add(event);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventList;
    }

    public ObservableList<Volunteer> volunteerInfo(){
        String roles = "volunteer";
        ObservableList<Volunteer>eventList= FXCollections.observableArrayList();

        String query = "select firstName,lastName,securityNbr,userName,role,email,birthday,address,phoneNbr from volunteers where role = '"+roles+"'";
        try (Connection connection = this.dbConnect();
             Statement statement = connection.createStatement()){
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                Volunteer volunteer = new Volunteer();
                volunteer.setFirstname(resultSet.getString(1));
                volunteer.setLastname(resultSet.getString(2));
                volunteer.setSecurityNbr(resultSet.getString(3));
                volunteer.setUsername(resultSet.getString(4));
                volunteer.setRole(resultSet.getString(5));
                volunteer.setEmail(resultSet.getString(6));
                volunteer.setBirthday(Date.valueOf(resultSet.getString(7)));
                volunteer.setAddress(resultSet.getString(8));
                volunteer.setPhoneNbr(resultSet.getString(9));
                eventList.add(volunteer);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return eventList;
    }




}