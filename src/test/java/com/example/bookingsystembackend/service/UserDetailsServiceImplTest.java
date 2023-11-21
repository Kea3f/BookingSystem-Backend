package com.example.bookingsystembackend.service;

import com.example.bookingsystembackend.entity.User;
import com.example.bookingsystembackend.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

@SpringBootTest
public class UserDetailsServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    public void testLoadUserByMailSuccess() {
        // Arrange
        String testMail = "test@example.com";
        String testPassword = "password";
        User mockUser = new User();
        mockUser.setMail(testMail);
        mockUser.setPassword(testPassword);

        Mockito.when(userRepository.findByMail(anyString())).thenReturn(mockUser);

        // Act
        UserDetails userDetails = userDetailsService.loadUserByMail(testMail);

        // Assert
        assertNotNull(userDetails);
        assertEquals(testMail, userDetails.getUsername());
        assertEquals(testPassword, userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().isEmpty());
        assertTrue(userDetails.isAccountNonExpired());
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isEnabled());
    }

    @Test
    public void testLoadUserByMailFailure() {
        // Arrange
        String testMail = "test@example.com";
        String wrongPassword = "wrongPassword";

        Mockito.when(userRepository.findByMail(anyString())).thenReturn(null);

        // Act and Assert
        assertThrows(UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByMail(testMail),
                "Expected loadUserByMail to throw UsernameNotFoundException");
    }
}
