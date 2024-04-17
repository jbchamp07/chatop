package com.openclassrooms.chatop.controller;

import com.openclassrooms.chatop.DTO.RentalDTO;
import com.openclassrooms.chatop.DTO.RentalResponse;
import com.openclassrooms.chatop.DTO.RentalsResponse;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("api/rentals")
@RestController
public class RentalController {
    @Autowired
    private RentalService rentalService;
    @GetMapping("")
    public RentalsResponse getRentals() {
        return rentalService.getRentals();
    }
    @GetMapping("/{id}")
    public Rental getRentalById(@PathVariable long id) {
        return rentalService.getRentalById(id);
    }
    @PostMapping("")
    public RentalResponse createRental(@RequestParam("name") String name,@RequestParam("surface") String surface,@RequestParam("price") String price,@RequestParam("description") String description,@RequestParam("picture") String picture){
        return rentalService.createRental(name,surface,price,description,picture);
    }
    @PutMapping("/{id}")
    public RentalResponse updateRental(@PathVariable long id,@RequestParam("name") String name,@RequestParam("surface") String surface,@RequestParam("price") String price,@RequestParam("description") String description,@RequestParam("description") String picture){
        return rentalService.updateRental(id,name,surface,price,description,picture);
    }
}
