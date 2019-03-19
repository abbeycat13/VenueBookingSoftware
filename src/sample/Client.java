package sample;

import java.sql.*;
import java.util.ArrayList;

/**
 * CLIENT CLASS
 * This file contains the Client class, and methods to control the client's event bookings.
 * METHODS: addToDatabase, bookEvent, cancelBooking, viewBookings
 */

public class Client {

    private String id;
    private String password;
    private String firstName;
    private String lastName;
    private Double phoneNumber; // this should be a Long (not Double) -- to be fixed
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


    /**
     * METHOD: addToDatabase
     * DESCRIPTION: Searches the database for client ID -- if found, updates the data (password and contact
     * info only). If not found, creates a new record with the current client data.
     *
     * Note: works for creating new records. Unsure about updating existing records -- needs testing.
     */
    public void addToDatabase(){
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
                    statement.execute("UPDATE clients SET password = '"+this.getPassword()+"', phone = '"+
                            this.getPhoneNumber()+"', email = '"+this.getEmailAddress()+"' WHERE id = '"+
                            this.getId()+"'");
                }
            }
            // if not found, create a new record
            if (!idExists){
                statement.execute("INSERT INTO clients (id, password, firstName, lastName, phone, email)" +
                        "VALUES ('"+this.getId()+"', '"+this.getPassword()+"', '"+this.getFirstName()+ "', '"+
                        this.getLastName()+"', '"+this.getPhoneNumber()+"', '"+this.getEmailAddress()+"')");
            }
            statement.close();
            conn.close();
        } catch (SQLException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * METHOD: viewBookings
     * DESCRIPTION: Searches database for all of a client's bookings and stores them in an ArrayList.
     * Should be called when user clicks the 'View Bookings' button.
     * RETURNS: ArrayList of EventBookings
     */
    public ArrayList<EventBooking> viewBookings(){
        ArrayList<EventBooking> clientBookings = new ArrayList<EventBooking>();
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS events (name TEXT, type TEXT, clientID TEXT, " +
                    "venue TEXT, privateEvent INTEGER, date TEXT, startTime INTEGER, endTime INTEGER, fee REAL, " +
                    "feePaid INTEGER)");
            // search event database for client ID
            ResultSet rs = statement.executeQuery("SELECT * FROM events ORDER BY date ASC");
            while (rs.next()) {
                if (this.getId().equals(rs.getString("clientID")))
                {
                    // convert booleans
                    boolean privateEvent = false;
                    if (rs.getInt("privateEvent") == 1)
                        privateEvent = true;
                    boolean feePaid = false;
                    if (rs.getInt("feePaid") == 1)
                        feePaid = true;
                    // instantiate an event from data and add to arraylist
                    clientBookings.add(new EventBooking(rs.getString("name"), rs.getString("type"),
                            rs.getString("clientID"), rs.getString("venue"), privateEvent,
                            rs.getString("date"), rs.getInt("startTime"),
                            rs.getInt("endTime"), rs.getDouble("fee"), feePaid));
                }
            }
            statement.close();
            conn.close();
        } catch (SQLException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return clientBookings;
    }

    /**
     * METHOD: bookEvent
     * DESCRIPTION: This method should receive the user's input somehow (maybe it takes an EventBooking as a
     * parameter), then check the database to ensure that the venue is available on the selected date. If so,
     * add new event data to event database. Else, allow the user to try again somehow.
     * RETURNS: void? (probably -- or a string that will be shown on the UI)
     *
     * Note: call this method after user has filled out form with event details and attempts to book the event,
     * NOT immediately when they press the 'Book Event' button at the top of the screen
     */
    public void bookEvent(EventBooking event){

        // insert code here

    }

    /**
     * METHOD: cancelBooking
     * DESCRIPTION: This method should perform the mechanics of cancelling a client's booking. Probably accepts an
     * EventBooking as parameter. It should remove the particular event from the database.
     * RETURNS: void? (probably -- or a string that will be shown on the UI)
     *
     * Note: should be called after user has selected which booking they would like to cancel, NOT immediately when
     * they press the 'Cancel Booking' button at the top of the screen
     */
    public void cancelBooking(EventBooking event){

        // insert code here

    }
}
