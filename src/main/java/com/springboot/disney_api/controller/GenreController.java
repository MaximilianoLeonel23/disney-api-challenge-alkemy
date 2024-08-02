package com.springboot.disney_api.controller;

import com.springboot.disney_api.dto.genre.GenreDetailedResponseDTO;
import com.springboot.disney_api.dto.genre.GenreResponseDTO;
import com.springboot.disney_api.model.Genre;
import com.springboot.disney_api.service.GenreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
@Tag(name = "Genre", description = "Controller for managing genres")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping
    @Operation(summary = "Get all genres", description = "Get a list of all genres")
    public ResponseEntity<List<GenreResponseDTO>> getAllGenres() {
        List<GenreResponseDTO> genres = genreService.getAllServices();
        if (!genres.isEmpty()) {
            return ResponseEntity.ok(genres);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a genre", description = "Get a genre by its ID")
    public ResponseEntity<GenreDetailedResponseDTO> getGenreById(@PathVariable Long id) {
        GenreDetailedResponseDTO genre = genreService.getGenreById(id);
        if (genre != null) {
            return ResponseEntity.ok(genre);
        } else return ResponseEntity.notFound().build();
    }
}
