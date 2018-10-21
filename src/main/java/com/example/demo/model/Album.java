package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "performer_id")
    private long performerId;

    @Column(name = "realize_year")
    private int realizeYear;

    protected Album() {
    }

    public Album(@NotNull long performerId) {
        this.performerId = performerId;
    }

    public Album(String title, @NotNull long performerId) {
        this.title = title;
        this.performerId = performerId;
    }

    public Album(String title, @NotNull long performerId, int realizeYear) {
        this.title = title;
        this.performerId = performerId;
        this.realizeYear = realizeYear;
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

    public long getPerformerId() {
        return performerId;
    }

    public void setPerformerId(long performerId) {
        this.performerId = performerId;
    }

    public int getRealizeYear() {
        return realizeYear;
    }

    public void setRealizeYear(int realizeYear) {
        this.realizeYear = realizeYear;
    }
}
