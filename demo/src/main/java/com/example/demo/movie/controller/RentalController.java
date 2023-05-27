package com.example.demo.movie.controller;

import com.example.demo.movie.model.Movie;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/rental")
public class RentalController {

    private final RestTemplate restTemplate;
    String movieUrl = "http://localhost:8080/movies";

    public RentalController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable(value="id") long movieId) {
        return ResponseEntity.ok(restTemplate.getForEntity(movieUrl + "/" + movieId, Movie.class).getBody());
    }

    @PutMapping("/return-movie/{id}")
    public ResponseEntity<Movie> returnMovie(@PathVariable(value="id") long movieId) {
        HttpEntity<Boolean> request = new HttpEntity<Boolean>(true);

        return ResponseEntity.ok(restTemplate.exchange(movieUrl + "/set-available/" + movieId, HttpMethod.PUT, request ,Movie.class).getBody());
    }

    @PutMapping("/rent-movie/{id}")
    public ResponseEntity<Movie> rentMovie(@PathVariable(value="id") long movieId) {
        HttpEntity<Boolean> request = new HttpEntity<Boolean>(false);

        return ResponseEntity.ok(restTemplate.exchange(movieUrl + "/set-available/" + movieId, HttpMethod.PUT, request ,Movie.class).getBody());
    }
}
