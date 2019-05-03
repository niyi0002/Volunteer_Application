package Model;

public class Event {

    private int eventID;
    private String eventName;
    private String eventDate;
    private String eventTime;
    private String eventInfo;
    private String country;
    private String city;

    public Event(){

    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventDate(String eventDate){
        this.eventDate = eventDate;
    }

    public void setEventTime(String eventTime){
        this.eventTime = eventTime;
    }

    public void setEventInfo(String eventInfo) {
        this.eventInfo = eventInfo;
    }

    public void setCountry(String country){
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getEventID() {
        return eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getEventInfo() { return eventInfo; }

    public String getCountry() {
        return country;
    }

    public String getCity() { return city;

    }
}
