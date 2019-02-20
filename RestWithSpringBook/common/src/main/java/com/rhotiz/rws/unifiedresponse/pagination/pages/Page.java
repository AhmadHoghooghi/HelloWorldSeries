package com.rhotiz.rws.unifiedresponse.pagination.pages;

import java.util.List;

public class Page {
    private List<Object> data;
    private Long startIndex;
    private Long maxCountInPage;//this is maximum number of items in page;

    private Long currentPageNumber;
    private Long pageCount;
    private Long totalCount;


    public Page(List<Object> data, Long startIndex, Long maxCountInPage, Long currentPageNumber, Long pageCount, Long totalCount) {
        this.data = data;
        this.startIndex = startIndex;
        this.maxCountInPage = maxCountInPage;
        this.currentPageNumber = currentPageNumber;
        this.pageCount = pageCount;
        this.totalCount = totalCount;
    }

    public Long getCurrentPageNumber() {
        return currentPageNumber;
    }

    public Long getPageCount() {
        return pageCount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public List<Object> getData() {
        return data;
    }

    public Long getStartIndex() {
        return startIndex;
    }

    public Long getMaxCountInPage() {
        return maxCountInPage;
    }



}
