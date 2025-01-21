package com.ticket.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String genre;
    private String director;
    private Integer year;

    @Lob
    private byte[] image;

    // Default Constructor
    public Movie() {}

    public Movie(String name, String genre, String director, Integer year, byte[] image) {
        this.name = name;
        this.genre = genre;
        this.director = director;
        this.year = year;
        this.image = image;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
    public Integer getYear() { return year; }
    public void setYear(Integer year) { this.year = year; }
    public byte[] getImage() { return image; }
    public void setImage(byte[] image) { this.image = image; }
}
