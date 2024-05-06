package com.openclassrooms.chatop.service;


import com.openclassrooms.chatop.DTO.RentalResponse;
import com.openclassrooms.chatop.DTO.RentalsResponse;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.RentalRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Data
@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private UserService userService;

    private String picturesPath = "src/main/resources/static/img";

    //Return all rentals
    public RentalsResponse getRentals(){
        RentalsResponse rentalsResponse = new RentalsResponse();
        rentalsResponse.setRentals((List<Rental>) rentalRepository.findAll());
        return rentalsResponse;
    }
    //Return a rental found by its id
    public Rental getRentalById(long id){
        return rentalRepository.findById(id).get();
    }

    //Create a rental and return a message for confirmation
    public RentalResponse createRental(String name, String surface, String price, String description, MultipartFile picture) {
        RentalResponse rentalResponse = new RentalResponse();
        Rental rental = new Rental();
        rental.setName(name);
        rental.setSurface(Double.parseDouble(surface));
        rental.setPrice(Double.parseDouble(price));
        rental.setDescription(description);
        rental.setCreated_at(Timestamp.from(Instant.now()));
        rental.setUpdated_at(Timestamp.from(Instant.now()));
        rental.setOwner(userService.getUserInfo());

        try {
            rental.setPicture(addPicture(picture));;
        } catch (IOException e) {
            rentalResponse.setMessage("Problem with picture : " + e.getMessage());
            return rentalResponse;
        }

        rentalRepository.save(rental);
        if(rentalRepository.findById(rental.getId()).get() != null)
            rentalResponse.setMessage("Rental created !");
        else
            rentalResponse.setMessage("Rental not created !");
        return rentalResponse;
    }

    private String addPicture(MultipartFile picture) throws IOException {
        Path filePath = Paths.get(picturesPath + File.separator + picture.getOriginalFilename());
        Files.copy(picture.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return filePath.toString();
    }

    //Update a rental and return a message for confirmation
    public RentalResponse updateRental(long id,String name, String surface, String price, String description, String picture) {
        RentalResponse rentalResponse = new RentalResponse();
        Rental rentalToUpdate = rentalRepository.findById(id).get();
        rentalToUpdate.setName(name);
        rentalToUpdate.setSurface(Double.parseDouble(surface));
        rentalToUpdate.setPrice(Double.parseDouble(price));
        rentalToUpdate.setDescription(description);
        rentalToUpdate.setPicture(picture);
        rentalToUpdate.setUpdated_at(Timestamp.from(Instant.now()));
        rentalToUpdate.setOwner(userService.getUserInfo());

        if(rentalRepository.findById(id).get().getId() == rentalToUpdate.getId()){
            rentalRepository.save(rentalToUpdate);
            rentalResponse.setMessage("Rental updated !");
        }
        else{
            rentalResponse.setMessage("Rental not updated !");
        }
        return rentalResponse;
    }
}
