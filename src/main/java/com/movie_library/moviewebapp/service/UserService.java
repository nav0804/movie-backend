package com.movie_library.moviewebapp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.movie_library.moviewebapp.dto.ListMovieDto;
import com.movie_library.moviewebapp.dto.LoginDto;
import com.movie_library.moviewebapp.dto.RegisterDto;
import com.movie_library.moviewebapp.dto.UserDto;
import com.movie_library.moviewebapp.entity.Movie;
import com.movie_library.moviewebapp.entity.User;
import com.movie_library.moviewebapp.entity.UserSession;
import com.movie_library.moviewebapp.repository.MovieListRepository;
import com.movie_library.moviewebapp.repository.UserRepository;
import com.movie_library.moviewebapp.repository.UserSessionRepository;

@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserSessionRepository userSessionRepository;
    @Autowired
    private MovieListRepository movieListRepository;

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public void registerUser(RegisterDto registerDto) throws Exception {
        // Optional<User> existingUser =
        // userRepository.findByEmail(registerDto.getEmail());

        // if(existingUser.isPresent()){
        // throw new Exception("User with this email already exists");
        // }

        User user = new User();
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(registerDto.getPassword());
        user.setCreatedOn(LocalDateTime.now());
        userRepository.save(user);
    }

    public UserSession login(LoginDto loginDto) throws Exception {
        // Optional<User> opt = userRepository.findByEmail(loginDto.getEmail());
        // if(!opt.isPresent())
        // throw new Exception("User does not exist");
        // User existingUser = opt.get();
        // Optional<UserSession> res =
        // userSessionRepository.findByUserId(existingUser.getUserId());

        // if(res.isPresent()) {

        // UserSession user = res.get();

        // if(user.getSessionEndTime().isBefore(LocalDateTime.now())) {
        // userSessionRepository.delete(user);
        // }
        // else
        // throw new Exception("User already logged in");

        // }
        User existingUser = userRepository.findByEmail(loginDto.getEmail());
        UserSession userSession = new UserSession();
        if (existingUser.getPassword().equals(loginDto.getPassword())) {
            userSession.setEmail(loginDto.getEmail());
            UUID uuid = UUID.randomUUID();
            String token = uuid.toString().split("-")[0];
            userSession.setToken(token);
            userSession.setUserId(existingUser.getUserId());
            userSession.setSessionStartTime(LocalDateTime.now());
            userSession.setSessionEndTime(LocalDateTime.now().plusHours(1));
        }
        return userSessionRepository.save(userSession);
    }

    public String logout(String token) throws Exception {
        // Optional<UserSession> opt = userSessionRepository.findBytoken(token);
        // if(!opt.isPresent())
        // throw new LoginException("User not logged in. Invalid session token. Login
        // Again.");
        // UserSession session = opt.get();

        UserSession opt = userSessionRepository.findBytoken(token);
        userSessionRepository.delete(opt);
        return "Logged out successfully";
    }

    public ResponseEntity<UserDto> displayAllMovies(String token){
        UserSession existingUser = userSessionRepository.findBytoken(token);
        User orignalUser = userRepository.findByEmail(existingUser.getEmail());
        List<Movie> allMovies = movieListRepository.findByUser(orignalUser);

        UserDto newUser = new UserDto();
        newUser.setUserId(orignalUser.getUserId());
        newUser.setUsername(orignalUser.getUsername());
        newUser.setEmail(orignalUser.getEmail());
        newUser.setCreatedOn(orignalUser.getCreatedOn());
        List<ListMovieDto> movieDtoList = new ArrayList<>();
        for (Movie movie : allMovies) {
            ListMovieDto movieDto = new ListMovieDto();
            movieDto.setMovieName(movie.getName());
            movieDto.setPublic(movie.isPublic());
            movieDtoList.add(movieDto);
        }
        newUser.setMovieList(movieDtoList);

        return new ResponseEntity<>(newUser,HttpStatus.OK);
    }

    public UserDto findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
