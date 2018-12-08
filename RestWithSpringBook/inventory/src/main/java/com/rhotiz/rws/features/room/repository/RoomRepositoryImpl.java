package com.rhotiz.rws.features.room.repository;

import com.rhotiz.rws.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class RoomRepositoryImpl implements RoomRepository {
    @Autowired
    EntityManager em;

    @Override
    public Room find(Long id) {
       return em.find(Room.class, id);
    }

    @Override
    public void update(Room room) {
        Room dbRoom = find(room.getId());
        dbRoom.setName(room.getName());
        dbRoom.setDescription(room.getDescription());
        em.merge(dbRoom);
    }

    @Override
    public void delete(Long id) {
        Query query = em.createQuery("delete from com.rhotiz.rws.model.Room as room where room.id = :roomId");
        query.setParameter("roomId", id);
        query.executeUpdate();
    }

    @Override
    public Room save(Room room) {
        em.persist(room);
        return room;
    }

    @Override
    public List<Room> getByCategoryId(Long id) {
        String queryString = "select room from Room as room where room.roomCategory.id = :id";
        Query query = em.createQuery(queryString);
        query.setParameter("id", id);
        List resultList = query.getResultList();
        List<Room> roomList = new ArrayList<>();
        resultList.forEach(obj->roomList.add((Room)obj ));
        return roomList;
    }
}
