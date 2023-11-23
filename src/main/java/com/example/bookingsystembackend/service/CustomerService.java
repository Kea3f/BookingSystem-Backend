package com.example.bookingsystembackend.service;

import com.example.bookingsystembackend.entity.Customer;
import com.example.bookingsystembackend.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //Logic for login
    public Customer authenticateCustomer(String username, String password) {
        Customer customer = customerRepository.findByUsername(username);
        if (customer != null && customer.getPassword().equals(password)) {
            return customer;
        }
        return null;
    }


    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(int customerId) {
        return customerRepository.findByCustomerId(customerId);
    }

    public Customer getCustomerByUsername(String username) {
        return customerRepository.findByUsername(username);
    }

    //signin
    public Customer signupCustomer(Customer customer) {
        // Hash the password before saving to the database

        return customerRepository.save(customer);
    }



    //Edit a customer
    public Customer updateCustomer(int customerId, Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findByCustomerId(customerId);

        if (existingCustomer != null) {
            existingCustomer.setFullName(updatedCustomer.getFullName());
            existingCustomer.setEmail(updatedCustomer.getEmail());
            existingCustomer.setPhoneNo(updatedCustomer.getPhoneNo());
            existingCustomer.setUsername(updatedCustomer.getUsername());
            existingCustomer.setPassword(updatedCustomer.getPassword());


            return customerRepository.save(existingCustomer);
        }

        return null; // customer not found
    }

    public void deleteCustomer(int customerId) {
        customerRepository.deleteById(customerId);
    }

}
