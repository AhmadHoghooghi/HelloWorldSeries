package com.rhotiz.rws.features.room.service;

import com.rhotiz.rws.features.room.resource.RoomDTO;
import com.rhotiz.rws.model.Room;
import com.rhotiz.rws.features.room.repository.RoomRepository;
import com.rhotiz.rws.unifiedresponse.pagination.pages.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<Room> findByCategoryId(Long id) {
        return roomRepository.getByCategoryId(id);
    }

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Page findAllInPage(Long startIndex, Long maxCountInPage) {
        List<Room> queriedRooms = roomRepository.findAll(startIndex, maxCountInPage);
        Long totalCount = roomRepository.countAll();

        long pageCount = (long) Math.ceil( (double)totalCount/maxCountInPage);
        long currentPage =(long) Math.floor((double)startIndex/maxCountInPage+1);
        List<Object> rooms = queriedRooms.stream().map(room -> (Object) room).collect(Collectors.toList());

        Page page = new Page(rooms, startIndex, maxCountInPage, currentPage, pageCount, totalCount);
        return page;
    }
}
