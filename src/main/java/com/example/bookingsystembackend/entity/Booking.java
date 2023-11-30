package com.example.bookingsystembackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingId;

    @ManyToOne
    private Customer customer;
    private int customerId;

    @ManyToOne
    @JoinColumn(name = "treatment_id")
    private Treatment treatment;

    private LocalDate bookingDate;

    private LocalTime startTime;

    private LocalTime endTime;

    private boolean available;



}
