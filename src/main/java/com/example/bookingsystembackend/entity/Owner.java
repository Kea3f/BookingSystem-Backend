package com.example.bookingsystembackend.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "owner_id")
    private int ownerId;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "parent_owner_id") // Choose a more meaningful name for the column
    private Owner parentOwner;
}
