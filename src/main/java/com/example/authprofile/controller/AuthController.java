package com.example.authprofile.controller;

import com.example.authprofile.model.Builder.UserBuilder;
import com.example.authprofile.model.UserEntity;
import com.example.authprofile.model.dto.AuthResponseDto;
import com.example.authprofile.model.dto.LoginDto;
import com.example.authprofile.model.dto.RegisterDto;
import com.example.authprofile.security.JWTGenerator;
import com.example.authprofile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
            UserService userService,
            PasswordEncoder passwordEncoder,
            JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtGenerator = jwtGenerator;
    }

    private AuthenticationManager authenticationManager;
    private UserService userService;
    private PasswordEncoder passwordEncoder;
    private UserBuilder userEntityBuilder;

    public AuthController(UserBuilder userEntityBuilder) {
        this.userEntityBuilder = userEntityBuilder;
    }

    private JWTGenerator jwtGenerator;

    @GetMapping("/login")
    public ModelAndView login(Model model) {
        LoginDto loginDto = new LoginDto();
        model.addAttribute("logindto", loginDto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        return modelAndView;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto, Model model) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

    @GetMapping("/postlogin")
    public ModelAndView postlogin(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("postlogin.html");
        return modelAndView;
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>("Logout success!", HttpStatus.OK);
    }

    @GetMapping("/register")
    public ModelAndView register(Model model) {
        RegisterDto registerDto = new RegisterDto();
        model.addAttribute("registerdto", registerDto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register.html");
        return modelAndView;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {

        if (userService.findByUsername(registerDto.getUsername()).isPresent()) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        UserEntity user = userEntityBuilder.reset()
                .addUsername(registerDto.getUsername())
                .addPassword(passwordEncoder.encode(registerDto.getPassword()))
                .addType(registerDto.getType())
                .addName(registerDto.getName())
                .build();

        if (user.getUsername() == null || user.getPassword() == null) {
            return new ResponseEntity<>("UserEntity fields must not be null!", HttpStatus.BAD_REQUEST);
        }

        userService.create(user);

        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }

}
