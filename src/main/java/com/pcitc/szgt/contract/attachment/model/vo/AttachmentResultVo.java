package com.pcitc.szgt.contract.attachment.model.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AttachmentResultVo {

    /**
     * 主键
     */
    private String AttachmentID;

    /**
     * 业务数据主键
     */
    private String PropertyID;

    /**
     * 附件名称，原始名称
     */
    private String AttachmentName;

    /**
     * 附件标题名称
     */
    private String AttachmentTypeName;

    /**
     * 附件类型名称，主要为了区分合同哪个环节上传的附件
     */
    private Integer AttachmentType;

    /**
     * 附件类型编码，主要为了区分合同哪个环节上传的附件
     */
    private Integer TypeCode;

    /**
     * 附件绝对路径
     */
    private String AttachmentPath;

    /**
     * 合同环节 准备、订立、打印等
     */
    private String PropertyModel;

    /**
     * 合同阶段 文本审查审批、文本打印等
     */
    private String Section;
    /**
     * 	合同附件位置
     */
    private String Location ;
    /**
     * 附件相对路径 如http://10.238.120.135
     */
    private String DocUrl;

    /**
     * 附件大小
     */
    private BigDecimal FileSize;

    /**
     * 附件扩展名　如 .doc,.xls
     */
    private String Extension;

    /**
     * 备注
     */
    private String Remark;

    /**
     * 排序
     */
    private Integer OrderNumber;

    /**
     * 创建时间
     */
    private LocalDateTime CreatedDate;

    /**
     * 企业标识 当前用户所在企业
     */
    private Integer Oulabel;

    public String getAttachmentID() {
        return AttachmentID;
    }

    public void setAttachmentID(String attachmentID) {
        AttachmentID = attachmentID;
    }

    public String getPropertyID() {
        return PropertyID;
    }

    public void setPropertyID(String propertyID) {
        PropertyID = propertyID;
    }

    public String getAttachmentName() {
        return AttachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        AttachmentName = attachmentName;
    }

    public String getAttachmentTypeName() {
        return AttachmentTypeName;
    }

    public void setAttachmentTypeName(String attachmentTypeName) {
        AttachmentTypeName = attachmentTypeName;
    }

    public Integer getAttachmentType() {
        return AttachmentType;
    }

    public void setAttachmentType(Integer attachmentType) {
        AttachmentType = attachmentType;
    }

    public Integer getTypeCode() {
        return TypeCode;
    }

    public void setTypeCode(Integer typeCode) {
        TypeCode = typeCode;
    }

    public String getAttachmentPath() {
        return AttachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        AttachmentPath = attachmentPath;
    }

    public String getPropertyModel() {
        return PropertyModel;
    }

    public void setPropertyModel(String propertyModel) {
        PropertyModel = propertyModel;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String section) {
        Section = section;
    }

    public String getDocUrl() {
        return DocUrl;
    }

    public void setDocUrl(String docUrl) {
        DocUrl = docUrl;
    }

    public BigDecimal getFileSize() {
        return FileSize;
    }

    public void setFileSize(BigDecimal fileSize) {
        FileSize = fileSize;
    }

    public String getExtension() {
        return Extension;
    }

    public void setExtension(String extension) {
        Extension = extension;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public LocalDateTime getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        CreatedDate = createdDate;
    }

    public Integer getOulabel() {
        return Oulabel;
    }

    public void setOulabel(Integer oulabel) {
        Oulabel = oulabel;
    }

    public Integer getOrderNumber() {
        return OrderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        OrderNumber = orderNumber;
    }

	public String getLocation() {
		return Location;
	}

	public void setLocation(String location) {
		Location = location;
	}
    
}
