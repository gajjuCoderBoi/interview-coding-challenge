package com.mohammad.solution1.Doa;

import com.mohammad.solution1.Model.Message;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakeMessageDataAccessService implements MessageDao {
    private static List<Message> DB = new ArrayList<>();

    @Override
    public Message insertMessage(UUID id, Message message) {
         Message newMessage = new Message(id, message.getMessage());
         DB.add(newMessage);
         return newMessage;
    }

    @Override
    public List<Message> allMessages() {
        return DB;
    }

    @Override
    public Optional<Message> selectMessageByDigest(String digest) {
        return DB.stream()
                .filter(message -> message.getDigest().equals(digest))
                .findFirst();
    }


}
