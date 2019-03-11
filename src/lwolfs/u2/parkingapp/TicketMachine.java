
package lwolfs.u2.parkingapp;

import java.time.*;
import java.util.Random;


public class TicketMachine {
    
    private static int ticketLog = 0;
    private Random r = new Random();
    /**
     * returns current ticket log used for generating ticket ids
     * @return int ticketLog for generating ticket ids
     */
    public static int getTicketLog()
    {
        return ticketLog;
    }
    /**
     * sets the ticket log
     * @param ticketLog int value to set to ticketLog
     */
    public  void setTicketLog(int ticketLog)
    {
        this.ticketLog = ticketLog;
    }
    /**
     * creates a ticket with ticket id and check in time
     * increments ticketLog
     * @return Ticket that was created
     */
    public Ticket checkIn()
    {
        
        //randomly creates a time between 7am and noon
        LocalTime checkInTime = LocalTime.of(r.nextInt(6)+7, r.nextInt(60));
        int ticketID = ++this.ticketLog;
        Ticket ticket = new Ticket(checkInTime, ticketID);
        return ticket;
    }
    /**
     * Sets check out time, closed status, and fee amount to provided ticket
     * 
     * @param t Ticket to close
     */
    public void checkOut(Ticket t)
    {        
        
        //generates random checkout time
        //can be replaced with calling current time
        LocalTime checkOutTime = LocalTime.of(r.nextInt(11)+13, r.nextInt(60));
        t.setCheckOutTime(checkOutTime);
        //calculates time between check in and check out
        Duration duration = Duration.between(t.getCHECK_IN_TIME(), checkOutTime);
        //extracts number of billable hours
        long hours = duration.toHours();
        
        if((duration.minusHours(hours).getSeconds()/60) > 0)
        {
            hours++;
        }
        
        t.setStatus(true);
        t.setPaidAmount(calcFee(hours));
        
       
        
        
        
    }
    /**
     * Calculates parking fee
     * @param hours billable hours to charge based on
     * @return returns parking fee
     */
    public double calcFee(long hours)
    {
        double fee = 0;
        
        if(hours < 3)
        {
            fee = 5.00;
        }
        else
        {
            fee = 5 + (hours-3);
            if(fee > 15)
            {
                fee = 15;
            }
        }
        
        return fee;
    }
   
}

