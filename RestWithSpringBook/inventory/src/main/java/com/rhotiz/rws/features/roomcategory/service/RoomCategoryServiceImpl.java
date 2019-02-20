package com.rhotiz.rws.features.roomcategory.service;

import com.rhotiz.rws.features.roomcategory.repository.RoomCategoryRepository;
import com.rhotiz.rws.model.Room;
import com.rhotiz.rws.model.RoomCategory;
import com.rhotiz.rws.unifiedresponse.pagination.pages.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public Page findAllInPage(Long startIndex, Long maxCountInPage) {
        List<RoomCategory> dbRoomCategories = rcRepository.findAllInRange(startIndex, maxCountInPage);
        Long totalCount = rcRepository.countAll();

        long pageCount = (long) Math.ceil( (double)totalCount/maxCountInPage);
        long currentPage =(long) Math.floor((double)startIndex/maxCountInPage+1);

        List<Object> roomCategories = dbRoomCategories.stream().map(rc -> (Object) rc).collect(Collectors.toList());
        Page page = new Page(roomCategories, startIndex, maxCountInPage, currentPage, pageCount, totalCount);
        return page;
    }


}
