package com.example.authprofile.controller;

import com.example.authprofile.model.Builder.UserBuilder;
import com.example.authprofile.model.UserEntity;
import com.example.authprofile.model.dto.AuthResponseDto;
import com.example.authprofile.model.dto.LoginDto;
import com.example.authprofile.model.dto.RegisterDto;
import com.example.authprofile.repository.UserRepository;
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
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserBuilder userEntityBuilder;

    private JWTGenerator jwtGenerator;

    @GetMapping("/login")
    public ModelAndView login(Model model) {
        LoginDto loginDto = new LoginDto();
        model.addAttribute("logindto", loginDto);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login.html");
        System.out.println("starting login");
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

    @PostMapping("/login-redirect")
    public ModelAndView loginRedirect(@RequestBody LoginDto loginDto, Model model) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        if (token == null) {
            return new ModelAndView("login.html");
        }
        return new ModelAndView("redirect:/profile");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>("Logout success!", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@RequestBody RegisterDto registerDto) {
        if (userRepository.findByUsername(registerDto.getUsername()) != null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        UserEntity user = userEntityBuilder.reset()
                .addUsername(registerDto.getUsername())
                .addPassword(passwordEncoder.encode(registerDto.getPassword()))
                .addType(registerDto.getType())
                .build();

        userRepository.createUser(user);

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registerDto.getUsername(),
                        registerDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        if (token == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }

}
