package com.example.bookingsystembackend.repositories;

import com.example.bookingsystembackend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

 List<Booking> findAllByCustomerId(int customerId);


 List<Booking> findAllBookingsBybookingDate(LocalDate bookingDate);
 List<Booking> findStartTimesByBookingDateAndAvailableTrue(LocalDate bookingDate);
}
