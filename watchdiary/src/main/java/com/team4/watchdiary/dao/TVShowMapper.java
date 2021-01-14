package com.team4.watchdiary.dao;

import com.team4.watchdiary.models.TVShow;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class TVShowMapper implements RowMapper<TVShow> {
    @Override
    public TVShow mapRow(ResultSet rs, int i) throws SQLException {
        TVShow tvShow = new TVShow();
        tvShow.setTvShowID(rs.getInt("tvShow_id"));
        tvShow.setTitle(rs.getString("title"));
        tvShow.setNote(rs.getString("note"));
        tvShow.setRating(rs.getInt("rating"));
        LocalDate viewDate = rs.getDate("viewDate").toLocalDate();
        tvShow.setViewDate(viewDate);
        tvShow.setToWatch(rs.getBoolean("toWatch"));
        tvShow.setWatched(rs.getBoolean("watched"));
        return tvShow;
    }
}