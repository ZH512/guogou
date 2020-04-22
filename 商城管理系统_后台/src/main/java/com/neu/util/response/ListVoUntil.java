package com.neu.util.response;

public class ListVoUntil<T>{
    private String limit;
    private String page;
    private String pages;
    private String total;
    private T list;

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setList(T list) {
        this.list = list;
    }

    public String getLimit() {
        return limit;
    }

    public String getPage() {
        return page;
    }

    public String getPages() {
        return pages;
    }

    public String getTotal() {
        return total;
    }

    public T getList() {
        return list;
    }
}
