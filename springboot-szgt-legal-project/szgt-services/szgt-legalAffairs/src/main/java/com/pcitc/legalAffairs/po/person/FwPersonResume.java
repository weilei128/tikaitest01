package com.pcitc.legalAffairs.po.person;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 
 * 法律人员简历表
 */
@TableName(value="fw_person_resume")
public class FwPersonResume implements Serializable {

    @TableId(value = "f_ID", type = IdType.AUTO)
    private Long fId;

    private Long fkPersonId;

    private String fkPersonName;

    /**
     * 公司名称
     */
    private String fCompanyName;

    /**
     * 在职时间 - 开始
     */
    private Date fBegindate;

    /**
     * 在职时间 - 结束
     */
    private Date fEnddate;

    /**
     * 职位
     */
    private String fPosition;

    /**
     * 职责
     */
    private String fDuty;

    /**
     * 业绩描述
     */
    private String fPerformance;

    /**
     * 排序
     */
    private Integer fSort;

    /**
     * 是否删除 1：删除，0：未删除
     */
    @TableLogic
    private Integer fIsdel;

    private Long fCreateId;

    /**
     * 创建人账号
     */
    private String fCreateUser;

    /**
     * 创建人姓名
     */
    private String fCreateName;

    /**
     * 创建时间
     */
    private Date fCreateTime;

    private Long fUpdateId;

    /**
     * 修改人账号
     */
    private String fUpdateUser;

    /**
     * 修改人姓名
     */
    private String fUpdateName;

    /**
     * 修改时间
     */
    private Date fUpdateTime;

    private static final long serialVersionUID = 1L;

    public Long getfId() {
        return fId;
    }

    public void setfId(Long fId) {
        this.fId = fId;
    }

    public Long getFkPersonId() {
        return fkPersonId;
    }

    public void setFkPersonId(Long fkPersonId) {
        this.fkPersonId = fkPersonId;
    }

    public String getFkPersonName() {
        return fkPersonName;
    }

    public void setFkPersonName(String fkPersonName) {
        this.fkPersonName = fkPersonName;
    }

    public String getfCompanyName() {
        return fCompanyName;
    }

    public void setfCompanyName(String fCompanyName) {
        this.fCompanyName = fCompanyName;
    }

    public Date getfBegindate() {
        return fBegindate;
    }

    public void setfBegindate(Date fBegindate) {
        this.fBegindate = fBegindate;
    }

    public Date getfEnddate() {
        return fEnddate;
    }

    public void setfEnddate(Date fEnddate) {
        this.fEnddate = fEnddate;
    }

    public String getfPosition() {
        return fPosition;
    }

    public void setfPosition(String fPosition) {
        this.fPosition = fPosition;
    }

    public String getfDuty() {
        return fDuty;
    }

    public void setfDuty(String fDuty) {
        this.fDuty = fDuty;
    }

    public String getfPerformance() {
        return fPerformance;
    }

    public void setfPerformance(String fPerformance) {
        this.fPerformance = fPerformance;
    }

    public Integer getfSort() {
        return fSort;
    }

    public void setfSort(Integer fSort) {
        this.fSort = fSort;
    }

    public Integer getfIsdel() {
        return fIsdel;
    }

    public void setfIsdel(Integer fIsdel) {
        this.fIsdel = fIsdel;
    }

    public Long getfCreateId() {
        return fCreateId;
    }

    public void setfCreateId(Long fCreateId) {
        this.fCreateId = fCreateId;
    }

    public String getfCreateUser() {
        return fCreateUser;
    }

    public void setfCreateUser(String fCreateUser) {
        this.fCreateUser = fCreateUser;
    }

    public String getfCreateName() {
        return fCreateName;
    }

    public void setfCreateName(String fCreateName) {
        this.fCreateName = fCreateName;
    }

    public Date getfCreateTime() {
        return fCreateTime;
    }

    public void setfCreateTime(Date fCreateTime) {
        this.fCreateTime = fCreateTime;
    }

    public Long getfUpdateId() {
        return fUpdateId;
    }

    public void setfUpdateId(Long fUpdateId) {
        this.fUpdateId = fUpdateId;
    }

    public String getfUpdateUser() {
        return fUpdateUser;
    }

    public void setfUpdateUser(String fUpdateUser) {
        this.fUpdateUser = fUpdateUser;
    }

    public String getfUpdateName() {
        return fUpdateName;
    }

    public void setfUpdateName(String fUpdateName) {
        this.fUpdateName = fUpdateName;
    }

    public Date getfUpdateTime() {
        return fUpdateTime;
    }

    public void setfUpdateTime(Date fUpdateTime) {
        this.fUpdateTime = fUpdateTime;
    }
}