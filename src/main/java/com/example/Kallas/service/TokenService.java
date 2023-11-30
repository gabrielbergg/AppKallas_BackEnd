package com.example.Kallas.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.example.Kallas.model.UserLogin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UserLogin userLogin) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create().withIssuer("auth-api").
                    withSubject(userLogin.getLogin()).
                    withExpiresAt(date()).sign(algorithm);

            return token;
        }
        catch (JWTCreationException exception){
            throw new RuntimeException(exception);
        }
    }

    public String validadeToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm).
                    withIssuer("auth-api").build().
                    verify(token).getSubject();
        }
        catch (JWTCreationException exception){
            throw new RuntimeException(exception);
        }
    }

    private Instant date() {
        return LocalDateTime.now().plusMinutes(25).toInstant(ZoneOffset.of("-03:00"));
    }
}
