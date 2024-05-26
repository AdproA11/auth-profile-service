package com.example.authprofile.model.Builder;

import com.example.authprofile.model.Enum.UserType;
import com.example.authprofile.model.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserBuilderTest {

    private UserBuilder userBuilder;

    @BeforeEach
    void setUp() {
        userBuilder = new UserBuilder();
    }

    @Test
    void testReset() {
        userBuilder.addName("John");
        userBuilder.reset();
        assertNull(userBuilder.getCurrent().getName());
    }

    @Test
    void testAddName() {
        userBuilder.addName("John");
        assertEquals("John", userBuilder.getCurrent().getName());
    }

    // Similar tests for other add methods...

    @Test
    void testAddType_ValidType() {
        userBuilder.addType(UserType.ADMIN.name());
        assertEquals(UserType.ADMIN.name(), userBuilder.getCurrent().getType());
    }

    @Test
    void testAddType_InvalidType() {
        assertThrows(IllegalArgumentException.class, () -> userBuilder.addType("InvalidType"));
    }

    @Test
    void testSetCurrent() {
        UserEntity user = new UserEntity();
        user.setUsername("testUser");
        userBuilder.setCurrent(user);
        assertEquals(user, userBuilder.getCurrent());
    }

    @Test
    void testBuild() {
        UserEntity user = userBuilder.addName("John")
                .addUsername("john123")
                .addPassword("password")
                .addAddress("123 Street")
                .addPhoneNumber("1234567890")
                .addType(UserType.BUYER.name())
                .build();
        assertNotNull(user);
    }

    @Test
    void testIsBuyer_True() {
        userBuilder.addType(UserType.BUYER.name());
        assertTrue(userBuilder.isBuyer());
    }

    @Test
    void testIsBuyer_False() {
        userBuilder.addType(UserType.ADMIN.name());
        assertFalse(userBuilder.isBuyer());
    }

    @Test
    void testIsAdmin_True() {
        userBuilder.addType(UserType.ADMIN.name());
        assertTrue(userBuilder.isAdmin());
    }

    @Test
    void testIsAdmin_False() {
        userBuilder.addType(UserType.BUYER.name());
        assertFalse(userBuilder.isAdmin());
    }
}
