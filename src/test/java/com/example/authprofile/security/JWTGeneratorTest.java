package com.example.authprofile.security;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class JWTGeneratorTest {

    @Mock
    private JWTGenerator jwtGenerator;

    @Test
    void testGenerateToken() {
        jwtGenerator = new JWTGenerator();
        jwtGenerator.jwtSecret = "testSecret";
        jwtGenerator.jwtExpiration = 3600000; // 1 hour in milliseconds

        Authentication authentication = new UsernamePasswordAuthenticationToken("username", "password");
        String token = jwtGenerator.generateToken(authentication);

        // Assert that token is not null
        assertEquals(true, token != null && !token.isEmpty());
    }
}
