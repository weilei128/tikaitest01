package com.pcitc.legalAffairs.po.Intermediary;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pcitc.szgt.legalAffairs.base.BasePojo;

/**
 * @author 
 * 中介准入操作记录
 */
@TableName("fw_intermediary_org_log")
public class FwIntermediaryOrgLog extends BasePojo implements Serializable {
    
    @TableId(value = "f_ID", type = IdType.AUTO)
    private Long fId;

    /**
     * 中介ID
     */
    private Long fkIntermediaryId;

    /**
     * 操作人ID
     */
    private Long fkOperatorId;

    /**
     * 操作人名称
     */
    private String fkOperatorName;

    /**
     * 操作时间
     */
    private Date fOperateTime;

    /**
     * 执行操作 0-准入 1-聘用 2-解除准入 3-启用 4-禁用
     */
    private Integer fOperate;

    private static final long serialVersionUID = 1L;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public Long getFkIntermediaryId() {
        return fkIntermediaryId;
    }

    public void setFkIntermediaryId(Long fkIntermediaryId) {
        this.fkIntermediaryId = fkIntermediaryId;
    }

    public Long getFkOperatorId() {
        return fkOperatorId;
    }

    public void setFkOperatorId(Long fkOperatorId) {
        this.fkOperatorId = fkOperatorId;
    }

    public String getFkOperatorName() {
        return fkOperatorName;
    }

    public void setFkOperatorName(String fkOperatorName) {
        this.fkOperatorName = fkOperatorName;
    }

    public Date getfOperateTime() {
        return fOperateTime;
    }

    public void setfOperateTime(Date fOperateTime) {
        this.fOperateTime = fOperateTime;
    }

    public Integer getfOperate() {
        return fOperate;
    }

    public void setfOperate(Integer fOperate) {
        this.fOperate = fOperate;
    }

}