package com.movie_library.moviewebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie_library.moviewebapp.dto.MovieDto;
import com.movie_library.moviewebapp.entity.Movie;
import com.movie_library.moviewebapp.service.MovieService;

@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping(value = "/movie")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @PostMapping("/addMovieLists")
    public ResponseEntity<?> createList(@RequestBody MovieDto movieDto , @RequestHeader String token){
        return movieService.addMovie(movieDto, token);
    }

    @GetMapping("/allMovies")
    public List<Movie> displayAllMovies(){
        return movieService.displayAll();
    }

    // @DeleteMapping("/allMovies/{id}")
    // public Movie removeMovie(@PathVariable Integer id) throws Exception{
    //     return movieService.removeMovie(id);
    // }

}
