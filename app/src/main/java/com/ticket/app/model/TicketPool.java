package com.ticket.app.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

// TicketPool class to represent the collection of tickets in the system
@Entity // Marking the class as a JPA entity (mapped to a database table)
public class TicketPool {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate ID for each ticket pool instance
    private Long id; // Primary key for the ticket pool

    @ElementCollection // Indicates the list of tickets is stored as a collection in a separate table
    @CollectionTable(name = "ticket_pool_tickets", joinColumns = @JoinColumn(name = "pool_id")) // Define the table and column for storing tickets
    @Column(name = "ticket_id") // Column name for ticket ID in the database table
    private List<Integer> tickets = new ArrayList<>(); // List to store the ticket IDs

    private int maxTicketCapacity; // Maximum capacity of the ticket pool (maximum number of tickets it can hold)

    // Default constructor
    public TicketPool() {}

    // Constructor to initialize the pool with a specific number of tickets and capacity
    public TicketPool(int totalTickets, int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
        for (int i = 0; i < totalTickets; i++) {
            tickets.add(i); // Initialize ticket IDs with values from 0 to totalTickets-1
        }
    }

    // Synchronized method to add a ticket to the pool (ensures thread safety)
    public synchronized boolean addTicket(int ticketId) {
        if (tickets.size() < maxTicketCapacity) { // Check if there is space in the pool
            tickets.add(ticketId); // Add ticket if there is space
            return true;
        }
        return false; // Return false if the pool is at max capacity
    }

    // Synchronized method to remove a ticket from the pool (ensures thread safety)
    public synchronized Integer removeTicket() {
        if (!tickets.isEmpty()) {
            return tickets.remove(0); // Remove the first ticket (FIFO) if tickets are available
        }
        return null; // Return null if no tickets are available
    }

    // Synchronized method to get the current ticket count (ensures thread safety)
    public synchronized int getTicketCount() {
        return tickets.size(); // Return the number of tickets currently in the pool
    }

    // Getter and Setter methods for the TicketPool class
    public Long getId() {
        return id;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    public List<Integer> getTickets() {
        return tickets;
    }

    public void setTickets(List<Integer> tickets) {
        this.tickets = tickets;
    }
}
