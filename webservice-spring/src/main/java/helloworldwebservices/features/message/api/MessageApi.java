package helloworldwebservices.features.message.api;

import helloworldwebservices.features.message.exceptions.MessageNotFoundException;
import helloworldwebservices.features.message.service.MessageService;
import helloworldwebservices.model.Message;
import helloworldwebservices.model.ServiceError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class MessageApi {
    @Autowired
    private MessageService messageService;


    @RequestMapping(value = "messages",method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    // @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Message> save(@RequestBody Message message, UriComponentsBuilder ucb) {
        Message savedMessage = messageService.save(message);
        HttpHeaders responseHeaders = new HttpHeaders();
        URI location = ucb.path("/messages/").path(String.valueOf(savedMessage.getId())).build().toUri();
        responseHeaders.setLocation(location);
        ResponseEntity<Message> response = new ResponseEntity<>(savedMessage, responseHeaders, HttpStatus.CREATED);
        return response;
    }


    @RequestMapping(value = "/message/{id}", method = RequestMethod.GET)
    public Message find(@PathVariable Long id) {
        return messageService.find(id);
    }

    @RequestMapping(value = "/message/{id}", method = RequestMethod.PUT)
    public void update(@RequestBody Message message, @PathVariable Long id) {
        message.setId(id);
        messageService.update(message);
    }

    @RequestMapping(value = "/message/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        messageService.delete(id);
    }

    @RequestMapping(value = "/messages" , method = RequestMethod.GET)
    public List<Message> findAllMessages(){
        return messageService.findAll();
    }

    @RequestMapping(value = "/populatedb", method = RequestMethod.GET)
    public void populateDb(){
        messageService.save(new Message("Message1"));
        messageService.save(new Message("Message2"));

    }
    @RequestMapping(value = "/messages", method = RequestMethod.DELETE)
    public void deleteAll(){
        messageService.deleteAll();
    }


    @ExceptionHandler(MessageNotFoundException.class)
    public ResponseEntity<ServiceError> messageNotFound(MessageNotFoundException ex){
        ServiceError error = new ServiceError(1000, "Message Not Found", "there is not a message in db with id=" + ex.getId());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }



}
