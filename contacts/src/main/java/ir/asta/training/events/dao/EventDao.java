package ir.asta.training.events.dao;

import ir.asta.training.events.entities.EventEntity;
import ir.asta.training.events.util.exceptions.EventNotFoundException;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Named("eventDao")
public class EventDao {
    @PersistenceContext
    private EntityManager em;

    public void save(EventEntity event) {
        em.persist(event);
    }

    public void update(EventEntity event) {
        EventEntity dbEvent = em.find(EventEntity.class, event.getId());
        if (dbEvent == null) {
            throw new EventNotFoundException(event.getId());
        }
        dbEvent.setTitle(event.getTitle());
        dbEvent.setDescription(event.getDescription());
        dbEvent.setStartDateTime(event.getStartDateTime());
        dbEvent.setEndDateTime(event.getEndDateTime());
        em.merge(dbEvent);
    }

    public List<EventEntity> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();

        //create criteria query
        CriteriaQuery<EventEntity> cq = cb.createQuery(EventEntity.class);
        Root<EventEntity> event = cq.from(EventEntity.class);
        CriteriaQuery<EventEntity> allEvents = cq.select(event);
        TypedQuery<EventEntity> allEventsQuery = em.createQuery(allEvents);

        //run query
        List<EventEntity> events = allEventsQuery.getResultList();
        return events;
    }

    public EventEntity load(Long id) {
        EventEntity event = em.find(EventEntity.class, id);
        if (event == null) {
            throw new EventNotFoundException(id);
        }
        return event;
    }

    public List<EventEntity> search(Map<String, String> searchCriteriaMap) throws ParseException {
        //setup basics
        CriteriaBuilder cb = em.getCriteriaBuilder();

        // create query
        CriteriaQuery<EventEntity> criteriaQuery = cb.createQuery(EventEntity.class);
        Root<EventEntity> event = criteriaQuery.from(EventEntity.class);

        List<Predicate> predicates = new ArrayList<>();
        // title
        String title = searchCriteriaMap.get("title");
        if (title != null && !"".equals(title)) {
            predicates.add(cb.like(event.get("title"), "%" + title + "%"));
        }
        // startDateTimeFrom
        String startDateTimeFrom = searchCriteriaMap.get("startDateTimeFrom");
        if (startDateTimeFrom != null && !"".equals(startDateTimeFrom)) {
            predicates.add(cb.greaterThanOrEqualTo(event.get("startDateTime"), parseToDate(startDateTimeFrom)));
        }
        // startDateTimeTo
        String startDateTimeTo = searchCriteriaMap.get("startDateTimeTo");
        if (startDateTimeTo != null && !"".equals(startDateTimeTo)) {
            predicates.add(cb.lessThanOrEqualTo(event.get("startDateTime"), parseToDate(startDateTimeTo)));
        }
        // endDateTimeFrom
        String endDateTimeFrom = searchCriteriaMap.get("endDateTimeFrom");
        if (endDateTimeFrom != null && !"".equals(endDateTimeFrom)) {
            predicates.add(cb.greaterThanOrEqualTo(event.get("endDateTime"), parseToDate(endDateTimeFrom)));
        }
        // endDateTimeTo
        String endDateTimeTo = searchCriteriaMap.get("endDateTimeTo");
        if (endDateTimeTo != null && !"".equals(endDateTimeTo)) {
            predicates.add(cb.lessThanOrEqualTo(event.get("endDateTime"), parseToDate(endDateTimeTo)));
        }


        Predicate[] predicateArray = predicates.stream().toArray(Predicate[]::new);
        CriteriaQuery<EventEntity> filteredQuery = criteriaQuery.select(event)
                .where(predicateArray);
        //run and get result
        TypedQuery<EventEntity> typedQuery = em.createQuery(filteredQuery);
        List<EventEntity> resultList = typedQuery.getResultList();
        return resultList;
    }

    private Date parseToDate(String utcDateString) throws ParseException {
        SimpleDateFormat dateFormatLocal = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        return dateFormatLocal.parse(utcDateString);
    }

    public void delete(Long id) {
        String queryString = "delete from EventEntity as ee where ee.id=:id";
        Query query = em.createQuery(queryString);
        query.setParameter("id", id);
        int deletedEventsNum = query.executeUpdate();
        if (deletedEventsNum != 1) {
            throw new EventNotFoundException(id);
        }
    }
}
