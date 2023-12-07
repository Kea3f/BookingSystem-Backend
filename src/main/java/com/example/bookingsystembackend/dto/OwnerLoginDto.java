
package com.example.bookingsystembackend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OwnerLoginDto {
    private String ownerEmail;
    private String ownerPassword;

    public OwnerLoginDto(String ownerEmail, String password) {
        this.ownerEmail = ownerEmail;
        this.ownerPassword = ownerPassword;
    }
}

