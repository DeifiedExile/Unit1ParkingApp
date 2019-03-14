/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lwolfs.u2.parkingapp;

import java.io.Serializable;


public class LostTicket implements Serializable, TicketInterface {
    private final int TICKET_ID;
    private boolean isPaid;
    private TicketType type = TicketType.LOST;

    public LostTicket(int TICKET_ID, boolean isPaid) {
        this.TICKET_ID = TICKET_ID;

        this.isPaid = isPaid;
    }
    public int getTicketID()
    {
        return TICKET_ID;
    }
    public String getTicketType()
    {
        return type.toString();
    }
    public boolean IsPaid() {
        return isPaid;
    }

    public void setIsPaid(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public int getTICKET_ID() {
        return TICKET_ID;
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
