package com.team4.watchdiary.controller;

import com.team4.watchdiary.dao.MovieDao;
import com.team4.watchdiary.models.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    @Autowired
    private MovieDao movieDao;

    @GetMapping("/movies")
    public List<Movie> getAllGames () {
        List<Movie> movies = movieDao.getAllMovies();
        return movies;
    }
}
