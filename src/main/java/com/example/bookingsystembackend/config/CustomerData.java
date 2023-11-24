
package com.example.bookingsystembackend.config;


import com.example.bookingsystembackend.entity.Customer;
import com.example.bookingsystembackend.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CustomerData implements CommandLineRunner {

    @Autowired
    CustomerRepository customerRepository;

    @Override

    public void run(String... args) throws Exception {

        Customer customer1 = new Customer();
        customer1.setCustomerId(1);
        customer1.setUsername("username1");
        customer1.setPassword("Kea1234");
        customer1.setFullName("Naja M.");
        customer1.setEmail("najamoe@outlook.dk");
        customer1.setPhoneNo(62622362);

        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setCustomerId(2);
        customer2.setUsername("username2");
        customer2.setPassword("Kea1234");
        customer2.setFullName("Sabrina E.");
        customer2.setEmail("Sabrina.ebbesen@gmail.com");
        customer2.setPhoneNo(27710977);

        customerRepository.save(customer2);

        Customer customer3 = new Customer();
        customer3.setCustomerId(3);
        customer3.setUsername("username3");
        customer3.setPassword("Kea1234");
        customer3.setFullName("Heval P.");
        customer3.setEmail("HevalP@outlook.dk");
        customer3.setPhoneNo(23263981);

        customerRepository.save(customer3);

        Customer customer4 = new Customer();
        customer4.setCustomerId(4);
        customer4.setUsername("username4");
        customer4.setPassword("Kea1234");
        customer4.setFullName("Mathilde T.");
        customer4.setEmail("Trendy@gmail.com");
        customer4.setPhoneNo(25263840);

        customerRepository.save(customer4);

    }

}

