
package lwolfs.u2.parkingapp;

import java.time.*;
import static java.time.temporal.ChronoUnit.MINUTES;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class TicketMachine {
    
    private static int ticketLog = 0;
    private Random r = new Random();
    private String filePath;
    private List<TicketInterface> ticketData = new ArrayList<TicketInterface>();
    
    /**
     * loads current ticket log used for generating ticket ids
     * @param filePath file path for ticket data
     * @return int ticketLog for generating ticket ids
     */
    
    private void loadTickets(String filePath)
    {
        try
        {
            for(TicketInterface t : FileInput.loadTickets(filePath))
            {
                ticketData.add(t);
                if(t.getTicketID() > getTicketLog())
                {
                    setTicketLog(t.getTicketID());
                }
            }
            
        }
        catch(Exception e)
        {
            System.out.println("File not found");
        }
    }
    /**
     * Constructor, calls method to load tickets
     * @param filePath filepath for ticket data
     */
    public TicketMachine(String filePath)
    {
        this.filePath = filePath;
        loadTickets(this.filePath);
    }
    /**
     * 
     * @return returns current ticket count
     */
    public static int getTicketLog()
    {
        return ticketLog;
    }
    /**
     * sets the ticket log
     * @param ticketLog int value to set to ticketLog
     */
    public void setTicketLog(int ticketLog)
    {
        this.ticketLog = ticketLog;
    }
    /**
     * creates a ticket with ticket id and check in time
     * increments ticketLog
     * @return Ticket that was created
     */
    public void checkIn()
    {
        //randomly creates a time between 7am and noon
        LocalTime checkInTime = LocalTime.of(r.nextInt(6)+7, r.nextInt(60));
        int ticketID = ++this.ticketLog;
        TicketInterface ticket = new StandardTicket(ticketID, false, checkInTime);
        ticketData.add(ticket);
    }
    /**
     * Sets check out time, closed status, and fee amount to provided ticket
     * 
     * @param t Ticket to close
     */
    public void checkOutStandard(StandardTicket t)
    {        
        
        //generates random checkout time
        //can be replaced with calling current time
        LocalTime checkOutTime = LocalTime.of(r.nextInt(11)+13, r.nextInt(60));
        t.setCheckOutTime(checkOutTime);        
        t.setIsPaid(true);
    }
    /**
     * adds a lost ticket to the ticket list
     */
    public void addLostTicket()
    {
        int ticketID = ++this.ticketLog;
        TicketInterface ticket = new LostTicket(ticketID, false);
        ticketData.add(ticket);
    }
   /**
    * Searches for ticket with a given ticket id
    * @param searchID ticket id to search for
    * @return returns a ticket if found, otherwise returns null
    */
    public TicketInterface getTicket(int searchID)
    {
        TicketInterface ticket = null;
        
        for(TicketInterface t : ticketData)
        {
            if(t.getTicketID() == searchID)
            {
                ticket = t;
            }
            
        }
        if(ticket != null)
        {
            return ticket;
        }
        else
        {
            return null;
        }
    }
    /**
     * closes a lost ticket
     * @param t tickte to close
     */
    public void checkOutLostTicket(LostTicket t)
    {
        
        t.setIsPaid(true);
        
    }
            
            
            
    /**
     * Calculates parking fee
     * @param t
     * 
     * @return returns parking fee
     */
    public static double getFee(TicketInterface t)
    {
        double fee;
        
        if(t.getTicketType().equalsIgnoreCase(TicketType.LOST.toString()))
        {
            LostTicket lost = (LostTicket)t;
            fee = lost.getLOST_TICKET_FEE();
        }
        else if(t.getTicketType().equalsIgnoreCase(TicketType.STANDARD.toString()) && t.IsPaid())
        {
            StandardTicket standard = (StandardTicket)t;
            int billableHours = getBillableHours(standard);
            
            if(billableHours <= standard.getMIN_PARKING_TIME())
            {
                fee = standard.getBASE_FEE_RATE();
            }
            else
            {
                fee = standard.getBASE_FEE_RATE() + ((billableHours - standard.getMIN_PARKING_TIME())*standard.getRATE_PER_HOUR());
            }
            if(fee > standard.getMAX_FEE())
            {
                fee = standard.getMAX_FEE();
            }
        }
        else
        {
            fee = 0;
        }
        
        
        return fee;
    }
    
    /**
     * calculates number of billable hours
     * @param t ticket to calculate hours from
     * @return returns number of billable hours
     */
    public static int getBillableHours(StandardTicket t)
    {
        StandardTicket standard = (StandardTicket)t;
        if(standard.getCheckOutTime() != null)
        {
            long minutes = MINUTES.between(standard.getCHECK_IN_TIME(), standard.getCheckOutTime());
            int billableHours = (int)minutes/60;
            if(minutes % 60 > 1)
            {
                billableHours++;
            }
            return billableHours;
        }
        else
        {
            return 0;
        }
    }
    
    /**
     * processes closing the garage
     */
   public void closeGarage()
   {
        double standardRevenue = 0;
        double lostRevenue = 0;
        int standardCount = 0;
        int lostCount = 0;
        
        for(TicketInterface ticket : ticketData)
        {
            if(ticket.getTicketType().equalsIgnoreCase(TicketType.STANDARD.toString()) && ticket.IsPaid())
            {
                StandardTicket standard = (StandardTicket)ticket;
                standardRevenue += TicketMachine.getFee(standard);
                standardCount++;

            }
            else if(ticket.getTicketType().equalsIgnoreCase(TicketType.LOST.toString()) && ticket.IsPaid())
            {
                lostRevenue += TicketMachine.getFee(ticket);
                lostCount++;
            }
        }
        ReceiptPrinter.printClosingTotals(standardRevenue, lostRevenue, standardCount, lostCount);
        
        FileOutput fo = new FileOutput(this.filePath);
        fo.saveToFile(this.ticketData);
        System.exit(0);
   }
}

