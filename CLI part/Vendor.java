public class Vendor implements Runnable {
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;

    // Constructor to initialize the vendor with the ticket pool and release rate
    public Vendor(TicketPool ticketPool, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    // Method to run the vendor's operations in a separate thread
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            if (ticketPool.addTicket()) {
                // Successfully added a ticket to the pool
                System.out.println("[Vendor] Added a ticket. Total tickets: " + ticketPool.getTicketsAvailable());
            } else {
                // Pool is full, cannot add more tickets
                System.out.println("[Vendor] Pool full. Waiting to add tickets...");
            }
            try {
                // Wait before releasing the next ticket
                Thread.sleep(ticketReleaseRate * 500);
            } catch (InterruptedException e) {
                // Handle thread interruption and exit the loop
                Thread.currentThread().interrupt();
            }
        }
    }
}
