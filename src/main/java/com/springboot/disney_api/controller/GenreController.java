package com.springboot.disney_api.controller;

import com.springboot.disney_api.dto.genre.GenreDetailedResponseDTO;
import com.springboot.disney_api.dto.genre.GenreResponseDTO;
import com.springboot.disney_api.model.Genre;
import com.springboot.disney_api.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
public class GenreController {
    @Autowired
    private GenreService genreService;

    @GetMapping
    public ResponseEntity<List<GenreResponseDTO>> getAllGenres() {
        List<GenreResponseDTO> genres = genreService.getAllServices();
        if (!genres.isEmpty()) {
            return ResponseEntity.ok(genres);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenreDetailedResponseDTO> getGenreById(@PathVariable Long id) {
        GenreDetailedResponseDTO genre = genreService.getGenreById(id);
        if (genre != null) {
            return ResponseEntity.ok(genre);
        } else return ResponseEntity.notFound().build();
    }
}
