package com.ticket.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ticket.app.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
