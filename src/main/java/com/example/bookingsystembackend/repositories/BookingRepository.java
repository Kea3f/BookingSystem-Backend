package com.example.bookingsystembackend.repositories;

import com.example.bookingsystembackend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

 List<Booking> findAllByCustomerId(int customerId);

}
