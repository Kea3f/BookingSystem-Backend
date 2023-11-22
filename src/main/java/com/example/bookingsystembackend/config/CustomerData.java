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
        createAndSaveClient("John", "Doe", "johndoe@live.com", "1234", 21467376);
        createAndSaveClient("Jane", "Smith", "janesmith@gmail.com", "5678", 98765432);
        createAndSaveClient("Mike", "Johnson", "mikejohnson@yahoo.com", "abcd", 87654321);
        createAndSaveClient("Emily", "Williams", "emilywilliams@hotmail.com", "efgh", 76543210);
        createAndSaveClient("Chris", "Anderson", "chrisanderson@gmail.com", "ijkl", 65432109);
        createAndSaveClient("Sarah", "Brown", "sarahbrown@yahoo.com", "mnop", 54321098);
        createAndSaveClient("Alex", "Davis", "alexdavis@hotmail.com", "qrst", 43210987);
        createAndSaveClient("Jessica", "Miller", "jessicamiller@gmail.com", "uvwx", 32109876);
        createAndSaveClient("Brian", "White", "brianwhite@yahoo.com", "yz12", 21098765);
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





