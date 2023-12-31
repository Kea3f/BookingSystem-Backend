package com.example.bookingsystembackend.config;

import com.example.bookingsystembackend.entity.Booking;
import com.example.bookingsystembackend.entity.Customer;
import com.example.bookingsystembackend.entity.Owner;
import com.example.bookingsystembackend.entity.Treatment;
import com.example.bookingsystembackend.repositories.BookingRepository;
import com.example.bookingsystembackend.repositories.CustomerRepository;
import com.example.bookingsystembackend.repositories.OwnerRepository;
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
    OwnerRepository ownerRepository;


    public CustomerData(CustomerRepository customerRepository, TreatmentRepository treatmentRepository, BookingRepository bookingRepository, OwnerRepository ownerRepository) {
        this.customerRepository = customerRepository;
        this.treatmentRepository = treatmentRepository;
        this.bookingRepository = bookingRepository;
        this.ownerRepository = ownerRepository;
    }

    @Override
    public void run(String... args) {

        // Single lashes treatments
        Treatment singleNewSet = new Treatment();
        singleNewSet.setTreatmentId(1);
        singleNewSet.setName("Single lashes - New Set");
        singleNewSet.setDescription("Application of a new set of single lashes.");
        singleNewSet.setDuration(120);
        singleNewSet.setPrice(400);
        treatmentRepository.save(singleNewSet);

        Treatment singleRefillLarge = new Treatment();
        singleRefillLarge.setTreatmentId(2);
        singleRefillLarge.setName("Single lashes - Large Refill");
        singleRefillLarge.setDescription("Refill for single lashes with 40-50 lashes remaining within 3 weeks.");
        singleRefillLarge.setDuration(120);
        singleRefillLarge.setPrice(350);
        treatmentRepository.save(singleRefillLarge);

        Treatment singleRefillSmall = new Treatment();
        singleRefillSmall.setTreatmentId(3);
        singleRefillSmall.setName("Single lashes - Small Refill");
        singleRefillSmall.setDescription("Refill for single lashes with 20-30% remaining within 2 weeks.");
        singleRefillSmall.setDuration(120);
        singleRefillSmall.setPrice(300);
        treatmentRepository.save(singleRefillSmall);

        // Hybrid lashes treatments
        Treatment hybridNewSet = new Treatment();
        hybridNewSet.setTreatmentId(4);
        hybridNewSet.setName("Hybrid lashes - New Set");
        hybridNewSet.setDescription("Application of a new set of hybrid lashes.");
        hybridNewSet.setDuration(120);
        hybridNewSet.setPrice(450);
        treatmentRepository.save(hybridNewSet);

        Treatment hybridRefillLarge = new Treatment();
        hybridRefillLarge.setTreatmentId(5);
        hybridRefillLarge.setName("Hybrid lashes - Large Refill");
        hybridRefillLarge.setDescription("Refill for hybrid lashes with 40-50 lashes remaining within 3 weeks.");
        hybridRefillLarge.setDuration(120);
        hybridRefillLarge.setPrice(400);
        treatmentRepository.save(hybridRefillLarge);

        Treatment hybridRefillSmall = new Treatment();
        hybridRefillSmall.setTreatmentId(6);
        hybridRefillSmall.setName("Hybrid lashes - Small Refill");
        hybridRefillSmall.setDescription("Refill for hybrid lashes with 20-30% remaining within 2 weeks.");
        hybridRefillSmall.setDuration(120);
        hybridRefillSmall.setPrice(350);
        treatmentRepository.save(hybridRefillSmall);

        // YY lashes treatments
        Treatment yyNewSet = new Treatment();
        yyNewSet.setTreatmentId(7);
        yyNewSet.setName("YY lashes - New Set");
        yyNewSet.setDescription("Application of a new set of YY lashes.");
        yyNewSet.setDuration(120);
        yyNewSet.setPrice(450);
        treatmentRepository.save(yyNewSet);

        Treatment yyRefillLarge = new Treatment();
        yyRefillLarge.setTreatmentId(8);
        yyRefillLarge.setName("YY lashes - Large Refill");
        yyRefillLarge.setDescription("Refill for YY lashes with 40-50 lashes remaining within 3 weeks.");
        yyRefillLarge.setDuration(120);
        yyRefillLarge.setPrice(400);
        treatmentRepository.save(yyRefillLarge);

        Treatment yyRefillSmall = new Treatment();
        yyRefillSmall.setTreatmentId(9);
        yyRefillSmall.setName("YY lashes - Small Refill");
        yyRefillSmall.setDescription("Refill for YY lashes with 20-30% remaining within 2 weeks.");
        yyRefillSmall.setDuration(120);
        yyRefillSmall.setPrice(350);
        treatmentRepository.save(yyRefillSmall);

        // Volume lashes treatments
        Treatment volumeNewSet = new Treatment();
        volumeNewSet.setTreatmentId(10);
        volumeNewSet.setName("Volume lashes - New Set");
        volumeNewSet.setDescription("Application of a new set of volume lashes.");
        volumeNewSet.setDuration(120);
        volumeNewSet.setPrice(500);
        treatmentRepository.save(volumeNewSet);

        Treatment volumeRefillLarge = new Treatment();
        volumeRefillLarge.setTreatmentId(11);
        volumeRefillLarge.setName("Volume lashes - Large Refill");
        volumeRefillLarge.setDescription("Refill for volume lashes with 40-50 lashes remaining within 3 weeks.");
        volumeRefillLarge.setDuration(120);
        volumeRefillLarge.setPrice(450);
        treatmentRepository.save(volumeRefillLarge);

        Treatment volumeRefillSmall = new Treatment();
        volumeRefillSmall.setTreatmentId(12);
        volumeRefillSmall.setName("Volume lashes - Small Refill");
        volumeRefillSmall.setDescription("Refill for volume lashes with 20-30% remaining within 2 weeks.");
        volumeRefillSmall.setDuration(120);
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
        booking1.setTreatment(volumeRefillLarge);
        booking1.setCustomer(customer1);
        booking1.setBookingDate(LocalDate.now()); // Just an example date, adjust as needed
        booking1.setStartTime(LocalTime.of(9, 0)); // Set the start time of the booking
        booking1.setEndTime(booking1.getStartTime().plusMinutes(volumeRefillLarge.getDuration())); // Calculate end time based on treatment duration
        booking1.setAvailable(true); // Example, adjust as needed
        bookingRepository.save(booking1);

        Booking booking2 = new Booking();
        booking2.setTreatment(hybridNewSet);
        booking2.setCustomer(customer2);
        booking2.setBookingDate(LocalDate.now().plusDays(1)); // Just an example date, adjust as needed
        booking2.setStartTime(LocalTime.of(14, 0)); // Set the start time of the booking
        booking2.setEndTime(booking2.getStartTime().plusMinutes(hybridNewSet.getDuration())); // Calculate end time based on treatment duration
        booking2.setAvailable(true); // Example, adjust as needed
        bookingRepository.save(booking2);

        Owner owner1 = new Owner();
        owner1.setId(1);
        owner1.setEmail("Amalie");
        owner1.setPassword("Kea1234");
        owner1.setEmail("biran@outlook.dk");
        ownerRepository.save(owner1);


}
}
