package com.example.authprofile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class AuthProfileController {
    @GetMapping("/")
    @ResponseBody
    public String addProfile() {
        return "<h1>Hello World</h1>";
    }
}