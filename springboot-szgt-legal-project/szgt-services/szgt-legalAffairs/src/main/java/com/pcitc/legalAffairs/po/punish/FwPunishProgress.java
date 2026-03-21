package com.pcitc.legalAffairs.po.punish;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pcitc.szgt.legalAffairs.base.BasePojo;

/**
 * @author 
 */
@TableName("fw_punish_progress")
public class FwPunishProgress extends BasePojo {
	
	@TableId
    private Long fId;

    private Long fkPunishId;

    /**
     * 执行情况
     */
    private String fDescription;

    /**
     * 处罚类型
     */
    private String fPunishType;

    /**
     * 处理时间
     */
    private Date fDate;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public Long getFkPunishId() {
        return fkPunishId;
    }

    public void setFkPunishId(Long fkPunishId) {
        this.fkPunishId = fkPunishId;
    }

    public String getfDescription() {
        return fDescription;
    }

    public void setfDescription(String fDescription) {
        this.fDescription = fDescription;
    }

    public String getfPunishType() {
        return fPunishType;
    }

    public void setfPunishType(String fPunishType) {
        this.fPunishType = fPunishType;
    }

    public Date getfDate() {
        return fDate;
    }

    public void setfDate(Date fDate) {
        this.fDate = fDate;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        FwPunishProgress other = (FwPunishProgress) that;
        return (this.getfId() == null ? other.getfId() == null : this.getfId().equals(other.getfId()))
            && (this.getFkPunishId() == null ? other.getFkPunishId() == null : this.getFkPunishId().equals(other.getFkPunishId()))
            && (this.getfDescription() == null ? other.getfDescription() == null : this.getfDescription().equals(other.getfDescription()))
            && (this.getfPunishType() == null ? other.getfPunishType() == null : this.getfPunishType().equals(other.getfPunishType()))
            && (this.getfDate() == null ? other.getfDate() == null : this.getfDate().equals(other.getfDate()))
            && (this.getfType() == null ? other.getfType() == null : this.getfType().equals(other.getfType()))
            && (this.getfSort() == null ? other.getfSort() == null : this.getfSort().equals(other.getfSort()))
            && (this.getfState() == null ? other.getfState() == null : this.getfState().equals(other.getfState()))
            && (this.getfIsdel() == null ? other.getfIsdel() == null : this.getfIsdel().equals(other.getfIsdel()))
            && (this.getfCreateuser() == null ? other.getfCreateuser() == null : this.getfCreateuser().equals(other.getfCreateuser()))
            && (this.getfCreatename() == null ? other.getfCreatename() == null : this.getfCreatename().equals(other.getfCreatename()))
            && (this.getfCreatetime() == null ? other.getfCreatetime() == null : this.getfCreatetime().equals(other.getfCreatetime()))
            && (this.getfUpdateuser() == null ? other.getfUpdateuser() == null : this.getfUpdateuser().equals(other.getfUpdateuser()))
            && (this.getfUpdatename() == null ? other.getfUpdatename() == null : this.getfUpdatename().equals(other.getfUpdatename()))
            && (this.getfUpdatetime() == null ? other.getfUpdatetime() == null : this.getfUpdatetime().equals(other.getfUpdatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getfId() == null) ? 0 : getfId().hashCode());
        result = prime * result + ((getFkPunishId() == null) ? 0 : getFkPunishId().hashCode());
        result = prime * result + ((getfDescription() == null) ? 0 : getfDescription().hashCode());
        result = prime * result + ((getfPunishType() == null) ? 0 : getfPunishType().hashCode());
        result = prime * result + ((getfDate() == null) ? 0 : getfDate().hashCode());
        result = prime * result + ((getfType() == null) ? 0 : getfType().hashCode());
        result = prime * result + ((getfSort() == null) ? 0 : getfSort().hashCode());
        result = prime * result + ((getfState() == null) ? 0 : getfState().hashCode());
        result = prime * result + ((getfIsdel() == null) ? 0 : getfIsdel().hashCode());
        result = prime * result + ((getfCreateuser() == null) ? 0 : getfCreateuser().hashCode());
        result = prime * result + ((getfCreatename() == null) ? 0 : getfCreatename().hashCode());
        result = prime * result + ((getfCreatetime() == null) ? 0 : getfCreatetime().hashCode());
        result = prime * result + ((getfUpdateuser() == null) ? 0 : getfUpdateuser().hashCode());
        result = prime * result + ((getfUpdatename() == null) ? 0 : getfUpdatename().hashCode());
        result = prime * result + ((getfUpdatetime() == null) ? 0 : getfUpdatetime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", fId=").append(fId);
        sb.append(", fkPunishId=").append(fkPunishId);
        sb.append(", fDescription=").append(fDescription);
        sb.append(", fPunishType=").append(fPunishType);
        sb.append(", fDate=").append(fDate);
        sb.append("]");
        return sb.toString();
    }
}