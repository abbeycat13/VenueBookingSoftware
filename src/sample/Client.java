package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.sql.*;

public class Client {

    private String id;
    private String password;
    private String firstName;
    private String lastName;
    private Double phoneNumber;
    private String emailAddress;

    /**
     *  CONSTRUCTOR
     */
    public Client() {
    }

    /**
     *  CONSTRUCTOR
     */
    public Client(String id, String password, String firstName, String lastName, Double phoneNumber,
                  String emailAddress) {
        this.id = id;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    /**
     * GET ID
     */
    public String getId() {
        return id;
    }

    /**
     * SET ID
     * Note: Checks the client database to ensure that the ID is
     * not already in use -- if it is, sets ID to "INVALID"
     */
    public void setId(String id) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS clients (id TEXT, password TEXT, firstName TEXT, " +
                    "lastName TEXT, phone INTEGER, email TEXT)");
            // search database for client ID
            ResultSet rs = statement.executeQuery("SELECT * FROM clients");
            boolean idExists = false;
            while (rs.next() && !idExists) { // searches until ID is found or there are no more records
                if (id.equals(rs.getString("id")))
                {
                    this.id = "INVALID";
                    idExists = true;
                }
            }
            // if not found, set ID
            if (!idExists)
                this.id = id;
            statement.close();
            conn.close();
        } catch (SQLException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * GET PASSWORD
     */
    public String getPassword() {
        return password;
    }

    /**
     * SET PASSWORD
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * GET FIRST NAME
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * SET FIRST NAME
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * GET LAST NAME
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * SET LAST NAME
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * GET PHONE NUMBER
     */
    public Double getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * SET PHONE NUMBER
     * Note: Removes all non-digits from input string before parsing to Double,
     * and ensuring that the number is exactly 10 digits -- if invalid, sets to 0.0
     */
    public void setPhoneNumber(String input) {
        String digits = "";
        Double phoneNumber = 0.0;
        for (int i = 0; i < input.length(); ++i)
        {
            if (Character.isDigit(input.charAt(i)))
                digits += input.charAt(i);
        }
        phoneNumber = phoneNumber.parseDouble(digits);
        if (phoneNumber < 10000000000.0 && phoneNumber > 999999999.0)
            this.phoneNumber = phoneNumber;
        else
            this.phoneNumber = 0.0;
    }

    /**
     * GET EMAIL ADDRESS
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * SET EMAIL ADDRESS
     * Note: Checks that input follows correct email address format: text@text.text --
     * if invalid, sets to "INVALID"
     */
    public void setEmailAddress(String emailAddress) {
        boolean foundAt = false;
        boolean foundDot = false;
        int j = 0;
        for (int i = 0; i < emailAddress.length(); ++i){
            // check if @ character is found anywhere except the first character
            if (emailAddress.charAt(i) == '@' && i > 0) {
                foundAt = true;
                j = i; // record where @ was found
            }
            // check if . is found after @ and before the end of the input string
            if (foundAt && i > j && emailAddress.charAt(i) == '.' && i < (emailAddress.length() - 1))
                foundDot = true;
        }
        if (foundAt && foundDot)
            this.emailAddress = emailAddress;
        else
            this.emailAddress = "INVALID";
    }



    public void addToDatabase(){

    }

    public void viewBookings(){
    }

    public void bookEvent(){

    }

    public void cancelBooking(){

    }
}
