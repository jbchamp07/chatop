package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.DTO.AuthSuccess;
import com.openclassrooms.chatop.DTO.LoginRequest;
import com.openclassrooms.chatop.DTO.RegisterRequest;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.repository.UserRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Data
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    /*@Autowired
    private AuthenticationManager authenticationManager;*/

    
    public AuthSuccess register(RegisterRequest request){
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreated_at(Timestamp.from(Instant.now()));
        user.setUpdated_at(Timestamp.from(Instant.now()));
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthSuccess(token);
    }
    public AuthSuccess authenticate(LoginRequest request) throws Exception {
        User user = userRepository.findByEmail(request.getEmail());
        //if(passwordEncoder.matches(user.getPassword(),passwordEncoder.encode(request.getPassword()))){
        if(passwordEncoder.matches(request.getPassword(),user.getPassword())){
            String token = jwtService.generateToken(user);
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
            return new AuthSuccess(token);
        }else{
            throw new Exception("Email or password incorrect");
        }
    }




    public User getUserById(long id) {
        return  userRepository.findById(id).get();
    }

    public User getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return  userRepository.findByName(username);
    }
/*
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
    }*/

}
