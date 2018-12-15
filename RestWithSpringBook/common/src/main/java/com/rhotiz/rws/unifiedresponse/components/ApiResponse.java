package com.rhotiz.rws.unifiedresponse.components;

import com.rhotiz.rws.unifiedresponse.pagination.pages.PageDTO;
import com.rhotiz.rws.unifiedresponse.pagination.urls.Links;

public class ApiResponse {
    private ApiResponseStatus status;
    private Links links;
    private Long currentPageNumber;
    private Long pageCount;
    private Long totalCount;
    private Object data;
    private ApiError error;



    private ApiResponse(ApiResponseStatus status) {
        this.status = status;
    }

    private ApiResponse(ApiResponseStatus status, Object data) {
        this.status = status;
        this.data = data;
    }

    private ApiResponse(ApiResponseStatus status, ApiError error) {
        this.status = status;
        this.error = error;
    }

    private ApiResponse(ApiResponseStatus status, Links links, Object data, Long currentPageNumber,Long pageCount, Long totalCount){
        this(status, data);
        this.links = links;
        this.currentPageNumber = currentPageNumber;
        this.pageCount = pageCount;
        this.totalCount = totalCount;
    }
    public static ApiResponse OK(){
        return new ApiResponse(ApiResponseStatus.OK);
    }

    public static ApiResponse DELETED(){
        return new ApiResponse(ApiResponseStatus.DELETED);
    }

    public static ApiResponse UPDATED(){
        return new ApiResponse(ApiResponseStatus.UPDATED);
    }

    public static ApiResponse ERROR(ApiError error){
        return new ApiResponse(ApiResponseStatus.ERROR, error);
    }

    public static ApiResponse CREATED(){
        return new ApiResponse(ApiResponseStatus.CREATED);
    }

    public static ApiResponse ofSingleData(Object data){
        return new ApiResponse(ApiResponseStatus.OK, data);
    }

    public static ApiResponse ofPageDTO(PageDTO pageDTO){
        ApiResponse apiResponse = new ApiResponse(
                ApiResponseStatus.OK,
                pageDTO.getLinks(),
                pageDTO.getData(),
                pageDTO.getCurrentPageNumber(),
                pageDTO.getPageCount(),
                pageDTO.getTotalCount());
        return apiResponse;
    }

    public ApiResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ApiResponseStatus status) {
        this.status = status;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Long getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(Long currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }
}
