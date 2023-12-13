package com.example.bookingsystembackend.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OwnerLoginDto {

    private String email;
    private String password;

    public OwnerLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
