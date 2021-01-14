package com.team4.watchdiary.dao;

import com.team4.watchdiary.models.Season;

import java.util.List;

public interface SeasonDao {
    Season addSeason(Season season);
    List<Season> getAllSeasons();
    Season getSeasonById(int id);
    boolean updateSeason(Season season);
    boolean deleteSeason(int id);
}
