
package com.example.bookingsystembackend.controller;



import com.example.bookingsystembackend.dto.LoginDto;
import com.example.bookingsystembackend.entity.Customer;
import com.example.bookingsystembackend.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/api")
public class CustomerRestController {

    private final CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }


    //Login
    @PostMapping("/login")
    public ResponseEntity<Customer> authenticateCustomer(@RequestBody LoginDto loginDto, HttpSession httpSession) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();

        Customer authenticatedCustomer = customerService.authenticateCustomer(username, password);

        if (authenticatedCustomer != null) {
            httpSession.setAttribute("customerId", authenticatedCustomer.getCustomerId());
            return ResponseEntity.ok(authenticatedCustomer);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/customerList")
    public List<Customer> getAllCustomer() {
        return customerService.getAllCustomer();
    }


    @GetMapping("/customerInfo/{customerId}")
    public ResponseEntity<Customer> getCustomerInfo(@PathVariable int customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer signupCustomer(@RequestBody Customer customer) {
        // Consider adding password hashing here before saving to the database
        return customerService.signupCustomer(customer);
    }



    @PutMapping("/editCustomer/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int customerId, @RequestBody Customer updatedCustomer) {
        Customer customer = customerService.updateCustomer(customerId, updatedCustomer);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        }
        return ResponseEntity.notFound().build();
    }




    @DeleteMapping("/deleteCustomer/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok("customer deleted");
    }

}


