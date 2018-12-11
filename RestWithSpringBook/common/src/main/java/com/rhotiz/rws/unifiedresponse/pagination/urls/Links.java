package com.rhotiz.rws.unifiedresponse.pagination.urls;


/**
 * There are two ideas about linsk:
 * 1-return nothing for empty links,
 * for example, previousPageURL of firstPage can be null;
 * 2-return unified response for empty links,
 * for example, previousPageURL of firstPage can be "NONE".
 *
 * to make it customizable I will create static factory methods of for this class.
 * so this kind of customization will be encapsulated in this class.
 */
public class Links {
    public static final String NONE = "NONE";
    private String previousPage;
    private String self;
    private String nextPage;

    private Links(){};

    private Links(String previousPage, String self, String nextPage) {
        this.previousPage = previousPage;
        this.self = self;
        this.nextPage = nextPage;
    }

    public static Links ofFirstPageNonOverFlowed(String self){
        return new Links(Links.NONE, self,Links.NONE);
    }

    public static Links ofFirstOverFlowed(String self, String nextPage){
        return new Links(Links.NONE, self,nextPage);
    }

    public static Links ofMiddlePage(String previousPage, String self, String nextPage){
        return new Links(previousPage,self,nextPage);
    }

    public static Links ofLastPage(String previousPage, String self){
        return new Links(previousPage,self,Links.NONE);
    }


    public String getPreviousPage() {
        return previousPage;
    }

    public String getSelf() {
        return self;
    }

    public String getNextPage() {
        return nextPage;
    }
}
