package com.openclassrooms.chatop.repository;

import com.openclassrooms.chatop.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByName(String name);
    User findByEmail(String email);

}
