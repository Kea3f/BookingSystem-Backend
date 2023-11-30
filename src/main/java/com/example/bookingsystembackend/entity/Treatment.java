package com.example.bookingsystembackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int treatmentId;

    private String name;
    private String description;
    private int duration;  // Duration in minutes
    private double price;

    public Treatment(int treatmentId, String name, String description, int duration, double price) {
        this.treatmentId = treatmentId;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.price = price;
    }


    @ManyToOne
    private Customer customer;

}
