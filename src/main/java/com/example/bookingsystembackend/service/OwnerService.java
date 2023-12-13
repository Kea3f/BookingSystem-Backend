package com.example.bookingsystembackend.service;

import com.example.bookingsystembackend.dto.OwnerLoginDto;

public interface OwnerService {

    String login(OwnerLoginDto ownerLoginDto);
}
