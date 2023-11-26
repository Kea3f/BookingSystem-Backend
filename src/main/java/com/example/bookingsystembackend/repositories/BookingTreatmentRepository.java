package com.example.bookingsystembackend.repositories;

import com.example.bookingsystembackend.entity.BookingTreatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingTreatmentRepository extends JpaRepository<BookingTreatment, Integer> {
}
