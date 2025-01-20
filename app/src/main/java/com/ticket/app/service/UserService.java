package com.ticket.app.service;

import com.ticket.app.model.User;
import com.ticket.app.repository.UserRepository;
import org.springframework.stereotype.Service;

// Service class that handles user-related operations
@Service // Marks this class as a Spring Service, making it a Spring-managed bean
public class UserService {

    private final UserRepository userRepository; // UserRepository for interacting with the User data in the database

    // Constructor that initializes the UserRepository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Method to get a User by username, throws an exception if the user is not found
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found")); // Throw an exception if the user doesn't exist
    }

    // Method to save a new or existing User to the database
    public User saveUser(User user) {
        return userRepository.save(user); // Save the user and return the saved entity
    }
}
