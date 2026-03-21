package com.pcitc.szgt.contract.appmanager.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-03-01
 */
public class AmQuerylicense implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("QueryLicenseID")
    private String QueryLicenseID;

    @TableField("UserID")
    private String UserID;

    @TableField("OrgID")
    private Integer OrgID;

    @TableField("OrgCode")
    private String OrgCode;

    @TableField("Oulabel")
    private Integer Oulabel;

    @TableField("CreatedBy")
    private String CreatedBy;

    @TableField("CreatedDate")
    private LocalDateTime CreatedDate;

    @TableField("ModifiedBy")
    private String ModifiedBy;

    @TableField("ModifiedDate")
    private LocalDateTime ModifiedDate;

    public String getQueryLicenseID() {
        return QueryLicenseID;
    }

    public void setQueryLicenseID(String QueryLicenseID) {
        this.QueryLicenseID = QueryLicenseID;
    }
    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }
    public Integer getOrgID() {
        return OrgID;
    }

    public void setOrgID(Integer OrgID) {
        this.OrgID = OrgID;
    }
    public String getOrgCode() {
        return OrgCode;
    }

    public void setOrgCode(String OrgCode) {
        this.OrgCode = OrgCode;
    }
    public Integer getOulabel() {
        return Oulabel;
    }

    public void setOulabel(Integer Oulabel) {
        this.Oulabel = Oulabel;
    }
    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }
    public LocalDateTime getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDateTime CreatedDate) {
        this.CreatedDate = CreatedDate;
    }
    public String getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(String ModifiedBy) {
        this.ModifiedBy = ModifiedBy;
    }
    public LocalDateTime getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(LocalDateTime ModifiedDate) {
        this.ModifiedDate = ModifiedDate;
    }

    @Override
    public String toString() {
        return "AmQuerylicense{" +
        "QueryLicenseID=" + QueryLicenseID +
        ", UserID=" + UserID +
        ", OrgID=" + OrgID +
        ", OrgCode=" + OrgCode +
        ", Oulabel=" + Oulabel +
        ", CreatedBy=" + CreatedBy +
        ", CreatedDate=" + CreatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        "}";
    }
}
