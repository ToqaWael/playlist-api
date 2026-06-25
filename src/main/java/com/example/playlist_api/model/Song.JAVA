package com.example.playlist_api.model;

import jakarta.persistence.*;

@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String artist;

    @ManyToOne // Many songs can belong to one playlist
    @JoinColumn(name = "playlist_id") // Foreign key column name in Song table referencing Playlist table
    private Playlist playlist;

    // so that jpa can create an instance of the class
    public Song() {
    }

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;

    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

}