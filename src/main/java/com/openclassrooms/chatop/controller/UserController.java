package com.openclassrooms.chatop.controller;

import com.openclassrooms.chatop.DTO.AuthSuccess;
import com.openclassrooms.chatop.DTO.LoginRequest;
import com.openclassrooms.chatop.DTO.RegisterRequest;
import com.openclassrooms.chatop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/auth/")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<AuthSuccess> register(@RequestBody RegisterRequest user){
        return ResponseEntity.ok(userService.register(user));
    }
    @PostMapping("login")
    public ResponseEntity<AuthSuccess> login(@RequestBody LoginRequest user){
        return ResponseEntity.ok(userService.authenticate(user));
    }




    /*@PostMapping("register")
    public ResponseEntity<String> createUser(@Validated @RequestBody RegisterRequest registerRequest, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()){
            userService.createUser(registerRequest);
            return ResponseEntity.ok("user Created");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error : " + bindingResult.getAllErrors());
        }

    }
    @PostMapping("login")
    public ResponseEntity<?> logUser(@Validated @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        return userService.logUser(loginRequest);
    }*/

}
