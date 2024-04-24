package com.example.authprofile.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserEntity {
    private String username;
    private String password;
    private String name;
    private String address;
    private String phoneNumber;
    private String type;
}
