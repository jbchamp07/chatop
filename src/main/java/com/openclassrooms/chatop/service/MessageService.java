package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.DTO.MessageRequest;
import com.openclassrooms.chatop.DTO.MessageResponse;
import com.openclassrooms.chatop.model.Message;
import com.openclassrooms.chatop.repository.MessageRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Data
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private RentalService rentalService;
    @Autowired
    private UserService userService;

    //Create a message
    public MessageResponse createMessage(MessageRequest messageRequest) {
        MessageResponse messageResponse = new MessageResponse();
        Message message = new Message();
        message.setId(message.getId());
        message.setRental(rentalService.getRentalById(messageRequest.getRental_id()));
        message.setUser(userService.getUserById(messageRequest.getUser_id()));
        message.setMessage(messageRequest.getMessage());
        message.setCreated_at(Timestamp.from(Instant.now()));
        message.setUpdated_at(Timestamp.from(Instant.now()));
        messageRepository.save(message);

        if(messageRepository.existsById(message.getId()))
            messageResponse.setMessage("Message send with success");
        else
            messageResponse.setMessage("Message not send with success");
        return messageResponse;
    }

}
