package com.springboot.disney_api.dto.genre;

import com.springboot.disney_api.dto.movie.MovieResponseDTO;
import com.springboot.disney_api.dto.series.SeriesResponseDTO;

import java.util.List;

public record GenreDetailedResponseDTO(
        Long id,
        String name,
        String image,
        List<MovieResponseDTO> movies,
        List<SeriesResponseDTO> series
) {
}
