public class TicketPool {
    private int ticketsAvailable; 
    private int maxCapacity; 

    // Constructor to initialize the ticket pool with total tickets and max capacity
    public TicketPool(int totalTickets, int maxCapacity) {
        this.ticketsAvailable = totalTickets;
        this.maxCapacity = maxCapacity;
    }

    // Synchronized method to add a ticket to the pool
    public synchronized boolean addTicket() {
        if (ticketsAvailable < maxCapacity) {
            ticketsAvailable++; // Increment the ticket count
            return true; // Indicate success
        }
        return false; // Indicate failure as max capacity is reached
    }

    // Synchronized method to remove a ticket from the pool
    public synchronized boolean removeTicket() {
        if (ticketsAvailable > 0) {
            ticketsAvailable--; // Decrement the ticket count
            return true; // Indicate success
        }
        return false; // Indicate failure as no tickets are available
    }

    // Synchronized method to get the current number of tickets available
    public synchronized int getTicketsAvailable() {
        return ticketsAvailable;
    }

    // Synchronized method to set the number of tickets available
    public synchronized void setTicketsAvailable(int ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }

    // Getter method to retrieve the maximum ticket capacity
    public int getMaxCapacity() {
        return maxCapacity;
    }

    // Setter method to update the maximum ticket capacity
    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
