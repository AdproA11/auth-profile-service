package com.example.authprofile.model.Enum;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class UserTypeTest {

    @Test
    public void testContains() {
        assertTrue(UserType.contains("BUYER"));
        assertTrue(UserType.contains("ADMIN"));
        assertFalse(UserType.contains("SELLER"));
    }

    @Test
    public void testGetAll() {
        List<String> allTypes = UserType.getAll();
        assertEquals(2, allTypes.size());
        assertTrue(allTypes.contains("BUYER"));
        assertTrue(allTypes.contains("ADMIN"));
    }

    @Test
    public void testFromValue() {
        assertEquals(UserType.BUYER, UserType.fromValue("BUYER"));
        assertEquals(UserType.ADMIN, UserType.fromValue("ADMIN"));
        assertNull(UserType.fromValue("SELLER"));
    }

    @Test
    public void testFromName() {
        assertEquals(UserType.BUYER, UserType.fromName("BUYER"));
        assertEquals(UserType.ADMIN, UserType.fromName("ADMIN"));
        assertNull(UserType.fromName("SELLER"));
    }

    @Test
    public void testIsBuyer() {
        assertTrue(UserType.isBuyer("BUYER"));
        assertFalse(UserType.isBuyer("ADMIN"));
    }

    @Test
    public void testIsAdmin() {
        assertTrue(UserType.isAdmin("ADMIN"));
        assertFalse(UserType.isAdmin("BUYER"));
    }

    @Test
    public void testGetValue() {
        assertEquals("BUYER", UserType.BUYER.getValue());
        assertEquals("ADMIN", UserType.ADMIN.getValue());
    }
}
