package com.example.bookingsystembackend.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserDetailsService {
    // Declares a method for loading user details based on email and password.
    // It may throw a UsernameNotFoundException if the user is not found.
    UserDetails loadUserByMail(String mail, String password) throws UsernameNotFoundException;
}
