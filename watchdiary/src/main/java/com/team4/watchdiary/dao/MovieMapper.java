package com.team4.watchdiary.dao;

import com.team4.watchdiary.models.Movie;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MovieMapper implements RowMapper<Movie> {
    @Override
    public Movie mapRow(ResultSet rs, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setMovieID(rs.getInt("movie_Id"));
        movie.setTitle(rs.getString("title"));
        movie.setNote(rs.getString("note"));
        movie.setRating(rs.getInt("rating"));
        LocalDate viewDate = rs.getDate("viewDate").toLocalDate();
        movie.setViewDate(viewDate);
        movie.setToWatch(rs.getBoolean("toWatch"));
        movie.setWatched(rs.getBoolean("watched"));
        return movie;
    }
}
