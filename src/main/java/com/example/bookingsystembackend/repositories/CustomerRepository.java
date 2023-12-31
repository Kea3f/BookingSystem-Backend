
package com.example.bookingsystembackend.repositories;

import com.example.bookingsystembackend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByUsername(String username);
    Customer findByCustomerId(int customerId);
    Customer findByEmail(String email);
}


