package com.example.bookingsystembackend.service;

import com.example.bookingsystembackend.entity.Client;
import com.example.bookingsystembackend.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository){
        this.clientRepository=clientRepository;
    }

    public Client authenticateClient(String mail, String password){
        Client client = clientRepository.findByMail(mail);
        if(client != null && client.getPassword().equals(password)){
            return client;
        }
        return null;
    }

}
