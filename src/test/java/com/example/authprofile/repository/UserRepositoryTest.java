package com.example.authprofile.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.authprofile.model.UserEntity;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class UserRepositoryTest {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    void createUser() {
        UserEntity newUser = new UserEntity();
        newUser.setUsername("testuser");
        newUser.setPassword("testpass");
        newUser.setType("BUYER");

        UserEntity createdUser = userRepository.createUser(newUser);
        assertEquals(newUser, createdUser);
        assertNotNull(userRepository.findByUsername("testuser"));
    }

    @Test
    void findAll() {
        Iterator<UserEntity> allUsers = userRepository.findAll();
        assertNotNull(allUsers);
        assertTrue(allUsers.hasNext());
    }

    @Test
    void findByUsername_nonExistingUser() {
        UserEntity foundUser = userRepository.findByUsername("nonexistent");
        assertNull(foundUser);
    }

    @Test
    void update_nonExistingUser() {
        UserEntity updatedUser = new UserEntity();
        updatedUser.setUsername("nonexistent");
        updatedUser.setPassword("updatedpass");
        updatedUser.setType("BUYER");

        assertNull(userRepository.update("nonexistent", updatedUser));
    }

    @Test
    void delete_existingUser() {
        userRepository.delete("user");
        assertNull(userRepository.findByUsername("user"));
    }

    @Test
    void delete_nonExistingUser() {
        userRepository.delete("nonexistent");
        assertNull(userRepository.findByUsername("nonexistent"));
    }

    @Test
    void testCreateUser() {
        UserEntity newUser = new UserEntity();
        newUser.setUsername("testuser");
        newUser.setPassword("testpass");
        newUser.setType("BUYER");

        UserEntity createdUser = userRepository.createUser(newUser);
        assertEquals(newUser, createdUser);
        assertNotNull(userRepository.findByUsername("testuser"));
    }

    @Test
    void testFindAll() {
        Iterator<UserEntity> allUsers = userRepository.findAll();
        assertNotNull(allUsers);
        assertTrue(allUsers.hasNext());
    }

    @Test
    void testFindByUsername_nonExistingUser() {
        UserEntity foundUser = userRepository.findByUsername("nonexistent");
        assertNull(foundUser);
    }

    @Test
    void testUpdate_nonExistingUser() {
        UserEntity updatedUser = new UserEntity();
        updatedUser.setUsername("nonexistent");
        updatedUser.setPassword("updatedpass");
        updatedUser.setType("BUYER");

        assertNull(userRepository.update("nonexistent", updatedUser));
    }

    @Test
    void testDelete_existingUser() {
        userRepository.delete("user");
        assertNull(userRepository.findByUsername("user"));
    }
}