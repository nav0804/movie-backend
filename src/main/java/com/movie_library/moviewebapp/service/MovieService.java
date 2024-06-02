package com.movie_library.moviewebapp.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.movie_library.moviewebapp.dto.ListMovieDto;
import com.movie_library.moviewebapp.dto.MovieDto;
import com.movie_library.moviewebapp.entity.Movie;
import com.movie_library.moviewebapp.entity.User;
import com.movie_library.moviewebapp.entity.UserSession;
import com.movie_library.moviewebapp.repository.MovieRepository;
import com.movie_library.moviewebapp.repository.UserRepository;
import com.movie_library.moviewebapp.repository.UserSessionRepository;


@Component
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired 
    private UserSessionRepository userSessionRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Movie> findByUser(User user){
        return movieRepository.findByUser(user);
    }

    public ResponseEntity<?> addMovie(MovieDto movieDto, String token){
        // Optional<UserSession> res = userSessionRepository.findBytoken(token);
        // UserSession existingUser = res.get();
        
        // User user = userRepository.findByEmail(existingUser.getEmail());

        UserSession res = userSessionRepository.findBytoken(token);
        User user = userRepository.findByEmail(res.getEmail());
        List<Movie>  existingMovies= movieRepository.findByUser(user);
        List<ListMovieDto> listMovies = movieDto.getListMovieDto();
        List<Movie> movies = new ArrayList<>();

        for (ListMovieDto listMovieDto : listMovies) {
            Movie movie = new Movie();
            movie.setUser(user);
            movie.setName(listMovieDto.getMovieName());
            movie.setPublic(listMovieDto.isPublic());
            movies.add(movie);
        }
        movies.addAll(existingMovies);
        movieRepository.saveAll(movies);
       // movieRepository.saveAll(movies);
        return new ResponseEntity<>("Added successfully",HttpStatus.CREATED);
    }

    public List<Movie> displayAll(){
        List<Movie> movies = new ArrayList<>();
        movieRepository.findAll().forEach(movies::add);
        return movies;
    }

    public Movie removeMovie(Integer id) throws Exception{
        Movie existingMovie = movieRepository.findById(id).get();
        if(existingMovie == null){
            throw new Exception("No movie found");
        }
        movieRepository.deleteById(id);;
        return existingMovie;
    }


    // public List<Movie> publicMovies(){
    //     return movieRepository.findByIsPublic();
    // }


    //user login -- enter token in add movie controller -- List of movies(enter movie name, isPublic) -- list size loop(add movie[i] to movie) -- return user details, list of
}
