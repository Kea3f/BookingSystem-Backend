package com.example.bookingsystembackend.controller;

import com.example.bookingsystembackend.dto.LoginDto;
import com.example.bookingsystembackend.entity.Customer;
import com.example.bookingsystembackend.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpSession;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @Mock
    private HttpSession httpSession;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAuthenticateCustomer() {
        // Arrange
        LoginDto loginDto = new LoginDto("username", "password");
        Customer authenticatedCustomer = new Customer();
        when(customerService.authenticateCustomer("username", "password")).thenReturn(authenticatedCustomer);

        // Act
        ResponseEntity<Customer> result = customerController.authenticateCustomer(loginDto, httpSession);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(authenticatedCustomer, result.getBody());
        verify(httpSession, times(1)).setAttribute(eq("customerId"), any());
    }

    @Test
    void testAuthenticateCustomerUnauthorized() {
        // Arrange
        LoginDto loginDto = new LoginDto("invalidUsername", "invalidPassword");
        when(customerService.authenticateCustomer("invalidUsername", "invalidPassword")).thenReturn(null);

        // Act
        ResponseEntity<Customer> result = customerController.authenticateCustomer(loginDto, httpSession);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
        verify(httpSession, never()).setAttribute(any(), any());
    }

    @Test
    void testSignupCustomer() {
        // Arrange
        Customer newCustomer = new Customer();
        when(customerService.signupCustomer(newCustomer)).thenReturn(newCustomer);

        // Act
        Customer result = customerController.signupCustomer(newCustomer);

        // Assert
        assertNotNull(result);
        assertEquals(newCustomer, result);
    }

    @Test
    void testGetCustomerById() {
        // Arrange
        int customerId = 1;
        Customer expectedCustomer = new Customer();
        when(customerService.getCustomerById(customerId)).thenReturn(expectedCustomer);

        // Act
        ResponseEntity<Customer> result = customerController.getCustomerById(customerId);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedCustomer, result.getBody());
    }

    @Test
    void testUpdateCustomer() throws Exception {
        // Arrange
        int customerId = 1;
        Customer updatedCustomer = new Customer();
        Customer expectedCustomer = new Customer();
        when(customerService.updateCustomer(customerId, updatedCustomer)).thenReturn(expectedCustomer);

        // Act
        ResponseEntity<Customer> result = customerController.updateCustomer(customerId, updatedCustomer);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedCustomer, result.getBody());
    }

    @Test
    void testDeleteCustomer() throws Exception {
        // Arrange
        int customerId = 1;

        // Act
        ResponseEntity<Void> result = customerController.deleteCustomer(customerId);

        // Assert
        assertNotNull(result);
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(customerService, times(1)).deleteCustomer(customerId);
    }
}
