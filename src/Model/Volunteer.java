package Model;

import java.time.LocalDate;

public class Volunteer extends User {
    public String birthday;
    public String phoneNbr;
    public String address;
    public Volunteer(String firstName, String lastName, String phoneNumber, LocalDate birthDay) throws Exception {
            super(firstName, lastName, phoneNumber, birthDay);
        }
    public Volunteer() {
            super();
        }

        public String getBirthday () {
            return birthday;
        }

        public void setBirthday (String birthday){
            this.birthday = birthday;
        }

        public String getPhoneNbr () {
            return phoneNbr;
        }

        public void setPhoneNbr (String phoneNbr){
            this.phoneNbr = phoneNbr;

        }


}