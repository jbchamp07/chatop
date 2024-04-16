package com.openclassrooms.chatop.DTO;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;

    private String password;

}
