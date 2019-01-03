package ir.asta.training.contacts.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ir.asta.training.contacts.entities.ContactEntity;
import ir.asta.training.contacts.util.exceptions.ContactNotFoundException;

@Named("contactDao")
public class ContactDao {

    @PersistenceContext
    private EntityManager em;

    public void save(ContactEntity entity) {
        em.persist(entity);
    }

    public ContactEntity load(Long id) {
        ContactEntity contactEntity = em.find(ContactEntity.class, id);
        if (contactEntity == null) {
            throw new ContactNotFoundException(id);
        }
        return contactEntity;
    }

    public List<ContactEntity> findAll() {
        TypedQuery<ContactEntity> query = em.createQuery("select ce from ContactEntity as ce", ContactEntity.class);
        List<ContactEntity> contacts = query.getResultList();
        return contacts;
    }

    public void update(ContactEntity contact) {
        ContactEntity dbContact = em.find(ContactEntity.class, contact.getId());
        if (dbContact == null) {
            throw new ContactNotFoundException(contact.getId());
        }
        dbContact.setName(contact.getName());
        dbContact.setAddress(contact.getAddress());
        dbContact.setHomePhone(contact.getHomePhone());
        dbContact.setMobile(contact.getMobile());
        dbContact.setEmail(contact.getEmail());
        em.merge(dbContact);
    }

    public void delete(Long id) {
        String queryString = "delete from ContactEntity as ce where ce.id=:id";
        Query query = em.createQuery(queryString);
        query.setParameter("id", id);
        int deletedContacts = query.executeUpdate();
        if (deletedContacts != 1) {
            throw new ContactNotFoundException(id);
        }
    }

    public List<ContactEntity> search(Map<String, String> searchCriteriaMap) {
        Set<String> criteriaSet = searchCriteriaMap.keySet();
        //setup basics
        CriteriaBuilder cb = em.getCriteriaBuilder();

        // create query
        CriteriaQuery<ContactEntity> criteriaQuery = cb.createQuery(ContactEntity.class);
        Root<ContactEntity> contact = criteriaQuery.from(ContactEntity.class);

        List<Predicate> predicates = new ArrayList<>();
        criteriaSet.forEach(criteria ->
                {
                    String value = searchCriteriaMap.get(criteria);
                    if (value != null && !"".equals(value)) {
                        predicates.add(cb.like(contact.get(criteria), "%" + value + "%"));
                    }
                }

        );

        Predicate[] predicateArray = predicates.stream().toArray(Predicate[]::new);
        CriteriaQuery<ContactEntity> filteredQuery = criteriaQuery.select(contact)
                .where(predicateArray);
        //run and get result
        TypedQuery<ContactEntity> typedQuery = em.createQuery(filteredQuery);
        List<ContactEntity> resultList = typedQuery.getResultList();
        return resultList;
    }
}
