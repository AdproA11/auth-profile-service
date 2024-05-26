package com.example.authprofile.controller;

import com.example.authprofile.model.UserEntity;
import com.example.authprofile.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    public UserControllerTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void userList() {
        List<UserEntity> userList = new ArrayList<>();
        when(userService.findAll()).thenReturn(userList);

        ResponseEntity<List<UserEntity>> responseEntity = userController.userList();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userList, responseEntity.getBody());
    }

    @Test
    void getUsername_userFound() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        UserDetails userDetails = new User("test", "password", new ArrayList<>());
        when(authentication.getPrincipal()).thenReturn(userDetails);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("test");
        when(userService.findByUsername("test")).thenReturn(Optional.of(userEntity));

        ResponseEntity<String> responseEntity = userController.getUsername();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("test", responseEntity.getBody());
    }

    @Test
    void getUsername_userNotFound() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        UserDetails userDetails = new User("test", "password", new ArrayList<>());
        when(authentication.getPrincipal()).thenReturn(userDetails);

        when(userService.findByUsername("test")).thenReturn(Optional.empty());

        ResponseEntity<String> responseEntity = userController.getUsername();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void getUsernameAndRole_userFound() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        UserDetails userDetails = new User("test", "password", new ArrayList<>());
        when(authentication.getPrincipal()).thenReturn(userDetails);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("test");
        userEntity.setType("ROLE_USER");
        when(userService.findByUsername("test")).thenReturn(Optional.of(userEntity));

        ResponseEntity<String> responseEntity = userController.getUsernameAndRole();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("ROLE_USER", responseEntity.getBody());
    }

    @Test
    void getUsernameAndRole_userNotFound() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        UserDetails userDetails = new User("test", "password", new ArrayList<>());
        when(authentication.getPrincipal()).thenReturn(userDetails);

        when(userService.findByUsername("test")).thenReturn(Optional.empty());

        ResponseEntity<String> responseEntity = userController.getUsernameAndRole();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void getCurrentUser_userFound() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        UserDetails userDetails = new User("test", "password", new ArrayList<>());
        when(authentication.getPrincipal()).thenReturn(userDetails);

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername("test");
        when(userService.findByUsername("test")).thenReturn(Optional.of(userEntity));

        ResponseEntity<UserEntity> responseEntity = userController.getCurrentUser();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userEntity, responseEntity.getBody());
    }

    @Test
    void getCurrentUser_userNotFound() {
        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        UserDetails userDetails = new User("test", "password", new ArrayList<>());
        when(authentication.getPrincipal()).thenReturn(userDetails);

        when(userService.findByUsername("test")).thenReturn(Optional.empty());

        ResponseEntity<UserEntity> responseEntity = userController.getCurrentUser();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
