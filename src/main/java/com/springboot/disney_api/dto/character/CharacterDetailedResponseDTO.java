package com.springboot.disney_api.dto.character;

import com.springboot.disney_api.dto.movie.MovieResponseDTO;
import com.springboot.disney_api.dto.series.SeriesResponseDTO;

import java.util.List;

public record CharacterDetailedResponseDTO(
        Long id,
        String name,
        String image,
        Integer age,
        Double weight,
        String history,
        List<MovieResponseDTO> movies,
        List<SeriesResponseDTO> series
) {
}
