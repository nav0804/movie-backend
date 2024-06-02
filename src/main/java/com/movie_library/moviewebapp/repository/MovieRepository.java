package com.movie_library.moviewebapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.movie_library.moviewebapp.entity.Movie;
import com.movie_library.moviewebapp.entity.User;

import java.util.List;


public interface MovieRepository extends CrudRepository<Movie, Integer> {

    List<Movie> findByUser(User user);
    List<Movie> findByIsPublicTrue();
}
