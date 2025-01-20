public class Customer implements Runnable {
    private final TicketPool ticketPool; // Reference to the shared TicketPool object
    private final int customerRetrievalRate; // The rate at which the customer attempts to purchase tickets

    // Constructor to initialize the Customer with the shared TicketPool and retrieval rate
    public Customer(TicketPool ticketPool, int customerRetrievalRate) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
    }

    // The run method contains the logic executed when the Customer thread is started
    @Override
    public void run() {
        // Loop to repeatedly try purchasing tickets until the thread is interrupted
        while (!Thread.currentThread().isInterrupted()) {
            // Attempt to remove a ticket from the TicketPool
            if (ticketPool.removeTicket()) {
                // If successful, display the updated ticket count
                System.out.println("[Customer] Purchased a ticket. Total tickets: " + ticketPool.getTicketsAvailable());
            } else {
                // If no tickets are available, display a waiting message
                System.out.println("[Customer] No tickets available. Waiting...");
            }
            try {
                // Pause for a duration based on the customer retrieval rate
                Thread.sleep(customerRetrievalRate * 500);
            } catch (InterruptedException e) {
                // If the thread is interrupted, exit the loop
                Thread.currentThread().interrupt();
            }
        }
    }
}
