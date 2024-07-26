package com.springboot.disney_api.dto.character;

public record CharacterDetailedResponseDTO(
        Long id,
        String name,
        String image,
        Integer age,
        Double weight,
        String history
) {
}
