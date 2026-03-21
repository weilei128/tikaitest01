package com.pcitc.szgt.contract.share.model;

import java.io.Serializable;
import java.util.List;

public class UserRoleList implements Serializable{

	private static final long serialVersionUID = -8472661815402591700L;
	
	private List<UserRole> userSelectRoleInfoList ;

	public List<UserRole> getUserSelectRoleInfoList() {
		return userSelectRoleInfoList;
	}

	public void setUserSelectRoleInfoList(List<UserRole> userSelectRoleInfoList) {
		this.userSelectRoleInfoList = userSelectRoleInfoList;
	}


}
