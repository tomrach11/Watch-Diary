/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4.watchdiary.models;

import java.time.LocalDate;

/**
 * 
 * @author rachellemagaram
 * @author tomrach11
 */
public class Movie {
    private int movieID;
    private String title;
    private String note;
    private int rating;
    private LocalDate viewDate;
    private boolean watched;
    private boolean toWatch;

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalDate getViewDate() {
        return viewDate;
    }

    public void setViewDate(LocalDate viewDate) {
        this.viewDate = viewDate;
    }

    public boolean isWatched() {
        return watched;
    }

    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    public boolean isToWatch() {
        return toWatch;
    }

    public void setToWatch(boolean toWatch) {
        this.toWatch = toWatch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;

        Movie movie = (Movie) o;

        if (getMovieID() != movie.getMovieID()) return false;
        if (getRating() != movie.getRating()) return false;
        if (isWatched() != movie.isWatched()) return false;
        if (isToWatch() != movie.isToWatch()) return false;
        if (getTitle() != null ? !getTitle().equals(movie.getTitle()) : movie.getTitle() != null) return false;
        if (getNote() != null ? !getNote().equals(movie.getNote()) : movie.getNote() != null) return false;
        return getViewDate() != null ? getViewDate().equals(movie.getViewDate()) : movie.getViewDate() == null;
    }

    @Override
    public int hashCode() {
        int result = getMovieID();
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getNote() != null ? getNote().hashCode() : 0);
        result = 31 * result + getRating();
        result = 31 * result + (getViewDate() != null ? getViewDate().hashCode() : 0);
        result = 31 * result + (isWatched() ? 1 : 0);
        result = 31 * result + (isToWatch() ? 1 : 0);
        return result;
    }
}
