package com.example.authprofile.model.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String username;
    private String password;
    private String type;
    private String name;
}
