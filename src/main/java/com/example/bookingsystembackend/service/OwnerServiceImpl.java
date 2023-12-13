package com.example.bookingsystembackend.service;

import com.example.bookingsystembackend.dto.OwnerLoginDto;
import com.example.bookingsystembackend.entity.Owner;
import com.example.bookingsystembackend.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public String login(OwnerLoginDto ownerLoginDto) {
        Owner owner = ownerRepository.findByEmail(ownerLoginDto.getEmail());

        if (owner != null && owner.getPassword().equals(ownerLoginDto.getPassword())) {
            return "Login successful!";
        } else {
            return "Login failed. Please check your username and password.";
        }
    }
}
