package Model;


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

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate birthDay;
    private File imageFile;
    private String userName;
    private String password;

    public User(String firstName, String lastName, String phoneNumber, LocalDate birthDay) throws Exception {
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setBirthDay(birthDay);
        setImageFile(new File("./src/images/Person.jpeg"));
    }

    public User(){}

    public User(String firstName, String lastName, String phoneNumber, LocalDate birthDay, File imageFile, String userName, String password) throws Exception {


        this(firstName, lastName, phoneNumber, birthDay);


        setImageFile(imageFile);
        copyImageFile();
    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }

    public String getPhoneNumber() {

        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {

        if (phoneNumber.matches("[2-9]\\d{2}[-.]?\\d{3}[-.]\\d{6}"))
            this.phoneNumber = phoneNumber;

        else
            throw new IllegalArgumentException("Phone Numbers must be in the pattern NXX-XXX-XXXXXX");

    }

    public LocalDate getBirthDay() {

        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) throws Exception {

        int age = Period.between(birthDay, LocalDate.now()).getYears();

        if (age >= 18 && age <= 70) {
            this.birthDay = birthDay;
        } else {
            throw new Exception("AGE NOT ALLOWED");
        }
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public File getImageFile() {

        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;

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


    public String toString() {

        return String.format("%s %s is %d years old ", firstName, lastName, Period.between(birthDay, LocalDate.now()).getYears());

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