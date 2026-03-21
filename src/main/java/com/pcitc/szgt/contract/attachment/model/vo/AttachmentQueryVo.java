package com.pcitc.szgt.contract.attachment.model.vo;

public class AttachmentQueryVo {

    /**
     * 业务数据主键
     */
    private String PropertyID;

    /**
     * 合同环节 准备、订立、打印等
     */
    private String PropertyModel;

    /**
     * 合同阶段 文本审查审批、文本打印等
     */
    private String Section;

    private String AttachmentType;

    private String TypeCode;

    public String getAttachmentType() {
        return AttachmentType;
    }

    public void setAttachmentType(String attachmentType) {
        AttachmentType = attachmentType;
    }

    public String getTypeCode() {
        return TypeCode;
    }

    public void setTypeCode(String typeCode) {
        TypeCode = typeCode;
    }

    public String getPropertyID() {
        return PropertyID;
    }

    public void setPropertyID(String propertyID) {
        PropertyID = propertyID;
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
}
