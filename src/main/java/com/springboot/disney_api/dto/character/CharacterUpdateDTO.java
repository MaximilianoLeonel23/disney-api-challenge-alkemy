package com.springboot.disney_api.dto.character;

public record CharacterUpdateDTO(
        String name,
        String image,
        int age,
        double weight,
        String history
) {
}
