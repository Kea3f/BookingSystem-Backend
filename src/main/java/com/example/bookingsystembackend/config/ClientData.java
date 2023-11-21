package com.example.bookingsystembackend.config;

import com.example.bookingsystembackend.entity.User;
import com.example.bookingsystembackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ClientData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ClientData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public void run(String... args)  {
        createAndSaveClient("John", "Doe", "johndoe@live.com", "1234", 21467376);
        createAndSaveClient("Jane", "Smith", "janesmith@gmail.com", "5678", 98765432);
        createAndSaveClient("Mike", "Johnson", "mikejohnson@yahoo.com", "abcd", 87654321);
        createAndSaveClient("Emily", "Williams", "emilywilliams@hotmail.com", "efgh", 76543210);
        createAndSaveClient("Chris", "Anderson", "chrisanderson@gmail.com", "ijkl", 65432109);
        createAndSaveClient("Sarah", "Brown", "sarahbrown@yahoo.com", "mnop", 54321098);
        createAndSaveClient("Alex", "Davis", "alexdavis@hotmail.com", "qrst", 43210987);
        createAndSaveClient("Jessica", "Miller", "jessicamiller@gmail.com", "uvwx", 32109876);
        createAndSaveClient("Brian", "White", "brianwhite@yahoo.com", "yz12", 21098765);
    }

    private void createAndSaveClient(String firstname, String lastname, String mail, String password, int phoneNo) {
        User user = new User();
        user.setFirstname(firstname);
        user.setLastname(lastname);
        user.setMail(mail);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhoneNo(phoneNo);
        userRepository.save(user);
    }
}
