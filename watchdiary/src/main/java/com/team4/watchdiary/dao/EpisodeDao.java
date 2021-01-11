package com.team4.watchdiary.dao;

import com.team4.watchdiary.models.Episode;

import java.util.List;

public interface EpisodeDao {
    Episode addEpisode(Episode episode);
    List<Episode> getAllEpisodes();
    Episode getEpisodeById(int id);
    void updateEpisode(Episode episode);
    void deleteEpisode(int id);
}
