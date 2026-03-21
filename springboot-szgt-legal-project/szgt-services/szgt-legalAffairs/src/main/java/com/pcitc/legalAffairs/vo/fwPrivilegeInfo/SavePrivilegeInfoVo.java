package com.pcitc.legalAffairs.vo.fwPrivilegeInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SavePrivilegeInfoVo {

	@ApiModelProperty("用户列表")
	private List<FwPrivilegeInfoVo> users;

	@ApiModelProperty("组织列表")
	private List<FwPrivilegeInfoVo> orgs;

}
