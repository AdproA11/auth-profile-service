package com.example.authprofile.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AuthResponseDtoTest {

    @Test
    void testTokenType() {
        String accessToken = "testToken";
        AuthResponseDto authResponseDto = new AuthResponseDto(accessToken);

        assertEquals("Bearer ", authResponseDto.getTokenType());
    }

    // test equals method
    @Test
    void testEquals() {
        String accessToken = "testToken";
        AuthResponseDto authResponseDto1 = new AuthResponseDto(accessToken);
        AuthResponseDto authResponseDto2 = new AuthResponseDto(accessToken);

        assertEquals(authResponseDto1, authResponseDto2);
    }

    @Test
    void testEqualsNotNull() {
        String accessToken = "testToken";
        AuthResponseDto authResponseDto = new AuthResponseDto(accessToken);

        assertEquals(false, authResponseDto.equals(null));
    }

    @Test
    void testEqualsSameObjectReference() {
        String accessToken = "testToken";
        AuthResponseDto authResponseDto = new AuthResponseDto(accessToken);

        assertEquals(authResponseDto, authResponseDto);
    }

    // test hashCode method
    @Test
    void testHashCode() {
        String accessToken = "testToken";
        AuthResponseDto authResponseDto1 = new AuthResponseDto(accessToken);
        AuthResponseDto authResponseDto2 = new AuthResponseDto(accessToken);

        assertEquals(authResponseDto1.hashCode(), authResponseDto2.hashCode());
    }

    // test canEqual
    @Test
    void testCanEqual() {
        String accessToken = "testToken";
        AuthResponseDto authResponseDto = new AuthResponseDto(accessToken);

        assertEquals(authResponseDto, authResponseDto);

        AuthResponseDto authResponseDto2 = new AuthResponseDto(accessToken);

        assertEquals(authResponseDto, authResponseDto2);
    }

    // test setAccessToken
    @Test
    void testSetAccessToken() {
        String accessToken = "testToken";
        AuthResponseDto authResponseDto = new AuthResponseDto(accessToken);

        String newAccessToken = "newTestToken";
        authResponseDto.setAccessToken(newAccessToken);

        assertEquals(newAccessToken, authResponseDto.getAccessToken());
    }

    // test setTokenType
    @Test
    void testSetTokenType() {
        String accessToken = "testToken";
        AuthResponseDto authResponseDto = new AuthResponseDto(accessToken);

        String tokenType = "newBearer";
        authResponseDto.setTokenType(tokenType);

        assertEquals(tokenType, authResponseDto.getTokenType());
    }
}
