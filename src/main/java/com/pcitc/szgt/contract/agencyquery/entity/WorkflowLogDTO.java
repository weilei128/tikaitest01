package com.pcitc.szgt.contract.agencyquery.entity;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 臧传军
 * @date 2021-01-19 14:22:41
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class WorkflowLogDTO implements Serializable {
    private List records;
    private Double total;
    private Integer size;
    private Integer current;
    private ArrayList orders;
    private Boolean searchCount;
    private Integer pages;

    public List getRecords() {
        return records;
    }

    public void setRecords(List records) {
        this.records = records;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public ArrayList getOrders() {
        return orders;
    }

    public void setOrders(ArrayList orders) {
        this.orders = orders;
    }

    public Boolean getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Boolean searchCount) {
        this.searchCount = searchCount;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }
}
