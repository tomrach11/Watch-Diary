package com.team4.watchdiary.dao;

import com.team4.watchdiary.models.Episode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EpisodeDaoImpl implements EpisodeDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Episode addEpisode(Episode episode) {
        //add to database
        final String INSERT_EPISODE = "INSERT INTO Episode (title, note, rating, viewDate, watched, season_id) " +
                "VALUE (?, ?, ?, ?, ?)";
        jdbc.update(INSERT_EPISODE,
                episode.getTitle(),
                episode.getNote(),
                episode.getRating(),
                episode.getViewDate(),
                episode.isWatched(),
                episode.getSeasonId());
        
        //inefficient; final marketed product would have something different
        final String CREATE_TEMP_TABLE = "CREATE TABLE Temp AS (SELECT COUNT(episode_ID) FROM Episode " +
                                        "INNER JOIN Season ON Season.season_Id=Episode.season_Id " +
                                        "WHERE Season.season_Id=?);";
        jdbc.update(CREATE_TEMP_TABLE, episode.getSeasonId());
        final String UPDATE_NUM_EPISODES = "UPDATE Season SET numEpisodes=(SELECT * FROM Temp) WHERE season_Id=?;";
        jdbc.update(UPDATE_NUM_EPISODES,episode.getSeasonId());
        final String REMOVE_TEMP_TABLE = "DROP TABLE Temp;";
        jdbc.update(REMOVE_TEMP_TABLE);

        //get ID from database and save to episode object
        int id = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        episode.setEpisodeID(id);

        return episode;
    }

    @Override
    public List<Episode> getAllEpisodes() {
        final String SELECT_ALL_EPISODE = "SELECT * FROM Episode";
        return jdbc.query(SELECT_ALL_EPISODE, new EpisodeMapper());
    }

    @Override
    public Episode getEpisodeById(int id) {
        //use try catch to avoid getting exception but return null instead
        try {
            final String SELECT_BY_EPISODE_BY_ID = "SELECT * FROM Episode WHERE episode_id = ?";
            return jdbc.queryForObject(SELECT_BY_EPISODE_BY_ID, new EpisodeMapper(), id);
        } catch (DataAccessException e) {
            //if id is not found in database return null
            return null;
        }
    }

    @Override
    public boolean updateEpisode(Episode episode) {
        final String UPDATE_EPISODE = "UPDATE Episode SET" +
                "title = ?," +
                "note = ?," +
                "rating = ?," +
                "viewDate = ?," +
                "watched = ?," +
                "season_id = ?" +
                "WHERE episode_id = ?";
        return jdbc.update(UPDATE_EPISODE,
                episode.getTitle(),
                episode.getNote(),
                episode.getRating(),
                episode.getViewDate(),
                episode.isWatched(),
                episode.getSeasonId(),
                episode.getEpisodeID()) > 0;
    }

    @Override
    public boolean deleteEpisode(int id) {
        final String DELETE_EPISODE = "DELETE FROM Episode WHERE episode_id = ?";
        return jdbc.update(DELETE_EPISODE, id) > 0;
    }
}
