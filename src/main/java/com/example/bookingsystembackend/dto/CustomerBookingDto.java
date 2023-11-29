package com.example.bookingsystembackend.dto;


import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CustomerBookingDto {

    private int bookingId;
    private LocalDate bookingDate;
    private LocalTime startTime;
    private String treatmentName;

    public CustomerBookingDto(int bookingId, LocalDate bookingDate, LocalTime startTime, String treatmentName) {
        this.bookingId = bookingId;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
        this.treatmentName = treatmentName;
    }
}
