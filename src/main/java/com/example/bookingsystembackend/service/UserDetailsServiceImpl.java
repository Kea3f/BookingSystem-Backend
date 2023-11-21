package com.example.bookingsystembackend.service;

import com.example.bookingsystembackend.entity.User;
import com.example.bookingsystembackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
    }
    // Overrides the method declared in the UserDetailsService interface to provide custom implementation.
    @Override
    public UserDetails loadUserByMail(String mail) throws UsernameNotFoundException {
        // Fetches user details from the UserRepository based on the provided email.
        User user = userRepository.findByMail(mail);

        // Checks if the user is not found.
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + mail);
        }

        // Creates and returns a UserDetails object with custom implementation.
        return new org.springframework.security.core.userdetails.User(
                user.getMail(),
                user.getPassword(),
                Collections.emptyList());
        }
    }


