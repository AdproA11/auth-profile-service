// package com.example.authprofile.service;

// import com.example.authprofile.model.UserEntity;
// import com.example.authprofile.repository.UserRepository;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.mockito.Mockito;
// import
// org.springframework.security.core.userdetails.UsernameNotFoundException;

// import java.util.Arrays;
// import java.util.List;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.Mockito.*;

// class UserServiceImplTest {

// private UserServiceImpl userService;
// private UserRepository userRepository;

// @BeforeEach
// void setUp() {
// userRepository = Mockito.mock(UserRepository.class);
// userService = Mockito.mock(UserServiceImpl.class);
// }

// @Test
// void createUserTest() {
// UserEntity user = new UserEntity();
// userService.create(user);

// verify(userRepository, times(1)).save(user);
// }

// @Test
// void fieldValidTest() {
// UserEntity user = new UserEntity();
// user.setAddress("address");
// user.setPhoneNumber("1234567890");

// assertTrue(userService.fieldValid(user));
// }

// @Test
// void findAllTest() {
// List<UserEntity> users = Arrays.asList(new UserEntity(), new UserEntity());
// when(userRepository.findAll()).thenReturn(users);

// assertEquals(users, userService.findAll());
// }

// @Test
// void findByUsernameTest() {
// UserEntity user = new UserEntity();
// when(userRepository.findById("username")).thenReturn(java.util.Optional.of(user));

// assertEquals(java.util.Optional.of(user),
// userService.findByUsername("username"));
// }

// @Test
// void updateTest() {
// UserEntity user = new UserEntity();
// user.setName("name");
// user.setAddress("address");

// UserEntity userToUpdate = new UserEntity();
// userToUpdate.setName("nameToUpdate");
// userToUpdate.setAddress("addressToUpdate");

// when(userRepository.findById("userId")).thenReturn(java.util.Optional.of(userToUpdate));

// userService.update("userId", user);

// assertEquals(user.getName(), userToUpdate.getName());
// assertEquals(user.getAddress(), userToUpdate.getAddress());

// verify(userRepository, times(1)).save(userToUpdate);
// }

// @Test
// void deleteUserByIdTest() {
// userService.deleteUserById("userId");

// verify(userRepository, times(1)).deleteById("userId");
// }

// @Test
// void loadUserByUsernameSuccessTest() {
// UserEntity user = new UserEntity();
// user.setUsername("username");
// when(userRepository.findById("username")).thenReturn(java.util.Optional.of(user));

// assertEquals(user, userService.loadUserByUsername("username"));
// }

// @Test
// void loadUserByUsernameFailTest() {
// when(userRepository.findById("username")).thenReturn(java.util.Optional.empty());

// assertThrows(UsernameNotFoundException.class, () ->
// userService.loadUserByUsername("username"));
// }

// }