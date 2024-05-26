package com.example.authprofile.security;

import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTConfig {

    private String secret = "secret";

    public JWTConfig() {
        // constructor for JWTConfig
        // needed for testing purposes
        // because annotation makes sonarcloud angry
    }

    public String getSecret() {
        return secret;
    }

    public static void main(String[] args) {
        JWTConfig jwtConfig = new JWTConfig();
        System.out.println(jwtConfig.getSecret());
    }
}
