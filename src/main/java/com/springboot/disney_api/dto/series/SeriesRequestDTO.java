package com.springboot.disney_api.dto.series;

import java.time.LocalDate;
import java.util.Optional;

public record SeriesRequestDTO(
        String title,
        Optional<String> image,
        Optional<LocalDate> creationDate,
        Optional<Double> rating,
        Optional<Integer> seasons,
        Optional<Integer> episodes
) {
}
