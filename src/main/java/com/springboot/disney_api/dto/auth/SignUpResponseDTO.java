package com.springboot.disney_api.dto.auth;

public record SignUpResponseDTO(
        UserResponseDTO user,
        String token
) {
}
