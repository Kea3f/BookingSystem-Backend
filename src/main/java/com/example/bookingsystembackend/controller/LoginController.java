package com.example.bookingsystembackend.controller;

import com.example.bookingsystembackend.dto.LoginDto;
import com.example.bookingsystembackend.service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        // Perform authentication
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getMail(), loginDto.getPassword()));

        // Set the authentication object in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // If authentication is successful, you can get UserDetails from SecurityContextHolder
        UserDetails userDetails = userDetailsService.loadUserByMail(loginDto.getMail());

        // You can return any other information you need
        return ResponseEntity.ok("Login successful for user: " + userDetails.getUsername());
    }
}