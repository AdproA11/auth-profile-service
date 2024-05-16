package com.example.authprofile.security;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class JWTAuthEntryPointTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private AuthenticationException authException;
    private MeterRegistry meterRegistry;
    private JWTAuthEntryPoint entryPoint;

    @BeforeEach
    public void setUp() {
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        authException = mock(AuthenticationException.class);
        meterRegistry = mock(MeterRegistry.class);
        entryPoint = new JWTAuthEntryPoint(meterRegistry);
    }

    @Test
    public void testNotFoundHandling() {
        when(response.getStatus()).thenReturn(HttpStatus.NOT_FOUND.value());
        Counter counter = mock(Counter.class);
        when(meterRegistry.counter("app.errors", "type", "404")).thenReturn(counter);

        assertThrows(ResponseStatusException.class, () -> entryPoint.commence(request, response, authException));
        verify(counter, times(1)).increment();
    }

    @Test
    public void testForbiddenHandling() {
        when(response.getStatus()).thenReturn(HttpStatus.FORBIDDEN.value());
        Counter counter = mock(Counter.class);
        when(meterRegistry.counter("app.errors", "type", "403")).thenReturn(counter);

        assertThrows(ResponseStatusException.class, () -> entryPoint.commence(request, response, authException));
        verify(counter, times(1)).increment();
    }

    @Test
    public void testOtherErrorsHandling() {
        when(response.getStatus()).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR.value());
        Counter counter = mock(Counter.class);
        when(meterRegistry.counter("app.errors", "type", "500")).thenReturn(counter);

        assertThrows(ResponseStatusException.class, () -> entryPoint.commence(request, response, authException));
        verify(counter, times(1)).increment();
    }
}
