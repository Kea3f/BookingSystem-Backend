/*
package com.example.bookingsystembackend.controller;

import com.example.bookingsystembackend.dto.CustomerBookingDto;
import com.example.bookingsystembackend.entity.Booking;
import com.example.bookingsystembackend.service.BookingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Verify.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class BookingControllerTest {

    @Mock
    private BookingService bookingService;

    @InjectMocks
    private BookingController bookingController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAvailableBookingTimesForDay_WithValidDate_ShouldReturnOkResponse() {
        // Arrange
        BookingService bookingService = mock(BookingService.class);
        BookingController bookingController = new BookingController(bookingService);

        List<LocalTime> mockAvailableTimes = Arrays.asList(LocalTime.of(10, 0), LocalTime.of(11, 0));
        when(bookingService.getAvailableBookingTimesForDay(any())).thenReturn(mockAvailableTimes);

        // Act
        ResponseEntity<Object> response = bookingController.getAvailableBookingTimesForDay("2023-12-01");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }



    @Test
    public void testCreateBooking_WithValidData_ShouldReturnOkResponse() {
        // Arrange
        BookingService bookingService = mock(BookingService.class);
        BookingController bookingController = new BookingController(bookingService);

        when(bookingService.createBooking(anyInt(), anyInt(), any(LocalDate.class), any(LocalTime.class)))
                .thenReturn(new Booking()); // Assuming createBooking returns a Booking object

        // Act
        ResponseEntity<Object> response = bookingController.createBooking(1, 2, "2023-12-01", "10:00");

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
        */