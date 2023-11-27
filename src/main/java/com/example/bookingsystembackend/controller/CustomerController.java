
package com.example.bookingsystembackend.controller;



import com.example.bookingsystembackend.dto.LoginDto;
import com.example.bookingsystembackend.entity.Customer;
import com.example.bookingsystembackend.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
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

    //Signup
    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer signupCustomer(@RequestBody Customer customer) {
        return customerService.signupCustomer(customer);
    }

    @GetMapping("/info{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId) {
        Customer customer = customerService.getCustomerById(customerId);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/update/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable int customerId, @RequestBody Customer updatedCustomer) throws Exception {
        Customer customer = customerService.updateCustomer(customerId, updatedCustomer);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int customerId) throws Exception {
        customerService.deleteCustomer(customerId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}


