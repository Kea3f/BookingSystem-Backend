package com.example.bookingsystembackend.service;

import com.example.bookingsystembackend.entity.Owner;
import com.example.bookingsystembackend.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    @Autowired
    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public Owner autenticateOwner(String ownerEmail, String ownerPassword) {
        if (ownerEmail == null || ownerPassword == null || ownerEmail.trim().isEmpty() || ownerPassword.trim().isEmpty()) {
            throw new IllegalArgumentException("Email or password cannot be null or empty");
        }
        Owner owner = ownerRepository.findByOwnerEmail(ownerEmail);
        if (owner == null) {
            throw new IllegalArgumentException("User not found");
        }
        if (owner.getOwnerPassword().equals(ownerPassword)) {
            return owner;
        } else {
            throw new IllegalArgumentException("Incorrect password");
        }
    }
}
