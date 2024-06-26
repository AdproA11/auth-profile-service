package com.example.authprofile.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegisterDtoTest {
    private RegisterDto registerDto;

    @BeforeEach
    void setUp() {
        registerDto = new RegisterDto();
    }

    @Test
    void testNotNull() {
        assertNotNull(registerDto);
    }

    @Test
    void testGetSetUsername() {
        String username = "testUser";
        registerDto.setUsername(username);
        assertEquals(username, registerDto.getUsername());
    }

    @Test
    void testGetSetPassword() {
        String password = "testPassword";
        registerDto.setPassword(password);
        assertEquals(password, registerDto.getPassword());
    }

    @Test
    void testGetSetType() {
        String type = "testType";
        registerDto.setType(type);
        assertEquals(type, registerDto.getType());
    }

    // test equals method
    @Test
    void testEquals() {
        RegisterDto registerDto1 = new RegisterDto();
        registerDto1.setUsername("testUser");
        registerDto1.setPassword("testPassword");
        registerDto1.setType("testType");
        RegisterDto registerDto2 = new RegisterDto();
        registerDto2.setUsername("testUser");
        registerDto2.setPassword("testPassword");
        registerDto2.setType("testType");

        assertEquals(registerDto1, registerDto2);
    }

    @Test
    void testEqualsNotNull() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername("testUser");
        registerDto.setPassword("testPassword");
        registerDto.setType("testType");

        assertEquals(false, registerDto.equals(null));
    }

    @Test
    void testEqualsSameObjectReference() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername("testUser");
        registerDto.setPassword("testPassword");
        registerDto.setType("testType");

        assertEquals(true, registerDto.equals(registerDto));
    }

    // test hashCode method
    @Test
    void testHashCode() {
        RegisterDto registerDto1 = new RegisterDto();
        registerDto1.setUsername("testUser");
        registerDto1.setPassword("testPassword");
        registerDto1.setType("testType");
        RegisterDto registerDto2 = new RegisterDto();
        registerDto2.setUsername("testUser");
        registerDto2.setPassword("testPassword");
        registerDto2.setType("testType");

        assertEquals(registerDto1.hashCode(), registerDto2.hashCode());
    }

    @Test
    void testHashCodeNotEquals() {
        RegisterDto registerDto1 = new RegisterDto();
        registerDto1.setUsername("testUser");
        registerDto1.setPassword("testPassword");
        registerDto1.setType("testType");
        RegisterDto registerDto2 = new RegisterDto();
        registerDto2.setUsername("testUser");
        registerDto2.setPassword("testPassword");
        registerDto2.setType("testType");

        assertEquals(registerDto1.hashCode(), registerDto2.hashCode());
    }

}
