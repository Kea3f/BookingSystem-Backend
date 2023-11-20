package com.example.bookingsystembackend.controller;

import com.example.bookingsystembackend.dto.LoginDto;
import com.example.bookingsystembackend.entity.Client;
import com.example.bookingsystembackend.service.ClientService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientRestcontroller {

    private final ClientService clientService;

    public ClientRestcontroller(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/login")
    public ResponseEntity<Client> authenticateClient(@RequestBody LoginDto loginDto, HttpSession httpSession){
        String mail = loginDto.getMail();
        String password = loginDto.getPassword();

        Client authenticatedClient = clientService.authenticateClient(mail, password);

        if (authenticatedClient != null) {
            httpSession.setAttribute("clientMail", authenticatedClient.getMail());
            return ResponseEntity.ok(authenticatedClient);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
