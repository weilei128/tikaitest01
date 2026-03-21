package com.pcitc.legalAffairs.po.person;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author 
 * 法律人员资质信息表
 */
@TableName(value="fw_person_qualification")
public class FwPersonQualification implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "f_ID", type = IdType.AUTO)
    private Long fId;

    /**
     * 关联person_info表f_IDID
     */
    private Long fkPersonId;

    /**
     * person_info表f_name
     */
    private String fkPersonName;

    /**
     * 资质/证书名称
     */
    private String fName;

    /**
     * 证书编号
     */
    private String fCode;

    /**
     * 颁证日期
     */
    private Date fAwardedDate;

    /**
     * 颁证机构
     */
    private String fAwardAgency;

    /**
     * 最近年检日期
     */
    private Date fYearlyCheckDate;

    /**
     * 所属国家/地区
     */
    private String fNationality;

    /**
     * 备案/年检周期
     */
    private String fCheckRound;

    /**
     * 资质文件主键ID
     */
    private Long fkFileId;

    /**
     * 资质文件名
     */
    private String fkFileName;

    /**
     * 资质文件服务器路径
     */
    private String fkFileServerPath;

    /**
     * 资质文件扩展名
     */
    private String fkFileExt;

    /**
     * 排序
     */
    private Integer fSort;

    /**
     * 是否删除 1：删除，0：未删除
     */
    @TableLogic
    private Integer fIsdel;

    private Integer fCreateId;

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

    private Integer fUpdateId;

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

    public String getfCode() {
        return fCode;
    }

    public void setfCode(String fCode) {
        this.fCode = fCode;
    }

    public Date getfAwardedDate() {
        return fAwardedDate;
    }

    public void setfAwardedDate(Date fAwardedDate) {
        this.fAwardedDate = fAwardedDate;
    }

    public String getfAwardAgency() {
        return fAwardAgency;
    }

    public void setfAwardAgency(String fAwardAgency) {
        this.fAwardAgency = fAwardAgency;
    }

    public Date getfYearlyCheckDate() {
        return fYearlyCheckDate;
    }

    public void setfYearlyCheckDate(Date fYearlyCheckDate) {
        this.fYearlyCheckDate = fYearlyCheckDate;
    }

    public String getfNationality() {
        return fNationality;
    }

    public void setfNationality(String fNationality) {
        this.fNationality = fNationality;
    }

    public String getfCheckRound() {
        return fCheckRound;
    }

    public void setfCheckRound(String fCheckRound) {
        this.fCheckRound = fCheckRound;
    }

    public Long getFkFileId() {
        return fkFileId;
    }

    public void setFkFileId(Long fkFileId) {
        this.fkFileId = fkFileId;
    }

    public String getFkFileName() {
        return fkFileName;
    }

    public void setFkFileName(String fkFileName) {
        this.fkFileName = fkFileName;
    }

    public String getFkFileServerPath() {
        return fkFileServerPath;
    }

    public void setFkFileServerPath(String fkFileServerPath) {
        this.fkFileServerPath = fkFileServerPath;
    }

    public String getFkFileExt() {
        return fkFileExt;
    }

    public void setFkFileExt(String fkFileExt) {
        this.fkFileExt = fkFileExt;
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

    public Integer getfCreateId() {
        return fCreateId;
    }

    public void setfCreateId(Integer fCreateId) {
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

    public Integer getfUpdateId() {
        return fUpdateId;
    }

    public void setfUpdateId(Integer fUpdateId) {
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