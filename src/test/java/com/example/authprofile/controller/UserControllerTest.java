package com.example.authprofile.controller;

import com.example.authprofile.model.UserEntity;
import com.example.authprofile.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser() throws URISyntaxException {
        UserEntity userEntity = new UserEntity();
        when(userService.create(any(UserEntity.class))).thenReturn(userEntity);

        ResponseEntity<UserEntity> response = userController.createUser(userEntity);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(userEntity, response.getBody());
    }

    @Test
    void userList() {
        List<UserEntity> userList = new ArrayList<>();
        when(userService.findAll()).thenReturn(userList);

        ResponseEntity<List<UserEntity>> response = userController.userList();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userList, response.getBody());
    }

    @Test
    void editUser_UserFound() {
        UserEntity userEntity = new UserEntity();
        when(userService.findByUsername(anyString())).thenReturn(userEntity);

        ResponseEntity<UserEntity> response = userController.editUser("username");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userEntity, response.getBody());
    }

    @Test
    void editUser_UserNotFound() {
        when(userService.findByUsername(anyString())).thenReturn(null);

        ResponseEntity<UserEntity> response = userController.editUser("username");

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void updateUser() {
        UserEntity userEntity = new UserEntity();
        doNothing().when(userService).update(anyString(), any(UserEntity.class));

        ResponseEntity<?> response = userController.updateUser("username", userEntity);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService).update("username", userEntity);
    }

    @Test
    void getUsername() {
        UserEntity userEntity = new UserEntity();
        when(userService.findByUsername(anyString())).thenReturn(userEntity);

        UserDetails userDetails = new User("username", "password", new ArrayList<>());
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        ResponseEntity<String> response = userController.getUsername();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService).findByUsername("username");
    }

    @SuppressWarnings("null")
    @Test
    void getUsernameAndRole() {
        UserEntity userEntity = new UserEntity();
        userEntity.setType("ADMIN");
        when(userService.findByUsername(anyString())).thenReturn(userEntity);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));
        UserDetails userDetails = new User("username", "password", authorities);
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        ResponseEntity<String> response = userController.getUsernameAndRole();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("ADMIN"));
        verify(userService).findByUsername("username");
    }
}