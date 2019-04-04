package sample;

import java.sql.*;
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
    private Long phoneNum;
    private Integer capacity;

    /**
     * no arg constructor
     */
    public Venue() {
    }

    /**
     * constructor
     */
    public Venue(String name, String address, String city, Long phoneNum, Integer capacity) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.phoneNum = phoneNum;
        this.capacity = capacity;
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
     *
     * RETURNS: ArrayList of Venues containing all the venues in database
     */
    public static ArrayList<Venue> getAllVenues(){
        ArrayList<Venue> allVenues = new ArrayList<>();
        try{
            Connection conn = DriverManager.getConnection("jdbc:sqlite:database.db");
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS venues (name TEXT, address TEXT, city TEXT, " +
                    "phone INTEGER, capacity INTEGER)");
            // search database for all venues
            ResultSet rs = statement.executeQuery("SELECT * FROM venues");
            while (rs.next()) {
                // instantiate a venue from data and add to arraylist
                allVenues.add(new Venue(rs.getString("name"), rs.getString("address"),
                        rs.getString("city"), rs.getLong("phone"),
                        rs.getInt("capacity")));
            }
            statement.close();
            conn.close();
        } catch (SQLException e){
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return allVenues;
    }
}
