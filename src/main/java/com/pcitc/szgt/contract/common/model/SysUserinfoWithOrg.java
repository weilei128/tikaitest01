package com.pcitc.szgt.contract.common.model;

import com.pcitc.szgt.contract.share.entity.SysOrganization;
import com.pcitc.szgt.contract.share.entity.SysUserinfo;

import java.util.List;

public class SysUserinfoWithOrg extends SysUserinfo {
    private List<SysOrganization> orgs;
    private SysOrganization unit;

    public List<SysOrganization> getOrgs() {
        return orgs;
    }

    public void setOrgs(List<SysOrganization> orgs) {
        this.orgs = orgs;
    }

    public SysOrganization getUnit() {
        return unit;
    }

    public void setUnit(SysOrganization unit) {
        this.unit = unit;
    }
}
