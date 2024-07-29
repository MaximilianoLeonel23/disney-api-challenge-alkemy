package com.springboot.disney_api.service;

import com.springboot.disney_api.dto.movie.MovieDetailedResponseDTO;
import com.springboot.disney_api.dto.movie.MovieRequestDTO;
import com.springboot.disney_api.dto.movie.MovieResponseDTO;
import com.springboot.disney_api.dto.movie.MovieUpdateDTO;
import com.springboot.disney_api.model.Movie;
import com.springboot.disney_api.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public MovieResponseDTO createNewMovie(MovieRequestDTO movieDTO) {
        Movie movie = new Movie(movieDTO);
        Movie createdMovie = movieRepository.save(movie);
        return new MovieResponseDTO(
                createdMovie.getId(),
                createdMovie.getTitle(),
                createdMovie.getImage(),
                createdMovie.getCreationDate(),
                createdMovie.getRating()
        );
    }

    public List<MovieResponseDTO> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        if (!movies.isEmpty()) {
            return movies.stream().map(m -> new MovieResponseDTO(
                    m.getId(),
                    m.getTitle(),
                    m.getImage(),
                    m.getCreationDate(),
                    m.getRating()
            )).collect(Collectors.toList());
        } else return null;
    }

    public MovieDetailedResponseDTO getMovieById(Long id) {
        Optional<Movie> movieFound = movieRepository.findById(id);
        if (movieFound.isPresent()) {
            Movie movie = movieFound.get();
            return new MovieDetailedResponseDTO(
                    movie.getId(),
                    movie.getTitle(),
                    movie.getImage(),
                    movie.getCreationDate(),
                    movie.getRating()
            );
        } else return null;
    }

    public MovieResponseDTO updateMovie(Long id, MovieUpdateDTO body) {
        Optional<Movie> movieFound = movieRepository.findById(id);
        if (movieFound.isPresent()) {
            Movie movie = movieFound.get();
            movie.updateMovie(body);
            Movie updatedMovie = movieRepository.save(movie);
            return new MovieResponseDTO(
                    updatedMovie.getId(),
                    updatedMovie.getTitle(),
                    updatedMovie.getImage(),
                    updatedMovie.getCreationDate(),
                    updatedMovie.getRating()
            );

        } else return null;
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}
