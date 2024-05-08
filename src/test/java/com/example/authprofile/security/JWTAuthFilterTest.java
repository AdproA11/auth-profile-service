package com.example.authprofile.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.lang.reflect.InvocationTargetException;

public class JWTAuthFilterTest {

    @Test
    public void testDoFilterInternal_NoToken() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);

        JWTAuthFilter filter = new JWTAuthFilter();
        filter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
    }

    @Test
    public void testDoFilterInternal_WithToken() throws ServletException, IOException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        FilterChain filterChain = mock(FilterChain.class);
        JWTGenerator tokenGenerator = mock(JWTGenerator.class);
        UserDetailsService userDetailsService = mock(UserDetailsService.class);

        JWTAuthFilter filter = new JWTAuthFilter();
        filter.setTokenGenerator(tokenGenerator);
        filter.setCustomUserDetailsService(userDetailsService);

        when(request.getHeader("Authorization")).thenReturn("Bearer mockToken");
        when(tokenGenerator.validateToken("mockToken")).thenReturn(true);
        when(tokenGenerator.getUsernameFromJWT("mockToken")).thenReturn("mockUser");
        UserDetails userDetails = mock(UserDetails.class);
        when(userDetailsService.loadUserByUsername("mockUser")).thenReturn(userDetails);

        filter.doFilterInternal(request, response, filterChain);

        verify(filterChain, times(1)).doFilter(request, response);
        verify(tokenGenerator, times(1)).validateToken("mockToken");
        verify(tokenGenerator, times(1)).getUsernameFromJWT("mockToken");
        verify(userDetailsService, times(1)).loadUserByUsername("mockUser");
    }

    @Test
    public void testGetJWTFromRequest()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getHeader("Authorization")).thenReturn("Bearer mockToken");

        JWTAuthFilter filter = new JWTAuthFilter();
        Method method = JWTAuthFilter.class.getDeclaredMethod("getJWTFromRequest", HttpServletRequest.class);
        method.setAccessible(true); // Allow invoking private method
        String jwt = (String) method.invoke(filter, request);

        assertEquals("mockToken", jwt);
    }
}
