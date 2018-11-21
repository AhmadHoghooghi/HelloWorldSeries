package helloworldwebservices.features.message.service;

import helloworldwebservices.features.message.repository.MessageRepository;
import helloworldwebservices.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public Message save(Message message) {
        return messageRepository.save(message);
    }

    @Override
    public Message find(Long id) {
        return messageRepository.find(id);
    }

    @Override
    public void update(Message message) {
        messageRepository.update(message);
    }

    @Override
    public void delete(Long id) {
        messageRepository.delete(id);
    }

    @Override
    public List<Message> findAll() {
        return messageRepository.findAll();
    }

    @Override
    public void deleteAll() {
        messageRepository.deleteAll();
    }
}
