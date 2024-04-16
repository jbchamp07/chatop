package com.openclassrooms.chatop.controller;

import com.openclassrooms.chatop.DTO.MessageDTO;
import com.openclassrooms.chatop.model.Message;
import com.openclassrooms.chatop.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/messages/")
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;
    @PostMapping("")
    public ResponseEntity<String> createMessage(@Validated @RequestBody MessageDTO messageDTO, BindingResult bindingResult) {
        if(!bindingResult.hasErrors()){
             messageService.createMessage(messageDTO);
            return ResponseEntity.ok("Message send with success");
        }else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error : " + bindingResult.getAllErrors());
        }
    }

}
