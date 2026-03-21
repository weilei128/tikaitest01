package com.pcitc.szgt.contract.share.model;

import com.pcitc.szgt.contract.common.model.SysUserinfoWithOrg;
import com.pcitc.szgt.contract.share.entity.SysUserinfo;

import java.util.List;

public class UserQueryResultModel {
    private Integer current;
    private Integer pages;
    private List<String> orders;
    private Boolean searchCount;
    private Integer size;
    private Integer total;
    private List<SysUserinfoWithOrg> records;

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public List<String> getOrders() {
        return orders;
    }

    public void setOrders(List<String> orders) {
        this.orders = orders;
    }

    public Boolean getSearchCount() {
        return searchCount;
    }

    public void setSearchCount(Boolean searchCount) {
        this.searchCount = searchCount;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<SysUserinfoWithOrg> getRecords() {
        return records;
    }

    public void setRecords(List<SysUserinfoWithOrg> records) {
        this.records = records;
    }
}
