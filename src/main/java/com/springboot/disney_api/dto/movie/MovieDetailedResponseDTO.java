package com.springboot.disney_api.dto.movie;

import com.springboot.disney_api.dto.character.CharacterResponseDTO;
import com.springboot.disney_api.dto.genre.GenreResponseDTO;

import java.time.LocalDate;
import java.util.List;

public record MovieDetailedResponseDTO(
        Long id,
        String title,
        String image,
        LocalDate creationDate,
        Double rating,
        List<CharacterResponseDTO> characters,
        List<GenreResponseDTO> genres
) {
}
