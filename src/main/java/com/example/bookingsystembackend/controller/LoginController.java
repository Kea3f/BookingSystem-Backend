package com.example.bookingsystembackend.controller;

import com.example.bookingsystembackend.dto.LoginDto;
import com.example.bookingsystembackend.entity.User;
import com.example.bookingsystembackend.repositories.UserRepository;
import com.example.bookingsystembackend.service.UserDetailsService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    // Handles HTTP POST requests, takes a LoginDto as the request body, and a HttpSession as a parameter.
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto, HttpSession session) {
        // Extracts the email and password from the LoginDto.
        String mail = loginDto.getMail();
        String password = loginDto.getPassword();

        try {
            // Attempts to load user details based on the provided email and password.
            UserDetails userDetails = userDetailsService.loadUserByMail(mail, password);

            // If successful, returns a response indicating a successful login.
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } catch (UsernameNotFoundException e) {
            // If the user is not found or the password is incorrect, handles the exception.
            // Returns a response indicating invalid credentials.
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }
}
