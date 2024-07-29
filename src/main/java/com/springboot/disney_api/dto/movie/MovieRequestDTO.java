package com.springboot.disney_api.dto.movie;

import java.time.LocalDate;
import java.util.Optional;

public record MovieRequestDTO(
        String title,
        Optional<String> image,
        Optional<LocalDate> creationDate,
        Optional<Double> rating
) {
}
