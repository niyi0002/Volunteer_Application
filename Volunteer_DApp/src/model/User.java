package model;


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

public class User {

   // public static void main(String[] args) {
   //

    //1: Try to find out why unique  name generation is wrong
    //   System.out.println("Generated file name: " + new Volunteer().getUniqueFileName("Person.jpeg"));

    // String name = "";
    //System.out.println("Name length: " + name.length());
    //System.out.println(System.currentTimeMillis()); //Prints out unix time stamp in milli seconds from 1979 until now
    //}

    //private Volunteer() {
    //}


    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate birthDay;

    private File userProfile; // this file object we create a default images

    public User(String firstName, String lastName, String phoneNumber, LocalDate birthDay) throws Exception {
        setFirstName(firstName);
        setLastName(lastName);
        setPhoneNumber(phoneNumber);
        setBirthDay(birthDay);
        setUserProfile(new File("./src/images/Person.jpeg"));
    }




    protected User(String firstName, String lastName, String phoneNumber, LocalDate birthDay
                   )throws Exception {


        this(firstName, lastName, phoneNumber, birthDay);
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.birthDay = birthDay;
       // this.userProfile = userProfile;


        setUserProfile(userProfile); // here we are overwriting it with the image we want to set
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

        if (phoneNumber.matches("[2-9]\\d{2}[-.]?\\d{3}[-.]\\d{6}"))   // [-.] represents dash or dot we can use either or not at all
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
        }
        else {
            throw new Exception("AGE NOT ALLOWED");
        }
    }

    public File getUserProfile() {

        return userProfile;
    }

    public void setUserProfile(File userProfile) { // create a link where the file is stored // what we do is copy that file and save it in a unique name

        this.userProfile = userProfile;

    }


    public void copyImageFile() throws IOException {// here we do exception cause the file may not be present

        Path path = userProfile.toPath();  // create a path object

        String uniqueFileName = getUniqueFileName2(userProfile.getName()); // create a unique file name

        //System.out.println(uniqueFileName);
        Path targetPath = Paths.get("./src/images/" + uniqueFileName); // move that file into that directory

        Files.copy(path, targetPath, StandardCopyOption.REPLACE_EXISTING); // copy the file to the new file directory
        //Files.copy(path, targetPath, StandardCopyOption.)
        userProfile = new File(targetPath.toString()); // update the image file to point to the new file

    }

    private String getUniqueFileName2(String oldFileName) {
        long currentTimeMillis = System.currentTimeMillis();
        String uniqName = String.format("%d%s", currentTimeMillis, oldFileName);
        return uniqName;
    }

        /***
         * recieve a string that represents a file name and return a string with a random, unique set of letters prefixed to it
         * */
    private String getUniqueFileName(String oldFileName) { // this method will receive a string which represents a new name ann
        // return String with a random, unique set of letters prefixed to it
        String name;

        SecureRandom rdm = new SecureRandom(); // create a random method

        do { // loop till we have a unique file name
            name = ""; // each time it is wrong reset the string to be empity

            // generate 32 random characters
            for (int count = 1; count <= 32; count++) {
                int nextChar;

                do {
                    nextChar = rdm.nextInt(123); // here we set the values which we can use from the ascii code we set it to be from since those are the one we want to retrieve

//                    System.out.println("NEXT CHAR: " + nextChar);

                } while (!validCharacterValue(nextChar));
                // we will keep picking till we have a valid character

//                System.out.println("NEXT CHAR TO FORMAT: " + nextChar);

                name = String.format("%s%c", name, nextChar); // %s%c this is a way of converting int into a character even though we are ( in away saying take int and read it as char

//                System.out.println("NAME: " + name);
            }

            name += oldFileName;


        } while (!uniqueFileInDirectory(name)); // we keep looping till we have a new name
        return name; // now we know we have a unique name we will return name


    }

    public boolean validCharacterValue(int asciiValue) {
        // here we spacify our name to the following ascii code convention also spacify the valid ASCII character


        // 0-9 = ASCII range 48 to 57
        if (asciiValue >= 48 && asciiValue <= 57)
            return true;
        // A-Z = ASCII range 65 to 90
        if (asciiValue >= 65 && asciiValue <= 90)
            return true;
        // a-z = ASCII range 97 to 122
        if (asciiValue >= 97 && asciiValue <= 122)
            return true;
        // thus above will give us a good information about the naming otherwise return false

        return false;
    }


    public String toString() {

        return String.format("%s %s is %d years old ", firstName, lastName, Period.between(birthDay, LocalDate.now()).getYears());

    }


    public boolean uniqueFileInDirectory(String fileName) { // method search for the file and make sure we have unique file

        File directory = new File("./src/images/"); //create new file and Pass where our image directory is
        File[] dir_contents = directory.listFiles();  //look in to the file directory and create an array for all the files in the directory file

        for (File file : dir_contents) { // check if any of the files has the same name
            if (file.getName().equals(fileName)) // if the file name is not the same return false

                return false;
        }
        return true; // loop all those conditions and if non of them are true return true
    }


      public void insertIntoDB() throws SQLException
     {


     Connection conn = null;
     PreparedStatement preparedStatement = null; // creating a prepaired statement

     try {
     // connecting to the database
     conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ volunteer","Students", "12345678Bam!" ); // five direction of the database and the name of the database

     // create a string that holds the query with? as user inputs
     String sql = "INSERT INTO volunteers (Insert into volunteers (firstName, lastName, phoneNumber, birthday, userProfile)" + " VALUES  (?,?,?,?,? )";
     preparedStatement = conn.prepareStatement(sql);

     Date db = Date.valueOf(birthDay); // convert the sql date into a birthdate!

     preparedStatement.setString(1,firstName); // accept the first name
     preparedStatement.setString(2,lastName); // accept the
     preparedStatement.setString(3,phoneNumber);
     preparedStatement.setDate(4, db);
     preparedStatement.setString(5, userProfile.getName()); // get the name of the file


     preparedStatement.executeUpdate();

     }
     catch (Exception e){
     System.err.println((e.getMessage()));

     }
     finally{ //  makes sure that our database is closed

     if (preparedStatement != null)
     preparedStatement.close();

     if (conn != null)
     conn.close();
     }
     }

}
