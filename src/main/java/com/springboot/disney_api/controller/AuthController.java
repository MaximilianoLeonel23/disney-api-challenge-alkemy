package com.springboot.disney_api.controller;

import com.springboot.disney_api.dto.auth.SignInRequestDTO;
import com.springboot.disney_api.dto.auth.SignInResponseDTO;
import com.springboot.disney_api.dto.auth.SignUpRequestDTO;
import com.springboot.disney_api.dto.auth.SignUpResponseDTO;
import com.springboot.disney_api.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Controller for managing authentication")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    @Operation(summary = "Sign Up", description = "Sign up as a new user and receive a confirmation email")
    public ResponseEntity<SignUpResponseDTO> signUp(@RequestBody @Valid SignUpRequestDTO signUpRequestDTO) {
        SignUpResponseDTO signUpResponse = authService.signUp(signUpRequestDTO);
        URI uri = UriComponentsBuilder.fromPath("/api/auth/signup/{id}").buildAndExpand(signUpResponse.user().id()).toUri();
        return ResponseEntity.created(uri).body(signUpResponse);
    }

    @PostMapping("/signin")
    @Operation(summary = "Sign In", description = "Sign in with an existed user and receive a valid token to access all routes")
    public ResponseEntity<SignInResponseDTO> signIn(@RequestBody @Valid SignInRequestDTO signInRequestDTO) {
        SignInResponseDTO signInResponseDTO = authService.signIn(signInRequestDTO);
        return ResponseEntity.ok(signInResponseDTO);
    }
}
