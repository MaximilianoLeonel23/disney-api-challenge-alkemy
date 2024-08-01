package com.springboot.disney_api.service;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private String apiSecret = "123456";

    public String generateToken(String username) {
        Algorithm algorithm = Algorithm.HMAC256(apiSecret);
        String token = JWT.create()
                .withSubject(username)
                .sign(algorithm);
        return token;
    }

    public DecodedJWT verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(apiSecret);
        return JWT.require(algorithm)
                .build().verify(token);
    }
}
