package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.DTO.AuthSuccess;
import com.openclassrooms.chatop.DTO.LoginRequest;
import com.openclassrooms.chatop.DTO.RegisterRequest;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Data
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User getUserById(long id) {
        return  userRepository.findById(id).get();
    }

    public void createUser(RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setName(registerRequest.getName());
        user.setPassword(registerRequest.getPassword());
        user.setCreated_at(Timestamp.from(Instant.now()));
        user.setUpdated_at(Timestamp.from(Instant.now()));
        userRepository.save(user);
    }

    public ResponseEntity<?> logUser(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getLogin());
        if (user.getPassword().equals(loginRequest.getPassword())){
            return ResponseEntity.ok(new AuthSuccess("tokenGenerated"));
        }else{
            return ResponseEntity.badRequest().body("Wrong email or password");
        }
    }
}
