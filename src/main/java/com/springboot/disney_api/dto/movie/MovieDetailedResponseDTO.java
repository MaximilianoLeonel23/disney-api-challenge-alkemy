package com.springboot.disney_api.dto.movie;

import java.time.LocalDate;

public record MovieDetailedResponseDTO(
        Long id,
        String title,
        String image,
        LocalDate creationDate,
        Double rating
) {
}
