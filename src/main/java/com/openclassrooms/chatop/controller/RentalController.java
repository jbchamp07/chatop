package com.openclassrooms.chatop.controller;

import com.openclassrooms.chatop.DTO.RentalDTO;
import com.openclassrooms.chatop.DTO.RentalResponse;
import com.openclassrooms.chatop.DTO.RentalsResponse;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.service.RentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
@RequestMapping("api/rentals")
@RestController
@Tag(name = "Rentals")
public class RentalController {
    @Autowired
    private RentalService rentalService;
    //Return all rentals
    @Operation(summary = "This method is used to get all rentals")
    @GetMapping("")
    public RentalsResponse getRentals() {
        return rentalService.getRentals();
    }
    //Return a rental
    @Operation(summary = "This method is used to get a rental")
    @GetMapping("/{id}")
    public Rental getRentalById(@PathVariable long id) {
        return rentalService.getRentalById(id);
    }
    //Create a rental
    @Operation(summary = "This method is used to create a rental")
    @PostMapping("")
    public RentalResponse createRental(@RequestParam("picture") MultipartFile file, @RequestParam("name") String name, @RequestParam("surface") String surface, @RequestParam("price") String price, @RequestParam("description") String description) {
        return rentalService.createRental(name,surface,price,description,file);
    }
    @Operation(summary = "This method is used to update a rental")
    @PutMapping("/{id}")
    public RentalResponse updateRental(@PathVariable long id,@RequestParam("name") String name,@RequestParam("surface") String surface,@RequestParam("price") String price,@RequestParam("description") String description,@RequestParam("description") MultipartFile picture){
        return rentalService.updateRental(id,name,surface,price,description,picture);
    }

    @Operation(summary = "This method is used to get the picture rental")
    @GetMapping(value = "/image/{imageUrl}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable String imageUrl){
        // Retourner les octets de l'image dans la réponse
        return ResponseEntity.ok().body(rentalService.getPictures(imageUrl));
    }
}
