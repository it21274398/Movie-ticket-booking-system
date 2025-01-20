package com.ticket.app.repository;

import com.ticket.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository interface for accessing User data in the database
@Repository // Marks the interface as a Spring Data JPA repository
public interface CustomerRepository extends JpaRepository<User, Long> {
    // JpaRepository provides CRUD operations for the User entity
    // The repository will automatically implement the methods for interacting with the User table in the database
}
