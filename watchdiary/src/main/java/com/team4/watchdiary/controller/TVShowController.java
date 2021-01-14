package com.team4.watchdiary.controller;

import com.team4.watchdiary.dao.TVShowDao;
import com.team4.watchdiary.models.TVShow;
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
public class TVShowController {

    @Autowired
    private TVShowDao dao;

    @GetMapping("/tvshows")
    public List<TVShow> getAllShows () {
        List<TVShow> shows = dao.getAllTVShows();
        return shows;
    }
    
    @GetMapping("/tvshow/{id}")
    public ResponseEntity<TVShow> findByTVShowID(@PathVariable int id) {
        TVShow result = dao.getTVShowById(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    
    @PostMapping("/tvshow")
    @ResponseStatus(HttpStatus.CREATED)
    public TVShow add(@RequestBody TVShow show) {
        return dao.addTVShow(show);
    }
    
    
    @PutMapping("tvshow/{id}")
    public ResponseEntity update(@PathVariable int id, @RequestBody TVShow show) {
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (id != show.getTvShowID()) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (dao.updateTVShow(show)) {
            response = new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return response;
    }
    
    @DeleteMapping("tvshow/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        if (dao.deleteTVShow(id)) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
