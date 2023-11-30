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

    public CustomerBookingDto(int bookingId, int treatmentId, LocalDate bookingDate, LocalTime startTime) {
        this.bookingId = bookingId;
        this.treatmentId = treatmentId;
        this.bookingDate = bookingDate;
        this.startTime = startTime;
    }

    private LocalTime startTime;
    private String treatmentName;


}
