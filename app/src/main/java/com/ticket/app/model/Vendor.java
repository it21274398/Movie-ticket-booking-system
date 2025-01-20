package com.ticket.app.model;

// Vendor class implements Runnable to simulate vendor behavior in the ticketing system
public class Vendor implements Runnable {
    private final TicketPool ticketPool; // Ticket pool where tickets will be added by the vendor
    private final int ticketReleaseRate; // The rate at which the vendor releases tickets (affects the delay between releases)

    // Constructor to initialize the ticket pool and ticket release rate
    public Vendor(TicketPool ticketPool, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    // Run method that defines the behavior of the vendor in the thread
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {  // Continuously run while the thread is not interrupted
            // Check if there is space in the ticket pool to add more tickets
            if (ticketPool.getTicketCount() < ticketPool.getMaxTicketCapacity()) {
                // Generate a random ticket ID and add it to the ticket pool
                boolean added = ticketPool.addTicket((int) (Math.random() * 1000));  // Generate random ticket ID
                if (added) {
                    // If the ticket was successfully added, print a message
                    System.out.println("Vendor added a ticket.");
                    System.out.println("Number of total tickets: " + ticketPool.getTicketCount()); // Print the current number of tickets
                }
            }
            try {
                // Thread sleeps for a period based on the ticket release rate, simulating vendor wait time between ticket releases
                Thread.sleep(ticketReleaseRate * 500); // Multiply by 500 to control the speed of ticket release
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // If interrupted, preserve the interrupt status of the thread
            }
        }
    }
}
