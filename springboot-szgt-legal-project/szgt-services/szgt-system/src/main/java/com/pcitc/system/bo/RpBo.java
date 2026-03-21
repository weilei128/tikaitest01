package com.pcitc.system.bo;

import java.util.List;

/***
 * @description
 * @author leigang
 * @date 2020年2月20日 09:38:59
 *
 */
public class RpBo {

    private String roleId;
    private Integer type;
    private List<String> addMenuIds;
    private List<String> deleteMenuIds;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<String> getAddMenuIds() {
        return addMenuIds;
    }

    public void setAddMenuIds(List<String> addMenuIds) {
        this.addMenuIds = addMenuIds;
    }

    public List<String> getDeleteMenuIds() {
        return deleteMenuIds;
    }

    public void setDeleteMenuIds(List<String> deleteMenuIds) {
        this.deleteMenuIds = deleteMenuIds;
    }
}
