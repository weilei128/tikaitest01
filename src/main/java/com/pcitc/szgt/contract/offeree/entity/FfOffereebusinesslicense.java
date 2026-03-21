package com.pcitc.szgt.contract.offeree.entity;

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
 * @since 2020-02-18
 */
public class FfOffereebusinesslicense implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 营业执照信息主键,GUID格式
     */
    @TableId("BusinessLicenseID")
    private String BusinessLicenseID;

    /**
     * 相对人主键
     */
    @TableField("OffereeID")
    private String OffereeID;

    /**
     * 注册号
     */
    @TableField("BusinessLicenseCode")
    private String BusinessLicenseCode;

    /**
     * 注册地址
     */
    @TableField("RegisterAddr")
    private String RegisterAddr;

    /**
     * 办公地址
     */
    @TableField("OfficeAddr")
    private String OfficeAddr;

    /**
     * 邮编
     */
    @TableField("PostCode")
    private String PostCode;

    /**
     * 暂时不用
     */
    @TableField("BusnissAllotedStartDate")
    private LocalDateTime BusnissAllotedStartDate;

    /**
     * 暂时不用
     */
    @TableField("BusnissAllotedEndDate")
    private LocalDateTime BusnissAllotedEndDate;

    /**
     * 是否删除 0否 1 是
     */
    @TableField("LogicDel")
    private Integer LogicDel;

    /**
     * 创建人
     */
    @TableField("CreatedBy")
    private String CreatedBy;

    /**
     * 创建时间
     */
    @TableField("CreatedDate")
    private LocalDateTime CreatedDate;

    /**
     * 修改人
     */
    @TableField("ModifiedBy")
    private String ModifiedBy;

    /**
     * 修改时间
     */
    @TableField("ModifiedDate")
    private LocalDateTime ModifiedDate;

    /**
     * 企业标识
     */
    @TableField("Oulabel")
    private Integer Oulabel;

    /**
     * 注册资本,例如：5000万
     */
    @TableField("RegisteredCapital")
    private String RegisteredCapital;

    /**
     * 营业期限，如果营业期限有值，营业期限为固定，否则营业期限为长期
     */
    @TableField("RegisterDate")
    private LocalDateTime RegisterDate;

    /**
     * 最新报告时间
     */
    @TableField("ReportDate")
    private LocalDateTime ReportDate;

    public String getBusinessLicenseID() {
        return BusinessLicenseID;
    }

    public void setBusinessLicenseID(String BusinessLicenseID) {
        this.BusinessLicenseID = BusinessLicenseID;
    }
    public String getOffereeID() {
        return OffereeID;
    }

    public void setOffereeID(String OffereeID) {
        this.OffereeID = OffereeID;
    }
    public String getBusinessLicenseCode() {
        return BusinessLicenseCode;
    }

    public void setBusinessLicenseCode(String BusinessLicenseCode) {
        this.BusinessLicenseCode = BusinessLicenseCode;
    }
    public String getRegisterAddr() {
        return RegisterAddr;
    }

    public void setRegisterAddr(String RegisterAddr) {
        this.RegisterAddr = RegisterAddr;
    }
    public String getOfficeAddr() {
        return OfficeAddr;
    }

    public void setOfficeAddr(String OfficeAddr) {
        this.OfficeAddr = OfficeAddr;
    }
    public String getPostCode() {
        return PostCode;
    }

    public void setPostCode(String PostCode) {
        this.PostCode = PostCode;
    }
    public LocalDateTime getBusnissAllotedStartDate() {
        return BusnissAllotedStartDate;
    }

    public void setBusnissAllotedStartDate(LocalDateTime BusnissAllotedStartDate) {
        this.BusnissAllotedStartDate = BusnissAllotedStartDate;
    }
    public LocalDateTime getBusnissAllotedEndDate() {
        return BusnissAllotedEndDate;
    }

    public void setBusnissAllotedEndDate(LocalDateTime BusnissAllotedEndDate) {
        this.BusnissAllotedEndDate = BusnissAllotedEndDate;
    }
    public Integer getLogicDel() {
        return LogicDel;
    }

    public void setLogicDel(Integer LogicDel) {
        this.LogicDel = LogicDel;
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
    public Integer getOulabel() {
        return Oulabel;
    }

    public void setOulabel(Integer Oulabel) {
        this.Oulabel = Oulabel;
    }
    public String getRegisteredCapital() {
        return RegisteredCapital;
    }

    public void setRegisteredCapital(String RegisteredCapital) {
        this.RegisteredCapital = RegisteredCapital;
    }
    public LocalDateTime getRegisterDate() {
        return RegisterDate;
    }

    public void setRegisterDate(LocalDateTime RegisterDate) {
        this.RegisterDate = RegisterDate;
    }
    public LocalDateTime getReportDate() {
        return ReportDate;
    }

    public void setReportDate(LocalDateTime ReportDate) {
        this.ReportDate = ReportDate;
    }

    @Override
    public String toString() {
        return "FfOffereebusinesslicense{" +
        "BusinessLicenseID=" + BusinessLicenseID +
        ", OffereeID=" + OffereeID +
        ", BusinessLicenseCode=" + BusinessLicenseCode +
        ", RegisterAddr=" + RegisterAddr +
        ", OfficeAddr=" + OfficeAddr +
        ", PostCode=" + PostCode +
        ", BusnissAllotedStartDate=" + BusnissAllotedStartDate +
        ", BusnissAllotedEndDate=" + BusnissAllotedEndDate +
        ", LogicDel=" + LogicDel +
        ", CreatedBy=" + CreatedBy +
        ", CreatedDate=" + CreatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", Oulabel=" + Oulabel +
        ", RegisteredCapital=" + RegisteredCapital +
        ", RegisterDate=" + RegisterDate +
        ", ReportDate=" + ReportDate +
        "}";
    }
}
