package com.pcitc.szgt.contract.attachment.model.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

public class AttachmentUploadVo {

    /**
     * 业务数据主键
     */
    private String PropertyID;

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
     * 附件备注
     */
    private String Remark;

    /**
     * 附件排序
     */
    private Integer OrderNumber;

    private MultipartFile file;

    public String getPropertyID() {
        return PropertyID;
    }

    public void setPropertyID(String propertyID) {
        PropertyID = propertyID;
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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
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
