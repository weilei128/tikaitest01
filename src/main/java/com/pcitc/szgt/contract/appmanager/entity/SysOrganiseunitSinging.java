package com.pcitc.szgt.contract.appmanager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2020-03-11
 */
public class SysOrganiseunitSinging implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "SingID", type = IdType.AUTO)
    private Integer SingID;

    @TableField("OUID")
    private Integer ouid;

    /**
     * 签约主体名称
     */
    @TableField("SingingName")
    private String SingingName;

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
     * 创建人
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

    public Integer getSingID() {
        return SingID;
    }

    public void setSingID(Integer SingID) {
        this.SingID = SingID;
    }
    public Integer getOuid() {
        return ouid;
    }

    public void setOuid(Integer ouid) {
        this.ouid = ouid;
    }
    public String getSingingName() {
        return SingingName;
    }

    public void setSingingName(String SingingName) {
        this.SingingName = SingingName;
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

    @Override
    public String toString() {
        return "SysOrganiseunitSinging{" +
        "SingID=" + SingID +
        ", ouid=" + ouid +
        ", SingingName=" + SingingName +
        ", CreatedBy=" + CreatedBy +
        ", CreatedDate=" + CreatedDate +
        ", ModifiedBy=" + ModifiedBy +
        ", ModifiedDate=" + ModifiedDate +
        ", Oulabel=" + Oulabel +
        "}";
    }
}
