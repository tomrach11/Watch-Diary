package com.team4.watchdiary.dao;

import com.team4.watchdiary.models.Movie;

import java.util.List;

public interface MovieDao {
    Movie addMovie(Movie movie);
    List<Movie> getAllMovies();
    Movie getMovieById(int id);
    boolean updateMovie(Movie movie);
    boolean deleteMovie(int id);
    List<Movie> getToWatchMovies();
    List<Movie> getWatchedMovies();
}
