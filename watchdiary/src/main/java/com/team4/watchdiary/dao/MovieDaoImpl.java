package com.team4.watchdiary.dao;

import com.team4.watchdiary.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieDaoImpl implements MovieDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Movie addMovie(Movie movie) {
        //add to database
        final String INSERT_MOVIE = "INSERT INTO Movie (title, note, rating, viewDate, toWatch, watched) " +
                "VALUE (?, ?, ?, ?, ?, ?)";
        jdbc.update(INSERT_MOVIE,
                movie.getTitle(),
                movie.getNote(),
                movie.getRating(),
                movie.getViewDate(),
                movie.isToWatch(),
                movie.isWatched());

        //get ID from database and save to movie object
        int id = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        movie.setMovieID(id);

        return movie;
    }

    @Override
    public List<Movie> getAllMovies() {
        final String SELECT_ALL_MOVIE = "SELECT * FROM Movie";
        return jdbc.query(SELECT_ALL_MOVIE, new MovieMapper());
    }

    @Override
    public Movie getMovieById(int id) {
        //use try catch to avoid getting exception but return null instead
        try {
            final String SELECT_BY_MOVIE_ID = "SELECT * FROM Movie WHERE movie_id = ?";
            return jdbc.queryForObject(SELECT_BY_MOVIE_ID, new MovieMapper(), id);
        } catch (DataAccessException e) {
            //if id is not found in database return null
            return null;
        }
    }

    @Override
    public void updateMovie(Movie movie) {
        final String UPDATE_MOVIE = "UPDATE Movie SET " +
                "title = ?, " +
                "note = ?, " +
                "rating = ?, " +
                "viewDate = ?, " +
                "toWatch = ?, " +
                "watched = ? " +
                "WHERE movie_id = ?";
        jdbc.update(UPDATE_MOVIE,
                movie.getTitle(),
                movie.getNote(),
                movie.getRating(),
                movie.getViewDate(),
                movie.isToWatch(),
                movie.isWatched(),
                movie.getMovieID());
    }

    @Override
    public void deleteMovie(int id) {
        final String DELETE_MOVIE = "DELETE FROM Movie WHERE movie_id = ?";
        jdbc.update(DELETE_MOVIE, id);
    }
}
