package com.pcitc.szgt.contract.appmanager.model;

import java.util.List;

public class AmQueryLicenseSetVo {

    private List<Integer> userIds;

    private List<Integer> orgIds;

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }

    public List<Integer> getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(List<Integer> orgIds) {
        this.orgIds = orgIds;
    }
}
