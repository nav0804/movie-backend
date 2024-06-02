package com.movie_library.moviewebapp.repository;

import org.springframework.data.repository.CrudRepository;

import com.movie_library.moviewebapp.entity.UserSession;

public interface UserSessionRepository extends CrudRepository<UserSession,Integer>{

    UserSession findBytoken(String token);
	UserSession findByUserId(Integer userId);

}
