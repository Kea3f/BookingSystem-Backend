package com.example.bookingsystembackend.repositories;

import com.example.bookingsystembackend.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {

    Treatment findByTreatmentId(int treatmentId);
}
