package com.rhotiz.rws.api.classes;

public class ApiResponse {
    private ApiResponseStatus status;
    private Object data;
    private ApiError error;
    private int currentPageNumber;
    private String nextPage;
    private String previousPage;
    private Long total;


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

    private ApiResponse(ApiResponseStatus status, Object data, String previousPageString, int currentPageNumber, String nextPage, Long total){
        new ApiResponse(status, data);
        this.setPreviousPage(previousPageString);
        this.setCurrentPageNumber(currentPageNumber);
        this.setNextPage(nextPage);
        this.setTotal(total);

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

    public static ApiResponse ofFirstPage(ApiResponseStatus status, Object data, String nextPage, Long total){
        return new ApiResponse(status, data, null, 1, nextPage, total);
    }

    public static ApiResponse ofMiddlePage(ApiResponseStatus status, Object data, String previousPage, int currentPageNumber, String nextPage, Long total){
        return new ApiResponse(status, data, previousPage, currentPageNumber, nextPage, total);
    }

    public static ApiResponse ofLastPage(ApiResponseStatus status, Object data, String previousPage, int currentPageNumber, Long total){
        return new ApiResponse(status, data, previousPage, currentPageNumber, null, total);
    }






    public ApiResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ApiResponseStatus status) {
        this.status = status;
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

    public int getCurrentPageNumber() {
        return currentPageNumber;
    }

    public void setCurrentPageNumber(int currentPageNumber) {
        this.currentPageNumber = currentPageNumber;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public String getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(String previousPage) {
        this.previousPage = previousPage;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
