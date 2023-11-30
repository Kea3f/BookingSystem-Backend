package com.example.bookingsystembackend.controller;

import com.example.bookingsystembackend.entity.Booking;
import com.example.bookingsystembackend.service.BookingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookingController.class)
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingService bookingService;

    /*
    @Test
    void testCreateBooking() throws Exception {
        // Step 1: Create a Booking object with a specific bookingId
        Booking booking = new Booking();
        booking.setBookingId(1);  // Set the bookingId explicitly

        // Step 2: Mock the service method to return the Booking object with the specified bookingId
        Mockito.when(bookingService.createBooking(Mockito.any(Booking.class))).thenReturn(booking);

        // Step 3: Perform a POST request to the "/api/booking/create" endpoint with a JSON payload
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/booking/create")
                        .content("{ \"customer\": {\"customerId\": 1}, \"treatments\": [], \"bookingDate\": \"2023-01-01\", \"startTime\": \"12:00\" }")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                // Step 4: Verify the expected response status, content type, and the bookingId in the response JSON
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.bookingId").value(1)); // Adjust the expectations based on your actual response
    }



    @Test
    void testReadBookings() throws Exception {
        // Step 1: Create two Booking objects for testing
        Booking booking1 = new Booking();
        Booking booking2 = new Booking();

        // Step 2: Create a list of bookings
        List<Booking> bookings = Arrays.asList(booking1, booking2);

        // Step 3: Mock the service method to return the list of bookings
        Mockito.when(bookingService.readBookings()).thenReturn(bookings);

        // Step 4: Perform a GET request to the "/api/booking/bookings" endpoint
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/booking/bookings")
                        .accept(MediaType.APPLICATION_JSON))
                // Step 5: Verify the expected response status, content type, and the bookingId values in the response JSON
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].bookingId").value(booking1.getBookingId())) // Adjust the expectations based on your actual response
                .andExpect(jsonPath("$[1].bookingId").value(booking2.getBookingId()));
    }

    @Test
    void testDeleteBooking() throws Exception {
        // Step 1: Provide a valid booking ID for deletion
        int bookingId = 1;

        // Step 2: Perform a DELETE request to the "/api/booking/delete/{bookingId}" endpoint
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/booking/delete/{bookingId}", bookingId)
                        .accept(MediaType.APPLICATION_JSON))
                // Step 3: Verify the expected response status and content
                .andExpect(status().isOk())
                .andExpect(content().string("Booking Deleted"));
    }

     */
}
