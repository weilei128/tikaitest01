package com.pcitc.szgt.contract.share.model;

import java.util.Map;

public class TaskCountModel {

    private Map<String, Integer> categoryCodeTotal;

    private Integer total;

    public Map<String, Integer> getCategoryCodeTotal() {
        return categoryCodeTotal;
    }

    public void setCategoryCodeTotal(Map<String, Integer> categoryCodeTotal) {
        this.categoryCodeTotal = categoryCodeTotal;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
