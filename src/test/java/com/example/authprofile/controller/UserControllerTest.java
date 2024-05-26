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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // @Test
    // void createUser() throws URISyntaxException {
    // UserEntity userEntity = new UserEntity();
    // when(userService.create(any(UserEntity.class))).thenReturn(userEntity);

    // ResponseEntity<UserEntity> response = userController.createUser(userEntity);

    // assertEquals(HttpStatus.CREATED, response.getStatusCode());
    // assertEquals(userEntity, response.getBody());
    // }

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
        when(userService.findByUsername(anyString())).thenReturn(Optional.of(userEntity));

        ResponseEntity<UserEntity> response = userController.editUser("username");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userEntity, response.getBody());
    }

    // @Test
    // void editUser_UserNotFound() {
    // when(userService.findByUsername(anyString())).thenReturn(null);

    // ResponseEntity<UserEntity> response = userController.editUser("username");

    // assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    // }

    @Test
    void updateUser() {
        UserEntity userEntity = new UserEntity();
        doNothing().when(userService).update(anyString(), any(UserEntity.class));

        ResponseEntity<?> response = userController.updateUser("username", userEntity);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(userService).update("username", userEntity);
    }

    // @Test
    // void getUsername() {
    // UserEntity userEntity = new UserEntity();
    // when(userService.findByUsername(anyString())).thenReturn(Optional.of(userEntity));

    // ResponseEntity<String> response = userController.getUsername();

    // assertEquals(HttpStatus.OK, response.getStatusCode());
    // assertEquals(userEntity.getUsername(), response.getBody());
    // }

    // @SuppressWarnings("null")
    // @Test
    // void getUsernameAndRole() {
    // UserEntity userEntity = new UserEntity();
    // userEntity.setType("ROLE_USER");
    // when(userService.findByUsername(anyString())).thenReturn(Optional.of(userEntity));

    // ResponseEntity<String> response = userController.getUsernameAndRole();

    // assertEquals(HttpStatus.OK, response.getStatusCode());
    // assertEquals(userEntity.getType(), response.getBody());
    // }
}