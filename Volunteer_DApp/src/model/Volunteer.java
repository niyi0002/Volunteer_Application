package model;

import java.time.LocalDate;

public class Volunteer extends User{
   private  int volunteerID;

    public Volunteer(String firstName, String lastName, String phoneNumber, LocalDate birthDay) throws Exception {
        super(firstName, lastName, phoneNumber, birthDay);
        this.volunteerID = volunteerID;
    }


    public int getVolunteerID() {
        return volunteerID;
    }

    public void setVolunteerID(int volunteerID) {
        if(volunteerID >= 0)  // make sure it is positive number
        this.volunteerID = volunteerID;
        else
            throw new IllegalArgumentException("Volunteers Must be Greater than 0");


    }
}
