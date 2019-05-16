package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

public class Volunteer extends User {
    private StringProperty birthday = new SimpleStringProperty(this, "birthday");
    private StringProperty address = new SimpleStringProperty(this, "address");
    public Volunteer( StringProperty birthday, StringProperty address){

        super(resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("phoneNumber"), resultSet.getDate("birthday").toLocalDate());
        this.birthday = birthday ;
        this.address = address ;
    }
    public Volunteer(StringProperty securityNbr, StringProperty firstname, StringProperty lastname,
                     StringProperty email, StringProperty password, StringProperty username ,
                     StringProperty birthday , StringProperty role) {

        super(resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("phoneNumber"), resultSet.getDate("birthday").toLocalDate());
    }
    public Volunteer (){super(resultSet.getString("firstName"), resultSet.getString("lastName"), resultSet.getString("phoneNumber"), resultSet.getDate("birthday").toLocalDate());}
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


}