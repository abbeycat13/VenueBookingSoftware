package sample;

public class EventBooking {

    private String eventType;
    private boolean isPublic;
    private double date;
    private boolean paidInFull;

    /**
     *  CONSTRUCTOR
     */
    public EventBooking() {
    }

    /**
     *  CONSTRUCTOR
     */
    public EventBooking(String eventType, boolean isPublic, double date,boolean paidInFull) {

        this.eventType=eventType;
        this.isPublic=isPublic;
        this.date=date;
        this.paidInFull=paidInFull;

    }

    /**
     * GET event type
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * SET event type
     */
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    /**
     * GET IF PUBLIC
     */
    public boolean isPublic() {
        return isPublic;
    }

    /**
     * SET IF PUBLIC
     */
    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    /**
     * GET DATE
     */
    public double getDate() {
        return date;
    }

    /**
     * SET DATE
     */
    public void setDate(double date) {
        this.date = date;
    }

    /**
     * GET IS PAID IN FULL
     */
    public boolean isPaidInFull() {
        return paidInFull;
    }

    /**
     * SET IS PAID IN FULL
     */
    public void setPaidInFull(boolean paidInFull) {
        this.paidInFull = paidInFull;
    }
}
