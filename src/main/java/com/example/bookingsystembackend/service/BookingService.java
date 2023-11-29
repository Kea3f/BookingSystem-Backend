package com.example.bookingsystembackend.service;

import com.example.bookingsystembackend.dto.CustomerBookingDto;
import com.example.bookingsystembackend.entity.Booking;
import com.example.bookingsystembackend.repositories.BookingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    public BookingService(BookingRepository bookingRepository, ModelMapper modelMapper) {
        this.bookingRepository=bookingRepository;
        this.modelMapper = modelMapper;
    }

    public Booking createBooking(Booking booking){
        return bookingRepository.save(booking);
    }

    public List<Booking> readBookings(){
        return bookingRepository.findAll();
    }

    public List<CustomerBookingDto> getCustomerBookingsDtoForCustomer(int customerId) {
        List<Booking> bookings = bookingRepository.findAllByCustomerId(customerId);
        return bookings.stream()
                .map(this::convertToCustomerBookingDto)
                .collect(Collectors.toList());
    }

    private CustomerBookingDto convertToCustomerBookingDto(Booking booking) {
        return modelMapper.map(booking, CustomerBookingDto.class);
    }

    public void deleteBooking(int bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}
