package com.pcitc.legalAffairs.po.Intermediary;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.pcitc.szgt.legalAffairs.base.BasePojo;

/**
 * 中介机构对应的市场评价
 */
@TableName("fw_intermediary_market_eval")
public class FwIntermediaryMarketEval extends BasePojo {
    /**
     * 主键
     */
    @TableId(value = "f_ID",type = IdType.AUTO)
    private Long fId;
    /**
     * 关联中介机构id
     */
    private Long fkIntermediaryId;
    /**
     * 不良记录
     */
    private String fBadRecord;
    /**
     * 单位名称
     */
    private String fCompanyName;

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

    public String getfBadRecord() {
        return fBadRecord;
    }

    public void setfBadRecord(String fBadRecord) {
        this.fBadRecord = fBadRecord;
    }

    public String getfCompanyName() {
        return fCompanyName;
    }

    public void setfCompanyName(String fCompanyName) {
        this.fCompanyName = fCompanyName;
    }
}
