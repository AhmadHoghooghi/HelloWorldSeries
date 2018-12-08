package com.rhotiz.rws.features.room.service;

import com.rhotiz.rws.features.room.resource.RoomDTO;
import com.rhotiz.rws.model.Room;
import com.rhotiz.rws.features.room.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomMapper roomMapper;

    @Override
    public Room find(Long id) {
        return roomRepository.find(id);
    }

    @Override
    public void update(Room room) {
        roomRepository.update(room);
    }

    @Override
    public void delete(Long id) {
        roomRepository.delete(id);
    }

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room save(RoomDTO roomDTO) {
        Room room = roomMapper.toEntity(roomDTO);
        return save(room);
    }

    @Override
    public List<Room> getByCategoryId(Long id) {
        return roomRepository.getByCategoryId(id);
    }
}
