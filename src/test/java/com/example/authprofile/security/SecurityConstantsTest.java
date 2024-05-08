package com.example.authprofile.security;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SecurityConstantsTest {

    @Test
    public void testJWTExpirationConstant() {
        assertEquals(3600000, SecurityConstants.JWT_EXPIRATION);
    }
}
