package com.ticket.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// User class to represent a user in the system
@Entity // Marking the class as a JPA entity (mapped to a database table)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generate ID for each user
    private Long id; // Unique identifier for each user

    private String username; // Username for user authentication
    private String password; // Password for user authentication
    private String role; // Role of the user (e.g., admin, customer)

    // Default constructor
    public User() {}

    // Constructor to initialize the user with specific values
    public User(Long id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getter and Setter methods for User class attributes
    public Long getId() {
        return id; // Return the user ID
    }

    public void setId(Long id) {
        this.id = id; // Set the user ID
    }

    public String getUsername() {
        return username; // Return the username
    }

    public void setUsername(String username) {
        this.username = username; // Set the username
    }

    public String getPassword() {
        return password; // Return the password
    }

    public void setPassword(String password) {
        this.password = password; // Set the password
    }

    public String getRole() {
        return role; // Return the user role
    }

    public void setRole(String role) {
        this.role = role; // Set the role of the user
    }
}
