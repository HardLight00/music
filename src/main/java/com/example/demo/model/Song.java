package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "songs")
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Column(name = "title")
    private String title;

    @Column(name = "lyrics")
    private String lyrics;

    protected Song() {
    }

    public Song(@NotNull String title) {
        this.title = title;
    }

    public Song(@NotNull String title, String lyrics) {
        this.title = title;
        this.lyrics = lyrics;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
