package com.rhotiz.rws.features.roomcategory.resource;

import com.rhotiz.rws.features.roomcategory.service.RoomCategoryService;
import com.rhotiz.rws.model.Room;
import com.rhotiz.rws.model.RoomCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class RoomCategoryResource {
    @Autowired
    RoomCategoryService rcService;

    @RequestMapping(value = "room-category/{id}",method = RequestMethod.GET)
    public RoomCategory getRoomCategoryById(@PathVariable Long id){
        return rcService.find(id);
    }

    @RequestMapping(value = "room-category/{id}",method = RequestMethod.DELETE)
    public void deleteRoomCategoryById(@PathVariable Long id){
        rcService.delete(id);
    }

    @RequestMapping(value = "room-categories", method = RequestMethod.POST)
    public ResponseEntity<RoomCategory> saveRoomCategory(@RequestBody RoomCategory rc, UriComponentsBuilder ucb){
        RoomCategory dbrc = rcService.save(rc);
        HttpHeaders headers = new HttpHeaders();
        URI location = ucb.path("/inventory").path("room-category").path(String.valueOf(dbrc.getId())).build().toUri();
        headers.setLocation(location);
        ResponseEntity<RoomCategory> responseEntity = new ResponseEntity<>(headers,HttpStatus.CREATED);
        return responseEntity;
    }

    @RequestMapping(value = "room-category/{id}",method = RequestMethod.PUT)
    public void updateRoomCategory(@PathVariable Long id,@RequestBody RoomCategory editedRoomCategory){
        editedRoomCategory.setId(id);
        rcService.update(editedRoomCategory);
    }

    @RequestMapping(value = "room-categories",method = RequestMethod.GET)
    public List<RoomCategory> getAll(){
        return rcService.getAll();
    }
}
