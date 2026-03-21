package com.pcitc.szgt.contract.offeree.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2020-02-18
 */
public class FfOffereeinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键 ，使用GUID
     */
    @TableId("OffereeId")
    private String OffereeId;

    /**
     * 相对人类型 机构0 1自然人
     */
    @TableField("OffereeType")
    private Integer OffereeType;

    /**
     * 相对人编码 同一企业下，编码唯一
     */
    @TableField("OffereeCode")
    private String OffereeCode;

    /**
     * 相对人名称
     */
    @TableField("OffereeName")
    private String OffereeName;

    /**
     * 相对人归属 股份/股份企业/集团/集团企业/石化合营/石化外，取数据字典
     */
    @TableField("OffereeeBelong")
    private Integer OffereeeBelong;

    /**
     * 机构类型 企业/政府机关/事业单位 ，取数据字典
     */
    @TableField("CompanyType")
    private Integer CompanyType;

    /**
     * 法人代表
     */
    @TableField("Corporation")
    private String Corporation;

    /**
     * 相对人分类 供应商/客户/承包商/其他,取数据字典
     */
    @TableField("OffereeSort")
    private String OffereeSort;

    /**
     * 统一社会信用代码
     */
    @TableField("CreditCode")
    private String CreditCode;

    /**
     * 相对人数据来源  1自建 0 MDM系统 依次类推
     */
    @TableField("DataSource")
    private Integer DataSource;

    /**
     * 自然人时，姓名
     */
    @TableField("NaturePerson")
    private String NaturePerson;

    /**
     * 自然人时，身份证号
     */
    @TableField("IDCard")
    private String IDCard;

    /**
     * 自然时，电话
     */
    @TableField("Phone")
    private String Phone;

    /**
     * 自然时，地址
     */
    @TableField("Address")
    private String Address;

    /**
     * 是否删除  0否 1 是 系统只展现正常数据
     */
    @TableField("LogicDel")
    private Integer LogicDel;

    /**
     * 是否启用 0否 1是 
     */
    @TableField("IsEnable")
    private Integer IsEnable;

    /**
     * 当前创建人
     */
    @TableField("CreatedBy")
    private String CreatedBy;

    /**
     * 创建时间
     */
    @TableField("CreatedDate")
    private LocalDateTime CreatedDate;

    /**
     * 当前修改人
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
     * 相对人Code+Name,格式为：Code+[Name]
     */
    @TableField("CodeNames")
    private String CodeNames;

    /**
     * 相对人所属单位（复核相对人时使用）
     */
    @TableField("OffereeOrgIds")
    private String OffereeOrgIds;

    /**
     * 复核审批意见
     */
    @TableField("CheckMessage")
    private String CheckMessage;

    /**
     * 复核人
     */
    @TableField("CheckModifiedBy")
    private String CheckModifiedBy;

    /**
     * 复核时间
     */
    @TableField("CheckModifiedDate")
    private LocalDate CheckModifiedDate;

    /**
     * 备注
     */
    @TableField("Remark")
    private String Remark;

    /**
     * 扩展字段1
     */
    @TableField("Remark1")
    private String Remark1;

    /**
     * 扩展字段2
     */
    @TableField("Remark2")
    private String Remark2;

    /**
     * 扩展字段3
     */
    @TableField("Remark3")
    private String Remark3;

    /**
     * 扩展字段4
     */
    @TableField("Remark4")
    private String Remark4;

    /**
     * 扩展字段5
     */
    @TableField("Remark5")
    private String Remark5;

    /**
     * 状态 0 完成  1草稿
     */
    @TableField("Status")
    private String Status;

    /**
     * 来源类型 INSYS_INUNIT 内部单位   INSYS_OUTUNIT 外部单位
     */
    @TableField("SourceType")
    private String SourceType;

    public String getOffereeId() {
        return OffereeId;
    }

    public void setOffereeId(String OffereeId) {
        this.OffereeId = OffereeId;
    }
    public Integer getOffereeType() {
        return OffereeType;
    }

    public void setOffereeType(Integer OffereeType) {
        this.OffereeType = OffereeType;
    }
    public String getOffereeCode() {
        return OffereeCode;
    }

    public void setOffereeCode(String OffereeCode) {
        this.OffereeCode = OffereeCode;
    }
    public String getOffereeName() {
        return OffereeName;
    }

    public void setOffereeName(String OffereeName) {
        this.OffereeName = OffereeName;
    }
    public Integer getOffereeeBelong() {
        return OffereeeBelong;
    }

    public void setOffereeeBelong(Integer OffereeeBelong) {
        this.OffereeeBelong = OffereeeBelong;
    }
    public Integer getCompanyType() {
        return CompanyType;
    }

    public void setCompanyType(Integer CompanyType) {
        this.CompanyType = CompanyType;
    }
    public String getCorporation() {
        return Corporation;
    }

    public void setCorporation(String Corporation) {
        this.Corporation = Corporation;
    }
    public String getOffereeSort() {
        return OffereeSort;
    }

    public void setOffereeSort(String OffereeSort) {
        this.OffereeSort = OffereeSort;
    }
    public String getCreditCode() {
        return CreditCode;
    }

    public void setCreditCode(String CreditCode) {
        this.CreditCode = CreditCode;
    }
    public Integer getDataSource() {
        return DataSource;
    }

    public void setDataSource(Integer DataSource) {
        this.DataSource = DataSource;
    }
    public String getNaturePerson() {
        return NaturePerson;
    }

    public void setNaturePerson(String NaturePerson) {
        this.NaturePerson = NaturePerson;
    }
    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }
    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }
    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }
    public Integer getLogicDel() {
        return LogicDel;
    }

    public void setLogicDel(Integer LogicDel) {
        this.LogicDel = LogicDel;
    }
    public Integer getIsEnable() {
        return IsEnable;
    }

    public void setIsEnable(Integer IsEnable) {
        this.IsEnable = IsEnable;
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
    public String getCodeNames() {
        return CodeNames;
    }

    public void setCodeNames(String CodeNames) {
        this.CodeNames = CodeNames;
    }
    public String getOffereeOrgIds() {
        return OffereeOrgIds;
    }

    public void setOffereeOrgIds(String OffereeOrgIds) {
        this.OffereeOrgIds = OffereeOrgIds;
    }
    public String getCheckMessage() {
        return CheckMessage;
    }

    public void setCheckMessage(String CheckMessage) {
        this.CheckMessage = CheckMessage;
    }
    public String getCheckModifiedBy() {
        return CheckModifiedBy;
    }

    public void setCheckModifiedBy(String CheckModifiedBy) {
        this.CheckModifiedBy = CheckModifiedBy;
    }
    public LocalDate getCheckModifiedDate() {
        return CheckModifiedDate;
    }

    public void setCheckModifiedDate(LocalDate CheckModifiedDate) {
        this.CheckModifiedDate = CheckModifiedDate;
    }
    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }
    public String getRemark1() {
        return Remark1;
    }

    public void setRemark1(String Remark1) {
        this.Remark1 = Remark1;
    }
    public String getRemark2() {
        return Remark2;
    }

    public void setRemark2(String Remark2) {
        this.Remark2 = Remark2;
    }
    public String getRemark3() {
        return Remark3;
    }

    public void setRemark3(String Remark3) {
        this.Remark3 = Remark3;
    }
    public String getRemark4() {
        return Remark4;
    }

    public void setRemark4(String Remark4) {
        this.Remark4 = Remark4;
    }
    public String getRemark5() {
        return Remark5;
    }

    public void setRemark5(String Remark5) {
        this.Remark5 = Remark5;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getSourceType() {
        return SourceType;
    }

    public void setSourceType(String sourceType) {
        SourceType = sourceType;
    }

    @Override
    public String toString() {
        return "FfOffereeinfo{" +
        "OffereeId=" + OffereeId +
        ", OffereeType=" + OffereeType +
        ", OffereeCode=" + OffereeCode +
        ", OffereeName=" + OffereeName +
        ", OffereeeBelong=" + OffereeeBelong +
        ", CompanyType=" + CompanyType +
        ", Corporation=" + Corporation +
        ", OffereeSort=" + OffereeSort +
        ", CreditCode=" + CreditCode +
        ", DataSource=" + DataSource +
        ", NaturePerson=" + NaturePerson +
        ", IDCard=" + IDCard +
        ", Phone=" + Phone +
        ", Address=" + Address +
        ", LogicDel=" + LogicDel +
        ", IsEnable=" + IsEnable +
        ", CreatedBy=" + CreatedBy +
        ", CreatedDate=" + CreatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", Oulabel=" + Oulabel +
        ", CodeNames=" + CodeNames +
        ", OffereeOrgIds=" + OffereeOrgIds +
        ", CheckMessage=" + CheckMessage +
        ", CheckModifiedBy=" + CheckModifiedBy +
        ", CheckModifiedDate=" + CheckModifiedDate +
        ", Remark=" + Remark +
        ", Remark1=" + Remark1 +
        ", Remark2=" + Remark2 +
        ", Remark3=" + Remark3 +
        ", Remark4=" + Remark4 +
        ", Remark5=" + Remark5 +
        ", Status=" + Status +
        "}";
    }
}
