package com.team4.watchdiary.dao;

import com.team4.watchdiary.TestApplicationConfiguration;
import com.team4.watchdiary.models.Movie;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
class MovieDaoTest {

    @Autowired
    MovieDao movieDao;

    @BeforeEach
    void setUp() {
        List<Movie> movies = movieDao.getAllMovies();
        for (Movie movie : movies) {
            movieDao.deleteMovie(movie.getMovieID());
        }
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addMovie() {
        Movie movie = new Movie();
        movie.setTitle("Movie1");
        movie.setNote("Note1");
        movie.setRating(5);
        movie.setViewDate(LocalDate.now());
        movie.setToWatch(true);
        movie.setWatched(false);
        movie = movieDao.addMovie(movie);

        Movie fromDao = movieDao.getMovieById(movie.getMovieID());

        assertEquals(movie, fromDao);
    }

    @Test
    void getAllMoviesAndGetById() {
        Movie movie = new Movie();
        movie.setTitle("Movie1");
        movie.setNote("Note1");
        movie.setRating(5);
        movie.setViewDate(LocalDate.now());
        movie.setToWatch(true);
        movie.setWatched(false);
        movie = movieDao.addMovie(movie);

        Movie movie2 = new Movie();
        movie2.setTitle("Movie2");
        movie2.setNote("Note2");
        movie2.setRating(5);
        movie2.setViewDate(LocalDate.now());
        movie2.setToWatch(true);
        movie2.setWatched(false);
        movie2 = movieDao.addMovie(movie2);

        List<Movie> fromDao = movieDao.getAllMovies();

        assertEquals(2, fromDao.size());
    }

    @Test
    void updateMovie() {
        Movie movie = new Movie();
        movie.setTitle("Movie1");
        movie.setNote("Note1");
        movie.setRating(5);
        movie.setViewDate(LocalDate.now());
        movie.setToWatch(true);
        movie.setWatched(false);
        movie = movieDao.addMovie(movie);
        //change and update
        movie.setWatched(true);
        movieDao.updateMovie(movie);

        Movie fromDao = movieDao.getMovieById(movie.getMovieID());
        assertEquals(movie, fromDao);
    }

    @Test
    void deleteMovie() {
        Movie movie = new Movie();
        movie.setTitle("Movie1");
        movie.setNote("Note1");
        movie.setRating(5);
        movie.setViewDate(LocalDate.now());
        movie.setToWatch(true);
        movie.setWatched(false);
        movie = movieDao.addMovie(movie);

        Movie movie2 = new Movie();
        movie2.setTitle("Movie2");
        movie2.setNote("Note2");
        movie2.setRating(5);
        movie2.setViewDate(LocalDate.now());
        movie2.setToWatch(true);
        movie2.setWatched(false);
        movie2 = movieDao.addMovie(movie2);

        movieDao.deleteMovie(movie2.getMovieID());
        List<Movie> fromDao = movieDao.getAllMovies();
        

        assertEquals(1, fromDao.size());
        assertNull(movieDao.getMovieById(movie2.getMovieID()));
    }
}