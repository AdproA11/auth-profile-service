package com.example.authprofile.controller;

import com.example.authprofile.model.UserEntity;
import com.example.authprofile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseEntity<List<UserEntity>> userList() {
        List<UserEntity> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/current-username")
    public ResponseEntity<String> getUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails jwtUser = (UserDetails) auth.getPrincipal();
        Optional<UserEntity> user = userService.findByUsername(jwtUser.getUsername());

        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user.get().getUsername(), HttpStatus.OK);
    }

    @GetMapping("/current-role")
    public ResponseEntity<String> getUsernameAndRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails jwtUser = (UserDetails) auth.getPrincipal();
        Optional<UserEntity> user = userService.findByUsername(jwtUser.getUsername());

        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user.get().getType(), HttpStatus.OK);
    }

    @GetMapping("/current-user")
    public ResponseEntity<UserEntity> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails jwtUser = (UserDetails) auth.getPrincipal();
        Optional<UserEntity> user = userService.findByUsername(jwtUser.getUsername());

        if (user.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }
}
