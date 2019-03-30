package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * VENUE CLASS
 * This file contains the Venue class -- used to create objects containing venue info
 *
 * METHODS: getAllVenues
 */

public class Venue {

    private String name;
    private String address;
    private String city;
    private Integer capacity;
    private Long phoneNum; // probably don't need this

    /**
     * no arg constructor
     */
    public Venue() {
    }

    /**
     * constructor
     */
    public Venue(String name, String address, String city, Integer capacity, Long phoneNum) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.capacity = capacity;
        this.phoneNum = phoneNum;
    }

    /**
     * GET name
     */
    public String getName() {
        return name;
    }

    /**
     * SET name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * GET address
     */
    public String getAddress() {
        return address;
    }

    /**
     * SET address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * GET CITY
     */
    public String getCity() {
        return city;
    }

    /**
     * SET CITY
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * GET CAPACITY
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * SET CAPACITY
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * GET PHONE NUM
     */
    public Long getPhoneNum() {
        return phoneNum;
    }

    /**
     * SET PHONE NUM
     */
    public void setPhoneNum(Long phoneNum) {
        this.phoneNum = phoneNum;
    }

    /**
     * METHOD: getAllVenues
     * RETURNS: ArrayList of Venues containing all the venues in database
     */
    public static ArrayList<Venue> getAllVenues(){
        ArrayList<Venue> allVenues = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS venues (name TEXT, address TEXT, city TEXT, " +
                    "phone INTEGER, capacity INTEGER");

            // TO DO !!
            // search database for all venues and add data to array list


            statement.close();
            conn.close();
        } catch (SQLException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return allVenues;
    }
}
