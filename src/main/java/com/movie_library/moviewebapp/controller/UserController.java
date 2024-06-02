package com.movie_library.moviewebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie_library.moviewebapp.dto.LoginDto;
import com.movie_library.moviewebapp.dto.RegisterDto;
import com.movie_library.moviewebapp.dto.UserDto;
import com.movie_library.moviewebapp.entity.User;
import com.movie_library.moviewebapp.entity.UserSession;
import com.movie_library.moviewebapp.service.UserService;


@CrossOrigin(origins= {"*"}, maxAge = 4800, allowCredentials = "false" )
@RestController
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) throws Exception{
        userService.registerUser(registerDto);
        return new ResponseEntity<>("Successfully registered", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserSession> login(@RequestBody LoginDto loginDto) throws Exception {
        return new ResponseEntity<>(userService.login(loginDto),HttpStatus.ACCEPTED);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader String token) throws Exception {
        return new ResponseEntity<>(userService.logout(token),HttpStatus.ACCEPTED);
    }

    @GetMapping("/allUsers")
    public List<User> user(){
        return userService.allUsers();
    }

    @GetMapping("/allMovies")
    public ResponseEntity<UserDto> allMovieList(@RequestHeader String token){
        return userService.displayAllMovies(token);
   } 

    
}
