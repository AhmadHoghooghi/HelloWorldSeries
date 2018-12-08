package com.rhotiz.rws.features.room.resource;

import com.rhotiz.rws.api.classes.ApiResponse;
import com.rhotiz.rws.features.room.service.RoomMapper;
import com.rhotiz.rws.model.Room;
import com.rhotiz.rws.features.room.service.RoomService;
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
public class RoomResource {
    @Autowired
    RoomService roomService;
    @Autowired
    RoomMapper roomMapper;

    @RequestMapping(value = "room/{id}",method = RequestMethod.GET)
    public ApiResponse getRoomById(@PathVariable Long id){
        Room room = roomService.find(id);
        RoomDTO roomDTO = roomMapper.toDTO(room);
        return ApiResponse.ofSingleData(roomDTO);
    }

    @RequestMapping(value = "room/{id}",method = RequestMethod.DELETE)
    public ApiResponse deleteRoomById(@PathVariable Long id){
        roomService.delete(id);
        return ApiResponse.DELETED();
    }

    @RequestMapping(value = "rooms", method = RequestMethod.POST)
    public ResponseEntity<ApiResponse> saveRoom(@RequestBody RoomDTO roomDTO, UriComponentsBuilder ucb){
        Room dbRoom = roomService.save(roomDTO);
        HttpHeaders headers = new HttpHeaders();
        URI location = ucb.path("/inventory").path("room").path(String.valueOf(dbRoom.getId())).build().toUri();
        headers.setLocation(location);
        ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(ApiResponse.CREATED(),headers,HttpStatus.CREATED);
        return responseEntity;
    }

    @RequestMapping(value = "room/{id}",method = RequestMethod.PUT)
    public ApiResponse updateRoom(@PathVariable Long id,@RequestBody Room editedRoom){
        editedRoom.setId(id);
        roomService.update(editedRoom);
        return ApiResponse.UPDATED();
    }

    @RequestMapping(value = "rooms/room-category/{id}", method = RequestMethod.GET)
    public List<Room> getByCategoryId(@PathVariable Long id){
        List<Room> rooms = roomService.getByCategoryId(id);
        return rooms;
    }
}
