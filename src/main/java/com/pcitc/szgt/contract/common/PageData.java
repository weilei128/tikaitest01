package com.pcitc.szgt.contract.common;

import java.util.List;

public class PageData<T> {

    private long totalCount;

    private long pageSize;

    private long currentPage;

    private long totalPage;

    private List<T> data;

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(long totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public static <T> PageData<T> emptyPageData(long pageSize, long currentPage){
        PageData<T> pageData = new PageData<>();
        pageData.setPageSize(pageSize);
        pageData.setCurrentPage(currentPage);
        return pageData;
    }
}
