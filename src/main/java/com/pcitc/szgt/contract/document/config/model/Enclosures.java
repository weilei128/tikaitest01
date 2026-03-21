package com.pcitc.szgt.contract.document.config.model;

import java.io.Serializable;

public class Enclosures implements Serializable {

    private static final long serialVersionUID = -2395748419144854330L;

    private String enclosureUrl;

    private String enclosureName;

    public String getEnclosureUrl() {
        return enclosureUrl;
    }

    public void setEnclosureUrl(String enclosureUrl) {
        this.enclosureUrl = enclosureUrl;
    }

    public String getEnclosureName() {
        return enclosureName;
    }

    public void setEnclosureName(String enclosureName) {
        this.enclosureName = enclosureName;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Enclosures{");
        sb.append("enclosureUrl='").append(enclosureUrl).append('\'');
        sb.append(", enclosureName='").append(enclosureName).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
