package com.rhotiz.rws.features.room.util;

import com.rhotiz.rws.unifiedresponse.components.ApiError;
import com.rhotiz.rws.unifiedresponse.components.ApiResponse;
import com.rhotiz.rws.features.room.exceptions.RoomNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Advice {
    @ExceptionHandler(RoomNotFoundException.class)
    public ApiResponse roomNotFoundExceptionHandler(RoomNotFoundException ex){
        ApiError apiError = new ApiError("1000", "Room wiht id=" + ex.getRoomId() + " Not Found");
        return ApiResponse.ERROR(apiError);
    }
}
