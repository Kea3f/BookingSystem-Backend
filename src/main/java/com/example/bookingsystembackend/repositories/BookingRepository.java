package com.example.bookingsystembackend.repositories;

import com.example.bookingsystembackend.entity.Booking;
import com.example.bookingsystembackend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

 List<Booking> findAllByCustomer(Customer customer);


 List<Booking> findAllBookingsBybookingDate(LocalDate bookingDate);
 List<Booking> findStartTimesByBookingDateAndAvailableTrue(LocalDate bookingDate);

 Booking updateCustomerAndTreatment(int bookingId, int customerId, int treatmentId);



}
