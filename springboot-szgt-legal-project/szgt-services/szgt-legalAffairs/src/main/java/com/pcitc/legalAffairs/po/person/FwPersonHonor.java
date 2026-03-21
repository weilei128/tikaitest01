package com.pcitc.legalAffairs.po.person;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 
 * 法律人员工作获奖信息表
 */
@TableName(value = "fw_person_honor")
public class FwPersonHonor implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "f_ID", type = IdType.AUTO)
    private Long fId;

    /**
     * 关联person_info.f_ID
     */
    private Long fkPersonId;

    /**
     * person_info.f_Name
     */
    private String fkPersonName;

    /**
     * 奖项名称
     */
    private String fName;

    /**
     * 奖项级别
     */
    private Long fHonorRank;

    /**
     * 获奖日期
     */
    private Date fAwardDate;

    /**
     * 颁证机构
     */
    private String fAwardAgency;

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

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public Long getfHonorRank() {
        return fHonorRank;
    }

    public void setfHonorRank(Long fHonorRank) {
        this.fHonorRank = fHonorRank;
    }

    public Date getfAwardDate() {
        return fAwardDate;
    }

    public void setfAwardDate(Date fAwardDate) {
        this.fAwardDate = fAwardDate;
    }

    public String getfAwardAgency() {
        return fAwardAgency;
    }

    public void setfAwardAgency(String fAwardAgency) {
        this.fAwardAgency = fAwardAgency;
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