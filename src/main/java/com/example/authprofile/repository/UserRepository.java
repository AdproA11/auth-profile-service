package com.example.authprofile.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
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
public interface UserRepository extends JpaRepository<UserEntity, String> {
}