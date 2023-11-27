package com.example.bookingsystembackend.service;

import com.example.bookingsystembackend.controller.CustomerController;
import com.example.bookingsystembackend.entity.Customer;
import com.example.bookingsystembackend.repositories.CustomerRepository;
import jakarta.servlet.http.HttpSession;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAuthenticateCustomer_ValidCredentials() {
        // Mocking a customer
        Customer mockedCustomer = new Customer();
        mockedCustomer.setUsername("testUser");
        mockedCustomer.setPassword("testPassword");

        // Stubbing the repository method to return the mocked customer
        Mockito.when(customerRepository.findByUsername(anyString())).thenReturn(mockedCustomer);

        // Valid credentials for authentication
        String username = "testUser";
        String password = "testPassword";

        // Calling the method to be tested
        Customer authenticatedCustomer = customerService.authenticateCustomer(username, password);

        // Asserting that the authenticated customer matches the mocked customer
        Assertions.assertEquals(mockedCustomer, authenticatedCustomer);
    }

    @Test
    public void testAuthenticateCustomer_InvalidCredentials() {
        // Stubbing the repository method to return null (user not found)
        Mockito.when(customerRepository.findByUsername(anyString())).thenReturn(null);

        // Invalid credentials for authentication
        String username = "nonExistentUser";
        String password = "wrongPassword";

        // Calling the method to be tested
        Customer authenticatedCustomer = customerService.authenticateCustomer(username, password);

        // Asserting that authenticated customer is null (authentication failed)
        Assertions.assertNull(authenticatedCustomer);
    }

}
