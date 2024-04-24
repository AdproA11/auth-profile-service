package com.example.authprofile.service;

import com.example.authprofile.model.UserEntity;

import java.util.List;

public interface UserService {
    public UserEntity create(UserEntity user);

    public List<UserEntity> findAll();

    UserEntity findByUsername(String username);

    public void update(String userId, UserEntity user);

    public void deleteUserById(String userId);
}
