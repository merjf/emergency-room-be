package com.portaleps.model.response;

public class PaginationResponse {

    private int currentPage;
    private int currentNItems;
    private int listLength;

    public PaginationResponse(){}

    public PaginationResponse(int currentNItems, int currentPage, int listLength){
        this.currentPage = currentPage;
        this.currentNItems = currentNItems;
        this.listLength = listLength;
    }

    public int getListLength() {
        return listLength;
    }

    public void setListLength(int listLength) {
        this.listLength = listLength;
    }

    public int getCurrentNItems() {
        return currentNItems;
    }

    public void setCurrentNItems(int currentNItems) {
        this.currentNItems = currentNItems;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}
