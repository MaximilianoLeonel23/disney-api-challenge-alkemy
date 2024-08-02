package com.springboot.disney_api.model;

import com.springboot.disney_api.dto.series.SeriesRequestDTO;
import com.springboot.disney_api.dto.series.SeriesUpdateDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Series")
@Table(name = "series")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    private String image;
    private LocalDate creationDate;
    private Double rating;
    private Integer seasons;
    private Integer episodes;

    @ManyToMany(mappedBy = "series")
    private Set<Character> characters = new HashSet<>();

    @ManyToMany(mappedBy = "series")
    private Set<Genre> genres = new HashSet<>();

    public Series(SeriesRequestDTO s) {
        this.title = s.title();
        this.image = s.image().orElse(null);
        this.creationDate = s.creationDate().orElse(null);
        this.rating = s.rating().orElse(0.0);
        this.seasons = s.seasons().orElse(1);
        this.episodes = s.episodes().orElse(1);
    }

    public void updateSeries(SeriesUpdateDTO s) {
        if (s.title() != null && !s.title().isBlank()) {
            this.title = s.title();
        }
        if (s.image() != null && !s.image().isBlank()) {
            this.image = s.image();
        }
        if (s.creationDate() != null) {
            this.creationDate = s.creationDate();
        }
        if (s.rating() != null && s.rating() > 0.0) {
            this.rating = s.rating();
        }
        if (s.seasons() != null && s.seasons() > 0) {
            this.seasons = s.seasons();
        }
        if (s.episodes() != null && s.episodes() > 0) {
            this.episodes = s.episodes();
        }
    }
}
