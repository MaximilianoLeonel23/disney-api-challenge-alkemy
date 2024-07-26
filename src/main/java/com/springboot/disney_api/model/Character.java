package com.springboot.disney_api.model;

import com.springboot.disney_api.dto.character.CharacterRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Character")
@Table(name = "characters")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;
    @Column(nullable = false)
    private String name;
    private int age;
    private double weight;
    private String history;
    @ManyToMany
    @JoinTable(
            name = "character_movies",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<Movie> movies = new HashSet<>();
    @ManyToMany
    @JoinTable(
            name = "character_series",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "series_id")
    )
    private Set<Series> series = new HashSet<>();

    public Character(CharacterRequestDTO c) {
        this.name = c.name();
        this.image = c.image().orElse(null);
        this.age = c.age().orElse(0);
        this.weight = c.weight().orElse(0.0);
        this.history = c.history().orElse(null);
    }
}
