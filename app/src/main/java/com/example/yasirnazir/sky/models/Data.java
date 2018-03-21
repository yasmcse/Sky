package com.example.yasirnazir.sky.models;

/**
 * Created by yasirnazir on 3/20/18.
 */

public class Data {
    private String genre;

    private long id;

    private String title;

    private String poster;

    private String year;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "ClassPojo [genre = " + genre + ", id = " + id + ", title = " + title + ", poster = " + poster + ", year = " + year + "]";
    }
}