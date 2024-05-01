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
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    String createHTML = "userCreate";
    String listHTML = "userList";

    String editHTML = "edituser";

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
    public ModelAndView userListPage(Model model) {
        List<UserEntity> allUsers = userService.findAll();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails jwtUser = (UserDetails) auth.getPrincipal();
        UserEntity user = userService.findByUsername(jwtUser.getUsername());
        System.out.println("user username on /list is " + user.getUsername());
        ModelAndView modelAndView = new ModelAndView(listHTML);
        modelAndView.addObject("userlogedin", user);
        modelAndView.addObject("users", allUsers);
        return modelAndView;
    }

    @GetMapping(value = "/edit/{userId}")
    public ModelAndView editProductPage(Model model, @PathVariable("userId") String username) {
        UserEntity user = userService.findByUsername(username);
        if (user != null) {
            ModelAndView modelAndView = new ModelAndView(editHTML);
            model.addAttribute("user", user);
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView(listHTML);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping("/edit")
    public ResponseEntity<UserEntity> editProductPost(@ModelAttribute("user") UserEntity user, Model model) {
        System.out.println("user username on /edit is " + user.getUsername());
        userService.update(user.getUsername(), user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/get-username")
    public ResponseEntity<String> getUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails jwtUser = (UserDetails) auth.getPrincipal();
        UserEntity user = userService.findByUsername(jwtUser.getUsername());
        return new ResponseEntity<String>(user.getUsername(), HttpStatus.OK);
    }

    @GetMapping("/get-role")
    public ResponseEntity<String> getUsernameAndRole() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails jwtUser = (UserDetails) auth.getPrincipal();
        UserEntity user = userService.findByUsername(jwtUser.getUsername());
        return new ResponseEntity<String>(user.getType(), HttpStatus.OK);
    }
}
