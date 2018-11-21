package helloworldwebservices.features.message.service;

import helloworldwebservices.model.Message;

import java.util.List;

public interface MessageService {
    Message save(Message message);
    Message find(Long id);
    void update(Message message);
    void delete(Long id);

    List<Message> findAll();

    void deleteAll();
}
