package com.example.bookingsystembackend.service;

import com.example.bookingsystembackend.entity.User;
import com.example.bookingsystembackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    // Overrides the method declared in the UserDetailsService interface to provide custom implementation.
    @Override
    public UserDetails loadUserByMail(String mail, String password) throws UsernameNotFoundException {
        // Fetches user details from the UserRepository based on the provided email.
        User user = userRepository.findByMail(mail);

        // Checks if the user is not found or if the provided password does not match the stored password.
        if (user == null || !password.equals(user.getPassword())) {
            // Throws a UsernameNotFoundException if the user is not found or if the credentials are invalid.
            throw new UsernameNotFoundException("Invalid credentials");
        }

        // Creates and returns a UserDetails object with custom implementation.
        return new UserDetails() {
            // Provides user authorities (roles). In this example, it returns an empty list.
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return Collections.emptyList();
            }

            // Returns the user's password.
            @Override
            public String getPassword() {
                return user.getPassword();
            }

            // Returns the user's email as the username.
            @Override
            public String getUsername() {
                return user.getMail();
            }

            // Indicates whether the user's account has not expired. In this example, it always returns true.
            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            // Indicates whether the user's account is not locked. In this example, it always returns true.
            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            // Indicates whether the user's credentials (password) are not expired. In this example, it always returns true.
            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            // Indicates whether the user is enabled. In this example, it always returns true.
            @Override
            public boolean isEnabled() {
                return true;
            }
        };
    }
}

