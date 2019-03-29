package sample;

import java.util.ArrayList;

/**
 * EVENT BOOKING CLASS
 * This file contains the EventBooking class -- used to create objects containing event info
 *
 * METHODS: getAllEvents
 */

public class EventBooking {
    private String eventName;
    private String eventType;
    private String clientID;
    private String venue;
    private boolean isPrivateEvent;
    private String eventDate; // year-month-day format (2019-12-01 = Dec 1, 2019)
    private int startTime; // maybe start/end times can be strings?
    private int endTime;
    private Double bookingFee;
    private boolean feePaid;

    /**
     * CONSTRUCTOR
     */
    public EventBooking() {
    }

    /**
     * CONSTRUCTOR
     */
    public EventBooking(String eventName, String eventType, String clientID, String venue, boolean isPrivateEvent,
                        String eventDate, int startTime, int endTime, Double bookingFee, boolean feePaid) {
        this.eventName = eventName;
        this.eventType = eventType;
        this.clientID = clientID;
        this.venue = venue;
        this.isPrivateEvent = isPrivateEvent;
        this.eventDate = eventDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.bookingFee = bookingFee;
        this.feePaid = feePaid;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public boolean isPrivateEvent() {
        return isPrivateEvent;
    }

    public void setPrivateEvent(boolean privateEvent) {
        isPrivateEvent = privateEvent;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public Double getBookingFee() {
        return bookingFee;
    }

    public void setBookingFee(Double bookingFee) {
        this.bookingFee = bookingFee;
    }

    public boolean isFeePaid() {
        return feePaid;
    }

    public void setFeePaid(boolean feePaid) {
        this.feePaid = feePaid;
    }

    /**
     * METHOD: getAllEvents
     * RETURNS: ArrayList of EventBookings containing all the events in database
     */
    public static ArrayList<EventBooking> getAllEvents(){
        ArrayList<EventBooking> allEvents = new ArrayList<>();

        // insert code here
        // search database for all events and add data to array list
        // basically the same as the viewBookings method in Client class without the if statement

        return allEvents;
    }

}
