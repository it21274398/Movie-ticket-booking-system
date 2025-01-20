package com.ticket.app.controller;

import com.ticket.app.model.User;
import com.ticket.app.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

// Controller to handle user-related operations (sign up, login, etc.)
@RestController
@RequestMapping("/api/users") // Base URL for all user-related endpoints
public class UserController {

    private final UserRepository userRepository; // Repository to interact with the User data

    // Constructor to inject UserRepository into the controller
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Endpoint to create a new user (Sign Up)
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Save the user object to the database and return the saved user
        return ResponseEntity.ok(userRepository.save(user));
    }

    // Endpoint for user login functionality (checking username, password, and role)
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, // User's username
                                        @RequestParam String password, // User's password
                                        @RequestParam String role) { // User's role
        // Find user by the provided username
        Optional<User> user = userRepository.findByUsername(username);

        // If the user is not found, return 404 (Not Found)
        if (user.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }

        // If the password doesn't match, return 400 (Bad Request)
        if (!user.get().getPassword().equals(password)) {
            return ResponseEntity.status(400).body("Invalid password");
        }

        // If the role doesn't match, return 400 (Bad Request)
        if (!user.get().getRole().equals(role)) {
            return ResponseEntity.status(400).body("Role mismatch");
        }

        // If all checks pass, return a success message
        return ResponseEntity.ok("Login successful");
    }

    // Other methods can be added here for additional user operations
}
