package com.rhotiz.rws.unifiedresponse.pagination.exception;

import java.net.BindException;

public class BadPaginationRequestException extends BindException {
    private Long startIndex;
    private Long num;

    public BadPaginationRequestException(String msg, Long startIndex, Long num) {
        super(msg);
        this.startIndex = startIndex;
        this.num = num;
    }

    public BadPaginationRequestException(Long startIndex, Long num) {
        this.startIndex = startIndex;
        this.num = num;
    }

    public Long getStartIndex() {
        return startIndex;
    }

    public Long getNum() {
        return num;
    }
}
