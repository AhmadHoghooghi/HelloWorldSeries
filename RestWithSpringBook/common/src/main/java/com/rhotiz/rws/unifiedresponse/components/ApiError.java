package com.rhotiz.rws.unifiedresponse.components;

/**
 * 1000: RoomNotFoundException
 * 2000: BadPaginationRequestException exception
 */
public class ApiError {
    private String errorCode;
    private String errorDescription;

    public ApiError() {
    }

    public ApiError(String errorCode, String errorDescription) {
        this.errorCode = errorCode;
        this.errorDescription = errorDescription;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
