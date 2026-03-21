package com.pcitc.szgt.contract.share.model;

import com.pcitc.szgt.contract.share.entity.SysOrganization;

public class SysOrganizationRoot extends SysOrganization {

	private static final long serialVersionUID = 5217510193925126799L;
	
	private SysOrganizationRoot sysParentOrganizationVo;

    public SysOrganizationRoot getSysParentOrganizationVo() {
        return sysParentOrganizationVo;
    }

    public void setSysParentOrganizationVo(SysOrganizationRoot sysParentOrganizationVo) {
        this.sysParentOrganizationVo = sysParentOrganizationVo;
    }
}
