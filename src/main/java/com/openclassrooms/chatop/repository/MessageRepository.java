package com.openclassrooms.chatop.repository;

import com.openclassrooms.chatop.model.Message;
import org.springframework.data.repository.CrudRepository;

public interface MessageRepository  extends CrudRepository<Message, Long> {

}
