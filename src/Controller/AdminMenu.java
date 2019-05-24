package Controller;

import javafx.collections.FXCollections;
import sample.DatabaseConnection;
import Model.Event;
import Model.Volunteer;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import sample.ChangeScene;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;

public class AdminMenu implements Initializable {

    @FXML
    private Button goBack;

    @FXML
    private GridPane gridPane;

    @FXML
    private DatePicker eventDate;

    @FXML
    private Label cityLabel;

    @FXML
    private Label countryLabel;

    @FXML
    private Label organizerLabel;

    @FXML
    private Label informationLabel;

    @FXML
    private Label dateLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private TextField city;

    @FXML
    private TextField country;

    @FXML
    private TextField eventInfo;

    @FXML
    private TextField organizer;

    @FXML
    private TextField eventTime;

    @FXML
    private TextField eventName;

    @FXML
    private Label timeLabel;

    @FXML
    private Button addEvent;

    @FXML
    private TableView<Event> table;

    @FXML
    private TableColumn<Event, Integer> eventId;

    @FXML
    private TableColumn<Event, String> eventName1;

    @FXML
    private TableColumn<Event, String> date;

    @FXML
    private TableColumn<Event, String> time;

    @FXML
    private TableColumn<Event, String> info;

    @FXML
    private TableColumn<Event, String> organizer1;

    @FXML
    private TableColumn<Event, String> country1;

    @FXML
    private TableColumn<Event, String> city1;
    @FXML
    private TextField enterID;
    @FXML
    private TextField search;

    @FXML
    private Label displayRow;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField txtFrom;

    @FXML
    private TextField txtTo;

    @FXML
    private TextArea txtMessage;

    @FXML
    private Button buttonSend;

    @FXML
    private Button signOut;

    @FXML
    private Button helpButton;


    @FXML
    private TableView<Volunteer> table1;

    @FXML
    private TableColumn<Volunteer, String> volunteer_name;

    @FXML
    private TableColumn<Volunteer, String> volunteer_lastname;

    @FXML
    private TextField searchVolunteer;


    private DatabaseConnection db = new DatabaseConnection();
    private Event event1 = new Event();
    private Volunteer volunteer = new Volunteer();
    private ChangeScene cs = new ChangeScene();
    private static String ourEmail = "VolunteerApplication123";  // GMail user name (just the part before "@gmail.com")
    private static String ourEmailsPassword = "volunteerapp"; // GMail password (Maybe make one just for this project team)
    private static String emailTitle = "Volunteer Application"; // Add the title of the e-mail here.
    private ObservableList<Volunteer> userList = db.volunteerInfo();
    private ObservableList<Event> eventObservableList = db.eventInformation();
    private ObservableList<Event> eventObservableListNew = eventInformation1();
    @FXML
    void handleAddEvent(ActionEvent event) {

        java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(eventDate.getValue());
        event1.setEventName(eventName.getText());
        event1.setEventDate(gettedDatePickerDate);
        event1.setEventTime(eventTime.getText());
        event1.setEventInfo(eventInfo.getText());
        event1.setEventOrganizer(organizer.getText());
        event1.setCountry(country.getText());
        event1.setCity(city.getText());
        db.insertEvent(event1);

    }


    @FXML
    void handleHelpButton(ActionEvent event) {

    }
    public void  dsjbgekd(){
        this.eventId.setCellValueFactory(new PropertyValueFactory("eventID"));
        this.eventName1.setCellValueFactory(new PropertyValueFactory("eventName"));
        this.date.setCellValueFactory(new PropertyValueFactory("eventDate"));
        this.time.setCellValueFactory(new PropertyValueFactory("eventTime"));
        this.info.setCellValueFactory(new PropertyValueFactory("eventInfo"));
        this.organizer1.setCellValueFactory(new PropertyValueFactory("eventOrganizer"));
        this.country1.setCellValueFactory(new PropertyValueFactory("country"));
        this.city1.setCellValueFactory(new PropertyValueFactory("city"));
        this.table.setItems(eventObservableListNew);

    }

