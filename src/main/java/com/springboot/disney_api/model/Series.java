package com.springboot.disney_api.model;

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
}
