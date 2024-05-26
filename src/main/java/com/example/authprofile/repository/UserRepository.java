package com.example.authprofile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.example.authprofile.model.UserEntity;

@Component
@Repository
public interface UserRepository extends JpaRepository<UserEntity, String> {
}