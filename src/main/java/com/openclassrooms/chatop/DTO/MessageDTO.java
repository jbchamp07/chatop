package com.openclassrooms.chatop.DTO;

import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.model.User;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class MessageDTO {

    @Id
    private long id;

    private int rental_id;

    private int user_id;

    private String message;
}
