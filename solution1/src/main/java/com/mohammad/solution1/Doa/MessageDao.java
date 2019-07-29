package com.mohammad.solution1.Doa;

import com.mohammad.solution1.Model.Message;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageDao {

    Message insertMessage(UUID id, Message message);


    default Message insertMessage(Message message){
        UUID id = UUID.randomUUID();
        return insertMessage(id, message);
    }

    List<Message> allMessages();

    Optional<Message> selectMessageByDigest(String digest);

}
