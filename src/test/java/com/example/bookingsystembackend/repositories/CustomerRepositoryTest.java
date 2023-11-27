package com.example.bookingsystembackend.repositories;

import com.example.bookingsystembackend.entity.Customer;
import com.example.bookingsystembackend.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CustomerRepositoryTest {


    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    public CustomerRepositoryTest() {
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerService(customerRepository);
    }


    //Test for retrieving a customer by their username in the databse
    @Test
    void testFindByUsername() {
        // Mocking a customer for testing
        Customer mockedCustomer = new Customer();
        mockedCustomer.setUsername("testUser");
        mockedCustomer.setPassword("testPassword"); // Ideally, this should be hashed in real scenarios

        // Stubbing the behavior of the repository method
        when(customerRepository.findByUsername("testUser")).thenReturn(mockedCustomer);

        // Call the service method that uses findByUsername
        Customer retrievedCustomer = customerService.authenticateCustomer("testUser", "testPassword");

        // Check if the retrieved customer matches the expected customer
        assertEquals("testUser", retrievedCustomer.getUsername());
        // Add more assertions based on your expected customer properties
    }



}