package com.openclassrooms.chatop.service;


import com.openclassrooms.chatop.DTO.RentalResponse;
import com.openclassrooms.chatop.DTO.RentalsResponse;
import com.openclassrooms.chatop.model.Rental;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.RentalRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;


@Service
public class RentalService {
    @Autowired
    private RentalRepository rentalRepository;
    @Autowired
    private UserService userService;

    private String picturesPath = "src/main/resources/static/img";
    @Value("${server.url}")
    private String serverUrl;
    @Value("${server.port}")
    private String serverPort;
    //Return all rentals
    public RentalsResponse getRentals(){
        RentalsResponse rentalsResponse = new RentalsResponse();
        List<Rental> rentals = (List<Rental>) rentalRepository.findAll();
        for (Rental rental: rentals) {
            rental.setPicture(getServerUrl() + "/api/rentals/image/" + rental.getPicture());
        }
        rentalsResponse.setRentals(rentals);
        return rentalsResponse;
    }
    //Return a rental found by its id
    public Rental getRentalById(long id){
        Rental rental = rentalRepository.findById(id).get();
        rental.setPicture(getServerUrl() + "/api/rentals/image/" + rental.getPicture());
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

    //Copy the picture and return the path
    private String addPicture(MultipartFile picture) throws IOException {
        String temp = Timestamp.from(Instant.now()).toString().substring(5,7);
        Path filePath = Paths.get(picturesPath + File.separator + temp + picture.getOriginalFilename());
        Files.copy(picture.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        return temp + picture.getOriginalFilename();
    }

    //Update a rental and return a message for confirmation
    public RentalResponse updateRental(long id,String name, String surface, String price, String description, MultipartFile picture) {
        RentalResponse rentalResponse = new RentalResponse();
        Rental rentalToUpdate = rentalRepository.findById(id).get();
        rentalToUpdate.setName(name);
        rentalToUpdate.setSurface(Double.parseDouble(surface));
        rentalToUpdate.setPrice(Double.parseDouble(price));
        rentalToUpdate.setDescription(description);


        try {
            rentalToUpdate.setPicture(addPicture(picture));;
        } catch (IOException e) {
            rentalResponse.setMessage("Problem with picture : " + e.getMessage());
            return rentalResponse;
        }

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
    //Get the picture with url
    public byte[] getPictures(String imageUrl) {
        // Charger l'image depuis les ressources
        Resource resource = new ClassPathResource("static/img/" + imageUrl);

        // Lire l'image en tant que flux d'octets
        InputStream inputStream = null;
        try {
            inputStream = resource.getInputStream();
            byte[] bytes = inputStream.readAllBytes();
            // Fermer le flux d'entrée
            inputStream.close();
            return bytes;
        } catch (IOException e) {
            return null;
        }
    }
    //Get the server and port
    private String getServerUrl() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }
}
