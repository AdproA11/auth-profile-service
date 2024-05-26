package com.example.authprofile.model.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class LoginDtoTest {
    private LoginDto loginDto;

    @BeforeEach
    void setUp() {
        loginDto = new LoginDto();
    }

    @Test
    void testNotNull() {
        assertNotNull(loginDto);
    }

    @Test
    void testGetSetUsername() {
        String username = "testUser";
        loginDto.setUsername(username);
        assertEquals(username, loginDto.getUsername());
    }

    @Test
    void testGetSetPassword() {
        String password = "testPassword";
        loginDto.setPassword(password);
        assertEquals(password, loginDto.getPassword());
    }

    // test equals method
    @Test
    void testEquals() {
        LoginDto loginDto1 = new LoginDto();
        loginDto1.setUsername("testUser");
        loginDto1.setPassword("testPassword");
        LoginDto loginDto2 = new LoginDto();
        loginDto2.setUsername("testUser");
        loginDto2.setPassword("testPassword");

        assertEquals(loginDto1, loginDto2);
    }

    @Test
    void testEqualsNotNull() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("testUser");
        loginDto.setPassword("testPassword");

        assertEquals(false, loginDto.equals(null));
    }

    @Test
    void testEqualsSameObjectReference() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("testUser");
        loginDto.setPassword("testPassword");

        assertEquals(true, loginDto.equals(loginDto));
    }

    // test hashCode method
    @Test
    void testHashCode() {
        LoginDto loginDto1 = new LoginDto();
        loginDto1.setUsername("testUser");
        loginDto1.setPassword("testPassword");
        LoginDto loginDto2 = new LoginDto();
        loginDto2.setUsername("testUser");
        loginDto2.setPassword("testPassword");

        assertEquals(loginDto1.hashCode(), loginDto2.hashCode());
    }

    @Test
    void testHashCodeNotEquals() {
        LoginDto loginDto1 = new LoginDto();
        loginDto1.setUsername("testUser");
        loginDto1.setPassword("testPassword");
        LoginDto loginDto2 = new LoginDto();
        loginDto2.setUsername("testUser");
        loginDto2.setPassword("testPassword2");

        assertNotEquals(loginDto1.hashCode(), loginDto2.hashCode());
    }

    // test toString
    @Test
    void testToString() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("testUser");
        loginDto.setPassword("testPassword");

        assertEquals("LoginDto(username=testUser, password=testPassword)", loginDto.toString());
    }

    @Test
    void testToStringNotEquals() {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("testUser");
        loginDto.setPassword("testPassword");

        assertNotEquals("LoginDto(username=testUser, password=testPassword2)", loginDto.toString());
    }

    @Test
    void testToStringNull() {
        LoginDto loginDto = new LoginDto();

        assertEquals("LoginDto(username=null, password=null)", loginDto.toString());
    }

    @Test
    void testToStringNullNotEquals() {
        LoginDto loginDto = new LoginDto();

        assertNotEquals("LoginDto(username=null, password=testPassword)", loginDto.toString());
    }

    @Test
    void testToStringNullNotEquals2() {
        LoginDto loginDto = new LoginDto();

        assertNotEquals("LoginDto(username=testUser, password=null)", loginDto.toString());
    }
}
