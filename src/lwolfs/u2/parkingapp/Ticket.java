
package lwolfs.u2.parkingapp;

import java.io.Serializable;
import java.time.*;


public class Ticket implements Serializable {
    private final LocalTime CHECK_IN_TIME;
    private final int TICKET_ID;
    private boolean isPaid;
    private double paidAmount;
    private LocalTime checkOutTime;
    

    /**
     * Constructor. Generates a Ticket object
     * @param CHECK_IN_TIME Time of Check-in
     * @param TICKET_ID Ticket ID
     */
    public Ticket(LocalTime CHECK_IN_TIME, int TICKET_ID) {
        this.CHECK_IN_TIME = CHECK_IN_TIME;
        this.TICKET_ID = TICKET_ID;
        this.checkOutTime = null;
        this.isPaid = false;
    }
    /**
     * Constructor for loading tickets from file
     * @param TICKET_ID Ticket ID
     * @param CHECK_IN_TIME Time of Check-In
     * @param checkOutTime Time of Check-out if any
     * @param status Open/closed status of ticket
     * @param amount Fee paid if any
     */
    public Ticket(int TICKET_ID, LocalTime CHECK_IN_TIME, LocalTime checkOutTime , boolean status, double amount)
    {
        this.CHECK_IN_TIME = CHECK_IN_TIME;
        this.checkOutTime = checkOutTime;
        this.TICKET_ID = TICKET_ID;
        this.isPaid = status;
        this.paidAmount = amount;
    }
    /**
     * 
     * @return check out time
     */
    public LocalTime getCheckOutTime()
    {
        return checkOutTime;
    }
    /**
     * Sets ticket check-out time
     * @param checkOutTime check out time to set
     */
    public void setCheckOutTime(LocalTime checkOutTime)
    {
        this.checkOutTime = checkOutTime;
    }

    /**
     * Gets check in time
     * @return Check in time for ticket
     */
    public LocalTime getCHECK_IN_TIME() {
        return CHECK_IN_TIME;
    }

    /**
     * Gets ticket ID
     * @return Ticket ID to return
     */
    public int getTICKET_ID() {
        return TICKET_ID;
    }
    /**
     * Sets ticket paid status
     * @param s status to set
     */
    public void setStatus(boolean s)
    {
        isPaid = s;
    }
    /**
     * Gets ticket paid status
     * @return Current ticket status
     */
    public boolean getStatus()
    {
        return isPaid;
    }
    /**
     * Sets ticket fee
     * @param amount amount of fee
     */
    public void setPaidAmount(double amount)
    {
        this.paidAmount = amount;
    }
    /**
     * Gets ticket fee amount
     * @return Ticket fee paid
     */
    public double getPaidAmount()
    {
        return paidAmount;
    }
    

}
