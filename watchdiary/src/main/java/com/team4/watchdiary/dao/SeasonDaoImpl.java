package com.team4.watchdiary.dao;

import com.team4.watchdiary.models.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeasonDaoImpl implements SeasonDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Season addSeason(Season season) {
        //add to database
        final String INSERT_SEASON = "INSERT INTO Season (title, note, rating, viewDate, watched, TVShowId) " +
                "VALUE (?, ?, ?, ?, ?, ?)";
        jdbc.update(INSERT_SEASON,
                season.getTitle(),
                season.getNote(),
                season.getRating(),
                season.getViewDate(),
                season.isWatched(),
                season.getTvShowId());

        //get ID from database and save to season object
        int id = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        season.setSeasonID(id);

        return season;
    }

    private void insertEpisode(Season season) {
        
    }

    @Override
    public List<Season> getAllSeason() {
        final String SELECT_ALL_SEASON = "SELECT * FROM Season";
        return jdbc.query(SELECT_ALL_SEASON, new SeasonMapper());
    }

    @Override
    public Season getSeasonById(int id) {
        //use try catch to avoid getting exception but return null instead
        try {
            final String SELECT_BY_SEASON_BY_ID = "SELECT * FROM Season WHERE season_id = ?";
            return jdbc.queryForObject(SELECT_BY_SEASON_BY_ID, new SeasonMapper(), id);
        } catch (DataAccessException e) {
            //if id is not found in database return null
            return null;
        }
    }

    @Override
    public void updateSeason(Season season) {

    }

    @Override
    public void deleteSeason(int id) {

    }
}
