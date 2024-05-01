package com.example.authprofile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/p")
public class TestController {
    @GetMapping("/")
    @ResponseBody
    public String testYoutube() {
        return "<h1><a href=\"https://www.youtube.com/watch?v=dQw4w9WgXcQ\">Hello World</a></h1>";
    }
}
