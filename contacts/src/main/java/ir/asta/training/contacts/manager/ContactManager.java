package ir.asta.training.contacts.manager;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import ir.asta.training.contacts.dao.ContactDao;
import ir.asta.training.contacts.entities.ContactEntity;

@Named("contactManager")
public class ContactManager {

    @Inject
    private ContactDao dao;
    @Transactional
    public void save(ContactEntity entity) {
        dao.save(entity);
    }

    @Transactional(readOnly = true)
    public ContactEntity load(Long id) {
        return dao.load(id);
    }

    @Transactional(readOnly = true)
    public List<ContactEntity> findAll() {
        return dao.findAll();
    }

    @Transactional
    public void update(ContactEntity contact) {
        dao.update(contact);
    }

    @Transactional
    public void delete(Long id) {
        dao.delete(id);
    }

    @Transactional(readOnly = true)
    public List<ContactEntity> search(Map<String, String> searchCriteriaMap) {
        return dao.search(searchCriteriaMap);
    }
}
