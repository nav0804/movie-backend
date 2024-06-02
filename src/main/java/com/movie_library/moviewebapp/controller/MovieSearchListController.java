package com.movie_library.moviewebapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.movie_library.moviewebapp.service.MovieSearchListService;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping("/movies")
public class MovieSearchListController {
    @Autowired
    private MovieSearchListService movieSearchListService;


    @GetMapping("/search")
    public String searchMovies(@RequestParam String query) {
        return movieSearchListService.searchMovies(query);
    }


    @GetMapping("/{id}")
    public String getMovieDetails(@PathVariable String id) {
        return movieSearchListService.getMovieDetails(id);
    }
}
