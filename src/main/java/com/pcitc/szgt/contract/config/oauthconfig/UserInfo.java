package com.pcitc.szgt.contract.config.oauthconfig;

import com.pcitc.szgt.contract.share.entity.SysOrganization;
import com.pcitc.szgt.contract.share.entity.SysRoleinfo;
import com.pcitc.szgt.contract.share.entity.SysUserinfo;

import java.io.Serializable;
import java.util.List;

public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户基本信息
     */
    private SysUserinfo sysUser;

    /*
     * 用户所属组织机构
     * */
    private List<SysOrganization> sysOrgList;
    /*
     * 用户所属角色
     * */
    private List<SysRoleinfo> sysRoleList;

    /**
     *  用户所在单位名称
     */
    private String unitName;

    /**
     * 用户所在单位id
     */
    private Integer unitId;

    public SysUserinfo getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUserinfo sysUser) {
        this.sysUser = sysUser;
    }

    public List<SysOrganization> getSysOrgList() {
        return sysOrgList;
    }

    public void setSysOrgList(List<SysOrganization> sysOrgList) {
        this.sysOrgList = sysOrgList;
    }

    public List<SysRoleinfo> getSysRoleList() {
        return sysRoleList;
    }

    public void setSysRoleList(List<SysRoleinfo> sysRoleList) {
        this.sysRoleList = sysRoleList;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserInfo{");
        sb.append("sysUser=").append(sysUser);
        sb.append(", sysOrgList=").append(sysOrgList);
        sb.append(", sysRoleList=").append(sysRoleList);
        sb.append(", unitName='").append(unitName).append('\'');
        sb.append(", unitId=").append(unitId);
        sb.append('}');
        return sb.toString();
    }
}
