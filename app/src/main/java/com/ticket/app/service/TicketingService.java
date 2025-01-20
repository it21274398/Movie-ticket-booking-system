package com.ticket.app.service;

import com.ticket.app.model.TicketPool;
import com.ticket.app.model.Vendor;
import com.ticket.app.model.Customer;
import org.springframework.stereotype.Service;

// Service class that manages the ticketing system
@Service // Marks this class as a Spring Service, making it a Spring-managed bean
public class TicketingService {

    private TicketPool ticketPool; // Ticket pool to hold available tickets
    private Vendor vendor; // Vendor that adds tickets to the pool
    private Customer customer; // Customer that removes tickets from the pool
    private Thread vendorThread; // Thread that runs the vendor logic
    private Thread customerThread; // Thread that runs the customer logic

    // Method to start the system with user-provided parameters
    public void startSystem(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {

        // Initialize ticket pool and set up vendor and customer with their respective parameters
        ticketPool = new TicketPool(totalTickets, maxTicketCapacity);
        vendor = new Vendor(ticketPool, ticketReleaseRate);
        customer = new Customer(ticketPool, customerRetrievalRate);

        // Create threads for vendor and customer
        vendorThread = new Thread(vendor);
        customerThread = new Thread(customer);

        // Start the vendor and customer threads
        vendorThread.start();
        customerThread.start();

        // Output the system's initial parameters for the user
        System.out.println("----------------------------------------");
        System.out.println("System started with:");
        System.out.println("Total Tickets: " + totalTickets);
        System.out.println("Ticket Release Rate: " + ticketReleaseRate + " seconds");
        System.out.println("Customer Retrieval Rate: " + customerRetrievalRate + " seconds");
        System.out.println("Maximum Ticket Capacity: " + maxTicketCapacity);
        System.out.println("----------------------------------------");
    }

    // Method to stop the system by interrupting the vendor and customer threads
    public void stopSystem() {
        if (vendorThread != null && vendorThread.isAlive()) {
            vendorThread.interrupt(); // Interrupt the vendor thread
        }
        if (customerThread != null && customerThread.isAlive()) {
            customerThread.interrupt(); // Interrupt the customer thread
        }
        System.out.println("System stopped.");
    }

    // Method to get the current ticket count from the ticket pool
    public String getTicketStatus() {
        if (ticketPool == null) {
            return "Ticket pool is not initialized."; // Return a message if the ticket pool is not yet set up
        }
        return "Tickets available: " + ticketPool.getTicketCount(); // Return the current count of available tickets
    }
}
