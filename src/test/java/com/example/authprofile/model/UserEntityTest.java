package com.example.authprofile.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserEntityTest {
    private UserEntity userEntity;

    @BeforeEach
    public void setUp() {
        userEntity = new UserEntity();
    }

    @Test
    public void testUsername() {
        String username = "testUsername";
        userEntity.setUsername(username);
        assertEquals(username, userEntity.getUsername());
    }

    @Test
    public void testPassword() {
        String password = "testPassword";
        userEntity.setPassword(password);
        assertEquals(password, userEntity.getPassword());
    }

    @Test
    public void testName() {
        String name = "testName";
        userEntity.setName(name);
        assertEquals(name, userEntity.getName());
    }

    @Test
    public void testAddress() {
        String address = "testAddress";
        userEntity.setAddress(address);
        assertEquals(address, userEntity.getAddress());
    }

    @Test
    public void testPhoneNumber() {
        String phoneNumber = "testPhoneNumber";
        userEntity.setPhoneNumber(phoneNumber);
        assertEquals(phoneNumber, userEntity.getPhoneNumber());
    }

    @Test
    public void testType() {
        String type = "testType";
        userEntity.setType(type);
        assertEquals(type, userEntity.getType());
    }
}
