package com.springboot.disney_api.dto.character;

import jakarta.validation.constraints.NotBlank;


import java.util.Optional;

public record CharacterRequestDTO(
        @NotBlank
        String name,
        Optional<String> image,
        Optional<Integer> age,
        Optional<Double> weight,
        Optional<String> history
) {
}
