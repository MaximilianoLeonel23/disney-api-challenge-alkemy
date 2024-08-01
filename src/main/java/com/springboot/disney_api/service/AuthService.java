package com.springboot.disney_api.service;

import com.springboot.disney_api.dto.auth.*;
import com.springboot.disney_api.model.User;
import com.springboot.disney_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private TokenService tokenService;

    public SignUpResponseDTO signUp(SignUpRequestDTO signUpRequestDTO) {
        String passwordEncoded = encoder.encode(signUpRequestDTO.password());
        User newUser = new User(
                signUpRequestDTO.username(),
                signUpRequestDTO.email(),
                passwordEncoded);
        User savedUser = userRepository.save(newUser);
        UserResponseDTO userDTO = new UserResponseDTO(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getCreateAt());
        String token = tokenService.generateToken(savedUser.getUsername());
        return new SignUpResponseDTO(userDTO, token);
    }

    public SignInResponseDTO signIn(SignInRequestDTO signInRequestDTO) {
        User user = userRepository.findByUsername(signInRequestDTO.username()).orElseThrow(() -> new RuntimeException("User not found"));
        if (encoder.matches(signInRequestDTO.password(), user.getPassword())) {
            String token = tokenService.generateToken(user.getUsername());
            return new SignInResponseDTO(token);
        } else {
            throw new RuntimeException("Invalid Credentials");
        }
    }
}
