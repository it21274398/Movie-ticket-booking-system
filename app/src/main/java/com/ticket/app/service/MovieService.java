package com.ticket.app.service;

import com.ticket.app.model.Movie;
import com.ticket.app.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // Save movie (add or update)
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // Get all movies
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // Delete movie by ID
    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }

    // Find movie by ID
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }
}
