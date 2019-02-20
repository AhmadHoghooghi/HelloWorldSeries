package com.rhotiz.rws.unifiedresponse.pagination.urls;

public class PageURI {

    private URIComponents uriComponents;
    private Long countValue;
    private Long startIndexValue;


    public PageURI(URIComponents uriComponents, Long startIndexValue, Long countValue) {
        this.uriComponents = uriComponents;
        this.countValue = countValue;
        this.startIndexValue = startIndexValue;
    }

    public String asString() {

        return uriComponents.getBaseURI() + "?"
                + uriComponents.getStartIndexKey() + "=" + startIndexValue + "&"
                + uriComponents.getCountKey() + "=" + countValue;

    }

}

