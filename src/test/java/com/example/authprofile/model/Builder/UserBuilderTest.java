package com.example.authprofile.model.Builder;

import com.example.authprofile.model.Enum.UserType;
import com.example.authprofile.model.UserEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserBuilderTest {

    private UserBuilder userBuilder;

    @BeforeEach
    public void setUp() {
        userBuilder = new UserBuilder();
    }

    @Test
    public void testReset() {
        userBuilder.addName("John");
        userBuilder.reset();
        assertNull(userBuilder.getCurrent().getName());
    }

    @Test
    public void testAddName() {
        userBuilder.addName("John");
        assertEquals("John", userBuilder.getCurrent().getName());
    }

    // Similar tests for other add methods...

    @Test
    public void testAddType_ValidType() {
        userBuilder.addType(UserType.ADMIN.name());
        assertEquals(UserType.ADMIN.name(), userBuilder.getCurrent().getType());
    }

    @Test
    public void testAddType_InvalidType() {
        assertThrows(IllegalArgumentException.class, () -> userBuilder.addType("InvalidType"));
    }

    @Test
    public void testSetCurrent() {
        UserEntity user = new UserEntity();
        user.setUsername("testUser");
        userBuilder.setCurrent(user);
        assertEquals(user, userBuilder.getCurrent());
    }

    @Test
    public void testBuild() {
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
    public void testIsBuyer_True() {
        userBuilder.addType(UserType.BUYER.name());
        assertTrue(userBuilder.isBuyer());
    }

    @Test
    public void testIsBuyer_False() {
        userBuilder.addType(UserType.ADMIN.name());
        assertFalse(userBuilder.isBuyer());
    }

    @Test
    public void testIsAdmin_True() {
        userBuilder.addType(UserType.ADMIN.name());
        assertTrue(userBuilder.isAdmin());
    }

    @Test
    public void testIsAdmin_False() {
        userBuilder.addType(UserType.BUYER.name());
        assertFalse(userBuilder.isAdmin());
    }
}
