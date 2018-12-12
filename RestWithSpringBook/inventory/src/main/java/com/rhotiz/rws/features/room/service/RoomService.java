package com.rhotiz.rws.features.room.service;

import com.rhotiz.rws.features.room.resource.RoomDTO;
import com.rhotiz.rws.model.Room;
import com.rhotiz.rws.unifiedresponse.pagination.pages.Page;

import java.util.List;

public interface RoomService {
    Room find(Long id);
    void update(Room room);
    void delete(Long id);
    Room save(Room room);
    Room save(RoomDTO roomDTO);
    List<Room> findByCategoryId(Long id);
    List<Room> findAll();
    Page findAllInPage(Long startIndex, Long num);
}
