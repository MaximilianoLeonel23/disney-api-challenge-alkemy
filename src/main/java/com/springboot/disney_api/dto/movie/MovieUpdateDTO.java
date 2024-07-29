package com.springboot.disney_api.dto.movie;

import java.time.LocalDate;
import java.util.Optional;

public record MovieUpdateDTO(
        String title,
        String image,
        LocalDate creationDate,
        Double rating
) {
}
