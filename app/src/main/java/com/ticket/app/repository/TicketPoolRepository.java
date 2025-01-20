package com.ticket.app.repository;

import com.ticket.app.model.TicketPool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository interface for accessing TicketPool data in the database
@Repository // Marks the interface as a Spring Data JPA repository
public interface TicketPoolRepository extends JpaRepository<TicketPool, Long> {
    // JpaRepository provides basic CRUD operations for the TicketPool entity

}
