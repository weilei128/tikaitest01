package com.pcitc.legalAffairs.vo.fwPrivilegeInfo;

public class PrivilegeUserVo {

	/**
	 * 用户ID
	 */
	private Long fkUserId;
	/**
	 * 用户名
	 */
	private String fkUserName;
	/**
	 * 授权组织名
	 */
	private String privilegeOrgs;
	/**
	 * 所属组织名
	 */
	private String orgs;

	public Long getFkUserId() {
		return fkUserId;
	}

	public void setFkUserId(Long fkUserId) {
		this.fkUserId = fkUserId;
	}

	public String getFkUserName() {
		return fkUserName;
	}

	public void setFkUserName(String fkUserName) {
		this.fkUserName = fkUserName;
	}

	public String getPrivilegeOrgs() {
		return privilegeOrgs;
	}

	public void setPrivilegeOrgs(String privilegeOrgs) {
		this.privilegeOrgs = privilegeOrgs;
	}

	public String getOrgs() {
		return orgs;
	}

	public void setOrgs(String orgs) {
		this.orgs = orgs;
	}
}
