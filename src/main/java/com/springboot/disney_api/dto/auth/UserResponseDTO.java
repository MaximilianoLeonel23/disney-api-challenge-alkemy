package com.springboot.disney_api.dto.auth;

import java.time.LocalDateTime;

public record UserResponseDTO(
        Long id,
        String username,
        String email,
        LocalDateTime createdAt
) {
}
