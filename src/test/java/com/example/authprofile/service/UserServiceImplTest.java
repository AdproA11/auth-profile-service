package com.example.authprofile.service;

import com.example.authprofile.model.UserEntity;
import com.example.authprofile.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    private UserServiceImpl userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = Mockito.mock(UserRepository.class);
        userService = new UserServiceImpl();
        userService.setUserRepository(userRepository);
    }

    @Test
    void create_invalidUser() {
        UserEntity user = new UserEntity();
        user.setUsername("testuser");
        user.setPassword("testpass");
        user.setType("BUYER");

        when(userRepository.findByUsername("testuser")).thenReturn(user);

        assertThrows(IllegalArgumentException.class, () -> userService.create(user));
    }

    @Test
    void findAll() {
        UserEntity user1 = new UserEntity();
        UserEntity user2 = new UserEntity();
        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2).iterator());

        List<UserEntity> allUsers = userService.findAll();

        assertEquals(2, allUsers.size());
    }

    @Test
    void findByUsername_existingUser() {
        UserEntity user = new UserEntity();
        user.setUsername("testuser");
        when(userRepository.findByUsername("testuser")).thenReturn(user);

        UserEntity foundUser = userService.findByUsername("testuser");

        assertEquals(user, foundUser);
    }

    @Test
    void findByUsername_nonExistingUser() {
        when(userRepository.findByUsername("nonexistent")).thenReturn(null);

        UserEntity foundUser = userService.findByUsername("nonexistent");

        assertNull(foundUser);
    }

    @Test
    void update_existingUser() {
        UserEntity user = new UserEntity();
        user.setUsername("testuser");
        userService.update("testuser", user);

        verify(userRepository, times(1)).update("testuser", user);
    }

    @Test
    void deleteUserById_existingUser() {
        userService.deleteUserById("testuser");

        verify(userRepository, times(1)).delete("testuser");
    }

    @Test
    void loadUserByUsername_existingUser() {
        UserEntity user = new UserEntity();
        user.setUsername("testuser");
        user.setPassword("testpass");
        user.setType("BUYER");
        when(userRepository.findByUsername("testuser")).thenReturn(user);

        assertNotNull(userService.loadUserByUsername("testuser"));
    }

    @Test
    void loadUserByUsername_nonExistingUser() {
        when(userRepository.findByUsername("nonexistent")).thenReturn(null);

        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("nonexistent"));
    }
}