    @FXML
    void sendMessage(ActionEvent event) {
        String reci = txtTo.getText().toString();
        String mess = txtMessage.getText().toString();

        String recipientEmailString = reci;
        String messageToBeSent = mess;


        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", ourEmail);
        props.put("mail.smtp.password", ourEmailsPassword);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        Message message = new MimeMessage(session);

        String[] recipientEmail = new String[]{
                recipientEmailString}; //Change the String into a String[]
        try {
            message.setFrom(new InternetAddress(ourEmail));

            InternetAddress[] toAddress = new InternetAddress[recipientEmail.length];
            // To get the array of addresses
            for (int i = 0; i < recipientEmail.length; i++) {
                toAddress[i] = new InternetAddress(recipientEmail[i]);
            }

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(emailTitle);
            message.setText(messageToBeSent);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, ourEmail, ourEmailsPassword);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
        System.out.println("email sent");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        Tooltip tooltip = new Tooltip();
        tooltip.setText("To edit events, click on the field you wish to change.");
        helpButton.setTooltip(tooltip);

        Tooltip tooltip1 = new Tooltip();
        tooltip1.setText("You can search events by their name.");
        search.setTooltip(tooltip1);

        Tooltip tooltip2 = new Tooltip();
        tooltip2.setText("Type in recipient's email address.");
        txtTo.setTooltip(tooltip2);

        comboBox.getItems().add("Ascending order");
        comboBox.getItems().add("Descending order");
        eventId.setVisible(false);

        this.eventId.setCellValueFactory(new PropertyValueFactory("eventID"));
        this.eventName1.setCellValueFactory(new PropertyValueFactory("eventName"));
        this.date.setCellValueFactory(new PropertyValueFactory("eventDate"));
        this.time.setCellValueFactory(new PropertyValueFactory("eventTime"));
        this.info.setCellValueFactory(new PropertyValueFactory("eventInfo"));
        this.organizer1.setCellValueFactory(new PropertyValueFactory("eventOrganizer"));
        this.country1.setCellValueFactory(new PropertyValueFactory("country"));
        this.city1.setCellValueFactory(new PropertyValueFactory("city"));

        editEvent();

        this.table.setItems(eventObservableList);
        this.table.setEditable(true);
        FilteredList<Event> filteredData = new FilteredList<>(eventObservableList, p -> true);
        search.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(event -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (event.getEventName().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (event.getCountry().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });
        SortedList<Event> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(table.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table.setItems(sortedData);

        comboBox.setOnAction((e) -> {
            String choice = comboBox.getSelectionModel().getSelectedItem();
            if (choice == "Ascending order") {
                eventName1.setSortType(TableColumn.SortType.ASCENDING);
                eventId.setSortType(TableColumn.SortType.ASCENDING);

                table.setItems(sortedData);
                table.getSortOrder().add(eventName1);

            } else if (choice == "Descending order") {
                eventName1.setSortType(TableColumn.SortType.DESCENDING);
                eventId.setSortType(TableColumn.SortType.DESCENDING);

                table.setItems(sortedData);
                table.getSortOrder().add(eventName1);
            }
        });



        ////////////////// view volunteer

        this.volunteer_name.setCellValueFactory(new PropertyValueFactory("firstname"));
        this.volunteer_lastname.setCellValueFactory(new PropertyValueFactory("lastname"));
        this.table1.setItems(userList);
        table1.setOnMouseClicked((MouseEvent event) -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                int index = table1.getSelectionModel().getSelectedIndex();
                volunteer = table1.getItems().get(index);
                popUpBox(volunteer);

            }
        });

         searchUser();



    }


    public void popUpBox(Volunteer volunteer) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Volunteer Information");
        alert.setHeaderText(String.valueOf(volunteer));
        alert.setContentText("Choose your option.");

        ButtonType buttonTypeOne = new ButtonType("Delete");
        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeCancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne) {
            // ... user chose "One"
            db.deleteVolunteer(volunteer.getUsername());
            table1.refresh();
        } else {
            // ... user chose CANCEL or closed the dialog
        }
    }



    public void editEvent() {
        eventName1.setCellFactory(TextFieldTableCell.forTableColumn());
        eventName1.setOnEditCommit(event -> {
            Event event2 = event.getRowValue();
            event2.setEventName(event.getNewValue());
            db.updateEventName(event.getNewValue(), event.getRowValue().getEventID());
        });

        // date.setCellFactory(TextFieldTableCell.forTableColumn());
        date.setCellFactory(TextFieldTableCell.forTableColumn());
        date.setOnEditCommit(event -> {
            Event event2 = event.getRowValue();
            event2.setEventDate(Date.valueOf(event.getNewValue()));
            db.updateEventDate(Date.valueOf(event.getNewValue()), event.getRowValue().getEventID());
        });

        time.setCellFactory(TextFieldTableCell.forTableColumn());
        time.setOnEditCommit(event -> {
            Event event2 = event.getRowValue();
            event2.setEventTime(event.getNewValue());
            db.updateEventTime(event.getNewValue(), event.getRowValue().getEventID());
        });
        info.setCellFactory(TextFieldTableCell.forTableColumn());
        info.setOnEditCommit(event -> {
            Event event2 = event.getRowValue();
            event2.setEventInfo(event.getNewValue());
            db.updateEventInfo(event.getNewValue(), event.getRowValue().getEventID());
        });
        organizer1.setCellFactory(TextFieldTableCell.forTableColumn());
        organizer1.setOnEditCommit(event -> {
            Event event2 = event.getRowValue();
            event2.setEventOrganizer(event.getNewValue());
            db.updateEventOrganizer(event.getNewValue(), event.getRowValue().getEventID());
        });
        country1.setCellFactory(TextFieldTableCell.forTableColumn());
        country1.setOnEditCommit(event -> {
            Event event2 = event.getRowValue();
            event2.setCountry(event.getNewValue());
            db.updateCountry(event.getNewValue(), event.getRowValue().getEventID());
        });
        city1.setCellFactory(TextFieldTableCell.forTableColumn());
        city1.setOnEditCommit(event -> {
            Event event2 = event.getRowValue();
            event2.setCity(event.getNewValue());
            db.updateCity(event.getNewValue(), event.getRowValue().getEventID());
        });

    }

    @FXML
    private void handleSignOut(ActionEvent event) throws IOException {
        cs.sceneHandler("../View/DefaultPage.fxml", event);
    }

    public void searchUser() {

// 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Volunteer> filteredData1 = new FilteredList<>(userList, v -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        searchVolunteer.textProperty().addListener((observable1, oldValue1, newValue1) -> {
            filteredData1.setPredicate(volunteer -> {
                // If filter text is empty, display all persons.
                if (newValue1 == null || newValue1.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue1.toLowerCase();

                if (volunteer.getFirstname().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (volunteer.getLastname().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }
                return false; // Does not match.
            });
        });

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Volunteer> sortedData1 = new SortedList<>(filteredData1);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData1.comparatorProperty().bind(table1.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        table1.setItems(sortedData1);
    }
    public ObservableList<Event> eventInformation1(){
        ObservableList<Event>eventList= FXCollections.observableArrayList();
        String query = "select * from events ";
        try (Connection connection = db.dbConnect();
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
}