package com.example.demo.movie.controller;

import com.example.demo.movie.model.Movie;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get movie by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the Movie",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad gateway",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "502",
                    description = "Bad gateway",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "504",
                    description = "Gateway timeout",
                    content = @Content
            )
    })
    public ResponseEntity<Movie> getMovie(@PathVariable(value="id") long movieId) {
        return ResponseEntity.ok(restTemplate.getForEntity(movieUrl + "/" + movieId, Movie.class).getBody());
    }

    @PutMapping("/return-movie/{id}")
    @Operation(summary = "Return movie")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the Movie",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = Movie.class))}
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad gateway",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "502",
                    description = "Bad gateway",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "504",
                    description = "Gateway timeout",
                    content = @Content
            )
    })
    public ResponseEntity<Movie> returnMovie(@PathVariable(value="id") long movieId) {
        HttpEntity<Boolean> request = new HttpEntity<Boolean>(true);

        return ResponseEntity.ok(restTemplate.exchange(movieUrl + "/set-available/" + movieId, HttpMethod.PUT, request ,Movie.class).getBody());
    }

    @PutMapping("/rent-movie/{id}")
    @Operation(summary = "Rent movie")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Movie was rented"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad gateway",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal server error",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "502",
                    description = "Bad gateway",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "504",
                    description = "Gateway timeout",
                    content = @Content
            )
    })
    public ResponseEntity<Movie> rentMovie(@PathVariable(value="id") long movieId) {
        HttpEntity<Boolean> request = new HttpEntity<Boolean>(false);

        return ResponseEntity.ok(restTemplate.exchange(movieUrl + "/set-available/" + movieId, HttpMethod.PUT, request ,Movie.class).getBody());
    }
}
