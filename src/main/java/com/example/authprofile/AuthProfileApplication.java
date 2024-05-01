package com.example.authprofile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
public class AuthProfileApplication {
	public static void main(String[] args) {
		SpringApplication.run(AuthProfileApplication.class, args);
	}
}
