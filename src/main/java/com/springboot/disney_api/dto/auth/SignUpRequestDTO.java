package com.springboot.disney_api.dto.auth;

public record SignUpRequestDTO(
        String username,
        String password,
        String email) {
}
