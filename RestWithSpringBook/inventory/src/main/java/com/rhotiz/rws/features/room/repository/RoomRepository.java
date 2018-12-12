package com.rhotiz.rws.features.room.repository;

import com.rhotiz.rws.model.Room;

import java.util.List;

public interface RoomRepository {

    Room find(Long id);
    void update(Room room);
    void delete(Long id);
    Room save(Room room);
    List<Room> findAll(Long startIndex, Long Num);
    List<Room> getByCategoryId(Long id);
    Long countAll();
    List<Room> findAll();
}
