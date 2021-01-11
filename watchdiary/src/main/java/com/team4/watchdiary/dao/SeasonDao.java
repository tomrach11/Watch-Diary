package com.team4.watchdiary.dao;

import com.team4.watchdiary.models.Season;

import java.util.List;

public interface SeasonDao {
    Season addSeason(Season season);
    List<Season> getAllSeason();
    Season getSeasonById(int id);
    void updateSeason(Season season);
    void deleteSeason(int id);
}
