package com.openclassrooms.chatop.controller;

import com.openclassrooms.chatop.DTO.MessageRequest;
import com.openclassrooms.chatop.DTO.MessageResponse;
import com.openclassrooms.chatop.service.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/messages")
@RestController
@Tag(name = "Messages")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Operation(summary = "This method is used to create a message")
    @PostMapping("")
    public MessageResponse createMessage(@RequestBody MessageRequest messageRequest) {
        return messageService.createMessage(messageRequest);
    }

}
