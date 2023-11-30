package com.example.bookingsystembackend.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CustomerBookingDto {

    private int bookingId;
    private int treatmentId;
    private int CustomerId;
    private LocalDate bookingDate;
    private LocalTime startTime;
    private String treatmentName;

    public CustomerBookingDto(int bookingId, int treatmentId, LocalDate bookingDate, LocalTime startTime, String treatmentName) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.treatmentId = treatmentId;
        this.startTime = startTime;
        this.treatmentName = treatmentName;
    }
}
