package helloworldwebservices.features.message.repository;

import helloworldwebservices.features.message.exceptions.MessageNotFoundException;
import helloworldwebservices.model.Message;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MessageRepositoryImpl implements MessageRepository {
    private Map<Long, Message> messages = new HashMap<>();

    @Override
    public Message save(Message message) {
        message.setId(messages.keySet().size()+1);
        messages.put(message.getId(), message);
        return message;
    }

    @Override
    public Message find(Long id) {
        Message message = messages.get(id);
        if(message == null){
            throw new MessageNotFoundException(id);
        }
        return message;
    }

    @Override
    public void update(Message message) {
        if(message.getId() == 0){
            throw new MessageNotFoundException(0);
        }
        Message dbMessage = messages.get(message.getId());
        if(dbMessage==null){
            throw new MessageNotFoundException(message.getId());
        }else{
            messages.put(message.getId(), message);
        }

    }

    @Override
    public void delete(Long id) {
        if(id <= 0){
            throw new MessageNotFoundException(id);
        }

        Message dbMessage = messages.remove(id);
        if(dbMessage==null){
            throw new MessageNotFoundException(id);
        }

    }

    @Override
    public List<Message> findAll() {
        List<Message> allMessages = messages.values().stream().collect(Collectors.toList());
        return allMessages;
    }

    @Override
    public void deleteAll() {
        messages = new HashMap<>();
    }
}
