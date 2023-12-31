package com.example.bookingsystembackend.controller;


import com.example.bookingsystembackend.dto.CustomerBookingDto;
import com.example.bookingsystembackend.entity.Booking;
import com.example.bookingsystembackend.entity.Customer;
import com.example.bookingsystembackend.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;


@RestController
@CrossOrigin
@RequestMapping("/api/booking")
public class BookingController {

    //
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }


    @GetMapping("/bookings")
    public List<Booking> readBookings(){
        return bookingService.readBookings();
    }

    @GetMapping("/bookings/{customerId}")
    public ResponseEntity<List<CustomerBookingDto>> getBookingsForCustomer(@PathVariable Customer customer) {
        List<CustomerBookingDto> bookingDtos = bookingService.getCustomerBookingsDtoForCustomer(customer);
        return ResponseEntity.ok(bookingDtos);
    }

    @PutMapping("/update/{bookingId}")
    public ResponseEntity<Object> updateBooking(
            @PathVariable int bookingId,
            @RequestParam int customerId,
            @RequestParam int treatmentId,
            @RequestParam String bookingDate,
            @RequestParam String startTime
    ) {
        try {
            // Assuming you have a method in BookingService to handle the update
            Booking updatedBooking = bookingService.updateBooking(
                    bookingId,
                    customerId,
                    treatmentId,
                    LocalDate.parse(bookingDate),
                    LocalTime.parse(startTime)
            );
            return ResponseEntity.ok(updatedBooking);
        } catch (DateTimeParseException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid input format or " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating booking: " + e.getMessage());
        }
    }
    @DeleteMapping("/delete/{bookingId}")
    public ResponseEntity<String> deleteBooking(@PathVariable int bookingId){
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.ok("Booking Deleted");
    }

    /*
    @PostMapping("/create")
    public ResponseEntity<Object> createBooking(
            @RequestParam int customerId,
            @RequestParam int treatmentId,
            @RequestParam String bookingDate,
            @RequestParam String startTime
    ) {
        try {
            LocalDate parsedBookingDate = LocalDate.parse(bookingDate);
            LocalTime parsedStartTime = LocalTime.parse(startTime);

            Booking newBooking = bookingService.createBooking(
                    customerId,
                    treatmentId,
                    parsedBookingDate,
                    parsedStartTime
            );
            return ResponseEntity.ok(newBooking);
        } catch (DateTimeParseException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid input format or " + e.getMessage());
        }
    }
    
     */


    @GetMapping("/available-times")
    public ResponseEntity<Object> getAvailableBookingTimesForDay(@RequestParam String bookingDate) {
        try {
            LocalDate parsedBookingDate = LocalDate.parse(bookingDate);
            List<LocalTime> availableTimes = bookingService.getAvailableBookingTimesForDay(parsedBookingDate);
            return ResponseEntity.ok(availableTimes);
        } catch (DateTimeParseException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid input format or " + e.getMessage());
        }
    }

    @GetMapping("/availableSlots")
    public ResponseEntity<List<LocalTime>> getAvailableBookingSlots(@RequestParam("bookingDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bookingDate) {
        try {
            List<LocalTime> availableTimes = bookingService.getAvailableBookingTimesForDay(bookingDate);
            return ResponseEntity.ok(availableTimes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
