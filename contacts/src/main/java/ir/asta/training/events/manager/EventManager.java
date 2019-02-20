package ir.asta.training.events.manager;

import ir.asta.training.events.dao.EventDao;
import ir.asta.training.events.entities.EventEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Named("eventManager")
public class EventManager {

    @Inject
    private EventDao dao;

    @Transactional
    public void save(EventEntity event){
        dao.save(event);
    }

    @Transactional
    public void update(EventEntity event){
        dao.update(event);
    }

    @Transactional(readOnly = true)
    public List<EventEntity> findAll(){
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public EventEntity load(Long id){
        return dao.load(id);
    }

    @Transactional(readOnly = true)
    public List<EventEntity> search(Map<String, String> searchCriteriaMap) throws ParseException {
        return dao.search(searchCriteriaMap);
    }
    @Transactional
    public void delete(Long id) {
        dao.delete(id);
    }
}
