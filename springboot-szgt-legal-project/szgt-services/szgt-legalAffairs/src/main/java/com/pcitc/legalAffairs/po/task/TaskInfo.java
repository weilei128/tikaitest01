package com.pcitc.legalAffairs.po.task;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pcitc.szgt.legalAffairs.base.BasePojo;


@TableName("fw_task_info")
public class TaskInfo extends BasePojo {

	@TableId(value = "f_ID", type = IdType.AUTO)
	private Long id;
	
	@TableField("f_Task_Id")
	private String taskId;
	
	@TableField("f_Category_Code")
	private String categoryCode;
	
	@TableField("f_Pure_Id")
	private String pureId;
	
	@TableField("f_Business_Id")
	private String businessId;
	
	@TableField("f_State")
    private Integer fState;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getPureId() {
		return pureId;
	}

	public void setPureId(String pureId) {
		this.pureId = pureId;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public Integer getfState() {
		return fState;
	}

	public void setfState(Integer fState) {
		this.fState = fState;
	}

}
