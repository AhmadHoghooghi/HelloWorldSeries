package com.rhotiz.rws.util;

import com.rhotiz.rws.unifiedresponse.components.ApiError;
import com.rhotiz.rws.unifiedresponse.components.ApiResponse;
import com.rhotiz.rws.unifiedresponse.pagination.exception.BadPaginationRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GeneralExceptionHandler {

    //todo:[?] I made this exception subtype of BindException to map it automatically to 400 but it does not work. why?
    @ExceptionHandler(value = BadPaginationRequestException.class)
    //todo:[?] which one is better 400 bad request as error code or 200 OK?
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResponse handleBadPaginationRequest(BadPaginationRequestException ex){

        //todo: startIndex and num are hardcoded here. change this!
        ApiError error = new ApiError("2000","Wrong Pagination parameters with startIndex="+ex.getStartIndex()
                +" and num="+ex.getNum());
        return ApiResponse.ERROR(error);
    }
}
