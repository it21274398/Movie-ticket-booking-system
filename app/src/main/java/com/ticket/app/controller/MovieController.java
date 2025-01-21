package com.ticket.app.controller;

import com.ticket.app.model.Movie;
import com.ticket.app.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    // Add Movie with image
    @PostMapping("/add")
    public ResponseEntity<Movie> addMovie(
            @RequestParam("name") String name,
            @RequestParam("genre") String genre,
            @RequestParam("director") String director,
            @RequestParam("year") Integer year,
            @RequestParam("image") MultipartFile image) throws IOException {

        Movie movie = new Movie(name, genre, director, year, image.getBytes());
        return ResponseEntity.ok(movieService.saveMovie(movie));
    }

    // Get all movies
    @GetMapping("/all")
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    // Update Movie with image
    @PutMapping("/update/{id}")
    public ResponseEntity<Movie> updateMovie(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("genre") String genre,
            @RequestParam("director") String director,
            @RequestParam("year") Integer year,
            @RequestParam("image") MultipartFile image) throws IOException {

        Movie movie = new Movie(name, genre, director, year, image.getBytes());
        movie.setId(id);
        return ResponseEntity.ok(movieService.saveMovie(movie));
    }

    // Delete Movie
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.ok("Movie deleted successfully");
    }
}
