package com.example.bookingsystembackend.controller;


import com.example.bookingsystembackend.entity.Booking;
import com.example.bookingsystembackend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService bookingService;
    @Autowired
    public BookingController(BookingService bookingService){
        this.bookingService=bookingService;
    }

    @PostMapping("/create")
    public Booking createBooking (@RequestBody Booking booking){
        return this.bookingService.createBooking(booking);
    }


}
