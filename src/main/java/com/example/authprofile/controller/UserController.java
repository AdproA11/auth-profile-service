package com.example.authprofile.controller;

import com.example.authprofile.model.Enum.UserType;
import com.example.authprofile.model.UserEntity;
import com.example.authprofile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    String createHTML = "userCreate";
    String listHTML = "userList";

    @Autowired
    private UserService userService;

    @GetMapping("/create")
    public String createUserPage(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        model.addAttribute("types", UserType.getAll());
        return createHTML;
    }

    @PostMapping("/create")
    public String createUserPost(@ModelAttribute("product") UserEntity user, Model model) {
        userService.create(user);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String userListPage(Model model) {
        List<UserEntity> allUsers = userService.findAll();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails jwtUser = (UserDetails) auth.getPrincipal();
        UserEntity user = userService.findByUsername(jwtUser.getUsername());
        System.out.println("user username is " + user.getUsername());
        model.addAttribute("userlogedin", user);
        model.addAttribute("users", allUsers);
        return listHTML;
    }

    @GetMapping(value = "/edit/{userId}")
    public String editProductPage(Model model, @PathVariable("userId") String username) {
        UserEntity user = userService.findByUsername(username);
        if (user != null) {
            model.addAttribute("user", user);
            return "edituser";
        }
        return "redirect:../list";
    }

    @PostMapping("/edit")
    public String editProductPost(@ModelAttribute("user") UserEntity user, Model model) {
        System.out.println("user username edit is " + user.getUsername());
        userService.update(user.getUsername(), user);
        return "redirect:list";
    }

    @GetMapping("/get-username")
    public ResponseEntity<String> getUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails jwtUser = (UserDetails) auth.getPrincipal();
        UserEntity user = userService.findByUsername(jwtUser.getUsername());
        return new ResponseEntity<String>(jwtUser.getUsername(), HttpStatus.OK);
    }

    @GetMapping("/get-role")
    public ResponseEntity<String> getUsernameAndRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails jwtUser = (UserDetails) auth.getPrincipal();
        UserEntity user = userService.findByUsername(jwtUser.getUsername());
        return new ResponseEntity<String>(user.getType(), HttpStatus.OK);
    }
}
