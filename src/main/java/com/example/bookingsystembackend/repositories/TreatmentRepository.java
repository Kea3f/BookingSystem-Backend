package com.example.bookingsystembackend.repositories;

import com.example.bookingsystembackend.entity.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {
    Treatment findByTreatmentId(int treatmentId);
    Treatment setCustomerTreatment(@Param("customer_id") int customerId, @Param("treatment_id") int treatmentId);;


}
