package com.example.bookingsystembackend.controller;


import com.example.bookingsystembackend.dto.CustomerBookingDto;
import com.example.bookingsystembackend.entity.Booking;
import com.example.bookingsystembackend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
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

    // Endpoint to get available booking times for a specific day
    @GetMapping("/available-times")
    public ResponseEntity<List<LocalTime>> getAvailableBookingTimes(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bookingDate) {
        List<LocalTime> availableTimes = bookingService.getAvailableBookingTimesForDay(bookingDate);
        return ResponseEntity.ok(availableTimes);
    }

    // Endpoint to create a booking for a customer
    @PostMapping("/create/{treatmentId}/{customerId}")
    public ResponseEntity<Booking> createBooking(@RequestBody CustomerBookingDto customerBookingDto) {
        Booking newBooking = bookingService.createBooking(
                customerBookingDto.getCustomerId(),
                customerBookingDto.getTreatmentId(),
                customerBookingDto.getBookingDate(),
                customerBookingDto.getStartTime()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(newBooking);
    }


}
