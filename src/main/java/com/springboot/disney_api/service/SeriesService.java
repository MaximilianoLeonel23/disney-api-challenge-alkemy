package com.springboot.disney_api.service;

import com.springboot.disney_api.dto.movie.MovieDetailedResponseDTO;
import com.springboot.disney_api.dto.movie.MovieRequestDTO;
import com.springboot.disney_api.dto.movie.MovieResponseDTO;
import com.springboot.disney_api.dto.series.SeriesDetailedResponseDTO;
import com.springboot.disney_api.dto.series.SeriesRequestDTO;
import com.springboot.disney_api.dto.series.SeriesResponseDTO;
import com.springboot.disney_api.dto.series.SeriesUpdateDTO;
import com.springboot.disney_api.model.Movie;
import com.springboot.disney_api.model.Series;
import com.springboot.disney_api.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SeriesService {

    @Autowired
    private SeriesRepository seriesRepository;

    public SeriesResponseDTO createNewSeries(SeriesRequestDTO seriesDTO) {
        Series series = new Series(seriesDTO);
        Series createdSeries = seriesRepository.save(series);
        return new SeriesResponseDTO(
                createdSeries.getId(),
                createdSeries.getTitle(),
                createdSeries.getImage(),
                createdSeries.getCreationDate(),
                createdSeries.getRating(),
                createdSeries.getSeasons(),
                createdSeries.getEpisodes()
        );

    }

    public List<SeriesResponseDTO> getAllSeries() {
        List<Series> series = seriesRepository.findAll();
        if (!series.isEmpty()) {
            return series.stream().map(s -> new SeriesResponseDTO(
                    s.getId(),
                    s.getTitle(),
                    s.getImage(),
                    s.getCreationDate(),
                    s.getRating(),
                    s.getSeasons(),
                    s.getEpisodes()
            )).collect(Collectors.toList());
        } else return null;
    }

    public SeriesDetailedResponseDTO getSeriesById(Long id) {
        Optional<Series> seriesFound = seriesRepository.findById(id);
        if (seriesFound.isPresent()) {
            Series series = seriesFound.get();
            return new SeriesDetailedResponseDTO(
                    series.getId(),
                    series.getTitle(),
                    series.getImage(),
                    series.getCreationDate(),
                    series.getRating(),
                    series.getSeasons(),
                    series.getEpisodes()
            );
        } else return null;
    }

    public SeriesResponseDTO updateSeries(Long id, SeriesUpdateDTO body) {
        Optional<Series> seriesFound = seriesRepository.findById(id);
        if (seriesFound.isPresent()) {
            Series series = seriesFound.get();
            series.updateSeries(body);
            Series updatedSeries = seriesRepository.save(series);
            return new SeriesResponseDTO(
                    updatedSeries.getId(),
                    updatedSeries.getTitle(),
                    updatedSeries.getImage(),
                    updatedSeries.getCreationDate(),
                    updatedSeries.getRating(),
                    updatedSeries.getSeasons(),
                    updatedSeries.getEpisodes()
            );

        } else return null;
    }

    public void deleteSeries(Long id) {
        seriesRepository.deleteById(id);
    }
}
