package com.openclassrooms.chatop.controller;

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
    public List<Rental> getRentals() {
        return rentalService.getRentals();
    }
    @GetMapping("/{id}")
    public Rental getRentalById(@PathVariable long id) {
        return rentalService.getRentalById(id);
    }
    @PostMapping("")
    public ResponseEntity<String> createRental(@Validated @RequestBody Rental rental, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            rentalService.createRental(rental);
            return ResponseEntity.ok("Rental created !");
        }else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error : " + bindingResult.getAllErrors());
        }
    }
    @PutMapping("")
    public ResponseEntity<String> updateRental(@Validated @RequestBody Rental rental, BindingResult bindingResult){
        if(!bindingResult.hasErrors()){
            rentalService.updateRental(rental);
            return ResponseEntity.ok("Rental updated !");
        }else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error : " + bindingResult.getAllErrors());
        }
    }
}
