package com.example.bookingsystembackend.repositories;

import com.example.bookingsystembackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByMail(String mail);
}
