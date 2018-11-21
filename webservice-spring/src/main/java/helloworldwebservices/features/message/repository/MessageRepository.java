package helloworldwebservices.features.message.repository;

import helloworldwebservices.model.Message;

import java.util.List;

public interface MessageRepository {

    Message save(Message message);
    Message find(Long id);
    void update(Message message);
    void delete(Long id);

    List<Message> findAll();

    void deleteAll();
}
