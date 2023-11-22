package com.example.bookingsystembackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
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
    @JoinColumn(name = "userId")
    private Customer customer;

    // Use @ManyToMany for multiple treatments in one booking
    @ManyToMany
    @JoinTable(
            name = "booking_treatments",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "treatment_id")
    )
    private List<Treatment> treatments;

    private LocalDate bookingDate;

    private LocalTime startTime;

    public  LocalTime getEndTime(){
        if (startTime != null && treatments != null){
            int totalDuration = treatments.stream().mapToInt(Treatment::getDuration).sum();
            return startTime.plusMinutes(totalDuration);
        }
        return null;
    }
}
