package com.example.bookingsystembackend.service;

import com.example.bookingsystembackend.entity.Booking;
import com.example.bookingsystembackend.repositories.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository=bookingRepository;
    }

    public Booking createBooking(Booking booking){
        return bookingRepository.save(booking);
    }

    public List<Booking> readBookings(){
        return bookingRepository.findAll();
    }

    public void deleteBooking(int bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}
