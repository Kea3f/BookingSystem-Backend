package com.example.bookingsystembackend.service;

import com.example.bookingsystembackend.entity.Customer;
import com.example.bookingsystembackend.entity.Treatment;
import com.example.bookingsystembackend.repositories.CustomerRepository;
import com.example.bookingsystembackend.repositories.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private TreatmentRepository treatmentRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Logic for login
    public Customer authenticateCustomer(String email, String password) {
        if (email == null || password == null || email.trim().isEmpty() || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Email or password cannot be null or empty");
        }

        Customer customer = customerRepository.findByEmail(email);
        if (customer == null) {
            throw new IllegalArgumentException("User not found");
        }

        if (customer.getPassword().equals(password)) {
            return customer; // Return authenticated customer
        } else {
            throw new IllegalArgumentException("Incorrect password");
        }

    }


    //signin
    public Customer signupCustomer(Customer customer) {
        // Hash the password before saving to the database

        return customerRepository.save(customer);
    }


    public Customer getCustomerById(int customerId) {
        return customerRepository.findByCustomerId(customerId);
    }

    public Customer getCustomerByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    public Customer updateCustomer(int customerId, Customer updatedCustomer) throws Exception {
        Customer existingCustomer = customerRepository.findByCustomerId(customerId);
        if (existingCustomer != null) {
            // Check if the customerId in the session matches the one in the request
            if (customerId == updatedCustomer.getCustomerId()) {
                // Update the fields that can be modified
                existingCustomer.setFullName(updatedCustomer.getFullName());
                existingCustomer.setPhoneNo(updatedCustomer.getPhoneNo());
                existingCustomer.setEmail(updatedCustomer.getEmail());
                existingCustomer.setUsername(updatedCustomer.getUsername());
                existingCustomer.setPassword(updatedCustomer.getPassword());

                // Save and return the updated customer
                return customerRepository.save(existingCustomer);
            } else {
                // Throw an exception or return a specific message indicating unauthorized access
                // As the customer IDs don't match
                throw new Exception("You are not authorized to update this customer.");
            }
        } else {
            throw new Exception("Customer not found with ID: " + customerId);
        }
    }

    public void deleteCustomer(int customerId) throws Exception {
        Customer customer = customerRepository.findByCustomerId(customerId);
        if (customer != null) {
            customerRepository.delete(customer);
        } else {
            throw new Exception("Customer not found with ID: " + customerId);
        }
    }
}
