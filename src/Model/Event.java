package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

public class Event {

    private IntegerProperty eventID = new SimpleIntegerProperty(this, "eventID");
    private StringProperty eventName = new SimpleStringProperty(this, "eventName");
    private StringProperty eventDate = new SimpleStringProperty(this, "eventDate");
    private StringProperty eventTime = new SimpleStringProperty(this, "eventTime");
    private StringProperty eventInfo = new SimpleStringProperty(this, "eventInfo");
    private StringProperty eventOrganizer = new SimpleStringProperty(this, "eventOrganizer");
    private StringProperty country = new SimpleStringProperty(this, "country");
    private StringProperty city = new SimpleStringProperty(this, "city");
    private StringProperty link = new SimpleStringProperty(this, "link");



    public Event(IntegerProperty eventID, StringProperty eventName, StringProperty eventDate, StringProperty eventTime
            , StringProperty eventInfo, StringProperty eventOrganizer, StringProperty country, StringProperty city,StringProperty link) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventInfo = eventInfo;
        this.eventOrganizer = eventOrganizer;
        this.country = country;
        this.city = city;
        this.link = link;

    }

    public String getLink() {
        return link.get();
    }

    public StringProperty linkProperty() {
        return link;
    }

    public Event() {
    }

    public void setEventName(String eventName) {
        this.eventName.set(eventName);
    }

    public void setEventID(int eventID) {
        this.eventID.set(eventID);
    }

    public void setEventDate(Date eventDate) {
        this.eventDate.set(String.valueOf(eventDate));
    }

    public void setEventTime(String eventTime) {
        this.eventTime.set(eventTime);
    }

    public void setEventInfo(String eventInfo) {
        this.eventInfo.set(eventInfo);
    }

    public void setEventOrganizer(String eventOrganizer) {
        this.eventOrganizer.set(eventOrganizer);
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public void setCity(String link) {
        this.city.set(link);
    }

    public void setLink(String link) {
        this.link.set(link);
    }



    public int getEventID() {
        return eventID.get();
    }

    public String getEventName() {
        return eventName.get();
    }

    public String getEventDate() {
        return eventDate.get();
    }

    public String getEventTime() {
        return eventTime.get();
    }

    public String getEventInfo() {
        return eventInfo.get();
    }

    public String getEventOrganizer() {
        return eventOrganizer.get();
    }

    public String getCountry() {
        return country.get();
    }

    public String getCity() {
        return city.get();
    }

    public String getEvent(){ return  link.get(); }

    public IntegerProperty eventIDProperty() {
        return eventID;
    }

    public StringProperty eventNameProperty() {
        return eventName;
    }

    public StringProperty eventDateProperty() {
        return eventDate;
    }

    public StringProperty eventTimeProperty() {
        return eventTime;
    }

    public StringProperty eventInfoProperty() {
        return eventInfo;
    }

    public StringProperty eventOrganizerProperty() {
        return eventOrganizer;
    }

    public StringProperty countryProperty() {
        return country;
    }

    public StringProperty cityProperty() {
        return city;
    }


}