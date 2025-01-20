package com.ticket.app.controller;

import com.ticket.app.service.TicketingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// Controller for handling ticketing system operations
@RestController
@RequestMapping("/api/tickets") // Base URL for all ticket-related endpoints
public class TicketingController {

    @Autowired
    private TicketingService ticketingService; // Injecting TicketingService to handle business logic

    // Endpoint to start the ticketing system with user-provided parameters
    @PostMapping("/start")
    public String startSystem(@RequestParam int totalTickets, // Total number of tickets available
                              @RequestParam int ticketReleaseRate, // Rate at which tickets are released
                              @RequestParam int customerRetrievalRate, // Rate at which customers retrieve tickets
                              @RequestParam int maxTicketCapacity) { // Maximum capacity of tickets in the system
        ticketingService.startSystem(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
        return "Ticketing system started with the provided parameters!"; // Return confirmation message
    }

    // Endpoint to stop the ticketing system
    @PostMapping("/stop")
    public String stopSystem() {
        ticketingService.stopSystem(); // Call service method to stop the system
        return "Ticketing system stopped."; // Return confirmation message
    }

    // Endpoint to get the current status of the ticket pool
    @GetMapping("/status")
    public String getTicketStatus() {
        return ticketingService.getTicketStatus(); // Fetch the current ticket status from the service
    }
}
