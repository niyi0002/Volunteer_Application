package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

public class User_Has_Events {
    private IntegerProperty eventID = new SimpleIntegerProperty(this, "eventID");
    private StringProperty idinformation = new SimpleStringProperty(this, "userID");
    private StringProperty history = new SimpleStringProperty(this, "history");



    public User_Has_Events(IntegerProperty eventID ,StringProperty idinformation ,StringProperty history){
        this.eventID = eventID;
        this.idinformation = idinformation;
        this.history = history;
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
}
