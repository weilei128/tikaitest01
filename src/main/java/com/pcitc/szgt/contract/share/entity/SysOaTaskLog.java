package com.pcitc.szgt.contract.share.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

public class SysOaTaskLog {
    @TableId
    private Integer fId;

    private String fTaskId;

    private String fBusinessId;

    private String fBusinessName;

    private String fCategoryCode;

    private String fExecutorId;

    private String fExecutorCode;

    private String fExecutorName;

    /**
     * '0 待办  1 发待办异常 2 已办  3 待办变已办异常   4 删除  5  删除异常
     */
    private Integer fStatus;

    private Date fCreateTime;

    private Date fModifyTime;

    private String fTodoException;

    private String fDoneException;

    private String fDelException;

    private String fTodoResult;

    private String fDoneResult;

    private String fDelResult;

    private Integer fLocalStatus;

    private Integer fIsMsg;

    public Integer getfId() {
        return fId;
    }

    public void setfId(Integer fId) {
        this.fId = fId;
    }

    public String getfTaskId() {
        return fTaskId;
    }

    public void setfTaskId(String fTaskId) {
        this.fTaskId = fTaskId;
    }

    public String getfBusinessId() {
        return fBusinessId;
    }

    public void setfBusinessId(String fBusinessId) {
        this.fBusinessId = fBusinessId;
    }

    public String getfBusinessName() {
        return fBusinessName;
    }

    public void setfBusinessName(String fBusinessName) {
        this.fBusinessName = fBusinessName;
    }

    public String getfCategoryCode() {
        return fCategoryCode;
    }

    public void setfCategoryCode(String fCategoryCode) {
        this.fCategoryCode = fCategoryCode;
    }

    public String getfExecutorId() {
        return fExecutorId;
    }

    public void setfExecutorId(String fExecutorId) {
        this.fExecutorId = fExecutorId;
    }

    public String getfExecutorName() {
        return fExecutorName;
    }

    public void setfExecutorName(String fExecutorName) {
        this.fExecutorName = fExecutorName;
    }

    public Integer getfStatus() {
        return fStatus;
    }

    public void setfStatus(Integer fStatus) {
        this.fStatus = fStatus;
    }

    public Date getfCreateTime() {
        return fCreateTime;
    }

    public void setfCreateTime(Date fCreateTime) {
        this.fCreateTime = fCreateTime;
    }

    public Date getfModifyTime() {
        return fModifyTime;
    }

    public void setfModifyTime(Date fModifyTime) {
        this.fModifyTime = fModifyTime;
    }

    public String getfTodoException() {
        return fTodoException;
    }

    public void setfTodoException(String fTodoException) {
        this.fTodoException = fTodoException;
    }

    public String getfDoneException() {
        return fDoneException;
    }

    public void setfDoneException(String fDoneException) {
        this.fDoneException = fDoneException;
    }

    public String getfExecutorCode() {
        return fExecutorCode;
    }

    public void setfExecutorCode(String fExecutorCode) {
        this.fExecutorCode = fExecutorCode;
    }

    public String getfTodoResult() {
        return fTodoResult;
    }

    public void setfTodoResult(String fTodoResult) {
        this.fTodoResult = fTodoResult;
    }

    public String getfDoneResult() {
        return fDoneResult;
    }

    public void setfDoneResult(String fDoneResult) {
        this.fDoneResult = fDoneResult;
    }

    public Integer getfLocalStatus() {
        return fLocalStatus;
    }

    public void setfLocalStatus(Integer fLocalStatus) {
        this.fLocalStatus = fLocalStatus;
    }

    public String getfDelException() {
        return fDelException;
    }

    public void setfDelException(String fDelException) {
        this.fDelException = fDelException;
    }

    public String getfDelResult() {
        return fDelResult;
    }

    public void setfDelResult(String fDelResult) {
        this.fDelResult = fDelResult;
    }

    public Integer getfIsMsg() {
        return fIsMsg;
    }

    public void setfIsMsg(Integer fIsMsg) {
        this.fIsMsg = fIsMsg;
    }
}
