package com.openclassrooms.chatop.DTO;

import lombok.Data;

@Data
public class MessageRequest {


    private int rental_id;

    private int user_id;

    private String message;
}
