package com.movie_library.moviewebapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.movie_library.moviewebapp.dto.UserDto;
import com.movie_library.moviewebapp.entity.User;

public interface UserRepository extends CrudRepository <User,Integer>{

    User findByEmail(String email);
    // UserDto findByEmailWtihoutPassword(String email);
    // User findByUsername(String name);
    // User findById(Integer id);
    UserDto findByUsername(String name);
}
