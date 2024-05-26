package com.example.authprofile.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTConfig {

    @Value("${jwt.secret}")
    private String secret;

    // constructor
    public JWTConfig() {
    }

    public String getSecret() {
        return secret;
    }
}
