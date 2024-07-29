package com.springboot.disney_api.model;

import com.springboot.disney_api.dto.movie.MovieRequestDTO;
import com.springboot.disney_api.dto.movie.MovieUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Movie")
@Table(name = "movies")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String image;
    private LocalDate creationDate;
    private Double rating;

    @ManyToMany(mappedBy = "movies")
    private Set<Character> characters = new HashSet<>();

    @ManyToMany(mappedBy = "movies")
    private Set<Genre> genres = new HashSet<>();

    public Movie(MovieRequestDTO movieDTO) {
        this.title = movieDTO.title();
        this.image = movieDTO.image().orElse(null);
        this.creationDate = movieDTO.creationDate().orElse(null);
        this.rating = movieDTO.rating().orElse(0.0);
    }

    public void updateMovie(MovieUpdateDTO movieDTO) {
        if (movieDTO.title() != null && !movieDTO.title().isBlank()) {
            this.title = movieDTO.title();
        }
        if (movieDTO.image() != null && !movieDTO.image().isBlank()) {
            this.image = movieDTO.image();
        }
        if (movieDTO.creationDate() != null) {
            this.creationDate = movieDTO.creationDate();
        }
        if (movieDTO.rating() != null && movieDTO.rating() > 0) {
            this.rating = movieDTO.rating();
        }
    }
}
