package com.rhotiz.rws.features.roomcategory.service;

import com.rhotiz.rws.features.roomcategory.repository.RoomCategoryRepository;
import com.rhotiz.rws.model.Room;
import com.rhotiz.rws.model.RoomCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomCategoryServiceImpl implements RoomCategoryService {
    @Autowired
    RoomCategoryRepository rcRepository;
    @Override
    public RoomCategory find(Long id) {
        return rcRepository.find(id);
    }

    @Override
    public void update(RoomCategory rc) {
        rcRepository.update(rc);
    }

    @Override
    public void delete(Long id) {
        rcRepository.delete(id);
    }

    @Override
    public RoomCategory save(RoomCategory rc) {
        return rcRepository.save(rc);
    }

    @Override
    public List<RoomCategory> getAll() {
        return rcRepository.getAll();
    }


}
