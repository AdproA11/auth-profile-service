package com.example.authprofile.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AuthResponseDtoTest {
    @Test
    void testAccessToken() {
        String accessToken = "testToken";
        AuthResponseDto authResponseDto = new AuthResponseDto(accessToken);

        assertEquals(accessToken, authResponseDto.getAccessToken());
    }

    @Test
    void testTokenType() {
        String accessToken = "testToken";
        AuthResponseDto authResponseDto = new AuthResponseDto(accessToken);

        assertEquals("Bearer ", authResponseDto.getTokenType());
    }

    @Test
    void testConstructor() {
        String accessToken = "testToken";
        AuthResponseDto authResponseDto = new AuthResponseDto(accessToken);

        assertEquals(accessToken, authResponseDto.getAccessToken());
        assertEquals("Bearer ", authResponseDto.getTokenType());
    }
}
