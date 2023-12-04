package com.example.bookingsystembackend.config;

import com.example.bookingsystembackend.entity.Booking;
import com.example.bookingsystembackend.entity.Customer;
import com.example.bookingsystembackend.entity.Treatment;
import com.example.bookingsystembackend.repositories.BookingRepository;
import com.example.bookingsystembackend.repositories.CustomerRepository;
import com.example.bookingsystembackend.repositories.TreatmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class CustomerData implements CommandLineRunner {

    CustomerRepository customerRepository;
    TreatmentRepository treatmentRepository;
    BookingRepository bookingRepository;

    public CustomerData(CustomerRepository customerRepository, TreatmentRepository treatmentRepository, BookingRepository bookingRepository) {
        this.customerRepository = customerRepository;
        this.treatmentRepository = treatmentRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public void run(String... args) {

        // Single lashes treatments
        Treatment singleNewSet = new Treatment();
        singleNewSet.setName("Single lashes - New Set");
        singleNewSet.setDescription("Application of a new set of single lashes.");
        singleNewSet.setDuration(90);
        singleNewSet.setPrice(400);
        treatmentRepository.save(singleNewSet);

        Treatment singleRefillLarge = new Treatment();
        singleRefillLarge.setName("Single lashes - Large Refill");
        singleRefillLarge.setDescription("Refill for single lashes with 40-50 lashes remaining within 3 weeks.");
        singleRefillLarge.setDuration(60);
        singleRefillLarge.setPrice(350);
        treatmentRepository.save(singleRefillLarge);

        Treatment singleRefillSmall = new Treatment();
        singleRefillSmall.setName("Single lashes - Small Refill");
        singleRefillSmall.setDescription("Refill for single lashes with 20-30% remaining within 2 weeks.");
        singleRefillSmall.setDuration(60);
        singleRefillSmall.setPrice(300);
        treatmentRepository.save(singleRefillSmall);

        // Hybrid lashes treatments
        Treatment hybridNewSet = new Treatment();
        hybridNewSet.setName("Hybrid lashes - New Set");
        hybridNewSet.setDescription("Application of a new set of hybrid lashes.");
        hybridNewSet.setDuration(120);
        hybridNewSet.setPrice(450);
        treatmentRepository.save(hybridNewSet);

        Treatment hybridRefillLarge = new Treatment();
        hybridRefillLarge.setName("Hybrid lashes - Large Refill");
        hybridRefillLarge.setDescription("Refill for hybrid lashes with 40-50 lashes remaining within 3 weeks.");
        hybridRefillLarge.setDuration(75);
        hybridRefillLarge.setPrice(400);
        treatmentRepository.save(hybridRefillLarge);

        Treatment hybridRefillSmall = new Treatment();
        hybridRefillSmall.setName("Hybrid lashes - Small Refill");
        hybridRefillSmall.setDescription("Refill for hybrid lashes with 20-30% remaining within 2 weeks.");
        hybridRefillSmall.setDuration(75);
        hybridRefillSmall.setPrice(350);
        treatmentRepository.save(hybridRefillSmall);

        // YY lashes treatments
        Treatment yyNewSet = new Treatment();
        yyNewSet.setName("YY lashes - New Set");
        yyNewSet.setDescription("Application of a new set of YY lashes.");
        yyNewSet.setDuration(100);
        yyNewSet.setPrice(450);
        treatmentRepository.save(yyNewSet);

        Treatment yyRefillLarge = new Treatment();
        yyRefillLarge.setName("YY lashes - Large Refill");
        yyRefillLarge.setDescription("Refill for YY lashes with 40-50 lashes remaining within 3 weeks.");
        yyRefillLarge.setDuration(70);
        yyRefillLarge.setPrice(400);
        treatmentRepository.save(yyRefillLarge);

        Treatment yyRefillSmall = new Treatment();
        yyRefillSmall.setName("YY lashes - Small Refill");
        yyRefillSmall.setDescription("Refill for YY lashes with 20-30% remaining within 2 weeks.");
        yyRefillSmall.setDuration(70);
        yyRefillSmall.setPrice(350);
        treatmentRepository.save(yyRefillSmall);

        // Volume lashes treatments
        Treatment volumeNewSet = new Treatment();
        volumeNewSet.setName("Volume lashes - New Set");
        volumeNewSet.setDescription("Application of a new set of volume lashes.");
        volumeNewSet.setDuration(150);
        volumeNewSet.setPrice(500);
        treatmentRepository.save(volumeNewSet);

        Treatment volumeRefillLarge = new Treatment();
        volumeRefillLarge.setName("Volume lashes - Large Refill");
        volumeRefillLarge.setDescription("Refill for volume lashes with 40-50 lashes remaining within 3 weeks.");
        volumeRefillLarge.setDuration(90);
        volumeRefillLarge.setPrice(450);
        treatmentRepository.save(volumeRefillLarge);

        Treatment volumeRefillSmall = new Treatment();
        volumeRefillSmall.setName("Volume lashes - Small Refill");
        volumeRefillSmall.setDescription("Refill for volume lashes with 20-30% remaining within 2 weeks.");
        volumeRefillSmall.setDuration(90);
        volumeRefillSmall.setPrice(450);
        treatmentRepository.save(volumeRefillSmall);




        // Create and save customers
        Customer customer1 = new Customer();
        customer1.setCustomerId(1);
        customer1.setUsername("username1");
        customer1.setPassword("Kea1234");
        customer1.setFullName("Naja Moe");
        customer1.setEmail("najamoe@outlook.dk");
        customer1.setPhoneNo(62622367);
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setCustomerId(2);
        customer2.setUsername("username2");
        customer2.setPassword("Kea1234");
        customer2.setFullName("Sabrina Ebbesen");
        customer2.setEmail("Sabrina.ebbesen@gmail.com");
        customer2.setPhoneNo(27710977);
        customerRepository.save(customer2);




        Booking booking1 = new Booking();
        booking1.setTreatment(singleNewSet);
        booking1.setCustomer(customer1);
        booking1.setBookingDate(LocalDate.now()); // Just an example date, adjust as needed
        booking1.setStartTime(LocalTime.of(9, 0)); // Set the start time of the booking
        booking1.setEndTime(booking1.getStartTime().plusMinutes(singleNewSet.getDuration())); // Calculate end time based on treatment duration
        booking1.setAvailable(true); // Example, adjust as needed
        bookingRepository.save(booking1);

        Booking booking2 = new Booking();
        booking2.setTreatment(volumeRefillLarge);
        booking2.setCustomer(customer2);
        booking2.setBookingDate(LocalDate.now().plusDays(1)); // Just an example date, adjust as needed
        booking2.setStartTime(LocalTime.of(14, 0)); // Set the start time of the booking
        booking2.setEndTime(booking2.getStartTime().plusMinutes(volumeRefillLarge.getDuration())); // Calculate end time based on treatment duration
        booking2.setAvailable(true); // Example, adjust as needed
        bookingRepository.save(booking2);


    }

}
