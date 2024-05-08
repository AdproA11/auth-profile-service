package com.example.authprofile.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.AuthenticationException;

import java.io.IOException;

import static org.mockito.Mockito.*;

public class JWTAuthEntryPointTest {

    @Test
    public void testCommence() throws IOException, ServletException {
        // Mock HttpServletRequest, HttpServletResponse, and AuthenticationException
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        AuthenticationException authException = mock(AuthenticationException.class);

        // Create an instance of JWTAuthEntryPoint
        JWTAuthEntryPoint entryPoint = new JWTAuthEntryPoint();

        // Call the commence method
        entryPoint.commence(request, response, authException);

        // Verify that response.sendError was called with expected parameters
        verify(response, times(1)).sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}
