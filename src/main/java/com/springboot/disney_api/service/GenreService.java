package com.springboot.disney_api.service;

import com.springboot.disney_api.dto.genre.GenreDetailedResponseDTO;
import com.springboot.disney_api.dto.genre.GenreResponseDTO;
import com.springboot.disney_api.model.Genre;
import com.springboot.disney_api.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;


    public List<GenreResponseDTO> getAllServices() {
        List<Genre> genres = genreRepository.findAll();
        if (!genres.isEmpty()) {
            return genres.stream().map(g -> new GenreResponseDTO(
                    g.getId(),
                    g.getName(),
                    g.getImage()
            )).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public GenreDetailedResponseDTO getGenreById(Long id) {
        Optional<Genre> genreFound = genreRepository.findById(id);
        if (genreFound.isPresent()) {
            Genre genre = genreFound.get();
            return new GenreDetailedResponseDTO(
                    genre.getId(),
                    genre.getName(),
                    genre.getImage()
            );
        } else return null;
    }
}
