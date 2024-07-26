package com.springboot.disney_api.model;

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
}
