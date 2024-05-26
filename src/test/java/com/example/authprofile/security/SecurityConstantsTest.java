package com.example.authprofile.security;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityConstantsTest {

    @Test
    void testJWTExpirationConstant() {
        assertEquals(3600000, SecurityConstants.JWT_EXPIRATION);
    }
}
