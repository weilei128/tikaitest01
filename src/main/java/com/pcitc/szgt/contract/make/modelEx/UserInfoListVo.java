package com.pcitc.szgt.contract.make.modelEx;

import java.io.Serializable;
import java.util.List;

import com.pcitc.szgt.contract.share.entity.SysUserinfo;

public class UserInfoListVo implements Serializable{

	private static final long serialVersionUID = 775784875181618580L;
	
	
	private List<SysUserinfo> records ;

	public List<SysUserinfo> getRecords() {
		return records;
	}

	public void setRecords(List<SysUserinfo> records) {
		this.records = records;
	}
	
	
	
}
