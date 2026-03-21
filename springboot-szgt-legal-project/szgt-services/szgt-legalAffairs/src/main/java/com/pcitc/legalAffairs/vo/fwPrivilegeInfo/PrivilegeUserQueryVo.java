package com.pcitc.legalAffairs.vo.fwPrivilegeInfo;

import com.pcitc.common.entity.BaseEntity;

public class PrivilegeUserQueryVo extends BaseEntity {

	/**
	 * 用户ID
	 */
//	private Long fkUserId;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 授权组织名
	 */
	private String privilegeOrg;
	/**
	 * 授权组织ID
	 */
	private Long privilegeOrgId;

//	public Long getFkUserId() {
//		return fkUserId;
//	}
//
//	public void setFkUserId(Long fkUserId) {
//		this.fkUserId = fkUserId;
//	}
//
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPrivilegeOrg() {
		return privilegeOrg;
	}

	public void setPrivilegeOrg(String privilegeOrg) {
		this.privilegeOrg = privilegeOrg;
	}

	public Long getPrivilegeOrgId() {
		return privilegeOrgId;
	}

	public void setPrivilegeOrgId(Long privilegeOrgId) {
		this.privilegeOrgId = privilegeOrgId;
	}
}
