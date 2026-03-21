package com.pcitc.szgt.contract.document.config.model;

import java.io.Serializable;

public class PushScope implements Serializable {

    private static final long serialVersionUID = -5684868299632531240L;

    private String userType;

    private String userCode;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PushScope{");
        sb.append("userType='").append(userType).append('\'');
        sb.append(", userCode='").append(userCode).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
