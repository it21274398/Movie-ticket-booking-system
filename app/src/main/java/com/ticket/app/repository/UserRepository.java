package com.ticket.app.repository;

import com.ticket.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Repository interface for accessing User data in the database
public interface UserRepository extends JpaRepository<User, Long> {
    // JpaRepository provides basic CRUD operations for the User entity

    // Custom query to find a user by their username
    Optional<User> findByUsername(String username); // Returns an Optional to handle the case where the user may not be found
}
