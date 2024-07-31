package com.springboot.disney_api.dto.series;

import com.springboot.disney_api.dto.character.CharacterResponseDTO;
import com.springboot.disney_api.dto.genre.GenreResponseDTO;
import com.springboot.disney_api.model.Character;

import java.time.LocalDate;
import java.util.List;

public record SeriesDetailedResponseDTO(
        Long id,
        String title,
        String image,
        LocalDate creationDate,
        Double rating,
        Integer seasons,
        Integer episodes,
        List<CharacterResponseDTO> characters,
        List<GenreResponseDTO> genres
) {
}
