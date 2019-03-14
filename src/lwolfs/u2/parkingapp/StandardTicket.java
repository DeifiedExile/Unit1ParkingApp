
package lwolfs.u2.parkingapp;

import java.io.Serializable;
import java.time.*;


public class StandardTicket implements Serializable, TicketInterface {
    private final LocalTime CHECK_IN_TIME;
    private final int TICKET_ID;
    private boolean isPaid;
    private static TicketType type = TicketType.STANDARD;
    private LocalTime checkOutTime;

    public StandardTicket(int TICKET_ID, boolean isPaid, LocalTime CHECK_IN_TIME) {
       
        this.TICKET_ID = TICKET_ID;
        this.isPaid = isPaid;
        this.CHECK_IN_TIME = CHECK_IN_TIME;
        
    }
    public String getTicketType()
    {
        return type.toString();
    }
    public double getMAX_FEE()
    {
        return MAX_FEE;
    }
    public int getTicketID()
    {
        return this.TICKET_ID;
    }

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
    
    public void setIsPaid(boolean status)
    {
        this.isPaid = true;
    }
    public boolean IsPaid()
    {
        return isPaid;
    }

    @Override
    public double getLOST_TICKET_FEE() {
        return LOST_TICKET_FEE;
    }

    @Override
    public double getBASE_FEE_RATE() {
        return BASE_FEE_RATE;
    }

    @Override
    public double getRATE_PER_HOUR() {
        return RATE_PER_HOUR;
    }

    @Override
    public int getMIN_PARKING_TIME() {
        return MIN_PARKING_TIME;
    }

    

}
