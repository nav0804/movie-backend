package com.movie_library.moviewebapp.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    private Integer userId;
    private String username;
    private String email;
    private LocalDateTime createdOn;
    private List<ListMovieDto> movieList;
}
