package com.team4.watchdiary.dao;

import com.team4.watchdiary.models.TVShow;

import java.util.List;

public interface TVShowDao {
    TVShow addTVShow(TVShow tvShow);
    List<TVShow> getAllTVShows();
    TVShow getTVShowById(int id);
    boolean updateTVShow(TVShow tvShow);
    boolean deleteTVShow(int id);
}
