package com.openclassrooms.chatop.service;


import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.repository.RentalRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;

    public List<Rental> getRentals(){
        return (List<Rental>) rentalRepository.findAll();
    }
    public Rental getRentalById(long id){
        return rentalRepository.findById(id).get();
    }

    public void createRental(Rental rental) {
        rentalRepository.save(rental);
    }

    public void updateRental(Rental rental) {
        rentalRepository.save(rental);
    }
}
