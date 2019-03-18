package sample;

import java.sql.*;

public class Client {

    private String id;
    private String password;
    private String firstName;
    private String lastName;
    private int phoneNumber;
    private String emailAddress;

    /**
     *  CONSTRUCTOR
     */
    public Client() {
    }

    /**
     *  CONSTRUCTOR
     */
    public Client(String id, String password, String firstName, String lastName, int phoneNumber, String emailAddress) {
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
     */
    public void setId(String id) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:clients.db");
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS clients (id TEXT, password TEXT, firstName TEXT, lastName TEXT, phone INTEGER, email TEXT)");

            // search database for client ID
            ResultSet rs = statement.executeQuery("SELECT * FROM clients");
            boolean idExists = false;
            while (rs.next() && !idExists) { // searches until ID is found or there are no more records
                if (id.equals(rs.getString("id")))
                {
                    this.id = null;
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
    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * SET PHONE NUMBER
     */
    public void setPhoneNumber(int phoneNumber) {
        if (phoneNumber < 1000000000 && phoneNumber > 99999999)
            this.phoneNumber = phoneNumber;
        else
            this.phoneNumber = 0;
    }

    /**
     * GET EMAIL ADDRESS
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     * SET EMAIL ADDRESS
     */
    public void setEmailAddress(String emailAddress) {
        boolean foundAt = false;
        boolean foundDot = false;
        int j = 0;
        for (int i = 0; i < emailAddress.length(); ++i){
            if (emailAddress.charAt(i) == '@') {
                foundAt = true;
                j = i;
            }
            if (foundAt && i > j && emailAddress.charAt(i) == '.')
                foundDot = true;
        }
        if (foundAt && foundDot)
            this.emailAddress = emailAddress;
        else
            this.emailAddress = null;
    }

    public void ViewBookings(){
    }

    public void BookEvent(){

    }

    public void CancelBooking(){

    }
}
