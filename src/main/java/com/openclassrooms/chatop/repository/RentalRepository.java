package com.openclassrooms.chatop.repository;

import com.openclassrooms.chatop.model.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository  extends CrudRepository<Rental, Long> {

}
