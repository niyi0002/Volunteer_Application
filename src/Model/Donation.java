package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.Date;

public class Donation {
    private StringProperty donation = new SimpleStringProperty(this, "donation");
    private StringProperty donationDate = new SimpleStringProperty(this, "donationDate");

    public Donation(StringProperty donation , StringProperty donationDate ){
        this.donation = donation ;
        this.donationDate = donationDate ;
    }

    public Donation(){}

    public String getDonation() {
        return donation.get();
    }

    public String getDonationDate() {
        return donationDate.get();
    }
    public void setDonation(String donation) {
        this.donation.set(donation);
    }

    public void setDonationDate(Date donationDate) {
        this.donationDate.set(String.valueOf(donationDate));
    }

    public StringProperty donationProperty() {
        return donation;
    }

    public StringProperty donationDateProperty() {
        return donationDate;
    }
}
