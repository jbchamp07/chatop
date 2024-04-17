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

    public RentalsResponse getRentals(){
        RentalsResponse rentalsResponse = new RentalsResponse();
        rentalsResponse.setRentals((List<Rental>) rentalRepository.findAll());
        return rentalsResponse;
    }
    public Rental getRentalById(long id){
        return rentalRepository.findById(id).get();
    }

    public RentalResponse createRental(String name, String surface, String price, String description, String picture) {
        RentalResponse rentalResponse = new RentalResponse();
        Rental rental = new Rental();
        rental.setName(name);
        rental.setSurface(Double.parseDouble(surface));
        rental.setPrice(Double.parseDouble(price));
        rental.setDescription(description);
        rental.setPicture(picture);
        rental.setCreated_at(Timestamp.from(Instant.now()));
        rental.setUpdated_at(Timestamp.from(Instant.now()));
        rental.setOwner(userService.getUserInfo());

        rentalRepository.save(rental);
        if(rentalRepository.findById(rental.getId()).get() != null)
            rentalResponse.setMessage("Rental created !");
        else
            rentalResponse.setMessage("Rental not created !");
        return rentalResponse;
    }

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
