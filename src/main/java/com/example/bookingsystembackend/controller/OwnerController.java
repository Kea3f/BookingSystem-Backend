package com.example.bookingsystembackend.controller;

import com.example.bookingsystembackend.dto.OwnerLoginDto;
import com.example.bookingsystembackend.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/owner")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @PostMapping("/login")
    public String login(@RequestBody OwnerLoginDto ownerLoginDto) {
        return ownerService.login(ownerLoginDto);
    }
}
