package com.springboot.disney_api.dto.series;

import java.time.LocalDate;

public record SeriesDetailedResponseDTO(
        Long id,
        String title,
        String image,
        LocalDate creationDate,
        Double rating,
        Integer seasons,
        Integer episodes
) {
}
