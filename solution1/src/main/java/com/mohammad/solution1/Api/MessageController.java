package com.mohammad.solution1.Api;

import com.mohammad.solution1.Model.Message;
import com.mohammad.solution1.Service.MessageService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/messages")
@RestController
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<String> addMessage(@RequestBody Message message){
        Message message1 =  messageService.addMessage(message);
        JSONObject jo = new JSONObject();jo.put("digest",message1.getDigest());
        return new ResponseEntity<String>(jo.toString()
                , HttpStatus.OK);
    }

    @GetMapping(path = "/{digest}")
    public ResponseEntity<String> getMessageByDigest(@PathVariable("digest") String digest){
        //Optional<Message> message = ;
        if(messageService.getMessageByDigest(digest).orElse(null) != null){
            JSONObject jo = new JSONObject();
            Optional<Message> message = messageService.getMessageByDigest(digest);
            jo.put("message", message.get().getMessage());
            return new ResponseEntity<String>(jo.toString()
                    , HttpStatus.OK);
        }else{
            JSONObject jo = new JSONObject();
            jo.put("err_msg", "Message Not Found");
            return new ResponseEntity<String>(jo.toString()
                    , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<Message> getAllMessage(){
        return messageService.getAllMessages();
    }
}
