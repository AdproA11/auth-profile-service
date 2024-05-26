package com.example.authprofile.controller;

import com.example.authprofile.model.UserEntity;
import com.example.authprofile.model.dto.AuthResponseDto;
import com.example.authprofile.model.dto.LoginDto;
import com.example.authprofile.model.dto.RegisterDto;
import com.example.authprofile.security.JWTGenerator;
import com.example.authprofile.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserService userService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JWTGenerator jwtGenerator;

    @InjectMocks
    private AuthController authController;

    public AuthControllerTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void login_get() {
        Model model = mock(Model.class);

        ModelAndView modelAndView = authController.login(model);

        assertNotNull(modelAndView);
        assertEquals("login.html", modelAndView.getViewName());
    }

    @Test
    void login_post_unauthorized() {
        Model model = mock(Model.class);
        LoginDto loginDto = new LoginDto();
        loginDto.setUsername("test");
        loginDto.setPassword("password");

        when(authenticationManager.authenticate(any())).thenReturn(null);

        ResponseEntity<AuthResponseDto> responseEntity = authController.login(loginDto, model);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }

    @Test
    void postlogin() {
        Model model = mock(Model.class);

        ModelAndView modelAndView = authController.postlogin(model);

        assertNotNull(modelAndView);
        assertEquals("postlogin.html", modelAndView.getViewName());
    }

    @Test
    void logout() {
        ResponseEntity<String> responseEntity = authController.logout();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Logout success!", responseEntity.getBody());
    }

    @Test
    void register_get() {
        Model model = mock(Model.class);

        ModelAndView modelAndView = authController.register(model);

        assertNotNull(modelAndView);
        assertEquals("register.html", modelAndView.getViewName());
    }

    @Test
    void register_post_username_taken() {
        RegisterDto registerDto = new RegisterDto();
        registerDto.setUsername("existingUser");

        when(userService.findByUsername("existingUser")).thenReturn(java.util.Optional.of(new UserEntity()));

        ResponseEntity<String> responseEntity = authController.register(registerDto);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Username is taken!", responseEntity.getBody());
    }
}
