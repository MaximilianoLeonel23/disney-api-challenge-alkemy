package com.springboot.disney_api.service;

import com.springboot.disney_api.dto.genre.GenreDetailedResponseDTO;
import com.springboot.disney_api.dto.genre.GenreResponseDTO;
import com.springboot.disney_api.dto.movie.MovieResponseDTO;
import com.springboot.disney_api.dto.series.SeriesResponseDTO;
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
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new RuntimeException("Genre not found"));
        List<MovieResponseDTO> movies = genre.getMovies().stream().map(m -> new MovieResponseDTO(
                m.getId(),
                m.getTitle(),
                m.getImage(),
                m.getCreationDate(),
                m.getRating()
        )).toList();
        List<SeriesResponseDTO> series = genre.getSeries().stream().map(s -> new SeriesResponseDTO(
                s.getId(),
                s.getTitle(),
                s.getImage(),
                s.getCreationDate(),
                s.getRating(),
                s.getSeasons(),
                s.getEpisodes()
        )).toList();
        return new GenreDetailedResponseDTO(
                genre.getId(),
                genre.getName(),
                genre.getImage(),
                movies,
                series
        );
    }
}
