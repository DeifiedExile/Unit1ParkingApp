/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lwolfs.u2.parkingapp;

import java.io.Serializable;

/**
 *
 * @author Exile
 */
public interface TicketInterface extends Serializable{
    double LOST_TICKET_FEE = 25.00;
    double BASE_FEE_RATE = 5.00;
    double RATE_PER_HOUR = 1.00;
    int MIN_PARKING_TIME = 3;
    double MAX_FEE = 15.00;
    
    public double getLOST_TICKET_FEE();
    public double getBASE_FEE_RATE();
    public double getRATE_PER_HOUR();
    public int getMIN_PARKING_TIME();
    public int getTicketID();
    public String getTicketType();
    public void setIsPaid(boolean status);
    public boolean IsPaid();
    
}
