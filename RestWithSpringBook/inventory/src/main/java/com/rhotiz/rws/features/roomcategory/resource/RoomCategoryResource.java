package com.rhotiz.rws.features.roomcategory.resource;

import com.rhotiz.rws.unifiedresponse.components.ApiResponse;
import com.rhotiz.rws.features.roomcategory.service.RoomCategoryService;
import com.rhotiz.rws.model.RoomCategory;
import com.rhotiz.rws.unifiedresponse.pagination.exception.BadPaginationRequestException;
import com.rhotiz.rws.unifiedresponse.pagination.pages.Page;
import com.rhotiz.rws.unifiedresponse.pagination.pages.PageDTO;
import com.rhotiz.rws.unifiedresponse.pagination.urls.URIComponents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

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

    @RequestMapping(value = "room-categories",method = RequestMethod.GET,params = {"startIndex","num"})
    public ApiResponse getAllInPage(@RequestParam(value = "startIndex",required = true) Long startIndex,
                                    @RequestParam(value = "num",required = true) Long num,
                                    UriComponentsBuilder ucb) throws BadPaginationRequestException {
        if(num<=0L){
            throw new BadPaginationRequestException(startIndex,num);
        }

        Page page = rcService.findAllInPage(startIndex, num);
        String baseUri = ucb.path("/inventory/room-categories").build().toUriString();
        URIComponents uriComponents = new URIComponents(baseUri, "startIndex", "num");
        PageDTO pageDTO = new PageDTO(page,uriComponents);
        return ApiResponse.ofPageDTO(pageDTO);
    }
}
