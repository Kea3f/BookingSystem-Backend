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

import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {

    public BookingService(BookingRepository bookingRepository, TreatmentRepository treatmentRepository, CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.treatmentRepository = treatmentRepository;
        this.modelMapper = modelMapper;
    }

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private final CustomerRepository customerRepository;

    @Autowired
    private final TreatmentRepository treatmentRepository;


    @Autowired
    private final ModelMapper modelMapper;


    //Viewing all bookings made (for admin)
    public List<Booking> readBookings() {
        return bookingRepository.findAll();
    }

    //Showing your own booking as a customer (for user)
    public List<CustomerBookingDto> getCustomerBookingsDtoForCustomer(Customer customer) {
        List<Booking> bookings = bookingRepository.findAllByCustomer(customer);
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

    //Showing all available bookings for a specific day (user)
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


    public void updateCustomerAndTreatment(int bookingId, int customerId, int treatmentId) {
        bookingRepository.updateCustomerAndTreatment(bookingId, customerId, treatmentId);
    }

    private boolean isTimeSlotAvailableByAdmin(LocalDate bookingDate, LocalTime startTime) {
        List<Booking> bookingsAtTime = bookingRepository.findStartTimesByBookingDateAndAvailableTrue(bookingDate);

        for (Booking booking : bookingsAtTime) {
            if (booking.getStartTime().equals(startTime)) {
                return false; // Time slot is not available
            }
        }
        return true; // Time slot is available
    }



    // Method to create a booking for a customer (user)
    public Booking createBooking(int customerId, int treatmentId, LocalDate bookingDate, LocalTime startTime) {
        Customer customer = customerRepository.findByCustomerId(customerId);
        Treatment treatment = treatmentRepository.findByTreatmentId(treatmentId);

        if (customer != null && treatment != null) {
            boolean isTimeSlotAvailable = isTimeSlotAvailableByAdmin(bookingDate, startTime);

            if (isTimeSlotAvailable) {
                boolean isTimeSlotBooked = isTimeSlotBooked(bookingDate, startTime);

                if (!isTimeSlotBooked) {
                    Booking newBooking = new Booking();
                    newBooking.setCustomer(customer);
                    newBooking.setTreatment(treatment);
                    newBooking.setBookingDate(bookingDate);
                    newBooking.setStartTime(startTime);
                    newBooking.setAvailable(false); // Assuming it's not available once booked

                    // Save the new booking under the customer's profile
                    customer.addBooking(newBooking);
                    customerRepository.save(customer);

                    return bookingRepository.save(newBooking);
                } else {
                    throw new IllegalArgumentException("Selected time slot is already booked.");
                }
            } else {
                throw new IllegalArgumentException("Selected time slot is not available by admin.");
            }
        } else {
            throw new IllegalArgumentException("Customer or Treatment not found.");
        }
    }


    public Booking updateBooking(int bookingId, int customerId, int treatmentId, LocalDate bookingDate, LocalTime startTime) {
        // Retrieve the existing booking from the repository
        Booking existingBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new IllegalArgumentException("Booking not found for ID: " + bookingId));

        // Check if the existing booking can be updated
        if (!existingBooking.isAvailable()) {
            throw new IllegalArgumentException("Booking cannot be updated as it is not available.");
        }

        // Check if the new time slot is available
        boolean isTimeSlotAvailable = isTimeSlotAvailable(bookingDate, startTime);
        if (!isTimeSlotAvailable) {
            throw new IllegalArgumentException("Selected time slot is not available.");
        }

        // Retrieve the customer and treatment entities
        Customer customer = customerRepository.findByCustomerId(customerId);
        Treatment treatment = treatmentRepository.findByTreatmentId(treatmentId);

        if (customer != null && treatment != null) {
            // Update the existing booking with the new information
            existingBooking.setCustomer(customer);
            existingBooking.setTreatment(treatment);
            existingBooking.setBookingDate(bookingDate);
            existingBooking.setStartTime(startTime);

            // Save the updated booking
            return bookingRepository.save(existingBooking);
        } else {
            throw new IllegalArgumentException("Customer or Treatment not found.");
        }
    }

    private boolean isTimeSlotAvailable(LocalDate bookingDate, LocalTime startTime) {
        List<Booking> bookingsOnDate = bookingRepository.findByBookingDate(bookingDate);

        for (Booking booking : bookingsOnDate) {
            // Check if the requested time slot overlaps with existing bookings
            if (startTime.equals(booking.getStartTime())) {
                return false; // Time slot is already booked
            }
        }
        return true; // Time slot is available

    }


    private boolean isTimeSlotBooked(LocalDate date, LocalTime startTime) {
        List<Booking> bookingsOnDate = bookingRepository.findByBookingDate(date);

        for (Booking booking : bookingsOnDate) {
            // Check if the requested time slot is already booked
            if (startTime.equals(booking.getStartTime())) {
                return true; // Time slot is already booked
            }
        }
        return false; // Time slot is not booked
    }


}

















