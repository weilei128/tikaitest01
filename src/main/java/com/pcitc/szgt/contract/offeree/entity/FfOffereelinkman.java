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
public class FfOffereelinkman implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 联系人信息 主键GUID
     */
    @TableId("LinkID")
    private String LinkID;

    /**
     * 相对人主键
     */
    @TableField("OffereeID")
    private String OffereeID;

    /**
     * 联系人姓名
     */
    @TableField("LinkManName")
    private String LinkManName;

    /**
     * 职务
     */
    @TableField("LinkManPosition")
    private String LinkManPosition;

    /**
     * 联系电话
     */
    @TableField("Phone")
    private String Phone;

    /**
     * 传真
     */
    @TableField("Fax")
    private String Fax;

    /**
     * 邮箱
     */
    @TableField("Email")
    private String Email;

    /**
     * 创建人
     */
    @TableField("CreatedBy")
    private String CreatedBy;

    /**
     * 创建时间
     */
    @TableField("CreatedDate")
    private LocalDate CreatedDate;

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
     * 是否删除 0否 1是
     */
    @TableField("LogicDel")
    private Integer LogicDel;

    /**
     * 排序字段
     */
    @TableField("OrderNumber")
    private Integer OrderNumber;

    public String getLinkID() {
        return LinkID;
    }

    public void setLinkID(String LinkID) {
        this.LinkID = LinkID;
    }
    public String getOffereeID() {
        return OffereeID;
    }

    public void setOffereeID(String OffereeID) {
        this.OffereeID = OffereeID;
    }
    public String getLinkManName() {
        return LinkManName;
    }

    public void setLinkManName(String LinkManName) {
        this.LinkManName = LinkManName;
    }
    public String getLinkManPosition() {
        return LinkManPosition;
    }

    public void setLinkManPosition(String LinkManPosition) {
        this.LinkManPosition = LinkManPosition;
    }
    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }
    public String getFax() {
        return Fax;
    }

    public void setFax(String Fax) {
        this.Fax = Fax;
    }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }
    public LocalDate getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDate CreatedDate) {
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
    public Integer getLogicDel() {
        return LogicDel;
    }

    public void setLogicDel(Integer LogicDel) {
        this.LogicDel = LogicDel;
    }
    public Integer getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(Integer OrderNumber) {
        this.OrderNumber = OrderNumber;
    }

    @Override
    public String toString() {
        return "FfOffereelinkman{" +
        "LinkID=" + LinkID +
        ", OffereeID=" + OffereeID +
        ", LinkManName=" + LinkManName +
        ", LinkManPosition=" + LinkManPosition +
        ", Phone=" + Phone +
        ", Fax=" + Fax +
        ", Email=" + Email +
        ", CreatedBy=" + CreatedBy +
        ", CreatedDate=" + CreatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", Oulabel=" + Oulabel +
        ", LogicDel=" + LogicDel +
        ", OrderNumber=" + OrderNumber +
        "}";
    }
}
