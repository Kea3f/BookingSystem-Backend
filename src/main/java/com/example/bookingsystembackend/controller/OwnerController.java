package com.example.bookingsystembackend.controller;

import com.example.bookingsystembackend.dto.LoginDto;
import com.example.bookingsystembackend.dto.OwnerLoginDto;
import com.example.bookingsystembackend.entity.Customer;
import com.example.bookingsystembackend.entity.Owner;
import com.example.bookingsystembackend.service.BookingService;
import com.example.bookingsystembackend.service.CustomerService;
import com.example.bookingsystembackend.service.OwnerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/owner")
public class OwnerController {

    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;


    }
    @PostMapping("/login")
    public ResponseEntity<Owner> authenticateOwner(@RequestBody OwnerLoginDto ownerLoginDto, HttpSession httpSession) {
        String ownerEmail = ownerLoginDto.getOwnerEmail();
        String ownerPassword = ownerLoginDto.getOwnerPassword();

        Owner authenticateOwner = ownerService.autenticateOwner(ownerEmail, ownerPassword);

        if (authenticateOwner != null) {
            httpSession.setAttribute("customerId", authenticateOwner.getOwnerId());
            return ResponseEntity.ok(authenticateOwner);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
