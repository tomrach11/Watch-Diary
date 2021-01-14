package com.team4.watchdiary.controller;

import com.team4.watchdiary.dao.MovieDao;
import com.team4.watchdiary.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//@CrossOrigin //may need to add parameter of where JavaScript is coming from (URL)
@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieDao movieDao;

    @GetMapping("/movies")
    public List<Movie> getAllMovies () {
        List<Movie> movies = movieDao.getAllMovies();
        return movies;
    }
    
    @GetMapping("/watchedmovies")
    public List<Movie> getAllWatchedMovies () {
        List<Movie> movies = movieDao.getWatchedMovies();
        return movies;
    }
    
    @GetMapping("/moviestowatch")
    public List<Movie> getAllMoviesToWatch () {
        List<Movie> movies = movieDao.getToWatchMovies();
        return movies;
    }
    
    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> findByMovieID(@PathVariable int id) {
        Movie result = movieDao.getMovieById(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/movie")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie add(@RequestBody Movie movie) {
        return movieDao.addMovie(movie);
    }
    
    
    @PutMapping("movie/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody Movie movie) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (id != movie.getMovieID()) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (movieDao.updateMovie(movie)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }
    
    @DeleteMapping("movie/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if (movieDao.deleteMovie(id)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
