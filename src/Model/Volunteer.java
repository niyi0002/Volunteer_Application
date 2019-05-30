package Model;


import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.awt.*;
import java.net.URI;
import java.sql.Date;
import java.time.LocalDate;

public class Volunteer extends User {
    private StringProperty birthday = new SimpleStringProperty(this, "birthday");
    private StringProperty address = new SimpleStringProperty(this, "address");
    private StringProperty balance = new SimpleStringProperty(this, "balance");
    public Volunteer( StringProperty birthday, StringProperty address, StringProperty balance){

        this.birthday = birthday ;
        this.address = address ;
        this.balance= balance;
    }
    public Volunteer(StringProperty securityNbr, StringProperty firstname, StringProperty lastname,
                     StringProperty email, StringProperty password, StringProperty username ,
                     StringProperty birthday , StringProperty role) {

    }
    public Volunteer (){
        super();
    }
    public void setAddress(String address) {
        this.address.set(address);
    }
    public String getAddress() {
        return address.get();
    }
    public String getBirthday() {
        return birthday.get();
    }
    public void setBirthday(Date birthday) {
        this.birthday.set(String.valueOf(birthday));
    }
    public StringProperty birthdayProperty() {
        return birthday;
    }
    public StringProperty addressProperty() {
        return address;
    }

    public String getBalance() {
        return balance.get();
    }

    public StringProperty balanceProperty() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance.set(balance);
    }



    @Override
    public String toString() {
        return  " ID : "+getSecurtyNbr()+ "\n Firstname : " +getFirstname()+ "\n Lastname : "+getLastname()+ "\n Username : " +getUsername()+ "\n Email : " +getEmail()+ "\n Birthday : " +getBirthday()+
                "\n Address : " +getAddress()+ "\n Phone Number : " + getPhoneNbr() + "\n Balance: " + getBalance();
    }


}