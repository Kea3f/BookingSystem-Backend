package com.example.bookingsystembackend.repositories;

import com.example.bookingsystembackend.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    Owner findByEmail(String email);
}
