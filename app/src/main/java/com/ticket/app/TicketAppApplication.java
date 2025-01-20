package com.ticket.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// Main class to start the Spring Boot application
@SpringBootApplication // This annotation indicates the entry point for the Spring Boot application
public class TicketAppApplication {

    // Main method to launch the application
    public static void main(String[] args) {
        SpringApplication.run(TicketAppApplication.class, args); // Starts the Spring Boot application
        System.out.println("Ticket App has started successfully...!"); // Prints a message to the console when the app starts
    }
}
