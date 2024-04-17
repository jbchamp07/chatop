package com.openclassrooms.chatop.DTO;

import com.openclassrooms.chatop.model.Rental;
import lombok.Data;

import java.util.List;

@Data
public class RentalsResponse {
    private List<Rental> rentals;
}
