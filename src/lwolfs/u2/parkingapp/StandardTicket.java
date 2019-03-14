
package lwolfs.u2.parkingapp;

import java.io.Serializable;
import java.time.*;

/**
 *
 * @author Exile
 */
public class StandardTicket implements Serializable, TicketInterface {
    private final LocalTime CHECK_IN_TIME;
    private final int TICKET_ID;
    private boolean isPaid;
    private static TicketType type = TicketType.STANDARD;
    private LocalTime checkOutTime;

    /**
     *
     * @param TICKET_ID
     * @param isPaid
     * @param CHECK_IN_TIME
     */
    public StandardTicket(int TICKET_ID, boolean isPaid, LocalTime CHECK_IN_TIME) {
       
        this.TICKET_ID = TICKET_ID;
        this.isPaid = isPaid;
        this.CHECK_IN_TIME = CHECK_IN_TIME;
        
    }

    /**
     *
     * @return
     */
    public String getTicketType()
    {
        return type.toString();
    }

    /**
     *
     * @return
     */
    public double getMAX_FEE()
    {
        return MAX_FEE;
    }

    /**
     *
     * @return
     */
    public int getTicketID()
    {
        return this.TICKET_ID;
    }

    /**
     *
     * @param TICKET_ID
     * @param isPaid
     * @param CHECK_IN_TIME
     * @param checkOutTime
     */
    public StandardTicket(int TICKET_ID, boolean isPaid, LocalTime CHECK_IN_TIME, LocalTime checkOutTime) {
        
        this.CHECK_IN_TIME = CHECK_IN_TIME;
        this.TICKET_ID = TICKET_ID;
        this.isPaid = isPaid;
        this.checkOutTime = checkOutTime;
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
     *
     * @param status
     */
    public void setIsPaid(boolean status)
    {
        this.isPaid = true;
    }

    /**
     *
     * @return
     */
    public boolean IsPaid()
    {
        return isPaid;
    }

    /**
     *
     * @return
     */
    @Override
    public double getLOST_TICKET_FEE() {
        return LOST_TICKET_FEE;
    }

    /**
     *
     * @return
     */
    @Override
    public double getBASE_FEE_RATE() {
        return BASE_FEE_RATE;
    }

    /**
     *
     * @return
     */
    @Override
    public double getRATE_PER_HOUR() {
        return RATE_PER_HOUR;
    }

    /**
     *
     * @return
     */
    @Override
    public int getMIN_PARKING_TIME() {
        return MIN_PARKING_TIME;
    }

    

}
