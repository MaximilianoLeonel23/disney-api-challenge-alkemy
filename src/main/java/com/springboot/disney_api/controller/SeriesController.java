package com.springboot.disney_api.controller;

import com.springboot.disney_api.dto.movie.MovieDetailedResponseDTO;
import com.springboot.disney_api.dto.movie.MovieRequestDTO;
import com.springboot.disney_api.dto.movie.MovieResponseDTO;
import com.springboot.disney_api.dto.movie.MovieUpdateDTO;
import com.springboot.disney_api.dto.series.SeriesDetailedResponseDTO;
import com.springboot.disney_api.dto.series.SeriesRequestDTO;
import com.springboot.disney_api.dto.series.SeriesResponseDTO;
import com.springboot.disney_api.dto.series.SeriesUpdateDTO;
import com.springboot.disney_api.service.MovieService;
import com.springboot.disney_api.service.SeriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/series")
@Tag(name = "Series", description = "Controller for managing series")
public class SeriesController {

    @Autowired
    private SeriesService seriesService;


    @PostMapping
    @Operation(summary = "Create a new series")
    public ResponseEntity<SeriesResponseDTO> createNewSeries(@RequestBody @Valid SeriesRequestDTO series) {
        SeriesResponseDTO newSeries = seriesService.createNewSeries(series);
        URI uri = UriComponentsBuilder.fromPath("/series/{id}").buildAndExpand(newSeries.id()).toUri();
        return ResponseEntity.created(uri).body(newSeries);
    }

    @GetMapping
    @Operation(summary = "Get all series", description = "Get all series with filters")
    public ResponseEntity<List<SeriesResponseDTO>> getAllSeries(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) List<Long> genres,
            @RequestParam(required = false) String order
    ) {
        List<SeriesResponseDTO> series = seriesService.getAllSeries(name, genres, order);
        if (series != null) {
            return ResponseEntity.ok(series);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get series details", description = "Get series details by its ID")
    public ResponseEntity<SeriesDetailedResponseDTO> getSeriesById(@PathVariable Long id) {
        SeriesDetailedResponseDTO series = seriesService.getSeriesById(id);
        if (series != null) {
            return ResponseEntity.ok(series);
        } else {
            return null;
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a series", description = "Update a series by its ID")
    public ResponseEntity<SeriesResponseDTO> updateSeries(@PathVariable Long id, @RequestBody @Valid SeriesUpdateDTO body) {
        SeriesResponseDTO series = seriesService.updateSeries(id, body);
        if (series != null) {
            return ResponseEntity.ok(series);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a series", description = "Delete a series by its ID")
    public ResponseEntity<Void> deleteSeries(@PathVariable Long id) {
        seriesService.deleteSeries(id);
        return ResponseEntity.noContent().build();
    }
}

