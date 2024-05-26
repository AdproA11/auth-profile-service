package com.example.authprofile.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.example.authprofile.model.Builder.UserBuilder;
import com.example.authprofile.model.dto.LoginDto;
import com.example.authprofile.repository.UserRepository;
import com.example.authprofile.security.JWTGenerator;
import com.example.authprofile.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserBuilder userEntityBuilder;

    @Mock
    private JWTGenerator jwtGenerator;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
    }

    @Test
    void testLogin() throws Exception {
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("test");
        loginDto.setPassword("password");

        Authentication auth = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        when(authenticationManager.authenticate(any())).thenReturn(auth);

        String token = "token";
        when(jwtGenerator.generateToken(auth)).thenReturn(token);

        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"test\",\"password\":\"password\"}"))
                .andExpect(status().isOk());

        verify(authenticationManager, times(1)).authenticate(any());
        verify(jwtGenerator, times(1)).generateToken(any());
    }
}
