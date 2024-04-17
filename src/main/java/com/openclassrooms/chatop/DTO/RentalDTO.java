package com.openclassrooms.chatop.DTO;

import lombok.Data;

@Data
public class RentalDTO {
    private String name;
    private double surface;
    private double price;
    private String description;
    private String picture;
}
