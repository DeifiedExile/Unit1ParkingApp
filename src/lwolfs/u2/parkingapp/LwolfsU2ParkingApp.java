
package lwolfs.u2.parkingapp;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;

public class LwolfsU2ParkingApp {

 
    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    private Scanner input = new Scanner(System.in);
    private String ticketFileName = "tickets.dat";

   /**
     * Initializes program
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        new LwolfsU2ParkingApp();
    }
    
    /**
     * Loads ticket data into memory
     * Generates check in / check out machine selection for ease of testing
     * in practice, this would be removed and each machine would only call the
     * necessary methods.
     * 
     */
    public LwolfsU2ParkingApp()
    {
        TicketMachine machine = new TicketMachine();
        
        //loads tickets into memory
        try
        {
            for(Ticket t : FileInput.loadTickets(ticketFileName))
            {
                tickets.add(t);
                if(t.getTICKET_ID()>machine.getTicketLog())
                {
                    machine.setTicketLog(t.getTICKET_ID());
                }
            }
            
        }
        catch(Exception e)
        {
            System.out.println("File not found");
        }
        
        
        
        int menu = 0;
        while(menu != 3)
        {
            printMainMenu();
            
            String s = input.nextLine();
            int choice = 3;
            try
            {
                choice = Integer.parseInt(s);
            }
            catch(Exception e)
            {
                System.out.println("Invalid selection. Exiting");
                choice = 3;
            }
            switch(choice)
            {
                case 1:
                    checkInMain(machine);
                    break;
                case 2:
                    checkOutMain(machine);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
        
    }
    /**
     * Prints the machine selection menu for testing purposes
     */
    public void printMainMenu()
    {
        System.out.println("Best Value Parking Garage");
        System.out.println("=========================\n");
        System.out.println("Select Machine");
        System.out.println("1.) Check/In");
        System.out.println("2.) Check/Out");
        System.out.println("3.) Exit");
    }
    
    /**
     * Handles the menu for creation of tickets and adds them to main ticket ArrayList
     * @param machine TicketMachine object for processing tickets
     * 
     */
    public void checkInMain(TicketMachine machine)
    {
        printCheckInMenu();

        String s = input.nextLine();
        int choice = 3;
        try
        {
            choice = Integer.parseInt(s);
        }
        catch(Exception e)
        {
            System.out.println("Invalid selection. Exiting");
            choice = 3;
        }
        switch(choice)
        {
            case 1:
                Ticket t = machine.checkIn();
                tickets.add(t);
                System.out.printf("Ticket ID: %d\nCheck-In Time: %s\n", t.getTICKET_ID(), t.getCHECK_IN_TIME().toString());
                
                break;
            case 2:
                closeGarage();
                break;
            default:
//                for(Ticket x : tickets)
//                {
//                    System.out.printf("%s   %d  %s  %.2f\n", x.getCHECK_IN_TIME().toString(), x.getTICKET_ID(),x.getStatus(), x.getPaidAmount());
//                }
                break;
        }
        
        
    }
    /**
     * Handles the menu for closing tickets
     * @param machine TicketMachine object for handling tickets
     */
    public void checkOutMain(TicketMachine machine)
    {
       
        
        printCheckOutMenu();

        String s = input.nextLine();
        int choice = 3;
        try
        {
            choice = Integer.parseInt(s);
        }
        catch(Exception e)
        {
            System.out.println("Invalid selection. Exiting");
            choice = 3;
        }
        switch(choice)
        {
            case 1:
                closeTicket(machine);
                break;
            case 2:
                closeNoTicket();
                break;
            default:
                break;
        }
        
        
    }
    /**
     * Handles the check out process for a lost ticket
     */
    public void closeNoTicket()
    {
        System.out.println("Lost tickets are subject to a $25 fee.");
        Ticket noTicket = new Ticket(0, null, null, true, 25);
        tickets.add(noTicket);
        ticketReceipt(noTicket);
    }
    /**
     * Outputs a receipt for a closed ticket
     * @param t Ticket requiring a receipt
     */
    public void ticketReceipt(Ticket t)
    {
        System.out.println("Best Value Parking Garage");
        System.out.println("=========================\n");
        
        if(t.getTICKET_ID() != 0)
        {
            int billedHours = (int)Duration.between(t.getCHECK_IN_TIME(), t.getCheckOutTime()).toHours();
            if(Duration.between(t.getCHECK_IN_TIME(), t.getCheckOutTime()).minusHours(billedHours).getSeconds()/60 > 0)
            {
                billedHours++;
            }           
            System.out.printf("Receipt for vehicle ID %d\n\n", t.getTICKET_ID());
            System.out.printf("%d hours parked %s - %s\n\n", billedHours, t.getCHECK_IN_TIME().toString(), t.getCheckOutTime().toString());
            System.out.printf("$%.2f", t.getPaidAmount());
        }
        else
        {
            System.out.println("Receipt for lost ticket\n");
            System.out.printf("$%.2f", t.getPaidAmount());
        }
        
    }
    /**
     * Searches the tickets ArrayList based on ticketID
     * @param id ticket id to search for
     * @return Ticket found if any
     */
    public Ticket findTicket(int id)
    {
        for(Ticket t : tickets)
        {
            if(t.getTICKET_ID() == id)
            {
                return t;
            }
        }
        return null;
    }
    /**
     * Closes a ticket
     * @param machine TicketMachine for processing tickets
     */
    public void closeTicket(TicketMachine machine)
    {
        System.out.println("Input ticket ID");
        String inputID = input.nextLine();
        int ticketID;
        try
        {
            ticketID = Integer.parseInt(inputID);

        }
        catch(Exception e)
        {
            ticketID = 0;
        }
        Ticket t = findTicket(ticketID);
        if(t == null || ticketID == 0)
        {
            System.out.println("Invalid Ticket ID. Defaulting to Lost Ticket");
            closeNoTicket();
            
        }
        else
        {
            //sets ticket status to closed and records fee
            machine.checkOut(t);
            System.out.println("Payment Complete");
            System.out.printf("TicketID: %d\nFee paid: %.2f", t.getTICKET_ID(), t.getPaidAmount());
            
        }
        
    }

    /**
     * Outputs financial results to date
     * calls method to save ticket data to file
     */
    public void closeGarage()
    {        
        double standardRevenue = 0;
        double lostRevenue = 0;
        int checkInCount = 0;
        int lostCount = 0;
        
        for(Ticket t : tickets)
        {
            if(t.getTICKET_ID() != 0)
            {
                if(t.getStatus())
                {
                    standardRevenue += t.getPaidAmount();
                    checkInCount++;
                    
                }
            }
            else
            {
                lostRevenue += t.getPaidAmount();
                lostCount++;
            }
        }
        
        System.out.println("Best Value Parking Garage");
        System.out.println("=========================\n");
        System.out.println("Activity to Date\n\n");
        System.out.printf("%.2f was collected from %d Check Ins\n", standardRevenue, checkInCount );
        System.out.printf("%.2f was collected from %d Lost Tickets\n\n", lostRevenue, lostCount);
        System.out.printf("%.2f was collected overall\n", (standardRevenue + lostRevenue));
        FileOutput fo = new FileOutput(ticketFileName);
        fo.saveToFile(tickets);
    }
    /**
     * Outputs check in menu
     */
    public void printCheckInMenu()
    {
        System.out.println("Best Value Parking Garage");
        System.out.println("=========================\n");
        System.out.println("1.) Check/In");
        System.out.println("2.) Close Garage");
        System.out.print("=> ");        
    }
    /**
     * Outputs check out menu
     */
    public void printCheckOutMenu()
    {
        System.out.println("Best Value Parking Garage");
        System.out.println("=========================\n");
        System.out.println("1.) Check/Out");
        System.out.println("2.) Lost Ticket");
        System.out.print("=> ");
    }
    
    
}
