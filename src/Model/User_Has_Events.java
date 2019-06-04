package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

public class User_Has_Events {
    private IntegerProperty ID = new SimpleIntegerProperty(this, "ID");
    private IntegerProperty eventID = new SimpleIntegerProperty(this, "eventID");
    private StringProperty idinformation = new SimpleStringProperty(this, "userID");
    private StringProperty history = new SimpleStringProperty(this, "history");
    private StringProperty country = new SimpleStringProperty(this, "country");
    private StringProperty city = new SimpleStringProperty(this, "city");
    private StringProperty eventTime = new SimpleStringProperty(this, "eventTime");
    private StringProperty eventName = new SimpleStringProperty(this, "eventName");







    public User_Has_Events(IntegerProperty ID,IntegerProperty eventID ,StringProperty idinformation ,
                           StringProperty history, StringProperty country, StringProperty city, StringProperty eventTime,
                           StringProperty eventName){
        this.eventID = eventID;
        this.idinformation = idinformation;
        this.history = history;
        this.country = country;
        this.city = city;
        this.eventName = eventName;
        this.eventTime = eventTime;
        this.ID = ID;
    }
    public User_Has_Events(){}

    public void setEventID(int eventID) {
        this.eventID.set(eventID);
    }

    public void setIdinformation(String idinformation) {
        this.idinformation.set(idinformation);
    }

    public void setHistory(Date history) {
        this.history.set(String.valueOf(history));
    }

    public int getEventID() {
        return eventID.get();
    }

    public String getIdinformation() {
        return idinformation.get();
    }

    public String getHistory() {
        return history.get();
    }

    public IntegerProperty eventIDProperty() {
        return eventID;
    }

    public StringProperty idinformationProperty() {
        return idinformation;
    }

    public StringProperty historyProperty() {
        return history;
    }

    public void setEventName(String eventName) {
        this.eventName.set(eventName);
    }


    public void setEventTime(String eventTime) {
        this.eventTime.set(eventTime);
    }


    public void setCountry(String country) {
        this.country.set(country);
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public void setID(int ID) {
        this.ID.set(ID);
    }

    public int getID() {
        return ID.get();
    }

    public String getCountry(){
        return country.get();
    }

    public String getCity(){
        return city.get();
    }

    public String getEventName() {
        return eventName.get();
    }

    public String getEventTime() {
        return eventTime.get();
    }

    public StringProperty cityProperty() {
        return city;
    }

    public StringProperty countryProperty() {
        return country;
    }

    public StringProperty eventNameProperty() {
        return eventName;
    }

    public StringProperty eventTimeProperty() {
        return eventTime;
    }

    public IntegerProperty IDProperty() {
        return ID;
    }
}

