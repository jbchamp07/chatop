package com.openclassrooms.chatop.controller;

import com.openclassrooms.chatop.model.User;
import com.openclassrooms.chatop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/auth/")
@RestController
public class UserController {
/*
    @Autowired
    private UserService userService;
    @GetMapping("/me")
    public List<User> getUsers() {
        return userService.getUsers();
    }
*/
}
