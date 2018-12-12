package com.rhotiz.rws.unifiedresponse.pagination.pages;

import com.rhotiz.rws.unifiedresponse.pagination.urls.Links;
import com.rhotiz.rws.unifiedresponse.pagination.urls.PageURI;
import com.rhotiz.rws.unifiedresponse.pagination.urls.URIComponents;

import java.util.List;

public class PageDTO {
    private Links links;
    private Long currentPageNumber;
    private Long pageCount;
    private Long totalCount;
    private List<Object> data;

    public PageDTO(Page page, URIComponents uriComponents) {
        this.currentPageNumber = page.getCurrentPageNumber();
        this.pageCount = page.getPageCount();
        this.totalCount = page.getTotalCount();
        this.data = page.getData();
        this.links = calculateLinks(page.getStartIndex(),page.getMaxCountInPage(), uriComponents);
    }

     private Links calculateLinks(Long startIndex,Long maxCountImPage, URIComponents uriComps){
         PageURI prevPageURI = new PageURI(uriComps, startIndex-maxCountImPage, maxCountImPage);
         PageURI selfPageURI = new PageURI(uriComps, startIndex, maxCountImPage);
         PageURI nexPageURI = new PageURI(uriComps, startIndex + maxCountImPage, maxCountImPage);


         if(currentPageNumber == 1 && pageCount==1 && data.size()<=totalCount){
             return Links.ofFirstPageNonOverFlowed(selfPageURI.asString());
        }else if(currentPageNumber==1 && pageCount>1){
             return Links.ofFirstOverFlowed(selfPageURI.asString(), nexPageURI.asString());
        }else if(currentPageNumber<pageCount){
             return Links.ofMiddlePage(prevPageURI.asString(), selfPageURI.asString(), nexPageURI.asString());
        }else if(currentPageNumber.equals(pageCount)){
             return Links.ofLastPage(prevPageURI.asString(),selfPageURI.asString());
        }else {
            throw new RuntimeException("Wrong Conditioning in convertToProperPageSubclass");
        }
     }

    public Links getLinks() {
        return links;
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
}