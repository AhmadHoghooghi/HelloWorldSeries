package com.rhotiz.rws.features.roomcategory.service;

import com.rhotiz.rws.model.Room;
import com.rhotiz.rws.model.RoomCategory;

import java.util.List;

public interface RoomCategoryService {
    RoomCategory find(Long id);
    void update(RoomCategory room);
    void delete(Long id);
    RoomCategory save(RoomCategory room);
    List<RoomCategory> getAll();
}
