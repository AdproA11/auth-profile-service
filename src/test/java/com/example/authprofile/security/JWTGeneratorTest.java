package com.example.authprofile.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JWTGeneratorTest {

    @Mock
    private JWTConfig jwtConfig;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private JWTGenerator jwtGenerator;

    private final String secret = "secret";
    private final String username = "testUser";

    @BeforeEach
    void setUp() {
        when(jwtConfig.getSecret()).thenReturn(secret);
        when(authentication.getName()).thenReturn(username);
    }

    @Test
    void generateToken_shouldReturnValidToken() {
        String generatedToken = jwtGenerator.generateToken(authentication);
        assertNotNull(generatedToken);

        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(generatedToken)
                .getBody();

        assertEquals(username, claims.getSubject());
        assertNotNull(claims.getIssuedAt());
        assertNotNull(claims.getExpiration());
    }
}
