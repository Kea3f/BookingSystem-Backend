package com.example.bookingsystembackend.config;

import com.example.bookingsystembackend.entity.Customer;
import com.example.bookingsystembackend.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomerData implements CommandLineRunner {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerData(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void run(String... args) {
        createAndSaveClient("John@gmail.com", "John Doe", "1234", "ROLE_USER", 21467376);
        createAndSaveClient("Jane@gmail.com", "Jane Smith", "1234", "ROLE_USER", 98765432);
        createAndSaveClient("Hanne@gmail.com", "Hanne Johnson", "1234", "ROLE_USER", 87654321);
        createAndSaveClient("Emily@gmail.com", "Emily  Williams", "1234", "ROLE_USER", 76543210);


    }

    private void createAndSaveClient(String email, String fullName, String pwd, String role, int phoneNo) {
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setFullName(fullName);
        customer.setRole(role);
        customer.setPwd(passwordEncoder.encode(pwd));
        customer.setPhoneNo(phoneNo);
        customerRepository.save(customer);
    }

}





