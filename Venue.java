package sample;

/**
 * VENUE CLASS
 * This file contains the Venue class -- used to create objects containing venue info
 */

public class Venue {

    private String address;
    private String city;
    private int capacity;
    private Long phoneNum;

    /**
     * no arg constructor
     */
    public Venue() {
    }

    /**
     * constructor
     */
    public Venue(String address, String city, int capacity, Long phoneNum) {
        this.address = address;
        this.city = city;
        this.capacity = capacity;
        this.phoneNum = phoneNum;
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
    public int getCapacity() {
        return capacity;
    }

    /**
     * SET CAPACITY
     */
    public void setCapacity(int capacity) {
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


    private void viewAllEvents(){

    }
}
