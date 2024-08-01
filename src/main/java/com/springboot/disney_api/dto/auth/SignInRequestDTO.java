package com.springboot.disney_api.dto.auth;

public record SignInRequestDTO(
        String username,
        String password
) {
}
