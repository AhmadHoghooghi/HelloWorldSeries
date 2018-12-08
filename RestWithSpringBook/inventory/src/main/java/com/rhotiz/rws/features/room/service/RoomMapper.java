package com.rhotiz.rws.features.room.service;

import com.rhotiz.rws.features.room.resource.RoomDTO;
import com.rhotiz.rws.features.roomcategory.service.RoomCategoryService;
import com.rhotiz.rws.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomMapper {
    @Autowired
    RoomCategoryService rcService;

    public RoomDTO toDTO(Room room){
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setName(room.getName());
        roomDTO.setDescription(room.getDescription());
        roomDTO.setRoomCategoryId(room.getRoomCategory().getId());
        return roomDTO;
    }

    public Room toEntity(RoomDTO roomDTO){
        Room room = new Room();
        room.setName(roomDTO.getName());
        room.setDescription(roomDTO.getDescription());
        room.setRoomCategory(rcService.find(roomDTO.getRoomCategoryId()));
        return room;
    }


}
