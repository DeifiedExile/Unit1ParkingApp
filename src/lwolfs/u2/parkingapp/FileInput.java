/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lwolfs.u2.parkingapp;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Exile
 */
public class FileInput {
    private static FileInputStream fis = null;
    private static ObjectInputStream ois = null;
    /**
     * Reads ticket data from file
     * @param fileName File path of data file
     * @return Arraylist of Ticket data
     * @throws IOException 
     */
    public static List<TicketInterface> loadTickets(String fileName) throws IOException
    {
        List<TicketInterface> tickets = new ArrayList<TicketInterface>();

        try {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
        } catch (Exception ex) {
            System.out.println("File not found");
        }
        
        while(true)
        {
            try
            {
                Object obj = ois.readObject();
                TicketInterface ticket = (TicketInterface)obj;
                tickets.add(ticket);
            }
            catch(Exception eof)
            {
                break;
            }
        }
        ois.close();
        return tickets;
    }
}
