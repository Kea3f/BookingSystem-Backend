package com.example.bookingsystembackend.controller;


import com.example.bookingsystembackend.dto.CustomerBookingDto;
import com.example.bookingsystembackend.entity.Booking;
import com.example.bookingsystembackend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/bookings")
    public List<Booking> readBookings(){
        return bookingService.readBookings();
    }

    @GetMapping("/bookings/{customerId}")
    public ResponseEntity<List<CustomerBookingDto>> getBookingsForCustomer(@PathVariable int customerId) {
        List<CustomerBookingDto> bookingDtos = bookingService.getCustomerBookingsDtoForCustomer(customerId);
        return ResponseEntity.ok(bookingDtos);
    }
    @DeleteMapping("/delete/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable int bookingId){
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.ok("Booking Deleted");
    }
}
