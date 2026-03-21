package com.pcitc.szgt.contract.documentinformation.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.xml.soap.Text;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;


public class Officialdocument implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId("subject")
    private String subject;
    /**
     * 文档创建人（用户编码）
     */
    @TableField("createCode")
    private String createCode;

    /**
     * 文档创建人部门（部门编码）
     */
    @TableField("createDept")
    private String createDept;

    /**
     * 文档创建人单位 (单位编码)
     */
    @TableField("createCompany")
    private String createCompany;

    /**
     * 文档创建时间（yyyy-MM-dd HH:mm:ss）
     */
    @TableField("createTime")
    private LocalDateTime createTime;

    /**
     * 文档推送时间（yyyy-MM-dd HH:mm:ss）
     */
    @TableField("pushTime")
    private LocalDateTime pushTime;

    /**
     * 流程分类名称
     */
    @TableField("tempateName")
    private String tempateName;

    /**
     * 处理笺（PDF形式）下载地址
     */
    @TableField("baseinfoUrl")
    private String baseinfoUrl;

    /**
     * 正文下载地址
     */
    @TableField("contentUrl")
    private String contentUrl;

    /**
     * 预留字段1
     */
    @TableField("param1")
    private String param1;

    /**
     * 预留字段2
     */
    @TableField("param2")
    private String param2;

    /**
     * 预留字段3
     */
    @TableField("param3")
    private String param3;
    /**
     * 文档推送过来后创建时间
     */
    @TableField("CreatedDate")
    private LocalDateTime CreatedDate;
    /**
     * 文档推送过来后更新时间
     */
    @TableField("ModifiedDate")
    private LocalDateTime ModifiedDate;



    public String getCreateCode() {
        return createCode;
    }

    public void setCreateCode(String createCode) {
        this.createCode = createCode;
    }

    public String getCreateDept() {
        return createDept;
    }

    public void setCreateDept(String createDept) {
        this.createDept = createDept;
    }

    public String getCreateCompany() {
        return createCompany;
    }

    public void setCreateCompany(String createCompany) {
        this.createCompany = createCompany;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getPushTime() {
        return pushTime;
    }

    public void setPushTime(LocalDateTime pushTime) {
        this.pushTime = pushTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTempateName() {
        return tempateName;
    }

    public void setTempateName(String tempateName) {
        this.tempateName = tempateName;
    }

    public String getBaseinfoUrl() {
        return baseinfoUrl;
    }

    public void setBaseinfoUrl(String baseinfoUrl) {
        this.baseinfoUrl = baseinfoUrl;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    public String getParam3() {
        return param3;
    }

    public void setParam3(String param3) {
        this.param3 = param3;
    }

    public LocalDateTime getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        CreatedDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return ModifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        ModifiedDate = modifiedDate;
    }

    @Override
    public String toString() {
        return "Officialdocument{" +
                ", createCode=" + createCode +
                ", createDept=" + createDept +
                ", createCompany=" + createCompany +
                ", createTime=" + createTime +
                ", pushTime=" + pushTime +
                ", subject=" + subject +
                ", tempateName=" + tempateName +
                ", param1=" + param1 +
                ", param2=" + param2 +
                ", param3=" + param3 +
                ", CreatedDate=" + CreatedDate +
                ", ModifiedDate=" + ModifiedDate +
                "}";
    }
}
