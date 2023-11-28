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
        mockedCustomer.setEmail("testEmail");
        mockedCustomer.setPassword("testPassword");

        // Stubbing the repository method to return the mocked customer
        Mockito.when(customerRepository.findByEmail(anyString())).thenReturn(mockedCustomer);

        // Valid credentials for authentication
        String email = "testEmail";
        String password = "testPassword";

        // Calling the method to be tested
        Customer authenticatedCustomer = customerService.authenticateCustomer(email, password);

        // Asserting that the authenticated customer matches the mocked customer
        Assertions.assertEquals(mockedCustomer, authenticatedCustomer);
    }

}
