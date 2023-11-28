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
    void testFindByEmail() {
        // Mocking a customer for testing
        Customer mockedCustomer = new Customer();
        mockedCustomer.setEmail("testEmail");
        mockedCustomer.setPassword("testPassword"); // Ideally, this should be hashed in real scenarios

        // Stubbing the behavior of the repository method
        when(customerRepository.findByEmail("testEmail")).thenReturn(mockedCustomer);

        // Call the service method that uses findByUsername
        Customer retrievedCustomer = customerService.authenticateCustomer("testEmail", "testPassword");

        // Check if the retrieved customer matches the expected customer
        assertEquals("testEmail", retrievedCustomer.getEmail());
        // Add more assertions based on your expected customer properties
    }



}