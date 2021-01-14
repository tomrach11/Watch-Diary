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
        
        //inefficient; final marketed product would have something different
        final String CREATE_TEMP_TABLE = "CREATE TABLE Temp AS (SELECT COUNT(season_Id) FROM Season \n" +
                                        "INNER JOIN TVShow ON TVShow.tvshow_Id=Season.tvshow_Id \n" +
                                        "WHERE TVShow.tvshow_Id=?);";
        jdbc.update(CREATE_TEMP_TABLE, season.getTvShowId());
        final String UPDATE_NUM_EPISODES = "UPDATE TVShow SET numSeasons=(SELECT * FROM Temp) WHERE tvshow_Id=?;";
        jdbc.update(UPDATE_NUM_EPISODES,season.getTvShowId());
        final String REMOVE_TEMP_TABLE = "DROP TABLE Temp;";
        jdbc.update(REMOVE_TEMP_TABLE);

        //get ID from database and save to season object
        int id = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        season.setSeasonID(id);

        return season;
    }


    @Override
    public List<Season> getAllSeasons() {
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
    public boolean updateSeason(Season season) {
        final String UPDATE_SEASON = "UPDATE Season SET" +
                "title = ?," +
                "note = ?," +
                "rating = ?," +
                "viewDate = ?," +
                "watched = ?," +
                "tvshow_Id = ?" +
                "WHERE season_Id = ?";
        return jdbc.update(UPDATE_SEASON,
                season.getTitle(),
                season.getNote(),
                season.getRating(),
                season.getViewDate(),
                season.isWatched(),
                season.getTvShowId(),
                season.getSeasonID()) > 0;

    }

    @Override
    public boolean deleteSeason(int id) {
        final String DELETE_SEASON = "DELETE FROM Season WHERE season_id = ?";
        return jdbc.update(DELETE_SEASON, id) > 0;

    }
}
