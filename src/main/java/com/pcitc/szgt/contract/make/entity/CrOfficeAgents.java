package com.pcitc.szgt.contract.make.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "cr_officeagents" , description = "办公代理信息表" )
public class CrOfficeAgents implements Serializable {

	@ApiModelProperty(name="id" ,value = "主键ID")
	private Integer id ;
	
	@ApiModelProperty(name="operationId" , value = "操作人用户ID")
	private Integer operationId ;
	
	@ApiModelProperty(name="agentId" , value = "代理人用户ID")
	private Integer agentId ;
	
	@ApiModelProperty(name="startTime" , value = "代理开始时间")
	private String startTime ;
	
	@ApiModelProperty(name="endTime" , value = "代理结束时间")
	private String endTime ;
	
	@ApiModelProperty(name="agentStatus" , value = "代理信息状态   0-代理中 1-已取消 2-已结束")
	private Integer agentStatus ;
	
	@ApiModelProperty(name="dealTime" , value = "信息处理时间")
	private Date dealTime ;
	
	@ApiModelProperty(name="createTime" , value = "记录创建时间")
	private Date createTime ;

}
