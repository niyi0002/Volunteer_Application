package Model;

import java.time.LocalDate;

public class Volunteer extends User{


    public String birthday;
    public String phoneNbr;
    public String address;
    public int volunteerID;


    public Volunteer(String firstName, String lastName, String phoneNumber, int volunteerID, LocalDate birthDay) throws Exception {
        super(firstName, lastName, phoneNumber, birthDay);
    }

    public int getVolunteerID() {
        return volunteerID;
    }

    public void setVolunteerID(int volunteerID) {
        if (volunteerID >= 0)  // make sure it is positive number
            this.volunteerID = volunteerID;
        else
            throw new IllegalArgumentException("Volunteers Must be Greater than 0");

    }

        public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoneNbr() {
        return phoneNbr;
    }

    public void setPhoneNbr(String phoneNbr) {
        this.phoneNbr = phoneNbr;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



}
