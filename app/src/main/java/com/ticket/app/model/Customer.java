package com.ticket.app.model;

// Customer class implements Runnable to simulate customer behavior in the ticketing system
public class Customer implements Runnable {
    private final TicketPool ticketPool; // Ticket pool where customers will retrieve tickets
    private final int customerRetrievalRate; // The rate at which the customer tries to retrieve tickets (affects the delay between attempts)

    // Constructor to initialize the ticket pool and retrieval rate
    public Customer(TicketPool ticketPool, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    // Run method that defines the behavior of the customer in the thread
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {  // Continuously run while the thread is not interrupted
            Integer ticket = ticketPool.removeTicket();  // Attempt to remove a ticket from the pool
            if (ticket != null) {
                System.out.println("Customer purchased ticket: " + ticket); // If a ticket is available, print a message
            } else {
                System.out.println("No tickets available, customer waiting..."); // If no ticket is available, inform the customer
            }
            try {
                // Thread sleeps for a period based on customer retrieval rate, simulating customer wait time between attempts
                Thread.sleep(customerRetrievalRate * 500); // Multiply by 500 to control the speed of customer ticket retrieval
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // If interrupted, preserve the interrupt status of the thread
            }
        }
    }
}
