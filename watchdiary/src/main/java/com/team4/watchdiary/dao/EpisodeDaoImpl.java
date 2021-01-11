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

        //get ID from database and save to episode object
        int id = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        episode.setEpisodeID(id);

        return episode;
    }

    @Override
    public List<Episode> getAllEpisode() {
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
    public void updateEpisode(Episode episode) {
        final String UPDATE_EPISODE = "UPDATE Episode SET" +
                "title = ?," +
                "note = ?," +
                "rating = ?," +
                "viewDate = ?," +
                "watched = ?," +
                "season_id = ?" +
                "WHERE episode_id = ?";
        jdbc.update(UPDATE_EPISODE,
                episode.getTitle(),
                episode.getNote(),
                episode.getRating(),
                episode.getViewDate(),
                episode.isWatched(),
                episode.getSeasonId(),
                episode.getEpisodeID());
    }

    @Override
    public void deleteEpisode(int id) {
        final String DELETE_EPISODE = "DELETE FROM Movie WHERE episode_id = ?";
        jdbc.update(DELETE_EPISODE, id);
    }
}
