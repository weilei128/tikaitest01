package com.pcitc.szgt.contract.make.entity;

import java.util.List;

import com.pcitc.szgt.contract.make.modelEx.CrOfficeAgentsVo;


public class CrOfficeAgentsList {

	private List<CrOfficeAgentsVo> records ;
    private Integer total  ;
    private Integer size ;
    private Integer current ;
    private List<?> orders ;
    private boolean searchCount ;
    private Integer pages ;
    
	public List<CrOfficeAgentsVo> getRecords() {
		return records;
	}

	public void setRecords(List<CrOfficeAgentsVo> records) {
		this.records = records;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
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

	public List<?> getOrders() {
		return orders;
	}

	public void setOrders(List<?> orders) {
		this.orders = orders;
	}

	public boolean isSearchCount() {
		return searchCount;
	}

	public void setSearchCount(boolean searchCount) {
		this.searchCount = searchCount;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}
	
	
}
