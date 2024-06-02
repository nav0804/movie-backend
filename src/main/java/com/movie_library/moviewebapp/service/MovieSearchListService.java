package com.movie_library.moviewebapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MovieSearchListService {
    private final String apiKey = "46775658";
    private final String apiUrl = "https://www.omdbapi.com/";


    public String searchMovies(String query) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "?apikey=" + apiKey + "&s=" + query;
        return restTemplate.getForObject(url, String.class);
    }


    public String getMovieDetails(String imdbId) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "?apikey=" + apiKey + "&i=" + imdbId;
        return restTemplate.getForObject(url, String.class);
        
    }
}
