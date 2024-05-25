package com.example.authprofile.controller;

import com.example.authprofile.model.UserEntity;
import com.example.authprofile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) throws URISyntaxException {
        UserEntity createdUser = userService.create(user);
        return ResponseEntity.created(new URI("/user/" + createdUser.getUsername())).body(createdUser);
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserEntity>> userList() {
        List<UserEntity> users = userService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/edit/{userId}")
    public ResponseEntity<UserEntity> editUser(@PathVariable("userId") String username) {
        UserEntity user = userService.findByUsername(username).orElse(null);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/edit/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") String userId, @RequestBody UserEntity user) {
        userService.update(userId, user);
        return new ResponseEntity<>(HttpStatus.OK);
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
