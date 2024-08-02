package com.springboot.disney_api.controller;

import com.springboot.disney_api.dto.movie.MovieDetailedResponseDTO;
import com.springboot.disney_api.dto.movie.MovieRequestDTO;
import com.springboot.disney_api.dto.movie.MovieResponseDTO;
import com.springboot.disney_api.dto.movie.MovieUpdateDTO;
import com.springboot.disney_api.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/movies")
@Tag(name = "Movie", description = "Controller for managing movies")
public class MovieController {

    @Autowired
    private MovieService movieService;


    @PostMapping
    @Operation(summary = "Create a new movie")
    public ResponseEntity<MovieResponseDTO> createNewMovie(@RequestBody @Valid MovieRequestDTO movie) {
        MovieResponseDTO newMovie = movieService.createNewMovie(movie);
        URI uri = UriComponentsBuilder.fromPath("/movies/{id}").buildAndExpand(newMovie.id()).toUri();
        return ResponseEntity.created(uri).body(newMovie);
    }

    @GetMapping
    @Operation(summary = "Get all movies", description = "Get all movies with filters")
    public ResponseEntity<List<MovieResponseDTO>> getAllMovies(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<Long> genres,
            @RequestParam(required = false) String order
    ) {
        List<MovieResponseDTO> movies = movieService.getAllMovies(name, genres, order);
        if (movies != null) {
            return ResponseEntity.ok(movies);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a movie details", description = "Get a movie details by its ID")
    public ResponseEntity<MovieDetailedResponseDTO> getMovieById(@PathVariable Long id) {
        MovieDetailedResponseDTO movie = movieService.getMovieById(id);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return null;
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a movie", description = "Update a movie by its ID")
    public ResponseEntity<MovieResponseDTO> updateMovie(@PathVariable Long id, @RequestBody @Valid MovieUpdateDTO body) {
        MovieResponseDTO movie = movieService.updateMovie(id, body);
        if (movie != null) {
            return ResponseEntity.ok(movie);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a movie", description = "Delete a movie by its ID")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }

}
