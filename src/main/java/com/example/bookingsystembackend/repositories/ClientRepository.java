package com.example.bookingsystembackend.repositories;

import com.example.bookingsystembackend.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByMail(String mail);
}
