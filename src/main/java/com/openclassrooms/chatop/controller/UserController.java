package com.openclassrooms.chatop.controller;

import com.openclassrooms.chatop.DTO.AuthSuccess;
import com.openclassrooms.chatop.DTO.LoginRequest;
import com.openclassrooms.chatop.DTO.RegisterRequest;
import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/auth/")
@RestController
@Tag(name = "Users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "This method is used to register")
    @PostMapping("register")
    public ResponseEntity<AuthSuccess> register(@RequestBody RegisterRequest user){
        return ResponseEntity.ok(userService.register(user));
    }
    @Operation(summary = "This method is used to login")
    @PostMapping("login")
    public ResponseEntity<AuthSuccess> login(@RequestBody LoginRequest user){
        try{
            return ResponseEntity.ok(userService.authenticate(user));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthSuccess(e.getMessage()));
        }

    }
    @Operation(summary = "This method is used to get user informations")
    @GetMapping("me")
    public ResponseEntity<User> login(){
        return ResponseEntity.ok(userService.getUserInfo());
    }



}
