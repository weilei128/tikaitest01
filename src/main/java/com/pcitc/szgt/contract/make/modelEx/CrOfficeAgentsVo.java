package com.pcitc.szgt.contract.make.modelEx;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "CrOfficeAgentsVo" , description = " 办公代理VO ")
public class CrOfficeAgentsVo {

	@ApiModelProperty(name="id" ,value = "主键ID")
	private Integer id ;
	
	@ApiModelProperty(name="operationId" , value = "操作人用户ID")
	private Integer operationId ;
	
	@ApiModelProperty(name="agentId" , value = "代理人用户ID")
	private Integer agentId ;
	
	@ApiModelProperty(name="agentAccount" , value = "代理人账号")
	private String agentAccount ;
	
	@ApiModelProperty(name="agentName" , value = "代理人姓名")
	private String agentName ;	
	
	@ApiModelProperty(name="agentOrg" , value = "代理人所在机构")
	private String agentOrg ;	
	
	@ApiModelProperty(name="startTime" , value = "代理开始时间")
	private String startTime ;
	
	@ApiModelProperty(name="endTime" , value = "代理结束时间")
	private String endTime ;
	
	@ApiModelProperty(name="agentStatus" , value = "代理信息状态")
	private Integer agentStatus ;
	
	@ApiModelProperty(name="dealTime" , value = "信息处理时间")
	private String dealTime ;
	
	@ApiModelProperty(name="createTime" , value = "记录创建时间")
	private String createTime ;
}
