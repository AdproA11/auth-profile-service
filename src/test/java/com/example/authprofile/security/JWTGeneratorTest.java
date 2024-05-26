package com.example.authprofile.security;

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
    private Authentication authentication;

    @InjectMocks
    private JWTGenerator jwtGenerator;

    private String testUsername = "testUser";

    @BeforeEach
    void setUp() {
        when(authentication.getName()).thenReturn(testUsername);
    }

    @Test
    void generateToken_ValidAuthentication_ReturnsToken() {
        String generatedToken = jwtGenerator.generateToken(authentication);
        assertNotNull(generatedToken);
        assertTrue(generatedToken.length() > 0);
    }

    @Test
    void getUsernameFromJWT_ValidToken_ReturnsUsername() {
        String token = jwtGenerator.generateToken(authentication);
        String username = jwtGenerator.getUsernameFromJWT(token);
        assertEquals(testUsername, username);
    }

    @Test
    void validateToken_ValidToken_ReturnsTrue() {
        String token = jwtGenerator.generateToken(authentication);
        boolean isValid = jwtGenerator.validateToken(token);
        assertTrue(isValid);
    }
}
