package com.example.authprofile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.authprofile.model.UserEntity;
import com.example.authprofile.model.Enum.UserType;
import com.example.authprofile.service.UserService;

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
        System.out.println(user.getUsername());
        userService.update(user.getUsername(), user);
        return "redirect:list";
    }
}
