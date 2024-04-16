package com.openclassrooms.chatop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private double surface;

    private double price;

    private String picture;

    private String description;

    //private int owner_id;
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    private Timestamp created_at;

    private Timestamp updated_at;
}
