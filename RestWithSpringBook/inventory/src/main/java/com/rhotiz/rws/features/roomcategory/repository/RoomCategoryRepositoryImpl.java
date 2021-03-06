package com.rhotiz.rws.features.roomcategory.repository;

import com.rhotiz.rws.model.Room;
import com.rhotiz.rws.model.RoomCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@Transactional
public class RoomCategoryRepositoryImpl implements RoomCategoryRepository {
    @Autowired
    EntityManager em;

    @Override
    public RoomCategory find(Long id) {
       return em.find(RoomCategory.class, id);
    }

    @Override
    public void update(RoomCategory rc) {
        RoomCategory dbrc = find(rc.getId());
        dbrc.setName(rc.getName());
        em.merge(dbrc);
    }

    @Override
    public void delete(Long id) {
        Query query = em.createQuery("delete from RoomCategory as rc where rc.id = :rcid");
        query.setParameter("rcid", id);
        query.executeUpdate();
    }

    @Override
    public RoomCategory save(RoomCategory rc) {
        em.persist(rc);
        return rc;
    }

    @Override
    public List<RoomCategory> getAll() {
        String queryString = "select rc from RoomCategory as rc ";
        Query query = em.createQuery(queryString);
        List resultList = query.getResultList();
        List<RoomCategory> roomList = new ArrayList<>();
        resultList.forEach(obj->roomList.add((RoomCategory) obj ));
        return roomList;
    }

    @Override
    public List<RoomCategory> findAllInRange(Long startIndex, Long maxResults) {
        String queryString = "select rc from RoomCategory as rc";
        Query query = em.createQuery(queryString);
        query.setFirstResult(startIndex.intValue());
        query.setMaxResults(maxResults.intValue());
        List queryResultList = query.getResultList();
        List<RoomCategory> rooms  =new ArrayList<>();
        queryResultList.stream().forEach(obj -> rooms.add((RoomCategory) obj));
        return rooms;
    }

    @Override
    public Long countAll() {
        String queryString = "select count(rc) from RoomCategory as rc";
        Query query = em.createQuery(queryString);
        Long count = (Long)query.getSingleResult();
        return count;
    }


}
