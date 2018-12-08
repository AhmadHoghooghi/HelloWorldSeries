package com.rhotiz.rws.features.roomcategory.resource;

import com.rhotiz.rws.api.classes.ApiResponse;
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
    public ApiResponse getRoomCategoryById(@PathVariable Long id){
        return ApiResponse.ofSingleData(rcService.find(id));
    }

    @RequestMapping(value = "room-category/{id}",method = RequestMethod.DELETE)
    public ApiResponse deleteRoomCategoryById(@PathVariable Long id){
        rcService.delete(id);
        return ApiResponse.DELETED();
    }

    @RequestMapping(value = "room-categories", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ApiResponse> saveRoomCategory(@RequestBody RoomCategory rc, UriComponentsBuilder ucb){
        RoomCategory dbrc = rcService.save(rc);
        HttpHeaders headers = new HttpHeaders();
        URI location = ucb.path("/inventory").path("/room-category").path("/"+String.valueOf(dbrc.getId())).build().toUri();
        headers.setLocation(location);
        ResponseEntity<ApiResponse> apiResponseResponseEntity = new ResponseEntity<>(ApiResponse.CREATED(), headers, HttpStatus.CREATED);
        return apiResponseResponseEntity;
    }

    @RequestMapping(value = "room-category/{id}",method = RequestMethod.PUT)
    public ApiResponse updateRoomCategory(@PathVariable Long id,@RequestBody RoomCategory editedRoomCategory){
        editedRoomCategory.setId(id);
        rcService.update(editedRoomCategory);
        return ApiResponse.UPDATED();
    }

    @RequestMapping(value = "room-categories",method = RequestMethod.GET)
    public ApiResponse getAll(){
        return ApiResponse.ofSingleData(rcService.getAll());

    }
}
