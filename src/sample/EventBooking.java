package sample;

import java.util.ArrayList;

/**
 * EVENT BOOKING CLASS
 * This file contains the EventBooking class -- used to create objects containing event info
 *
 * METHODS: getAllEvents, toString, calcFee
 */

public class EventBooking {
    private String eventName;
    private String eventType;
    private String clientID;
    private String venue;
    private boolean isPrivateEvent;
    private String eventDate; // year-month-day format (2019-12-01 = Dec 1, 2019)
    private String startTime; // XX:XXAM/PM format (e.g. 11:00PM)
    private String endTime;
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
                        String eventDate, String startTime, String endTime, Double bookingFee, boolean feePaid) {
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
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

        // TO DO !!
        // search database for all events and add data to array list
        // basically the same as the viewBookings method in Client class without the if statement

        return allEvents;
    }

    /**
     * METHOD: toString
     * RETURNS: String - "Event Name on Date"
     *
     * NOTE: Used with booking cancellation method
     */
    public String toString(){
        return this.eventName + " on " + this.eventDate;
    }

    public Double calcFee(Venue venue){
        Double fee = 0.0;


        String digits = "";
        Double start = 0.0;
        Double end = 0.0;
        boolean stpm = false;
        boolean etpm = false;
        // this part converts a String to Integer -- removes all non-digit characters before parsing
        for (int i = 0; i < this.getStartTime().length(); ++i)
        {
            if (Character.isDigit(this.getStartTime().charAt(i)))
                digits += this.getStartTime().charAt(i);
            else if (this.getStartTime().charAt(i)=='P')
                stpm = true;
        }
        start = start.parseDouble(digits);
        if (stpm && start >= 100.0) // don't do this for 12pm
            start += 1200.0; // covert to 24 hour format, e.g. 7PM will parse to 700, add 1200 = 1900
        // do the same for end time
        digits = "";
        for (int i = 0; i < this.getEndTime().length(); ++i)
        {
            if (Character.isDigit(this.getEndTime().charAt(i)))
                digits += this.getEndTime().charAt(i);
            else if (this.getEndTime().charAt(i)=='P')
                etpm = true;
        }
        end = start.parseDouble(digits);
        if (etpm && end >= 100.0) // don't do this for 12pm
            end += 1200.0;


        fee = (venue.getCapacity().doubleValue() / 4.0) * (end - start);
        switch (this.getEventType()){
            case "Wedding": {
                fee *= 2.0;
                break;
            }
            case "Charity": {
                fee *= 0.75;
                break;
            }
            default: {
                break;
            }
        }
        if (!this.isPrivateEvent && !this.eventType.equals("Charity"))
            fee *= 2.0;
        if (fee > 0.0)
            return fee;
        else
            return 0.0;
    }

}
