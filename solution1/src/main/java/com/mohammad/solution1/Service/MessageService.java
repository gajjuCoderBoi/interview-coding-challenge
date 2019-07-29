package com.mohammad.solution1.Service;

import com.mohammad.solution1.Doa.MessageDao;
import com.mohammad.solution1.Model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageDao messageDao;

    @Autowired
    public MessageService(@Qualifier("fakeDao") MessageDao messageDao) {
        this.messageDao = messageDao;
    }

    public Message addMessage(Message message){
        return messageDao.insertMessage(message);
    }

    public List<Message> getAllMessages(){
        return messageDao.allMessages();
    }

    public Optional<Message> getMessageByDigest(String digest){
        return messageDao.selectMessageByDigest(digest);
    }
}
