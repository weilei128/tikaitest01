package com.pcitc.szgt.contract.appmanager.model;

public class AmQueryLicenseResultVo {
    private Integer userId;

    private String userName;

    private String orgAdminName;
    private String orgAdminId;

    private String userOrg;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrgAdminName() {
        return orgAdminName;
    }

    public void setOrgAdminId(String orgAdminId) {
        this.orgAdminId = orgAdminId;
    }

    public String getOrgAdminId() {
        return orgAdminId;
    }

    public void setOrgAdminName(String orgAdminName) {
        this.orgAdminName = orgAdminName;
    }

    public String getUserOrg() {
        return userOrg;
    }

    public void setUserOrg(String userOrg) {
        this.userOrg = userOrg;
    }
}
