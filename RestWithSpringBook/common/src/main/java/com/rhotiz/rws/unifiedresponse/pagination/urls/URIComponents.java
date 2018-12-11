package com.rhotiz.rws.unifiedresponse.pagination.urls;

public class URIComponents {

    private String baseURI;
    private String startIndexKey;
    private String countKey;


    public URIComponents(String baseURI, String startIndexKey, String countKey) {
        this.baseURI = baseURI;
        this.startIndexKey = startIndexKey;
        this.countKey = countKey;
    }

    public String getBaseURI() {
        return baseURI;
    }

    public void setBaseURI(String baseURI) {
        this.baseURI = baseURI;
    }

    public String getStartIndexKey() {
        return startIndexKey;
    }

    public void setStartIndexKey(String startIndexKey) {
        this.startIndexKey = startIndexKey;
    }

    public String getCountKey() {
        return countKey;
    }

    public void setCountKey(String countKey) {
        this.countKey = countKey;
    }
}
