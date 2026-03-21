package com.pcitc.szgt.contract.appmanager.model;

import java.util.List;

public class AmQueryLicenseQueryBo {
    private List<String> userIds;

    private List<Integer> orgIds;

    public List<String> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<String> userIds) {
        this.userIds = userIds;
    }

    public List<Integer> getOrgIds() {
        return orgIds;
    }

    public void setOrgIds(List<Integer> orgIds) {
        this.orgIds = orgIds;
    }
}
