package com.example.bookingsystembackend.config;

import com.example.bookingsystembackend.entity.Booking;
import com.example.bookingsystembackend.entity.BookingTreatment;
import com.example.bookingsystembackend.entity.Customer;
import com.example.bookingsystembackend.entity.Treatment;
import com.example.bookingsystembackend.repositories.BookingRepository;
import com.example.bookingsystembackend.repositories.BookingTreatmentRepository;
import com.example.bookingsystembackend.repositories.CustomerRepository;
import com.example.bookingsystembackend.repositories.TreatmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@Component
public class CustomerData implements CommandLineRunner {

    CustomerRepository customerRepository;
    TreatmentRepository treatmentRepository;
    BookingRepository bookingRepository;
    BookingTreatmentRepository bookingTreatmentRepository;

    public CustomerData(BookingTreatmentRepository bookingTreatmentRepository, CustomerRepository customerRepository, TreatmentRepository treatmentRepository, BookingRepository bookingRepository) {
        this.customerRepository = customerRepository;
        this.treatmentRepository = treatmentRepository;
        this.bookingRepository = bookingRepository;
        this.bookingTreatmentRepository = bookingTreatmentRepository;
    }

    @Override
    public void run(String... args) {

        // Create and save treatments
        Treatment treatment1 = new Treatment();
        treatment1.setName("Single lashes");
        treatment1.setDescription("Description of the treatment");
        treatment1.setDuration(60);
        treatment1.setPrice(400);
        treatmentRepository.save(treatment1);

        Treatment treatment2 = new Treatment();
        treatment2.setName("Hybrid lashes");
        treatment2.setDescription("Description of the treatment");
        treatment2.setDuration(45);
        treatment2.setPrice(400);
        treatmentRepository.save(treatment2);

        // Create and save customers
        Customer customer1 = new Customer();
        customer1.setUsername("username1");
        customer1.setPassword("Kea1234");
        customer1.setFullName("Naja Moe");
        customer1.setEmail("najamoe@outlook.dk");
        customer1.setPhoneNo(62622367);

        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setUsername("username2");
        customer2.setPassword("Kea1234");
        customer2.setFullName("Sabrina Ebbesen");
        customer2.setEmail("Sabrina.ebbesen@gmail.com");
        customer2.setPhoneNo(27710977);
        customerRepository.save(customer2);

        // Create and save booking with treatments
        Booking booking1 = new Booking();
        booking1.setCustomer(customer1);
        booking1.setBookingTreatments(new ArrayList<>()); // Initialize the list

        BookingTreatment bookingTreatment1 = new BookingTreatment();
        bookingTreatment1.setBooking(booking1);
        bookingTreatment1.setCustomer(customer1);
        bookingTreatment1.setTreatment(treatment2);

        // Save the booking first
        bookingRepository.save(booking1);

        // Now, save the BookingTreatment
        bookingTreatmentRepository.save(bookingTreatment1);

        booking1.getBookingTreatments().add(bookingTreatment1);
        booking1.setBookingDate(LocalDate.now());
        booking1.setStartTime(LocalTime.of(14, 0));
        bookingRepository.save(booking1);

        // Create and save booking with treatments
        Booking booking2 = new Booking();
        booking2.setCustomer(customer1);
        booking2.setBookingTreatments(new ArrayList<>()); // Initialize the list

        BookingTreatment bookingTreatment2 = new BookingTreatment();
        bookingTreatment2.setBooking(booking2);
        bookingTreatment2.setCustomer(customer1);
        bookingTreatment2.setTreatment(treatment2);

        // Save the booking first
        bookingRepository.save(booking2);

        // Now, save the BookingTreatment
        bookingTreatmentRepository.save(bookingTreatment2);

        booking2.getBookingTreatments().add(bookingTreatment2);
        booking2.setBookingDate(LocalDate.now());
        booking2.setStartTime(LocalTime.of(14, 0));
        bookingRepository.save(booking2);
    }

}
