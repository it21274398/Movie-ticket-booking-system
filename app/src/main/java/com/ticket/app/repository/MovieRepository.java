package com.ticket.app.repository;

import com.ticket.app.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Marks the interface as a Spring Data JPA repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    // Custom queries can be added here if needed
}
