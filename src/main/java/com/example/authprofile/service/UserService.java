package com.example.authprofile.service;

import com.example.authprofile.model.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public UserEntity create(UserEntity user);

    public List<UserEntity> findAll();

    Optional<UserEntity> findByUsername(String username);

    public void update(String userId, UserEntity user);

    public void deleteUserById(String userId);
}
