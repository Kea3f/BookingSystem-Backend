package com.example.bookingsystembackend.service;

import com.example.bookingsystembackend.dto.CustomerBookingDto;
import com.example.bookingsystembackend.entity.Booking;
import com.example.bookingsystembackend.entity.Customer;
import com.example.bookingsystembackend.entity.Treatment;
import com.example.bookingsystembackend.repositories.BookingRepository;
import com.example.bookingsystembackend.repositories.CustomerRepository;
import com.example.bookingsystembackend.repositories.TreatmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    public BookingService(BookingRepository bookingRepository, TreatmentRepository treatmentRepository, CustomerRepository customerRepository, ModelMapper modelMapper, OAuth2AuthorizedClientService authorizedClientService) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.treatmentRepository = treatmentRepository;
        this.modelMapper = modelMapper;
        this.authorizedClientService = authorizedClientService;
    }

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final TreatmentRepository treatmentRepository;


    @Autowired
    private final ModelMapper modelMapper;

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;


    //Viewing all bookings made (for admin)
    public List<Booking> readBookings() {
        return bookingRepository.findAll();
    }

    //Showing your own booking as a customer (for user)
    public List<CustomerBookingDto> getCustomerBookingsDtoForCustomer(int customerId) {
        List<Booking> bookings = bookingRepository.findAllByCustomerId(customerId);
        return bookings.stream()
                .map(this::convertToCustomerBookingDto)
                .collect(Collectors.toList());
    }

    private CustomerBookingDto convertToCustomerBookingDto(Booking booking) {
        return modelMapper.map(booking, CustomerBookingDto.class);
    }

    //Deleting a booking (for user) - chancel
    public void deleteBooking(int bookingId) {
        bookingRepository.deleteById(bookingId);

    }

    //Showing all available bookings for a specific day
    public List<LocalTime> getAvailableBookingTimesForDay(LocalDate bookingDate) {
        List<LocalTime> availableTimeList = new ArrayList<>();

        // Assuming you have a service/repository method to fetch all available bookings for a specific date
        List<Booking> allBookingsForDate = bookingRepository.findAllBookingsBybookingDate(bookingDate);

        // Iterate through all bookings for the given date
        for (Booking booking : allBookingsForDate) {
            if (booking.getCustomer() == null) {
                // If the booking doesn't have a customer assigned, add its start time to the availableTimeList
                availableTimeList.add(booking.getStartTime());
            }
        }

        return availableTimeList;
    }

    // Method to create a booking for a customer
    public Booking createBooking(int customerId, int treatmentId, LocalDate bookingDate, LocalTime startTime) {
        Customer customer = customerRepository.findByCustomerId(customerId);
        Treatment treatment = treatmentRepository.findByTreatmentId(treatmentId);

        if (customer != null && treatment != null) {
            // Check if the selected time slot is available
            List<Booking> bookingsAtTime = bookingRepository.findStartTimesByBookingDateAndAvailableTrue(bookingDate);

            if (bookingsAtTime.isEmpty()) {
                // Time slot is available, create the booking
                Booking newBooking = new Booking();
                newBooking.setCustomer(customer);
                newBooking.setTreatment(treatment);
                newBooking.setBookingDate(bookingDate);
                newBooking.setStartTime(startTime);
                newBooking.setAvailable(false); // Assuming it's not available once booked

                return bookingRepository.save(newBooking);
            } else {
                throw new IllegalArgumentException("Selected time slot is not available.");
            }
        } else {
            throw new IllegalArgumentException("Customer or Treatment not found.");
        }
    }





}















