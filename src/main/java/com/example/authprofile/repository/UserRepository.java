package com.example.authprofile.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.authprofile.model.UserEntity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@Repository
public class UserRepository {
    private final List<UserEntity> userData = new ArrayList<>();

    @Autowired
    public UserRepository() {
        PasswordEncoder pe = new BCryptPasswordEncoder();
        UserEntity newUser = new UserEntity();
        newUser.setUsername("user");
        newUser.setPassword(pe.encode("pass"));
        newUser.setType("ADMIN");
        userData.add(newUser);
        System.out.println("zczc " + userData.getFirst());
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
        for (int i = 0; i < userData.size(); i++) {
            UserEntity user = userData.get(i);
            if (user.getUsername().equals(username)) {
                userData.set(i, updatedUser);
                return updatedUser;
            }
        }
        return null;
    }

    public void delete(String username) {
        userData.removeIf(User -> User.getUsername().equals(username));
    }
}
