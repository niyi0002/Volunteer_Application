package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {
    private IntegerProperty userID  = new SimpleIntegerProperty(this, "userID");
    private StringProperty causeOfEvent = new SimpleStringProperty(this, "causeOfEvent");
    private StringProperty scheduleDate = new SimpleStringProperty(this, "eventDate");
    private StringProperty scheduleTime = new SimpleStringProperty(this, "eventTime");
    private StringProperty country = new SimpleStringProperty(this, "country");
    private StringProperty city = new SimpleStringProperty(this, "city");

    public Schedule(IntegerProperty userID, StringProperty causeOfEvent,
                    StringProperty scheduleDate, StringProperty scheduleTime, StringProperty country, StringProperty city) {
        this.userID = userID;
        this.causeOfEvent = causeOfEvent;
        this.scheduleDate = scheduleDate;
        this.scheduleTime = scheduleTime;
        this.country = country;
        this.city = city;
    }
    public Schedule(int userID, String causeOfEvent, LocalTime eventTIME, LocalDate eventDATE, String country, String city) {
    }


    public int getUserID() {
        return userID.get();
    }

    public IntegerProperty userIDProperty() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID.set(userID);
    }

    public String getCauseOfEvent() {
        return causeOfEvent.get();
    }

    public StringProperty causeOfEventProperty() {
        return causeOfEvent;
    }

    public void setCauseOfEvent(String causeOfEvent) {
        this.causeOfEvent.set(causeOfEvent);
    }

    public String getScheduleDate() {
        return scheduleDate.get();
    }

    public StringProperty scheduleDateProperty() {
        return scheduleDate;
    }

    public void setScheduleDate(String scheduleDate) {
        this.scheduleDate.set(scheduleDate);
    }

    public String getScheduleTime() {
        return scheduleTime.get();
    }

    public StringProperty scheduleTimeProperty() {
        return scheduleTime;
    }

    public void setScheduleTime(String scheduleTime) {
        this.scheduleTime.set(scheduleTime);
    }

    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public String getCity() {
        return city.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public void setCity(String city) {
        this.city.set(city);
    }
}

