package com.example.authprofile.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import io.micrometer.core.instrument.MeterRegistry;

import java.io.IOException;

@Component
public class JWTAuthEntryPoint implements AuthenticationEntryPoint {

    private final MeterRegistry meterRegistry;

    public JWTAuthEntryPoint(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        if (HttpStatus.NOT_FOUND.value() == response.getStatus()) {
            meterRegistry.counter("app.errors", "type", "404").increment();
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    authException.getMessage());
        } else if (HttpStatus.FORBIDDEN.value() == response.getStatus()) {
            meterRegistry.counter("app.errors", "type", "403").increment();
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                    authException.getMessage());
        } else if (HttpStatus.UNAUTHORIZED.value() == response.getStatus()) {
            meterRegistry.counter("app.errors", "type", "401").increment();
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                    authException.getMessage());
        } else if (HttpStatus.BAD_REQUEST.value() == response.getStatus()) {
            meterRegistry.counter("app.errors", "type", "400").increment();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    authException.getMessage());
        } else {
            meterRegistry.counter("app.errors", "type", "500").increment();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    authException.getMessage());
        }
    }
}
