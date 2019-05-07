package Model;



import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.SecureRandom;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;

public abstract class User {


    private File imageFile;
    private StringProperty id = new SimpleStringProperty(this, "idinformation");
    private StringProperty firstName = new SimpleStringProperty(this, "firstname");
    private StringProperty lastName = new SimpleStringProperty(this, "lastname");
    private StringProperty email = new SimpleStringProperty(this, "email");
    private StringProperty userName = new SimpleStringProperty(this, "username");
    private StringProperty password = new SimpleStringProperty(this,"password");
    private StringProperty address = new SimpleStringProperty(this, "address");
    private StringProperty phoneNbr = new SimpleStringProperty(this,"phoneNbr");
    private StringProperty role = new SimpleStringProperty(this,"role");

    public User(StringProperty idinformation ,StringProperty firstname ,StringProperty lastname , StringProperty email
            , StringProperty password , StringProperty username, StringProperty address , StringProperty phoneNbr ,StringProperty role){
        this.id = idinformation;
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.userName = username;
        this.password = password ;
        this.address = address ;
        this.phoneNbr = phoneNbr ;
        this.role = role ;

    }


    public User() {

    }

    public void setIdinformation(String idinformation) {
        this.id.set(idinformation);
    }

    public void setFirstname(String firstname) {
        this.firstName.set(firstname);
    }

    public void setLastname(String lastname) {
        this.lastName.set(lastname);
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public void setUsername(String username) {
        this.userName.set(username);
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public void setPhoneNbr(String phoneNbr) {
        this.phoneNbr.set(phoneNbr);
    }

    public void setRole(String role) {
        this.role.set(role);
    }

    public String getIdinformation() {
        return id.get();
    }

    public String getFirstname() {
        return firstName.get();
    }

    public String getLastname() {
        return lastName.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getUsername() {
        return userName.get();
    }

    public String getPassword() {
        return password.get();
    }

    public String getAddress() {
        return address.get();
    }

    public String getPhoneNbr() {
        return phoneNbr.get();
    }

    public String getRole() {
        return role.get();
    }








    public File getImageFile() {

        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;

    }
    public StringProperty idinformationProperty() {
        return id;
    }

    public StringProperty firstnameProperty() {
        return firstName;
    }

    public StringProperty lastnameProperty() {
        return lastName;
    }

    public StringProperty emailProperty() {
        return email;
    }

    public StringProperty usernameProperty() {
        return userName;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public StringProperty phoneNbrProperty() {
        return phoneNbr;
    }

    public StringProperty roleProperty() {
        return role;
    }

    public void copyImageFile() throws IOException {

        Path path = imageFile.toPath();

        String uniqueFileName = getUniqueFileName2(imageFile.getName());

        Path targetPath = Paths.get("./src/images/" + uniqueFileName);

        Files.copy(path, targetPath, StandardCopyOption.REPLACE_EXISTING);
        imageFile = new File(targetPath.toString());

    }

    private String getUniqueFileName2(String oldFileName) {
        long currentTimeMillis = System.currentTimeMillis();
        String uniqName = String.format("%d%s", currentTimeMillis, oldFileName);
        return uniqName;
    }

    /***
     * recieve a string that represents a file name and return a string with a random, unique set of letters prefixed to it
     * */
    private String getUniqueFileName(String oldFileName) {
        String name;

        SecureRandom rdm = new SecureRandom();

        do {
            name = "";

            for (int count = 1; count <= 32; count++) {
                int nextChar;

                do {
                    nextChar = rdm.nextInt(123);

//                    System.out.println("NEXT CHAR: " + nextChar);

                } while (!validCharacterValue(nextChar));

//                System.out.println("NEXT CHAR TO FORMAT: " + nextChar);

                name = String.format("%s%c", name, nextChar);
//                System.out.println("NAME: " + name);
            }

            name += oldFileName;


        } while (!uniqueFileInDirectory(name));
        return name;


    }

    public boolean validCharacterValue(int asciiValue) {


        if (asciiValue >= 48 && asciiValue <= 57)
            return true;
        if (asciiValue >= 65 && asciiValue <= 90)
            return true;
        if (asciiValue >= 97 && asciiValue <= 122)
            return true;

        return false;
    }




    public boolean uniqueFileInDirectory(String fileName) {
        File directory = new File("./src/images/");
        File[] dir_contents = directory.listFiles();
        for (File file : dir_contents) {
            if (file.getName().equals(fileName))

                return false;
        }
        return true;
    }

}

