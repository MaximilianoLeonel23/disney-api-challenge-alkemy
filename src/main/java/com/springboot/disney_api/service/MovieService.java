package com.springboot.disney_api.service;

import com.springboot.disney_api.dto.character.CharacterResponseDTO;
import com.springboot.disney_api.dto.genre.GenreResponseDTO;
import com.springboot.disney_api.dto.movie.MovieDetailedResponseDTO;
import com.springboot.disney_api.dto.movie.MovieRequestDTO;
import com.springboot.disney_api.dto.movie.MovieResponseDTO;
import com.springboot.disney_api.dto.movie.MovieUpdateDTO;
import com.springboot.disney_api.model.Character;
import com.springboot.disney_api.model.Genre;
import com.springboot.disney_api.model.Movie;
import com.springboot.disney_api.repository.GenreRepository;
import com.springboot.disney_api.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    public List<MovieResponseDTO> getAllMovies(
            String name,
            List<Long> genres,
            String order
    ) {
        List<Movie> movies = movieRepository.findAllWithFilters(name, genres);

        if (order != null && order.equalsIgnoreCase("DESC")) {
            movies.sort(Comparator.comparing(Movie::getCreationDate).reversed());
        } else if (order != null && order.equalsIgnoreCase("ASC")) {
            movies.sort(Comparator.comparing(Movie::getCreationDate));
        }

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
        Movie movie = movieRepository.findById(id).orElseThrow(() -> new RuntimeException("Movie not found"));
        List<GenreResponseDTO> genres = movie.getGenres().stream().map(g -> new GenreResponseDTO(
                g.getId(),
                g.getName(),
                g.getImage()
        )).toList();
        List<CharacterResponseDTO> characters = movie.getCharacters().stream().map(c -> new CharacterResponseDTO(
                c.getId(),
                c.getName(),
                c.getImage(),
                c.getAge(),
                c.getWeight(),
                c.getHistory()
        )).toList();
        return new MovieDetailedResponseDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getImage(),
                movie.getCreationDate(),
                movie.getRating(),
                characters,
                genres
        );

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
