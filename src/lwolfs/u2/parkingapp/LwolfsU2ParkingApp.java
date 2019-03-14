
package lwolfs.u2.parkingapp;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Exile
 */
public class LwolfsU2ParkingApp {

 

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
     * 
     * Generates check in / check out machine selection for ease of testing
     * in practice, this would be removed and each machine would only call the
     * necessary methods.
     * 
     */
    public LwolfsU2ParkingApp()
    {
       TicketMachine machine = new TicketMachine(ticketFileName);
        
        int menu = 0;
        while(menu != 3)
        {
            printMainMenu();
            
            String s = input.nextLine();
            System.out.println("\n");
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
      //  System.out.println("3.) Exit");
    }
    
    /**
     * Handles the menu for creation of tickets
     * @param machine TicketMachine object for processing tickets
     * 
     */
    public void checkInMain(TicketMachine machine)
    {
        printCheckInMenu();

        String s = input.nextLine();
        System.out.println("\n");
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
                machine.checkIn();
                StandardTicket t = (StandardTicket)machine.getTicket(machine.getTicketLog());
                System.out.printf("Ticket ID: %d\nCheck-In Time: %s\n\n", t.getTICKET_ID(), t.getCHECK_IN_TIME().toString());
                
                break;
            case 2:
                machine.closeGarage();
                break;
            default:
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
        System.out.println("\n");
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
                closeNoTicket(machine);
                break;
            default:
                break;
        }
        
        
    }
    /**
     * Handles the check out process for a lost ticket
     * @param machine
     */
    public void closeNoTicket(TicketMachine machine)
    {
        System.out.println("Lost tickets are subject to a $25 fee.");
        
        machine.addLostTicket();
        LostTicket lost = (LostTicket)machine.getTicket(machine.getTicketLog());
        machine.checkOutLostTicket(lost);

        ReceiptPrinter.printReceipt(lost);
    }
    
    /**
     * Closes a ticket
     * @param machine TicketMachine for processing tickets
     */
    public void closeTicket(TicketMachine machine)
    {
        TicketInterface ticket = null;
        int ticketID;
        do
        {
            System.out.println("Input ticket ID");
            
            String inputID = input.nextLine();
            
            try
            {
                ticketID = Integer.parseInt(inputID);

            }
            catch(Exception e)
            {
                ticketID = 0;
            }
        }while(ticketID == 0);
        
        ticket = machine.getTicket(ticketID);
        if(ticket == null || ticket.getTicketType().equalsIgnoreCase(TicketType.LOST.toString())||ticket.IsPaid())
        {
            System.out.println("Invalid Ticket ID. Defaulting to Lost Ticket");
            closeNoTicket(machine);
            
        }
        else
        {
            //sets ticket status to closed and records fee
            machine.checkOutStandard((StandardTicket)ticket);
            ReceiptPrinter.printReceipt(ticket);
            
        }
        
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
