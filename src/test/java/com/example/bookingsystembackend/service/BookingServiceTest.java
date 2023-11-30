package com.example.bookingsystembackend.service;

import com.example.bookingsystembackend.entity.Booking;
import com.example.bookingsystembackend.entity.Customer;
import com.example.bookingsystembackend.entity.Treatment;
import com.example.bookingsystembackend.repositories.BookingRepository;
import com.example.bookingsystembackend.repositories.CustomerRepository;
import com.example.bookingsystembackend.repositories.TreatmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookingServiceTest {

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private TreatmentRepository treatmentRepository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAvailableBookingTimesForDay() {
        // Arrange
        LocalDate bookingDate = LocalDate.of(2023, 12, 1);
        Booking booking1 = new Booking();
        booking1.setStartTime(LocalTime.of(10, 0));
        Booking booking2 = new Booking();
        booking2.setStartTime(LocalTime.of(11, 0));

        List<Booking> allBookingsForDate = new ArrayList<>();
        allBookingsForDate.add(booking1);
        allBookingsForDate.add(booking2);

        when(bookingRepository.findAllBookingsBybookingDate(bookingDate)).thenReturn(allBookingsForDate);

        // Act
        List<LocalTime> availableTimes = bookingService.getAvailableBookingTimesForDay(bookingDate);

        // Assert
        assertEquals(2, availableTimes.size());
        assertTrue(availableTimes.contains(LocalTime.of(10, 0)));
        assertTrue(availableTimes.contains(LocalTime.of(11, 0)));
    }

    @Test
    public void testCreateBooking_Success() {
        int customerId = 1;
        int treatmentId = 1;
        LocalDate bookingDate = LocalDate.of(2023, 12, 31);
        LocalTime startTime = LocalTime.of(10, 30);

        Customer customer = new Customer();
        customer.setCustomerId(customerId);

        Treatment treatment = new Treatment();
        treatment.setTreatmentId(treatmentId);

        when(customerRepository.findByCustomerId(customerId)).thenReturn(customer);
        when(treatmentRepository.findByTreatmentId(treatmentId)).thenReturn(treatment);
        when(bookingRepository.findStartTimesByBookingDateAndAvailableTrue(bookingDate)).thenReturn(new ArrayList<>());

        Booking mockedBooking = new Booking();
        mockedBooking.setCustomer(customer);
        mockedBooking.setTreatment(treatment);
        mockedBooking.setBookingDate(bookingDate);
        mockedBooking.setStartTime(startTime);

        when(bookingRepository.save(any())).thenReturn(mockedBooking);

        Booking result = bookingService.createBooking(customerId, treatmentId, bookingDate, startTime);

        assertNotNull(result);
        assertEquals(customerId, result.getCustomer().getCustomerId());
        assertEquals(treatmentId, result.getTreatment().getTreatmentId());
        assertEquals(bookingDate, result.getBookingDate());
        assertEquals(startTime, result.getStartTime());
        assertFalse(result.isAvailable());
    }

  }