package com.pcitc.szgt.contract.share.model;

import com.pcitc.szgt.contract.share.entity.SysOrganization;

import java.util.List;

public class SysOrganizationTree extends SysOrganization {

	private static final long serialVersionUID = -5604999819114579171L;

	private List<SysOrganizationTree> childNodeList;

    public List<SysOrganizationTree> getChildNodeList() {
        return childNodeList;
    }

    public void setChildNodeList(List<SysOrganizationTree> childNodeList) {
        this.childNodeList = childNodeList;
    }
}
