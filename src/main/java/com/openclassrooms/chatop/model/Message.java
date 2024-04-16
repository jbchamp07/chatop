package com.openclassrooms.chatop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //private int rental_id;
    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

    //private int user_id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String message;

    private Timestamp created_at;

    private Timestamp updated_at;

}
