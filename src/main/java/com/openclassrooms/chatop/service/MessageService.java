package com.openclassrooms.chatop.service;

import com.openclassrooms.chatop.DTO.MessageDTO;
import com.openclassrooms.chatop.model.Message;
import com.openclassrooms.chatop.repository.MessageRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Data
@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private RentalService rentalService;
    @Autowired
    private UserService userService;

    public void createMessage(MessageDTO messageDTO) {
        Message message = new Message();
        message.setId(message.getId());
        message.setRental(rentalService.getRentalById(messageDTO.getRental_id()));
        message.setUser(userService.getUserById(messageDTO.getUser_id()));
        message.setMessage(messageDTO.getMessage());
        message.setCreated_at(Timestamp.from(Instant.now()));
        message.setUpdated_at(Timestamp.from(Instant.now()));
        messageRepository.save(message);
    }

}
