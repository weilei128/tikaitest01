package com.pcitc.szgt.contract.finality.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

public class CrContractfromlegal implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("disputeNo")
    private String disputeNo ;//纠纷编号

    @TableField("handlingResults")
    private String handlingResults ;//办理结果

    @TableField("closingTime")
    private LocalDateTime closingTime ;//结案时间

    @TableField("contractNum")
    private String contractNum ;//对应合同编号

    @TableField("CreatedBy")
    private String CreatedBy;

    @TableField("CreatedDate")
    private LocalDateTime CreatedDate;

    @TableField("ModifiedBy")
    private String ModifiedBy;

    @TableField("ModifiedDate")
    private LocalDateTime ModifiedDate;


    public String getDisputeNo() {
        return disputeNo;
    }

    public void setDisputeNo(String disputeNo) {
        this.disputeNo = disputeNo;
    }

    public String getHandlingResults() {
        return handlingResults;
    }

    public void setHandlingResults(String handlingResults) {
        this.handlingResults = handlingResults;
    }

    public LocalDateTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalDateTime closingTime) {
        this.closingTime = closingTime;
    }

    public String getContractNum() {
        return contractNum;
    }

    public void setContractNum(String contractNum) {
        this.contractNum = contractNum;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public LocalDateTime getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        CreatedDate = createdDate;
    }

    public String getModifiedBy() {
        return ModifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        ModifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        ModifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "CrContractfromlegal{" +
                "disputeNo=" + disputeNo +
                ", handlingResults=" + handlingResults +
                ", closingTime=" + closingTime +
                ", contractNum=" + contractNum +
                ", CreatedBy=" + CreatedBy +
                ", CreatedDate=" + CreatedDate +
                ", ModifiedBy=" + ModifiedBy +
                ", ModifiedDate=" + ModifiedDate +
                "}";
    }

}
