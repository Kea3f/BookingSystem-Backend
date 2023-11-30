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

        Treatment treatment3 = new Treatment();
        treatment3.setName("Volume lashes");
        treatment3.setDescription("Description of the treatment");
        treatment3.setDuration(75);
        treatment3.setPrice(500);
        treatmentRepository.save(treatment3);

        Treatment treatment4 = new Treatment();
        treatment4.setName("YY lashes");
        treatment4.setDescription("Description of the treatment");
        treatment4.setDuration(60);
        treatment4.setPrice(450);
        treatmentRepository.save(treatment4);





        Booking booking1 = new Booking();
        booking1.setCustomer(customer1);
        booking1.setTreatment(treatment1);
        booking1.setBookingDate(LocalDate.now()); // Just an example date, adjust as needed
        booking1.setStartTime(LocalTime.of(9, 0)); // Set the start time of the booking
        booking1.setEndTime(booking1.getStartTime().plusMinutes(treatment1.getDuration())); // Calculate end time based on treatment duration
        booking1.setAvailable(true); // Example, adjust as needed
        bookingRepository.save(booking1);

        Booking booking2 = new Booking();
        booking2.setCustomer(customer2);
        booking2.setTreatment(treatment2);
        booking2.setBookingDate(LocalDate.now().plusDays(1)); // Just an example date, adjust as needed
        booking2.setStartTime(LocalTime.of(14, 0)); // Set the start time of the booking
        booking2.setEndTime(booking2.getStartTime().plusMinutes(treatment2.getDuration())); // Calculate end time based on treatment duration
        booking2.setAvailable(true); // Example, adjust as needed
        bookingRepository.save(booking2);


    }

}
