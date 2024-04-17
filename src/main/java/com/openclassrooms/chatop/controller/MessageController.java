package com.openclassrooms.chatop.controller;

import com.openclassrooms.chatop.DTO.MessageRequest;
import com.openclassrooms.chatop.DTO.MessageResponse;
import com.openclassrooms.chatop.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/messages")
@RestController
public class MessageController {

    @Autowired
    private MessageService messageService;
    @PostMapping("")
    public MessageResponse createMessage(@RequestBody MessageRequest messageRequest) {
        return messageService.createMessage(messageRequest);
    }

}
