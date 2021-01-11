/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4.watchdiary.models;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author rachellemagaram
 * @author tomrach11
 */
public class TVShow {
    private int tvShowID;
    private String title;
    private String note;
    private int rating;
    private LocalDate viewDate;
    private boolean watched;
    private boolean toWatch;
    private ArrayList<Season> arr;

    public int getTvShowID() {
        return tvShowID;
    }

    public void setTvShowID(int tvShowID) {
        this.tvShowID = tvShowID;
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

    public ArrayList<Season> getArr() {
        return arr;
    }

    public void setArr(ArrayList<Season> arr) {
        this.arr = arr;
    }
    
    
    
}
