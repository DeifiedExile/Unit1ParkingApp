/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package lwolfs.u2.parkingapp;


public class ReceiptPrinter {

    /**
     * prints a receipt for closing a ticket
     * @param t ticket to generate a receipt for
     */
    public static void printReceipt(TicketInterface t)
    {
        if(t.getTicketType().equalsIgnoreCase(TicketType.STANDARD.toString()))
        {
            StandardTicket ticket = (StandardTicket)t;
            String checkIn, checkOut;
            System.out.printf("Receipt for vehicle ID %d\n\n", ticket.getTICKET_ID());
            if(ticket.getCHECK_IN_TIME().getHour() <= 12)
            {
                checkIn = ticket.getCHECK_IN_TIME().getHour() + "AM";
            }
            else
            {
                checkIn = (ticket.getCHECK_IN_TIME().getHour() - 12) + "PM";
            }
            if(ticket.getCheckOutTime().getHour() <= 12)
            {
                checkOut = ticket.getCheckOutTime().getHour() + "AM";
            }
            else
            {
                checkOut = (ticket.getCheckOutTime().getHour() - 12) + "PM";
            }
            
            System.out.printf("%d hours parked %s - %s\n\n", TicketMachine.getBillableHours(ticket), checkIn, checkOut);
            System.out.printf("$%.2f\n\n", TicketMachine.getFee(ticket));
        }
        else if(t.getTicketType().equalsIgnoreCase(TicketType.LOST.toString()))
        {
            LostTicket ticket = (LostTicket)t;
            System.out.printf("Receipt for vehicle ID %d\n\n", ticket.getTICKET_ID());
            System.out.println("Lost Ticket\n");
            System.out.println(TicketInterface.LOST_TICKET_FEE);
        }
    }
    /**
     * prints the final totals after closing the garage
     * @param standardRevenue total revenue from standard tickets closed
     * @param lostRevenue total revenue from lost tickets
     * @param standardCount total number of standard tickets
     * @param lostCount total number of lost tickets
     */
    public static void printClosingTotals(double standardRevenue, double lostRevenue, int standardCount, int lostCount)
    {
        System.out.println("Best Value Parking Garage");
        System.out.println("=========================\n");
        System.out.println("Activity to Date\n\n");
        System.out.printf("%.2f was collected from %d Check Ins\n", standardRevenue, standardCount );
        System.out.printf("%.2f was collected from %d Lost Tickets\n\n", lostRevenue, lostCount);
        System.out.printf("%.2f was collected overall\n", (standardRevenue + lostRevenue));
    }
}
