package com.example.authprofile.service;

import com.example.authprofile.model.UserEntity;
import com.example.authprofile.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void create() {
        UserEntity user = new UserEntity();
        when(userRepository.save(user)).thenReturn(user);

        UserEntity createdUser = userService.create(user);

        assertNotNull(createdUser);
        verify(userRepository).save(user);
    }

    @Test
    void fieldValid() {
        UserEntity user = new UserEntity();
        user.setPhoneNumber("1234567890");
        user.setAddress("Test Address");

        assertTrue(userService.fieldValid(user));
    }

    @Test
    void findAll() {
        List<UserEntity> users = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(users);

        List<UserEntity> foundUsers = userService.findAll();

        assertEquals(users, foundUsers);
        verify(userRepository).findAll();
    }

    @Test
    void findByUsername() {
        UserEntity user = new UserEntity();
        user.setUsername("testUser");
        when(userRepository.findById("testUser")).thenReturn(Optional.of(user));

        Optional<UserEntity> foundUser = userService.findByUsername("testUser");

        assertTrue(foundUser.isPresent());
        assertEquals(user, foundUser.get());
        verify(userRepository).findById("testUser");
    }

    @Test
    void update() {
        UserEntity existingUser = new UserEntity();
        existingUser.setName("Old Name");
        existingUser.setAddress("Old Address");
        Optional<UserEntity> optionalUser = Optional.of(existingUser);

        UserEntity updatedUser = new UserEntity();
        updatedUser.setName("New Name");
        updatedUser.setAddress("New Address");

        when(userRepository.findById("userId")).thenReturn(optionalUser);

        userService.update("userId", updatedUser);

        assertEquals("New Name", existingUser.getName());
        assertEquals("New Address", existingUser.getAddress());
        verify(userRepository).save(existingUser);
    }

    @Test
    void deleteUserById() {
        userService.deleteUserById("userId");

        verify(userRepository).deleteById("userId");
    }

    @Test
    void loadUserByUsername_userNotFound() {
        when(userRepository.findById("username")).thenReturn(Optional.empty());

        assertThrows(UsernameNotFoundException.class, () -> userService.loadUserByUsername("username"));
    }

    @Test
    void loadUserByUsername_userFound() {
        UserEntity user = new UserEntity();
        user.setUsername("username");
        user.setPassword("password");
        user.setType("ROLE_USER");
        when(userRepository.findById("username")).thenReturn(Optional.of(user));

        UserDetails userDetails = userService.loadUserByUsername("username");

        assertNotNull(userDetails);
        assertEquals("username", userDetails.getUsername());
        assertEquals("password", userDetails.getPassword());
        assertEquals("ROLE_USER", userDetails.getAuthorities().iterator().next().getAuthority());
    }
}
