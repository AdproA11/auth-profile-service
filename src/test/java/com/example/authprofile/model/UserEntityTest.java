package com.example.authprofile.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserEntityTest {
    private UserEntity userEntity;

    @BeforeEach
    void setUp() {
        userEntity = new UserEntity();
    }

    @Test
    void testUsername() {
        String username = "testUsername";
        userEntity.setUsername(username);
        assertEquals(username, userEntity.getUsername());
    }

    @Test
    void testPassword() {
        String password = "testPassword";
        userEntity.setPassword(password);
        assertEquals(password, userEntity.getPassword());
    }

    @Test
    void testName() {
        String name = "testName";
        userEntity.setName(name);
        assertEquals(name, userEntity.getName());
    }

    @Test
    void testAddress() {
        String address = "testAddress";
        userEntity.setAddress(address);
        assertEquals(address, userEntity.getAddress());
    }

    @Test
    void testPhoneNumber() {
        String phoneNumber = "testPhoneNumber";
        userEntity.setPhoneNumber(phoneNumber);
        assertEquals(phoneNumber, userEntity.getPhoneNumber());
    }

    @Test
    void testType() {
        String type = "testType";
        userEntity.setType(type);
        assertEquals(type, userEntity.getType());
    }
}
