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
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/series")
public class SeriesController {

    @Autowired
    private SeriesService seriesService;


    @PostMapping
    public ResponseEntity<SeriesResponseDTO> createNewSeries(@RequestBody @Valid SeriesRequestDTO series) {
        SeriesResponseDTO newSeries = seriesService.createNewSeries(series);
        URI uri = UriComponentsBuilder.fromPath("/series/{id}").buildAndExpand(newSeries.id()).toUri();
        return ResponseEntity.created(uri).body(newSeries);
    }

    @GetMapping
    public ResponseEntity<List<SeriesResponseDTO>> getAllSeries() {
        List<SeriesResponseDTO> series = seriesService.getAllSeries();
        if (series != null) {
            return ResponseEntity.ok(series);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeriesDetailedResponseDTO> getSeriesById(@PathVariable Long id) {
        SeriesDetailedResponseDTO series = seriesService.getSeriesById(id);
        if (series != null) {
            return ResponseEntity.ok(series);
        } else {
            return null;
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<SeriesResponseDTO> updateSeries(@PathVariable Long id, @RequestBody @Valid SeriesUpdateDTO body) {
        SeriesResponseDTO series = seriesService.updateSeries(id, body);
        if (series != null) {
            return ResponseEntity.ok(series);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeries(@PathVariable Long id) {
        seriesService.deleteSeries(id);
        return ResponseEntity.noContent().build();
    }
}

