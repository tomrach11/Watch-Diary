package com.team4.watchdiary.dao;

import com.team4.watchdiary.models.TVShow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TVShowDaoImpl implements TVShowDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public TVShow addTVShow(TVShow tvShow) {
        //add to database
        final String INSERT_TVSHOW = "INSERT INTO TVShow (title, note, rating, viewDate, toWatch, watched) " +
                "VALUE (?, ?, ?, ?, ?, ?)";
        jdbc.update(INSERT_TVSHOW,
                tvShow.getTitle(),
                tvShow.getNote(),
                tvShow.getRating(),
                tvShow.getViewDate(),
                tvShow.isToWatch(),
                tvShow.isWatched());

        //get ID from database and save to TVShow object
        int id = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        tvShow.setTvShowID(id);

        return tvShow;
    }

    @Override
    public List<TVShow> getAllTVShows() {
        final String SELECT_ALL_TVSHOW = "SELECT * FROM TVShow";
        return jdbc.query(SELECT_ALL_TVSHOW, new TVShowMapper());
    }

    @Override
    public TVShow getTVShowById(int id) {
        //use try catch to avoid getting exception but return null instead
        try {
            final String SELECT_BY_TVSHOW_ID = "SELECT * FROM TVShow WHERE tvShow_id = ?";
            return jdbc.queryForObject(SELECT_BY_TVSHOW_ID, new TVShowMapper(), id);
        } catch (DataAccessException e) {
            //if id is not found in database return null
            return null;
        }
    }

    @Override
    public boolean updateTVShow(TVShow tvShow) {
        return true;

    }

    @Override
    public boolean deleteTVShow(int id) {
        return true;

    }
}
