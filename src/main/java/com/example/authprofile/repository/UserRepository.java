package com.example.authprofile.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.authprofile.model.UserEntity;
import com.example.authprofile.model.Builder.UserBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Repository
public class UserRepository {
    private final List<UserEntity> userData = new ArrayList<>();

    @Autowired
    public UserRepository() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserEntity user1 = new UserBuilder("John Doe", "johndoe", passwordEncoder.encode("password"), "1234 Elm St",
                "1234567890", "BUYER").build();

        UserEntity user2 = new UserBuilder("Jane Doe", "janedoe", passwordEncoder.encode("password"), "1234 Elm St",
                "123489643", "ADMIN").build();

        userData.add(user1);
        userData.add(user2);
    }

    public UserEntity createUser(UserEntity newUser) {
        userData.add(newUser);
        return newUser;
    }

    public Iterator<UserEntity> findAll() {
        return userData.iterator();
    }

    public UserEntity findByUsername(String username) {
        for (UserEntity User : userData) {
            if (User.getUsername().equals(username)) {
                return User;
            }
        }
        return null;
    }

    public UserEntity update(String username, UserEntity updatedUser) {
        for (UserEntity User : userData) {
            if (User.getUsername().equals(username)) {
                User.setName(updatedUser.getName());
                User.setUsername(updatedUser.getUsername());
                User.setPassword(updatedUser.getPassword());
                User.setAddress(updatedUser.getAddress());
                User.setPhoneNumber(updatedUser.getPhoneNumber());
                User.setType(updatedUser.getType());
                return User;
            }
        }
        return null;
    }

    public void delete(String username) {
        userData.removeIf(User -> User.getUsername().equals(username));
    }
}
