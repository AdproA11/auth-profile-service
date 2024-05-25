package com.example.authprofile.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class UserEntity {
    @Id
    private String username;
    private String password;
    private String name;
    private String address;
    private String phoneNumber;
    private String type;
}
