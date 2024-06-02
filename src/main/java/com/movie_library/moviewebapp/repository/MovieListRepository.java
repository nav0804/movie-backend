package com.movie_library.moviewebapp.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.movie_library.moviewebapp.entity.Movie;
import com.movie_library.moviewebapp.entity.User;

public interface MovieListRepository extends CrudRepository<Movie,Integer>{
    List<Movie> findByUser(User user);
    // List<Movie> findByUserDetails(User user);
}
